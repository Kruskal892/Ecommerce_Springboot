package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    @Column(name = "is_activated", columnDefinition = "boolean default true") // Specify default value here
    private boolean activated;

    @Column(name = "is_deleted",  columnDefinition = "boolean default false")
    private boolean deleted;

    public Category(String name) {
        this.name = name;
        this.activated = true;
    }
}
