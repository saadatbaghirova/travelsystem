package com.example.travelsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contact_info")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private String email;

    private String address;

    private String workingHours;

    private String facebook;

    private String instagram;

    private String linkedin;

    private String twitter;

    private String youtube;

    private String mapUrl;
}