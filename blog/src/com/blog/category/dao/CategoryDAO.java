package com.blog.category.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.blog.category.vo.Category;
import com.blog.util.db.DB;

public class CategoryDAO {

    public List<Category> selectCategoryList() {

        List<Category> list = new ArrayList<>();
        String sql = "SELECT cate_no, cate_name FROM category ORDER BY cate_no";

        try (
            Connection conn = DB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
                Category c = new Category(
                    rs.getInt("cate_no"),
                    rs.getString("cate_name")
                );
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
