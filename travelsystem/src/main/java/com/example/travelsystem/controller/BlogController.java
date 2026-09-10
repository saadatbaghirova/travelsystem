package com.example.travelsystem.controller;

import com.example.travelsystem.model.Blog;
import com.example.travelsystem.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // ==========================
    // PUBLIC
    // ==========================

    @GetMapping("/blog")
    public String findAllBlogs(Model model) {

        model.addAttribute("blogs", blogService.findAll());

        return "blogList";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable Long id,
                              Model model) {

        Blog blog = blogService.findById(id);

        if (blog == null) {
            return "redirect:/blog";
        }

        model.addAttribute("blog", blog);

        return "blogDetails";
    }

    // ==========================
    // ADMIN
    // ==========================

    @GetMapping("/admin/blog")
    public String adminBlogs(Model model) {

        model.addAttribute("blogs", blogService.findAll());

        return "admin/blogs";
    }

    @GetMapping("/admin/blog/create")
    public String createBlog(Model model) {

        model.addAttribute("blog", new Blog());

        return "blogCreate";
    }

    @GetMapping("/admin/blog/edit/{id}")
    public String editBlog(@PathVariable Long id,
                           Model model) {

        Blog blog = blogService.findById(id);

        if (blog == null) {
            return "redirect:/admin/blog";
        }

        model.addAttribute("blog", blog);

        return "blogEdit";
    }

    @PostMapping("/admin/blog/save")
    public String saveBlog(@ModelAttribute Blog blog) {

        if (blog.getCreatedDate() == null) {
            blog.setCreatedDate(LocalDateTime.now());
        }

        blogService.save(blog);

        return "redirect:/admin/blog";
    }

    @PostMapping("/admin/blog/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {

        blogService.deleteById(id);

        return "redirect:/admin/blog";
    }
}