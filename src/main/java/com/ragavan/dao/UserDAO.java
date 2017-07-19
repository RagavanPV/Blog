package com.ragavan.dao;

import java.util.List;

import com.ragavan.exception.ValidationException;
import com.ragavan.model.User;

public interface UserDAO {

	int save(User user);

	int update(User user);

	int updateUsername(User user);

	int activateUser(User user);

	int updatePassword(User user);

	int updateRole(User user);

	int delete(int id);

	List<User> list();

	User listOne(String name);

	boolean functionCheckUserName(String name);

	boolean functionCheckPassword(String name);

	boolean functionIsUserActive(String name);

	int functionGetUserId(String name);

	boolean functionLogin(User user) throws ValidationException;

	int functionGetRoleId(String user) throws ValidationException;

	String functionGetUserName(int id) throws ValidationException;

	String functionGetUserEmail(int id) throws ValidationException;

	String getHashedPassword(String userName) throws ValidationException;

}