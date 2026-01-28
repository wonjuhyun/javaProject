package com.blog.category.service;

import java.util.List;

import com.blog.board.service.BoardlistCategory;
import com.blog.board.vo.BoardVO;
import com.blog.main.service.Execute;
import com.blog.main.service.Service;

// 카테고리에서 "카테고리별 게시글 목록"을 가져오기 위한 서비스 래퍼
public class PostService implements Service {

    @Override
    @SuppressWarnings("unchecked")
    public List<BoardVO> service(Object obj) throws Exception {
        Integer cateNo = (Integer) obj;

        // 게시글 쪽 카테고리 목록 서비스 호출
        return (List<BoardVO>) Execute.execute(new BoardlistCategory(), cateNo);
    }

    // 화면 코드에서 편하게 쓰기 위한 헬퍼 메소드
    public List<BoardVO> getPostsByCategory(int cateNo) throws Exception {
        return service(cateNo);
    }
}
