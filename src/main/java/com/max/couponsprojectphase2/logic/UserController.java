package com.max.couponsprojectphase2.logic;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.max.couponsprojectphase2.entities.User;
import com.max.couponsprojectphase2.enums.ClientType;
import com.max.couponsprojectphase2.enums.ErrorType;
import com.max.couponsprojectphase2.cache.ICacheController;
import com.max.couponsprojectphase2.dao.IUserDao;
import com.max.couponsprojectphase2.data.LoggedInUserData;
import com.max.couponsprojectphase2.data.LoginResponseDataObject;
import com.max.couponsprojectphase2.exceptions.ApplicationException;
import com.max.couponsprojectphase2.utils.DateUtils;

@Controller
public class UserController {

	@Autowired
	private ICacheController cacheController;

	@Autowired
	private IUserDao userDao;
	

	public LoginResponseDataObject login(String userName, String password) throws ApplicationException {
		validateUserLogin(userName, password);
		User user = userDao.login(userName, password);
		LoggedInUserData loggedInUserData = new LoggedInUserData(user.getType(), user.getId());
		if (loggedInUserData.getUserId() == null) {
			// Throws an exception that indicates a failed login.
			throw new ApplicationException(ErrorType.LOGIN_FAILED, " Log in failed! " + userName);
		}
		int token = generateToken(userName, loggedInUserData);
		loggedInUserData.setToken(token);
		// Save Login user data in cache.
		cacheController.put(token+"", loggedInUserData);
		return new LoginResponseDataObject(user.getType(), token);
	}

	private int generateToken(String userName, LoggedInUserData loggedInUserData) {
		Random rnd = new Random();
		String salt = "#####";
		return (userName + rnd.nextInt(9999999) + salt + loggedInUserData.getUserId()).hashCode();

	}

	public Long createUser(User user) throws ApplicationException {
		validateUserId(user.getId(), false);
		validateUser(user);
		validateUserDoesNotExist(userDao.findByUserName(user.getUserName()));
		return userDao.save(user).getId();
	}

	public User getUserById(Long userId) throws ApplicationException {
		validateUserExist(userId);
		return userDao.findById(userId).get();
	}

	public User getUsersByUserName(String userName) throws ApplicationException {
		validateUserName(userName);
		return userDao.findByUserName(userName);
	}

	public List<User> getAllUsers() throws ApplicationException {
		validateTable();
		List<User> users = (List<User>) userDao.findAll();
		return users;
	}

	public void updateUser(User updatedUser) throws ApplicationException {
		validateUser(updatedUser);
		validateUpdateUser(updatedUser);
		this.userDao.save(updatedUser);
	}

	public void deleteUser(Long userId) throws ApplicationException {
		validateUserExist(userId);
		this.userDao.deleteById(userId);
	}

	private void validateUserLogin(String suppliedUserName, String suppliedPassword) throws ApplicationException {
		if (userDao.login(suppliedUserName, suppliedPassword) == null) {
			throw new ApplicationException(ErrorType.LOGIN_FAILED,
					DateUtils.getCurrentDateAndTime() + ": Invalid username or password.");
		}
	}

	private void validateUserId(Long userId, boolean isRequired) throws ApplicationException {
		if (isRequired) {
			if (userId == null) {
				throw new ApplicationException(ErrorType.NULL_DATA,
						DateUtils.getCurrentDateAndTime() + ": User id is not supplied.");
			}
		} else {
			if (userId != null) {
				throw new ApplicationException(ErrorType.REDUNDANT_DATA,
						DateUtils.getCurrentDateAndTime() + ": Id is redundant.");
			}
		}
	}

	private void validateUserName(String userName) throws ApplicationException {
		if (userDao.findByUserName(userName) == null) {
			throw new ApplicationException(ErrorType.USERNAME_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": Username doesn't exist.");
		}
	}

	private void validateUserExist(Long userId) throws ApplicationException {
		if (!userDao.findById(userId).isPresent()) {
			throw new ApplicationException(ErrorType.USER_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": User doesn't exist.");
		}
	}

	private void validateUserDoesNotExist(User user) throws ApplicationException {
		if (user != null) {
			throw new ApplicationException(ErrorType.USER_ALREADY_EXIST,
					DateUtils.getCurrentDateAndTime() + ": User already exist.");
		}
	}

	private void validateUpdateUser(User updatedUser) throws ApplicationException {
		User currentUser = userDao.findById(updatedUser.getId()).get();
		if (currentUser.equals(updatedUser)) {
			throw new ApplicationException(ErrorType.UPDATE_FAILED,
					DateUtils.getCurrentDateAndTime() + ": No difference found between old and new data.");
		}
	}

	private void validateUser(User user) throws ApplicationException {
		List<User> usersList = (List<User>) userDao.findAll();
		if (user == null) {
			throw new ApplicationException(ErrorType.NULL_DATA, DateUtils.getCurrentDateAndTime() + ": User is null.");
		}
		if (user.getUserName() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": Username is null.");
		}
		if (user.getUserName().isEmpty()) {
			throw new ApplicationException(ErrorType.EMPTY_DATA,
					DateUtils.getCurrentDateAndTime() + ": Username is empty.");
		}
		if (user.getPassword() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": Password is null.");
		}
		if (user.getPassword().isEmpty()) {
			throw new ApplicationException(ErrorType.EMPTY_DATA,
					DateUtils.getCurrentDateAndTime() + ": Password is empty.");
		}
		if (!isStrongPassword(user.getPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD,
					DateUtils.getCurrentDateAndTime() + ": Password " + user.getPassword() + " is not strong.");
		}
		if (user.getType() == null) {
			throw new ApplicationException(ErrorType.NULL_DATA,
					DateUtils.getCurrentDateAndTime() + ": User type is null.");
		}
		if (!ClientType.contains(user.getType())) {
			throw new ApplicationException(ErrorType.USER_TYPE_DOES_NOT_EXIST,
					DateUtils.getCurrentDateAndTime() + ": User type " + user.getType() + " doesn't exist.");
		}
		if (usersList.contains(user)) {
			throw new ApplicationException(ErrorType.USER_ALREADY_EXIST,
					DateUtils.getCurrentDateAndTime() + ": User " + user.getUserName() + " already exist.");
		}

	}

	private void validateTable() throws ApplicationException {
		if (userDao.findAll() == null) {
			throw new ApplicationException(ErrorType.EMPTY_TABLE,
					DateUtils.getCurrentDateAndTime() + ": Table is not exist.");
		}

	}

	private boolean isStrongPassword(String password) {
		String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}";
		return password.matches(pattern);
	}

}