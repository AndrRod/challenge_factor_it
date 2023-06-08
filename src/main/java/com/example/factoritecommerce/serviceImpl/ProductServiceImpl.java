package com.example.factoritecommerce.serviceImpl;

import com.example.factoritecommerce.dto.ProductDto;
import com.example.factoritecommerce.excepcion.NotFoundException;
import com.example.factoritecommerce.model.Product;
import com.example.factoritecommerce.repository.ProductRepository;
import com.example.factoritecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public ProductDto createProduct(ProductDto productInput) {
        //TODO quitar if cuando tome @Valid
        Product product = mapper.map(productInput, Product.class);
        productRepository.save(product);
        product.setId(null);
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProductById(Long id, ProductDto productInput) {
        Product productFound = this.getProductEntityById(id);
        productInput.setId(null);
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.map(productInput, productFound);
        productRepository.save(productFound);
        return mapper.map(productFound, ProductDto.class);
    }

    @Override
    public Map<String, String> deleteProductById(Long id) {
        Product productFound = this.getProductEntityById(id);
        productRepository.delete(productFound);
        return Map.of("Mensaje: ", "El producto con id " + id + ", ha sido eliminado exitosamente" );
    }

    @Override
    public ProductDto getProductById(Long id) {
        return mapper.map(this.getProductEntityById(id), ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts(Integer page) {
        List<ProductDto> productsDto = productRepository.findAll(PageRequest.of(page, 20))
                .stream().map(product -> mapper.map(product, ProductDto.class)).toList();
        return productsDto;
    }
    public Product getProductEntityById(Long id){
        Product productFound = productRepository.findById(id).orElseThrow(()-> new NotFoundException("Producto no encontrado"));
        return productFound;
    }
}
