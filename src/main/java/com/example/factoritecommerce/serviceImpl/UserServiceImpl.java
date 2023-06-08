package com.example.factoritecommerce.serviceImpl;

import com.example.factoritecommerce.dto.UserDto;
import com.example.factoritecommerce.excepcion.BadRequestException;
import com.example.factoritecommerce.excepcion.NotFoundException;
import com.example.factoritecommerce.model.UserEcommerce;
import com.example.factoritecommerce.repository.UserRepository;
import com.example.factoritecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public UserDto createUser(UserDto userInput) {
        userInput.setId(null);
        //TODO quitar if cuando tome @Valid
        if(userInput.getDni() == null || userInput.getEmail() ==null || userInput.getUserName() == null) throw new BadRequestException("Los campos no pueden estar vacios.");
        mapper.getConfiguration().setSkipNullEnabled(true);
        UserEcommerce user = mapper.map(userInput, UserEcommerce.class);
        userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userInput, String dni) {
        UserEcommerce userFound = getUserEntityById(dni);
        userInput.setId(null);
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(userInput, userFound);
        userRepository.save(userFound);
        return mapper.map(userFound, UserDto.class);
    }

    @Override
    public Map<String, String> deleteUser(String dni) {
        UserEcommerce userFound = getUserEntityById(dni);
        userRepository.delete(userFound);
        return Map.of("Mensaje: ", "El usuario con dni " + dni + ", ha sido eliminado exitosamente" );
    }

    @Override
    public List<UserDto> getAllUser(Integer page) {
        List<UserDto> usersDto = userRepository.findAll(PageRequest.of(page, 20))
                .stream().map(user-> mapper.map(user, UserDto.class)).toList();
        return usersDto;
    }

    @Override
    public UserDto getUserByDni(String dni) {
        return mapper.map(getUserEntityById(dni), UserDto.class);
    }

    @Override
    public UserEcommerce getUserEntityById(String dni){
        UserEcommerce userFound = userRepository.findByDni(dni).orElseThrow(()-> new NotFoundException("Usuario no encontrado"));
        return userFound;
    }
}
