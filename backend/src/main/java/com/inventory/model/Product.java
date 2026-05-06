package com.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;

    @Column
    private String category;

    @Column(name = "sku", unique = true)
    private String sku;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Product() {}

    public Product(Long id, String name, String description, BigDecimal price,
                   Integer quantity, String category, String sku,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.sku = sku;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId()                  { return id; }
    public String getName()              { return name; }
    public String getDescription()       { return description; }
    public BigDecimal getPrice()         { return price; }
    public Integer getQuantity()         { return quantity; }
    public String getCategory()          { return category; }
    public String getSku()               { return sku; }
    public LocalDateTime getCreatedAt()  { return createdAt; }
    public LocalDateTime getUpdatedAt()  { return updatedAt; }

    public void setId(Long id)                         { this.id = id; }
    public void setName(String name)                   { this.name = name; }
    public void setDescription(String description)     { this.description = description; }
    public void setPrice(BigDecimal price)             { this.price = price; }
    public void setQuantity(Integer quantity)          { this.quantity = quantity; }
    public void setCategory(String category)           { this.category = category; }
    public void setSku(String sku)                     { this.sku = sku; }
    public void setCreatedAt(LocalDateTime createdAt)  { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt)  { this.updatedAt = updatedAt; }
}
