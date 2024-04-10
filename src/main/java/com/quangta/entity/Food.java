package com.quangta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    private int quantity;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 1000)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> images;

    private boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVegetarian;

    private boolean isNonVegetarian;

    private boolean isSeasonal;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<IngredientsItem> ingredients = new ArrayList<>();

    private LocalDateTime createdDate;
}
