package com.example.dinesh.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dinesh.user.exception.PostsNotFoundException;
import com.example.dinesh.user.exception.UserNotFoundException;
import com.example.dinesh.user.exception.UserNotSavedException;

/*import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;*/

@RestController
public class UserResource {

	@Autowired
	private UserDaoService userDaoService;

	// Get all users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDaoService.findAll();
	}

	// Get one user{id}
	@GetMapping("/users/{id}")
	/*public EntityModel<User> retrieveUser(@PathVariable int id)*/ 
	public User retrieveUser(@PathVariable int id){
		User user = userDaoService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Id=" + id);
		}

		/*EntityModel<User> resource = new EntityModel<User>(user);

		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));
		return resource;
*/
		return user;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		User user = userDaoService.deleteUerById(id);
		if (user == null) {
			throw new UserNotFoundException("Id=" + id);
		}

	}

	/*
	 * This below post method returns the HTTP 200 code with created user details
	 * object
	 */
	/*
	 * @PostMapping("/users") public User createUser(@RequestBody User user) { User
	 * savedUser=userDaoService.saveUser(user); return
	 * userDaoService.findOne(savedUser.getId());
	 * 
	 * }
	 */

	/*
	 * The below post method returns the 'Created' status code with responseentity
	 */
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		if (null == user.getName()) {
			throw new UserNotSavedException("Not Enough information to store user");
		}
		User savedUser = userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/users/posts")
	public List<Posts> findAllPosts() {
		return userDaoService.findAllPosts();
	}

	@GetMapping("/users/{userId}/posts")
	public Posts findUserPost(@PathVariable int userId) {
		Posts posts = userDaoService.findPostForUser(userId);
		if (posts == null) {
			throw new PostsNotFoundException("No Posts found for the user");
		}
		return posts;
	}

	@PostMapping("/users/{userId}/posts")
	public Posts saveUserPost(@Valid @RequestBody Posts userPosts) {
		if (null == userPosts.getUserId()) {
			throw new UserNotSavedException("Not Enough information to store user");
		}
		Posts posts = userDaoService.savePostForUser(userPosts);
		return posts;
	}
}
