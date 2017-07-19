package com.ragavan.dao;

import java.util.List;

import com.ragavan.model.Role;

public interface RoleDAO {

	int save(Role role);

	int update(Role role);

	int delete(int id);

	List<Role> list();

}