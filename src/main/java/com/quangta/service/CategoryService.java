package com.quangta.service;

import com.quangta.entity.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> getCategoryByRestaurantId(Long restaurantId) throws Exception;

    public Category getCategoryById(Long id) throws Exception;


}
