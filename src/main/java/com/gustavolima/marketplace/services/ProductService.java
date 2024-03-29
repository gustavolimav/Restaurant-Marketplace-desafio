package com.gustavolima.marketplace.services;

import com.gustavolima.marketplace.controllers.DTOs.ProductDTO;
import com.gustavolima.marketplace.domain.category.Category;
import com.gustavolima.marketplace.domain.product.Product;
import com.gustavolima.marketplace.domain.category.exceptions.CategoryNotFoundException;
import com.gustavolima.marketplace.domain.product.exceptions.ProductNotFoundException;
import com.gustavolima.marketplace.repositories.ProductRepository;
import com.gustavolima.marketplace.services.aws.AWSSNSService;
import com.gustavolima.marketplace.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository _productRepository;
    private final CategoryService _categoryService;

    private final AWSSNSService _awsSNSService;

    public ProductService(CategoryService categoryService, ProductRepository productRepository, AWSSNSService awssnsService) {
        _categoryService = categoryService;
        _productRepository = productRepository;
        _awsSNSService = awssnsService;
    }

    public Product postProduct(ProductDTO productDTO) {
        Category category = _categoryService.getCategoryById(productDTO.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product product = new Product(productDTO);

        _productRepository.save(product);

        _awsSNSService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public List<Product> getAllProducts() {
        return _productRepository.findAll();
    }

    public Product updateProduct(String productId, ProductDTO productDTO) {
        Product product = _productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        if (productDTO.categoryId() != null) {
            _categoryService
                    .getCategoryById(productDTO.categoryId())
                    .orElseThrow(CategoryNotFoundException::new);

            product.setCategoryId(productDTO.categoryId());
        }

        if (productDTO.title() != null) {
            product.setTitle(productDTO.title());
        }

        if (productDTO.description() != null) {
            product.setDescription(productDTO.description());
        }

        if (productDTO.price() != null) {
            product.setPrice(productDTO.price());
        }

        if (productDTO.ownerId() != null) {
            product.setOwnerId(productDTO.ownerId());
        }

        _productRepository.save(product);

        _awsSNSService.publish(new MessageDTO(product.toString()));

        return product;
    }

    public void deleteProduct(String productId) {
        Product product = _productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        _productRepository.delete(product);
    }

    public Product getProductById(String productId) {
        return _productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

}
