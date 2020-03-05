package com.max.couponsprojectphase2.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.data.LoginResponseDataObject;
import com.max.couponsprojectphase2.data.UserLoginDetailsDataObject;
import com.max.couponsprojectphase2.entities.User;
import com.max.couponsprojectphase2.logic.UserController;

@RestController
@RequestMapping("/user")
public class UserApi {

	@Autowired
	private UserController userController;

	@PostMapping("/login")
	public LoginResponseDataObject login(@RequestBody UserLoginDetailsDataObject userLoginDetails)
			throws ApplicationException {
		return this.userController.login(userLoginDetails.getUserName(), userLoginDetails.getPassword());
	}
	
	// method=POST URL=http://localhost:8080/user
	@PostMapping
	public Long createUser(@RequestBody User user) throws ApplicationException {
		Long userId = this.userController.createUser(user);
		System.out.println("The user was successfully created with id: " + userId);
		return userId;
	}

	// method=GET URL=http://localhost:8080/user/111
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") Long userId) throws ApplicationException {
		System.out.println("Requested user id is: " + userId);
		return userController.getUserById(userId);
	}

	// method=GET URL=http://localhost:8080/user/byUserName?userName=un
	@GetMapping("/byUserName")
	public User getUserByUserName(@RequestParam("userName") String userName) throws ApplicationException {
		return userController.getUsersByUserName(userName);
	}

	// method=GET URL=http://localhost:8080/user
	@GetMapping
	public List<User> getAllUsers() throws ApplicationException {
		System.out.println("This is a users list:");
		return userController.getAllUsers();
	}

	// method=PUT URL=http://localhost:8080/user
	@PutMapping
	public void updateUser(@RequestBody User user) throws ApplicationException {
		this.userController.updateUser(user);
		System.out.println("Updated user is: " + user);
	}

	// method=DELETE URL=http://localhost:8080/user/111
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) throws ApplicationException {
		this.userController.deleteUser(userId);
		System.out.println("Delete user by id: " + userId + " succeed.");
	}

}
