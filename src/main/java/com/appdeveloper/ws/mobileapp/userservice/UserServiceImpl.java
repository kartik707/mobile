package com.appdeveloper.ws.mobileapp.userservice;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.appdeveloper.ws.mobileapp.dependency.UserService;
import com.appdeveloper.ws.mobileapp.ws.ui.model.post.UserDetailsRequestModel;
import com.appdeveloper.ws.mobileapp.ws.ui.model.response.UserRest;

@Service
public class UserServiceImpl implements UserService {

	Map<String, UserRest> users;
	
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		// TODO Auto-generated method stub
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail("test@test");
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		
		String userId = UUID.randomUUID().toString();
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;

		
	}

}
