package com.blog.comment.controller;

import java.util.List;

import com.blog.comment.service.CommentDeleteService;
import com.blog.comment.service.CommentListService;
import com.blog.comment.service.CommentUpdateService;
import com.blog.comment.service.CommentViewService;
import com.blog.comment.service.CommentWriteService;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Execute;
import com.blog.util.io.CommentPrint;
import com.blog.util.io.In;


public class CommentController {

	public void execute() {
		// 종료를 선택하기 전까지 무한반복
		// while(~) - ~ 조건이 true인 동안 반복 처리한다
		//   - 빠져나가는 방법 : break;
		while (true) {
			try {
				// 일반 게시판 처리
				// 1. 메뉴 출력 - 일반 게시판이 가지고 있는 기능
				System.out.println();
				System.out.println("<<< 댓글 >>>");
				System.out.println("==============================================");
				System.out.println(" 1. 댓글 리스트  2. 댓글 작성  3. 내 댓글  0. 이전");
				System.out.println("==============================================");
				
				// 2. 메뉴 입력
				String menu = In.getStr("메뉴 입력");
				System.out.println();  // 줄바꿈
				
				// 3. 메뉴 처리 - switch
				// 사용 변수 선언
				Comment vo;
				Integer result;
				long no;
				Long[] arrs;
				switch (menu) {
				case "1" :
//					System.out.println("댓글 리스트 처리");
					@SuppressWarnings("unchecked")
					List<Comment> list = (List<Comment>) Execute.execute(new CommentListService(), null); 
					CommentPrint.print(list);
					break;
				case "2" :
//					System.out.println("댓글 작성 처리");
					vo = new Comment();
					vo.setContent(In.getStr("댓글 입력"));
					result = (Integer) Execute.execute(new CommentWriteService(), vo);
					break;
				case "3" :
//					case "3" :
					System.out.println("내 댓글 보기 처리");
					
					// 1. 로그인 여부 확인 (Main.login에 로그인 정보가 있다고 가정)
					// if (Main.login == null) { System.out.println("로그인이 필요합니다."); break; }

					// 2. 게시글 번호 입력 받기
					Comment searchVO = new Comment();
					searchVO.setPostNo(In.getInt("게시글 번호")); 
					
					// 3. 내 아이디 세팅 (테스트를 위해 강제로 "testId"라고 넣거나, Main.login.getId() 사용)
					// 실제 사용 시: searchVO.setWriterId(Main.login.getId());
					searchVO.setWriterId(Main.login.getId()); // ★ 테스트용 임시 ID (나중에 Main.login.getId()로 변경하세요)

					// 4. 서비스 실행 (새로 만들 Service 클래스 호출)
					// 결과는 목록(List)이어야 하므로 List로 형변환
					@SuppressWarnings("unchecked")
					List<Comment> myList = (List<Comment>) Execute.execute(new CommentMyListService(), searchVO); 
					
					// 5. 출력
					CommentPrint.print(myList);
				case "4" :
					// 1. 수정할 글 번호를 입력받는다.
					no = In.getInt("댓글 번호");
					// 2. 입력받은 글 번호의 데이터를 가져온다. (vo = 글 보기)
					// new Long[] {no, 1L} - new Long[] {번호:0, 증가:1} - 생성하고 바로 초기값을 세팅한다.
					vo = (Comment) Execute.execute(new CommentViewService(), new Long[] {no, 0L}); 
					CommentPrint.print(vo);
					// 3. 가져온 데이터를 수정 입력한다. (수정 항목 선택 -> 입력 : 입력 끝남 선택 빠져나옴)
					Integer item = update(vo);
					// 4. DB 수정하러 간다. (BoardUpdateService)
					// 수정 데이터 확인
//					System.out.println("------ 수정 데이터 확인 ------");
//					BoardPrint.print(vo);
					
					if (item == 9) {
						System.out.println("**** 수정이 취소되었습니다. ****\n");
					} else {
						// 수정하러 간다
						// 본인 확인용 비밀번호를 받는다
						result = (Integer) Execute.execute(new CommentUpdateService(), vo);
						if (result >= 1) System.out.println("***** 수정이 완료되었습니다. *****");
						else System.out.println("***** 수정에 실패했습니다. 정보를 다시 확인해 주세요. *****");
					}
					
					break;
				case "5" :
					System.out.println("댓글 삭제 처리");
					// 글번호와 비밀번호 받기 - vo
					vo = new Comment();
					vo.setCommentNo(In.getInt("댓글 번호"));
					result = (Integer) Execute.execute(new CommentDeleteService(), vo);
					if (result >= 1) System.out.println("***** 삭제가 완료되었습니다. *****");
					else System.out.println("***** 삭제가 되지 않았습니다. 정보를 다시 확인해 주세요. *****");
					break;
				case "0" :
					// 자신을 호출한 프로그램으로 돌아간. Main.main()
					return;
				default :
//					Main.invalidMenuPrint();
				}  // switch 끝
				System.out.println();  // 화면을 구분하는 빈 줄 출력
			}  // try 정상처리 끝
			catch (Exception e) {  // 예외 처리
				e.printStackTrace();  // 개발자를 위한 예외 상세 출력
				// 사용자를 위한 예외 코드 작성
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(" 일반 게시판 처리 중 오류가 발생되었습니다.");
				System.out.println(" 오류 : " + e.getMessage());
				System.out.println(" 다시 한 번 실행해 주시고, 오류가 계속되면 문의를 남겨주세요.");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
			}  // catch 끝
		}  // while(true) 끝
	}  // execute() 끝
	
	// 데이터를 수정하는 메서드
	// 전달된 vo는 주소가 전달된다. 안에서 수정하면 밖에서도 수정이 같이 된다
	public Integer update (Comment vo) {
		while(true) {
			// 수정 메뉴 입력
			vo.setContent(In.getStr("내용"));
			// 수정된 내용 출력
			CommentPrint.print(vo);
		}  // while(true) 끝
	}  // update() 끝
}
