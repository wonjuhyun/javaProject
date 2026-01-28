package com.blog.board.vo;


import java.text.SimpleDateFormat;



public class BoardVO {

private int postNo;              // 게시글 번호 (PK)

private String writerId;         // 작성자 아이디

private int cateNo;              // 카테고리 번호

private String title;            // 제목

private String content;          // 내용

private int hit;                 // 조회수

private  String createdAt; // 작성일     LocalDateTime-> String

private  String updatedAt; // 수정일     LocalDateTime-> String

public int getPostNo() { // 게시글 번호가져오기

	return postNo;

}

public void setPostNo(int postNo) {  // 게시글 번호 설정

	this.postNo = postNo;

}

public String getWriterId() {// 작성자 아이디 가져오기

	return writerId;

}

public void setWriterId(String writerId) { // 작성자 아이디 설정

	this.writerId = writerId;

}

public int getCateNo() {// 카테고리 번호 가져오기

	return cateNo;

}

public void setCateNo(int cateNo) {// 카테고리 번호 설정

	this.cateNo = cateNo;

}

public String getTitle() { //제목 가져오기

	return title;

}

public void setTitle(String title) {//제목설정

	this.title = title;

}

public String getContent() { //내용 가져오기

	return content;

}

public void setContent(String content) {//내용 설정

	this.content = content;

}

public int getHit() { //조회수 가져오기

	return hit;

}

public void setHit(int hit) {//조회수 설정

	this.hit = hit;

}

public  String getCreatedAt() {//작성일 가져오기

	return createdAt;

}

public void setCreatedAt(String createdAt) { //작성일 설정
	this.createdAt = createdAt;

}

public  String getUpdatedAt() {//수정일 가져오기

	return updatedAt;

}

public void setUpdatedAt(String updatedAt) { //수정일 설정
	this.updatedAt = updatedAt;

	}

@Override
public String toString() {
	return "Post [postNo=" + postNo + ", writerId=" + writerId + ", cateNo=" + cateNo + ", title=" + title
			+ ", content=" + content + ", hit=" + hit + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
}


}



