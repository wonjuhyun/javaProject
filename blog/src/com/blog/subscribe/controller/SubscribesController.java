package com.blog.subscribe.controller;

import java.util.List;
import java.util.Scanner;

import com.blog.main.service.Execute;
import com.blog.main.service.Service;
import com.blog.subscribe.service.FollowerIdListService;
import com.blog.subscribe.service.FollowingIdListService;
import com.blog.subscribe.service.SubscribesDeleteService;
import com.blog.subscribe.service.SubscribesInsertService;
import com.blog.subscribe.vo.SubscribesVO;

public class SubscribesController {

    private Scanner scanner = new Scanner(System.in);

    // 메인에서 <구독> 선택 시 실행
    public void execute(String loginId) {
        while (true) {
            System.out.println("\n===================================");
            System.out.println("<구독>");
            System.out.println("1. 구독 리스트  2. 구독자 리스트  0. 돌아가기");
            System.out.println("===================================");
            System.out.print("메뉴 선택 > ");
            String menu = scanner.nextLine();

            if (menu.equals("1")) {
                followingMenu(loginId); // 1. 구독 리스트 상세 메뉴로
            } else if (menu.equals("2")) {
                followerMenu(loginId);  // 2. 구독자 리스트로
            } else if (menu.equals("0")) {
                return; // 메인으로 돌아가기
            } else {
                System.out.println("다시 한 번 확인해주세요.");
            }
		}
    }

    // 1. 구독 리스트 메뉴 (출력 + 구독하기/취소하기)
    @SuppressWarnings("unchecked")
	private void followingMenu(String loginId) {
        while (true) {
            System.out.println("\n===================================");
            System.out.println("<구독 리스트>");
            
            try {
                // 리스트 출력 - 갱신을 위해 반복문 최상단에 배치
                List<SubscribesVO> list = (List<SubscribesVO>) Execute.execute(new FollowingIdListService(), loginId);
                if (list == null || list.isEmpty()) {
                    System.out.println("구독 중인 블로그가 없습니다.");
                } else {
                    for (SubscribesVO vo : list) {
                        System.out.println("- " + vo.getFollowingId());
                    }
                }
            } catch (Exception e) {
                System.out.println("오류가 발생했습니다.");
            }

            System.out.println("===================================");
            System.out.println("1. 구독하기  2. 구독 취소하기  0. 돌아가기");
            System.out.print("메뉴 선택 > ");
            String subMenu = scanner.nextLine();

            if (subMenu.equals("1")) {
                doAction(loginId, "insert"); // 구독하기 실행
            } else if (subMenu.equals("2")) {
                doAction(loginId, "delete"); // 구독 취소 실행
            } else if (subMenu.equals("0")) {
                break; // <구독> 메뉴로 이동
            } else {
                System.out.println("다시 한 번 확인해주세요.");
            }
        }
    }

    // 구독하기(1-1) & 구독 취소하기(1-2) 공통 로직 메서드
    private void doAction(String loginId, String type) {
        String inordl = type.equals("insert") ? "구독" : "구독 취소";
        
        System.out.println("\n" + inordl + "하고 싶은 블로그의 아이디를 입력하세요.");
        System.out.print("블로그 아이디 : ");
        String targetId = scanner.nextLine();

        System.out.println("===================================");
        System.out.println(targetId + " 님을 " + inordl + "하시겠습니까? (y/n)");
        System.out.println("===================================");
        System.out.print("> ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            try {
                SubscribesVO vo = new SubscribesVO();
                vo.setFollowingId(targetId);
                vo.setFollowerId(loginId);
                
                Service service = type.equals("insert") ? new SubscribesInsertService() : new SubscribesDeleteService();
                int result = (int) Execute.execute(service, vo);

                if (result > 0) {
                    System.out.println("===================================");
                    System.out.println(targetId + " 님을 " + inordl+ "하셨습니다.");
                    System.out.println("===================================");
                }
            } catch (Exception e) {
                System.out.println(inordl + " 중 오류 발생: " + e.getMessage());
            }
        } else if (confirm.equalsIgnoreCase("n")) {
            // 그대로 돌아감 (while문에 의해 리스트 재출력)
        } else {
            System.out.println("다시 한 번 확인해주세요.");
        }
    }

    // 2. 구독자 리스트 메뉴
    @SuppressWarnings("unchecked")
	private void followerMenu(String loginId) {
        System.out.println("\n===================================");
        System.out.println("<구독자 리스트>");
        try {
            List<SubscribesVO> list = (List<SubscribesVO>) Execute.execute(new FollowerIdListService(), loginId);
            if (list == null || list.isEmpty()) {
                System.out.println("나를 구독하는 사람이 없습니다.");
            } else {
                for (SubscribesVO vo : list) {
                    System.out.println("- " + vo.getFollowerId());
                }
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
        }
        System.out.println("===================================");
        System.out.println("0. 돌아가기");
        while(!scanner.nextLine().equals("0")) {
        }
    }
}