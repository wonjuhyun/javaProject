package com.blog.category.view;

import java.util.List;
import java.util.Scanner;

import com.blog.category.model.Category;
import com.blog.category.service.CategoryService;

public class CategoryView {

    private CategoryService service = new CategoryService();
    private Scanner sc = new Scanner(System.in);

    public void categoryMenu() {

        while (true) {
            System.out.println("========================================");
            System.out.println("카테고리 메뉴");
            System.out.println("========================================");
            System.out.println("1. 카테고리 목록 보기");
            System.out.println("2. 카테고리별 게시글 보기");
            System.out.println("3. 이전 메뉴");
            System.out.println("========================================");
            System.out.print("메뉴 선택 >> ");

            int menu = sc.nextInt();

            if (menu == 1) {
                showCategoryList();
            } else if (menu == 2) {
                selectCategoryForPosts();
            } else if (menu == 3) {
                return;
            }
        }
    }

    private void showCategoryList() {
        List<Category> list = service.getCategoryList();

        System.out.println("\n----------------------------------------------------");
        System.out.println("번호 | 카테고리명");
        System.out.println("----------------------------------------------------");

        for (Category c : list) {
            System.out.printf("%-4d | %s\n",
                c.getCateNo(), c.getCateName());
        }

        System.out.println("----------------------------------------------------\n");
    }

    private void selectCategoryForPosts() {
        showCategoryList();
        System.out.print("카테고리 번호 입력 >> ");
        int cateNo = sc.nextInt();

        // 🔗 여기서 게시글 담당자 코드 호출 예정
        System.out.println("선택한 카테고리 번호: " + cateNo);
        System.out.println("(게시글 목록 연동 예정)\n");
    }
}
