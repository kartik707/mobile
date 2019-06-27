package com.appdeveloper.ws.mobileapp.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appdeveloper.ws.mobileapp.dependency.UserService;
import com.appdeveloper.ws.mobileapp.exception.CustomException;
import com.appdeveloper.ws.mobileapp.ws.ui.model.post.UpdateUserDetailsRequestModel;
import com.appdeveloper.ws.mobileapp.ws.ui.model.post.UserDetailsRequestModel;
import com.appdeveloper.ws.mobileapp.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	Map<String, UserRest> users;
	
	@Autowired
	UserService userService;

	@GetMapping()
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
		return "get user was called with page" + page + "& limit " + limit + "and sort" + sort;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

		if(true) throw new CustomException("A Custom Exception");
		
		if(users.containsKey(userId)) 
		{
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
			}
		else{
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
		}

	//@PostMapping
	//public String createUsers(@RequestParam(value = "page", defaultValue = "1") int page,
		//	@RequestParam(value = "limit", defaultValue = "50") int limit) {
		//return "create user was called with page" + page + "& limit " + limit;
	//}

	@PostMapping(path = "/{userId}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		 
		UserRest returnValue = userService.createUser(userDetails);

			
			return new ResponseEntity<UserRest>(HttpStatus.OK);
		}

	

	@PutMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })

	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}

	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		
		users.remove(id);
		return ResponseEntity.noContent().build(); 
	}
}
