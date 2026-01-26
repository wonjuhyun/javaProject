package com.blog.subscribe.vo;

public class SubscribesVO {
	private int subNo; // 구독 번호(PK)
	private String followingId; // 내가 구독한 사람 아이디
	private String followerId; // 나를 구독한 사람 아이디

	public SubscribesVO() {
	} // 기본 생성자

	// 객체 생성을 위한 전체 필드 생성자
	public SubscribesVO(int subNo, String followingId, String followerId) {
		this.subNo = subNo;
		this.followingId = followingId;
		this.followerId = followerId;
	}

	public int getSubNo() {
		return subNo;
	}

	public void setSubNo(int subNo) {
		this.subNo = subNo;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	@Override
	public String toString() {
		return "Subscribes [subNo = " + subNo + ", followingId = " + followingId + ", followerId = " + followerId + "]";
	}
}