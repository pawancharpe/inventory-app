package com.inventory.controller;

import com.inventory.model.Product;
import com.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /api/products  — list all
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /api/products/{id}  — get one
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/products  — create
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product saved = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/products/{id}  — update
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                  @Valid @RequestBody Product product) {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/products/{id}  — delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/products/search?name=xyz  — search
    @GetMapping("/search")
    public List<Product> search(@RequestParam String name) {
        return productService.searchByName(name);
    }

    // GET /api/products/category/{cat}  — filter by category
    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productService.getByCategory(category);
    }

    // GET /api/products/low-stock?threshold=5  — low stock alert
    @GetMapping("/low-stock")
    public List<Product> getLowStock(@RequestParam(defaultValue = "5") int threshold) {
        return productService.getLowStock(threshold);
    }

    // GET /api/products/categories  — all category names
    @GetMapping("/categories")
    public List<String> getCategories() {
        return productService.getAllCategories();
    }
}
