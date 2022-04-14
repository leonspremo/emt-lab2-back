package com.example.Lab1.service.impl;

import com.example.Lab1.model.enumerations.Category;
import com.example.Lab1.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAll() {
        return List.of(Category.values());
    }
}
