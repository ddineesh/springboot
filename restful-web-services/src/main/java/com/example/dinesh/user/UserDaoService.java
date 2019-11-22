package com.example.dinesh.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static List<Posts> posts = new ArrayList<Posts>();
	private static int userCount = 334;
	private static int postsCount = 334;
	static {
		users.add(new User(331, "Dinesh", new Date()));
		users.add(new User(332, "Suba", new Date()));
		users.add(new User(333, "Ila", new Date()));
		users.add(new User(334, "Ovi", new Date()));
	}

	static {
		posts.add(new Posts(1, "Static post Dinesh", 331));
		posts.add(new Posts(1, "Suba Static post", 332));
		posts.add(new Posts(1, "Ila Static post", 333));
		posts.add(new Posts(1, "Ovi Static post", 334));
	}

	public List<User> findAll() {
		return users;
	}

	public User saveUser(User user) {

		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User deleteUerById(int id) {
		Iterator<User> iterator=users.iterator();
		while(iterator.hasNext())
		{
			User user=iterator.next();
			
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

	
	public List<Posts> findAllPosts() {
		return posts;
	}

	public Posts findPostForUser(int userId) {
		for (Posts post : posts) {
			if (post.getUserId() == userId) {
				return post;
			}
		}
		return null;

	}
	
	public Posts savePostForUser(Posts userPosts)
	{
		if(userPosts.getPostId()==null) {
			userPosts.setPostId(++postsCount);
		}
		posts.add(userPosts);
		return userPosts;
	}
}
