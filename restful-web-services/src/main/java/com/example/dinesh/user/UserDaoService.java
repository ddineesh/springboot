package com.example.dinesh.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users=new ArrayList<>();
	private static int userCount=334;
	
	static
	{
		users.add(new User(331,"Dinesh",new Date()));
		users.add(new User(332,"Suba",new Date()));
		users.add(new User(333,"Ila",new Date()));
		users.add(new User(334,"Ovi",new Date()));
	}
	
	public List<User> findAll()
	{
		return users;
	}
	
	public User saveUser(User user) {
		
		if(user.getId()==null)
		{
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id)
	{
		for(User user:users)
		{
			if(user.getId()==id)
			{
				return user;
			}
		}
		return null;
	}
}
