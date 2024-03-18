package com.quangta.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Embeddable
public class RestaurantDto {

    private Long id;

    private String title;

    @Column(length = 1000)
    private List<String > images;

    private String description;
}
