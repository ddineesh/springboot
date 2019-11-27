package com.example.dinesh.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Posts {

	Integer postId;
	@Size(min=5,message="Post Mesage should be minimum of 5 char")
	String postMessage;
	@NotNull(message=" Post can't be saved with out User id in request")
	Integer userId;
	public Posts() {
		super();
	}
	
	
	public Posts(Integer postId, String postMessage, Integer userId) {
		super();
		this.postId = postId;
		this.postMessage = postMessage;
		this.userId = userId;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public String getPostMessage() {
		return postMessage;
	}


	public void setPostMessage(String postMessage) {
		this.postMessage = postMessage;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Override
	public String toString()
	{
		return String.format("User [UserId=%d, PostId=%d, Message=%s]", userId,postId,postMessage);
				
	}
	
}
