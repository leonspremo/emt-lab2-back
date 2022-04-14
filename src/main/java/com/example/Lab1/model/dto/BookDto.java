package com.example.Lab1.model.dto;

import com.example.Lab1.model.enumerations.Category;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long authorId;

    private Integer availableCopies;
}
