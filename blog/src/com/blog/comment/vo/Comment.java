package com.blog.comment.vo;

import java.time.LocalDateTime;

public class Comment {

    private int commentNo;       // 댓글 번호 (PK)
    private int postNo;      // 게시글 번호
    private String writerId;      // 댓글 작성자 아이디
    private String content;      // 댓글 내용
    private LocalDateTime createdAt;      // 댓글 작성일
    private LocalDateTime updatedAt;      // 댓글 수정일
    
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", postNo=" + postNo + ", writerId=" + writerId + ", content="
				+ content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
    
}
