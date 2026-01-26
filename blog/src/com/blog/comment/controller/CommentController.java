package com.blog.comment.controller;

import java.util.List;

import com.blog.comment.service.CommentDeleteService;
import com.blog.comment.service.CommentListService;
import com.blog.comment.service.CommentUpdateService;
import com.blog.comment.service.CommentViewService;
import com.blog.comment.service.CommentWriteService;
import com.blog.comment.vo.Comment;
import com.blog.main.service.Execute;
import com.blog.member.vo.Login;
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
					vo.setPostNo(Post.getPostNO());
					vo.setWriterId(Login.getId());
					vo.setContent(In.getStr("댓글 입력"));
					result = (Integer) Execute.execute(new CommentWriteService(), vo);
					break;
				case "3" :
					// 2. 조회할 데이터 세팅 (게시글 번호 + 로그인된 내 아이디)
					vo = new Comment();
					vo.setPostNo(Post.getPostNo());
					vo.setWriterId(Login.getId()); // 로그인 정보 자동 입력
					// 1. 로그인 여부 확인 (Main.login에 로그인 정보가 있다고 가정)
					// if (Main.login == null) { System.out.println("로그인이 필요합니다."); break; }
					
					// 5. 출력
					CommentPrint.print(vo);
					
					break;
				case "4" :
					
					Comment checkVO = (Comment) Execute.execute(new CommentViewService(), vo);
					
					if (checkVO != null) {
						CommentPrint.print(checkVO);
						
						// 내용 수정 입력 (내부에서 checkVO 내용이 바뀜)
						update(checkVO);
						
						// [수정] 실제로 DB에 업데이트하는 코드 추가
						result = (Integer) Execute.execute(new CommentUpdateService(), checkVO);
						
						if (result >= 1) System.out.println("***** 수정이 완료되었습니다. *****");
						else System.out.println("***** 수정 실패 *****");
						
					} else {
						System.out.println("***** 이 게시글에 작성하신 댓글이 없습니다. *****");
					}
					break;
				case "5" :
					System.out.println("댓글 삭제 처리");
					// 글번호와 비밀번호 받기 - vo
					vo = new Comment();
					vo.setPostNo(Post.getPostNo());
					vo.setWriterId(Login.getId());
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
