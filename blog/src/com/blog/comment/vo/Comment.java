package com.blog.comment.vo;

public class Comment {

    private int commentNo;       // 댓글 번호 (PK)
    private int postNo;      // 게시글 번호
    private String writerId;      // 댓글 작성자 아이디
    private String writerNick;      // 댓글 작성자 닉네임
    private String content;      // 댓글 내용
    private String createdAt;      // 댓글 작성일
    private String updatedAt;      // 댓글 수정일
    
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
	public String getWriterNick() {
		return writerNick;
	}
	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", postNo=" + postNo + ", writerId=" + writerId + ", writerNick="
				+ writerNick + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}	
    
}
