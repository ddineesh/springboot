package com.example.dinesh.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = " Contains the user details")
@Entity
public class UserTable {

	protected UserTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTable(Integer id, String name, Date dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	@Id
	@GeneratedValue
	private Integer id;
	@ApiModelProperty(notes = " User name should be minimum 3 characters")
	@Size(min = 3, message = " User name should be minimum 3 characters")
	private String name;
	@NotNull(message = "User Date of birth should not be null")
	@ApiModelProperty(notes = " Birth date cannot be in past")
	private Date dateOfBirth;

	@OneToMany(mappedBy="user")
	private List<Post> posts;

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

	
	
	

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return String.format("User [id=%d, name=%s, dateOfBirth=%s]", id, name, dateOfBirth);

	}
}
