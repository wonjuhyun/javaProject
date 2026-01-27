package com.blog.like.vo;

public class LikeVO {

	private int postNo;
	private String likerId;
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getLikerId() {
		return likerId;
	}
	public void setLikerId(String likerId) {
		this.likerId = likerId;
	}
	
	@Override
	public String toString() {
		return "LikeVO [postNo=" + postNo + ", likerId=" + likerId + "]";
	}
	
}
