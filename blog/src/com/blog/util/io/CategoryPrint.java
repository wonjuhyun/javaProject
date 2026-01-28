package com.blog.util.io;

import java.util.List;
import java.util.Scanner;

import com.blog.category.service.CategoryService;
import com.blog.category.service.PostService;
import com.blog.category.vo.Category;
import com.blog.board.vo.BoardVO;

public class CategoryPrint {

    private CategoryService categoryService = new CategoryService();
    private PostService postService = new PostService();
    private Scanner sc = new Scanner(System.in);

    // 카테고리 메인 메뉴
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
                showCategoryList();          // 전체 카테고리 목록
            } else if (menu == 2) {
                categoryPostMenu();          // ★ 카테고리별 게시글 메뉴(내부 루프)
            } else if (menu == 3) {
                return;                      // 호출한 쪽(메인)으로 돌아감
            } else {
                System.out.println("잘못된 메뉴입니다.\n");
            }
        }
    }

    // 1번: 카테고리 목록 출력
    private void showCategoryList() {
        List<Category> list = categoryService.getCategoryList();

        System.out.println("\n----------------------------------------------------");
        System.out.println("번호 | 카테고리명");
        System.out.println("----------------------------------------------------");

        for (Category c : list) {
            System.out.printf("%-4d | %s\n",
                    c.getCateNo(), c.getCateName());
        }

        System.out.println("----------------------------------------------------\n");
    }

    // 2번: 카테고리별 게시글 메뉴 (내부 루프)
    // 여기서 '3. 이전 메뉴'를 누르면 다시 카테고리 번호 입력 화면으로 돌아옴
    private void categoryPostMenu() {
        while (true) {
            // 카테고리 목록 보여주기
            showCategoryList();

            System.out.println("1. 카테고리 선택");
            System.out.println("3. 이전 메뉴");
            System.out.println("========================================");
            System.out.print("메뉴 선택 >> ");

            int menu = sc.nextInt();

            if (menu == 1) {
                selectCategoryForPosts();   // 실제 카테고리 번호 입력 + 게시글 리스트
            } else if (menu == 3) {
                // 카테고리 메인 메뉴로 돌아감
                return;
            } else {
                System.out.println("잘못된 메뉴입니다.\n");
            }
        }
    }

    // 카테고리 번호 선택 후, 해당 카테고리의 게시글 목록 출력
    private void selectCategoryForPosts() {
        try {
            // 이미 바로 위에서 목록을 한 번 보여줬지만,
            // 번호 선택 직전에 다시 한 번 보여주고 싶으면 주석 해제
            // showCategoryList();

            System.out.print("카테고리 번호 입력 >> ");
            int cateNo = sc.nextInt();

            // 선택한 카테고리의 게시글 목록 조회
            List<BoardVO> posts = postService.getPostsByCategory(cateNo);

            if (posts == null || posts.isEmpty()) {
                System.out.println("\n해당 카테고리에 게시글이 없습니다.\n");
                return;   // categoryPostMenu()의 while로 돌아감
            }

            // 게시글 목록 출력
            BoardPrint.print(posts);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("카테고리별 게시글 조회 중 오류가 발생했습니다.\n");
        }
    }
}
