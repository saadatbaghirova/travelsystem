package com.example.travelsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString; // Bu import artıq burdadır

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hotel name cannot be empty")
    private String name;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @Min(value = 1, message = "Minimum star is 1")
    @Max(value = 5, message = "Maximum star is 5")
    private Integer star;

    @NotBlank(message = "Description cannot be empty")
    @Column(length = 1000)
    private String description;

    // HOTEL IMAGE
    private String imageUrl;

    // 1. BURANI DƏYİŞDİK: City-ni toString-dən çıxarırıq ki, sonsuz dövr yaratmasın
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @ToString.Exclude
    private City city;

    // 2. BURANI DƏYİŞDİK: Gələcəkdə Room ilə də eyni xətanı almayasan deyə bunu da çıxarırıq
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Room> rooms;

    @ManyToMany
    @JoinTable(
            name = "hotel_amenity",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;
}