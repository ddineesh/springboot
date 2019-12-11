package com.example.dinesh.jpa;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.dinesh.user.User;
import com.example.dinesh.user.exception.UserNotFoundException;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepo;

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
}
