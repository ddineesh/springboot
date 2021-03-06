package com.example.dinesh.user;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description=" Contains the user details")
public class User {


	
	
	protected User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	private Integer id;
	@ApiModelProperty(notes=" User name should be minimum 3 characters")
	@Size(min=3,message=" User name should be minimum 3 characters")
	private String name;
	@NotNull(message="User Date of birth should not be null")
	@ApiModelProperty(notes=" Birth date cannot be in past")
	private Date dateOfBirth;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public String toString()
	{
		return String.format("User [id=%d, name=%s, dateOfBirth=%s]", id,name,dateOfBirth);
				
	}
}
