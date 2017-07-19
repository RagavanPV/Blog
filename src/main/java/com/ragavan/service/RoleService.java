package com.ragavan.service;

import java.util.List;

import com.ragavan.exception.ServiceException;
import com.ragavan.model.Role;

public interface RoleService {

	int saveService(Role role) throws ServiceException;

	int updateService(Role role) throws ServiceException;

	int deleteService(int id) throws ServiceException;

	List<Role> listService();

}