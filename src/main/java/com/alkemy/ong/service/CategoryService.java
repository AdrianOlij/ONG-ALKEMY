package com.alkemy.ong.service;

import com.alkemy.ong.models.request.CategoryRequest;
import com.alkemy.ong.models.response.CategoryNameResponse;
import com.alkemy.ong.models.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryNameResponse> getCategories();
    CategoryResponse getCategoryDetail(Long id);

    CategoryResponse createCategory(CategoryRequest category);
}
