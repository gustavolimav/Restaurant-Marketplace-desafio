package com.gustavolima.marketplace.controllers;

import com.gustavolima.marketplace.controllers.DTOs.CategoryDTO;
import com.gustavolima.marketplace.controllers.DTOs.ProductDTO;
import com.gustavolima.marketplace.domain.category.Category;
import com.gustavolima.marketplace.domain.product.Product;
import com.gustavolima.marketplace.services.CategoryService;
import com.gustavolima.marketplace.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService _productService;

    public ProductController(ProductService productService) {
        _productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> postProduct(@RequestBody ProductDTO productDTO) {
        Product product = _productService.postProduct(productDTO);

        return ResponseEntity.ok().body(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = _productService.getAllProducts();

        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String productId) {
        Product product = _productService.getProductById(productId);

        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable("id") String productId, @RequestBody ProductDTO productDTO) {
        Product product = _productService.updateProduct(productId, productDTO);

        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteProduct(@PathVariable("id") String productId) {
        _productService.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }
}
