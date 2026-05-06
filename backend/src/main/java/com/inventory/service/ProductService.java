package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get one product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Create a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Update an existing product
    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(existing -> {
            existing.setName(updatedProduct.getName());
            existing.setDescription(updatedProduct.getDescription());
            existing.setPrice(updatedProduct.getPrice());
            existing.setQuantity(updatedProduct.getQuantity());
            existing.setCategory(updatedProduct.getCategory());
            existing.setSku(updatedProduct.getSku());
            return productRepository.save(existing);
        });
    }

    // Delete a product
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Search products by name
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Filter by category
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Low stock alert (default threshold = 5)
    public List<Product> getLowStock(int threshold) {
        return productRepository.findByQuantityLessThanEqual(threshold);
    }

    // Get all categories
    public List<String> getAllCategories() {
        return productRepository.findAllCategories();
    }
}
