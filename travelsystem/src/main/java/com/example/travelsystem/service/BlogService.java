package com.example.travelsystem.service;

import com.example.travelsystem.model.Blog;

import java.util.List;

public interface BlogService {

    Blog findById(Long id);

    List<Blog> findAll();

    Blog save(Blog blog);

    void deleteById(Long id);

}