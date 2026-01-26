package com.blog.category.service;

import java.util.List;

import com.blog.category.dao.CategoryDAO;
import com.blog.category.vo.Category;

public class CategoryService {

    private CategoryDAO dao = new CategoryDAO();

    public List<Category> getCategoryList() {
        return dao.selectCategoryList();
    }
}
