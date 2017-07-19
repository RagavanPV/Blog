package com.ragavan.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragavan.dao.RoleDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Role;
import com.ragavan.service.RoleService;
import com.ragavan.validator.RoleValidator;
@Service
public class RoleServiceImplementation implements RoleService {
	@Autowired
	RoleDAO dao;
	RoleValidator roleValidator=new RoleValidator();

	/* (non-Javadoc)
	 * @see com.ragavan.service.RoleService#saveService(com.ragavan.model.Role)
	 */
	@Override
	public int saveService(Role role) throws ServiceException {
		try {
			roleValidator.validateSave(role);
			return dao.save(role);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.RoleService#updateService(com.ragavan.model.Role)
	 */
	@Override
	public int updateService(Role role) throws ServiceException {
		try {
			roleValidator.validateUpdate(role);
			return dao.update(role);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.RoleService#deleteService(int)
	 */
	@Override
	public int deleteService(int id) throws ServiceException {
		try {
			roleValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.RoleService#listService()
	 */
	@Override
	public List<Role> listService() {
		return dao.list();
	}
}
