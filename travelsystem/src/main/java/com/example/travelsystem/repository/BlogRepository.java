package com.example.travelsystem.repository;

import com.example.travelsystem.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}