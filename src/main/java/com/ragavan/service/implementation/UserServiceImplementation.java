package com.ragavan.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.ragavan.dao.UserDAO;
import com.ragavan.exception.ServiceException;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.User;
import com.ragavan.service.UserService;
import com.ragavan.validator.UserValidator;
@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserDAO dao;
	private UserValidator userValidator=new UserValidator();

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#saveService(com.ragavan.model.User)
	 */
	@Override
	public int saveService(User user) throws ServiceException {
		try {
			userValidator.validateSave(user);
			return dao.save(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		} catch (DuplicateKeyException e) {
			throw new ServiceException("Name or email_id already exists", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#updateService(com.ragavan.model.User)
	 */
	@Override
	public int updateService(User user) throws ServiceException {
		try {
			userValidator.validateUpdate(user);
			return dao.update(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#updateUsernameService(com.ragavan.model.User)
	 */
	@Override
	public int updateUsernameService(User user) throws ServiceException {
		try {
			userValidator.validateUpdateUsername(user);
			return dao.updateUsername(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#activateUserService(com.ragavan.model.User)
	 */
	@Override
	public int activateUserService(User user) throws ServiceException {
		
			return dao.activateUser(user);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#updatePasswordService(com.ragavan.model.User)
	 */
	@Override
	public int updatePasswordService(User user) throws ServiceException {
		try {
			userValidator.validateUpdatePassword(user);
			return dao.updatePassword(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#updateRoleService(com.ragavan.model.User)
	 */
	@Override
	public int updateRoleService(User user) throws ServiceException {
			return dao.updateRole(user);
		
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#deleteService(int)
	 */
	@Override
	public int deleteService(int id) throws ServiceException {
		try {
			userValidator.validateDelete(id);
			return dao.delete(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#listService()
	 */
	@Override
	public List<User> listService() {
		return dao.list();
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#listOneService(java.lang.String)
	 */
	@Override
	public User listOneService(String name) throws ServiceException {
		try {
			userValidator.validateListOne(name);
			return dao.listOne(name);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#functionLoginService(com.ragavan.model.User)
	 */
	@Override
	public boolean functionLoginService(User user) throws ServiceException {
		try {
			userValidator.validateFunctionLogin(user);
			return dao.functionLogin(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#functionIsValidUserService(java.lang.String)
	 */
	@Override
	public boolean functionIsValidUserService(String name) throws ServiceException {
			return dao.functionIsUserActive(name);
		
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#functionGetRoleId(java.lang.String)
	 */
	@Override
	public int functionGetRoleId(String name) throws ServiceException {
		try {
			userValidator.validateGetRole(name);
			return dao.functionGetRoleId(name);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#functionGetUserId(java.lang.String)
	 */
	@Override
	public int functionGetUserId(String user) throws ServiceException {
		try {
			userValidator.validateGetUserId(user);
			return dao.functionGetUserId(user);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#functionGetUserName(int)
	 */
	@Override
	public String functionGetUserName(int id) throws ServiceException {
		try {
			return dao.functionGetUserName(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#functionGetUserEmail(int)
	 */
	@Override
	public String functionGetUserEmail(int id) throws ServiceException {
		try {
			return dao.functionGetUserEmail(id);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/* (non-Javadoc)
	 * @see com.ragavan.service.UserService#getHashedPassword(java.lang.String)
	 */
	@Override
	public String getHashedPassword(String userName) throws ServiceException {
		try {
			return dao.getHashedPassword(userName);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
}
