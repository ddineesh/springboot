package com.example.dinesh.jpa;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dinesh.user.Posts;
import com.example.dinesh.user.User;
import com.example.dinesh.user.exception.UserNotFoundException;
import com.example.dinesh.user.exception.UserNotSavedException;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	// Get all users
	@GetMapping("/jpa/users")
	public List<UserTable> retrieveAllUsers() {
		return userRepo.findAll();
	}

	// Get one user{id}
	@GetMapping("/jpa/users/{id}")
	public Resource<UserTable> retrieveUser(@PathVariable int id) {
		Optional<UserTable> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id=" + id);
		}

		Resource<UserTable> resource = new Resource<UserTable>(user.get());

		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));
		return resource;

	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		userRepo.deleteById(id);
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserTable user) {
		if (null == user.getName()) {
			throw new UserNotSavedException("Not Enough information to store user");
		}
		UserTable savedUser = userRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}


	// Get one user{id}
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id) {
		Optional<UserTable> user = userRepo.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id=" + id);
		}

		
		/*Resource<UserTable> resource = new Resource<UserTable>(user.get());

		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));
		return resource;
*/
		return user.get().getPosts();
	}

	@PostMapping("/jpa/users/{userId}/posts")
	public ResponseEntity<Object> saveUserPost(@Valid @PathVariable int userId, @RequestBody Post userPosts) {
		
		Optional<UserTable> user = userRepo.findById(userId);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id=" + userId);
		}
				
		UserTable userTable=user.get();
		
		userPosts.setUser(userTable);
		
		postRepo.save(userPosts);
			
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}").buildAndExpand(userPosts.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

}
