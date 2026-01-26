package com.blog.category.model;

public class Category {

    private int cateNo;
    private String cateName;

    public Category() {}

    public Category(int cateNo, String cateName) {
        this.cateNo = cateNo;
        this.cateName = cateName;
    }

    public int getCateNo() {
        return cateNo;
    }

    public void setCateNo(int cateNo) {
        this.cateNo = cateNo;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
