package com.quangta.payload.request;

import com.quangta.entity.Address;
import com.quangta.entity.ContactInformation;
import com.quangta.entity.User;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private User owner;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
