package com.example.travelsystem.repository;
import com.example.travelsystem.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AmenityRepository extends JpaRepository<Amenity,Long> {

}