package com.quangta.controller;

import com.quangta.entity.Category;
import com.quangta.entity.User;
import com.quangta.service.CategoryService;
import com.quangta.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    private final UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwtToken
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);

        List<Category> categories = categoryService.getCategoryByRestaurantId(id);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
