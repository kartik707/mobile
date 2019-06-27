package com.appdeveloper.ws.mobileapp.dependency;

import com.appdeveloper.ws.mobileapp.ws.ui.model.post.UserDetailsRequestModel;
import com.appdeveloper.ws.mobileapp.ws.ui.model.response.UserRest;


public interface UserService {
  UserRest createUser(UserDetailsRequestModel userDetails);
}
