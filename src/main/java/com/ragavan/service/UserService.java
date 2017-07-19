package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.User;

public interface UserService {

	int saveService(User user) throws ServiceException;

	int updateService(User user) throws ServiceException;

	int updateUsernameService(User user) throws ServiceException;

	int activateUserService(User user) throws ServiceException;

	int updatePasswordService(User user) throws ServiceException;

	int updateRoleService(User user) throws ServiceException;

	int deleteService(int id) throws ServiceException;

	List<User> listService();

	User listOneService(String name) throws ServiceException;

	boolean functionLoginService(User user) throws ServiceException;

	boolean functionIsValidUserService(String name) throws ServiceException;

	int functionGetRoleId(String name) throws ServiceException;

	int functionGetUserId(String user) throws ServiceException;

	String functionGetUserName(int id) throws ServiceException;

	String functionGetUserEmail(int id) throws ServiceException;

	String getHashedPassword(String userName) throws ServiceException;

}