package com.technologies.productservice.service;

import com.technologies.productservice.model.dto.ProductDto;
import com.technologies.productservice.model.entity.Product;
import com.technologies.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public Product createProduct(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        product.setTotal(productDto.getPrice() * productDto.getQuantity());
        return productRepository.save(product);
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElseThrow();
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product updateProduct(Long productId, ProductDto productDto){
        Product existingProduct = getProductById(productId);
        existingProduct = Product.builder()
                .id(existingProduct.getId())
                .name(productDto.getName())
                .type(productDto.getType())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .total(productDto.getPrice() * productDto.getQuantity())
                .build();
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long productId) {
        Product existingProduct = getProductById(productId);
        productRepository.delete(existingProduct);
    }
}
