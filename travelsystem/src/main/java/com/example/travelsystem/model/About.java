package com.example.travelsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "abouts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String heroTitle;
    private String heroSubtitle;
    @Column(columnDefinition = "TEXT")
    private String aboutText;
    @Column(columnDefinition = "TEXT")
    private String mission;
    @Column(columnDefinition = "TEXT")
    private String vision;
    private String imageUrl;
    private String phone;
    private String email;
    private String instagram;
    private String facebook;
    private String X;
    private String linkedin;
    private String youtube;
    private String tiktok;

}
