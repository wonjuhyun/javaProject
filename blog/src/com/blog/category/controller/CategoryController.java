package com.blog.category.controller;

import com.blog.util.io.CategoryPrint;

public class CategoryController {

    private CategoryPrint categoryPrint = new CategoryPrint();

    public void execute() {
        categoryPrint.categoryMenu();
    }
}
