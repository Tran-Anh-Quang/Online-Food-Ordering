package com.quangta.payload.request;

import com.quangta.entity.Category;
import com.quangta.entity.IngredientsItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;

    private String description;

    private Long price;

    private int quantity;

    private Category category;

    private List<String> images;

    private boolean available;

    private Long restaurantId;

    private boolean vegetarian;

    private boolean nonVegetarian;

    private boolean seasonal;

    private List<IngredientsItem> ingredients;

    private LocalDateTime createdDate;
}
