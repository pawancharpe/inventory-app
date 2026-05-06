package com.inventory.repository;

import com.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Search by name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Filter by category
    List<Product> findByCategory(String category);

    // Find by SKU
    Optional<Product> findBySku(String sku);

    // Low stock alert (quantity below threshold)
    List<Product> findByQuantityLessThanEqual(int threshold);

    // Get all distinct categories
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.category IS NOT NULL")
    List<String> findAllCategories();
}
