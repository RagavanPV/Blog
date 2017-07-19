package com.ragavan.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ragavan.dao.UserDAO;
import com.ragavan.exception.ValidationException;
import com.ragavan.model.Role;
import com.ragavan.model.User;
@Repository
public class UserDAOImplementation implements UserDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#save(com.ragavan.model.User)
	 */
	@Override
	public int save(User user) {
		String sql = "insert into users(username,password,email_id,activation_code)values (?,?,?,?)";
		Object[] params = { user.getUserName(), user.getPassword(), user.getEmailId() ,user.getActivationCode()};
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#update(com.ragavan.model.User)
	 */
	@Override
	public int update(User user) {
		String sql = "update users set username=?,password=?,email_id=?,role_id=? where id=?";
		Object[] params = { user.getUserName(), user.getPassword(), user.getEmailId(), user.getRoleId().getId(),
				user.getId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#updateUsername(com.ragavan.model.User)
	 */
	@Override
	public int updateUsername(User user) {
		String sql = "update users set username=? where email_id=?";
		Object[] params = { user.getUserName(), user.getEmailId() };
		return jdbcTemplate.update(sql, params);
	}
	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#activateUser(com.ragavan.model.User)
	 */
	@Override
	public int activateUser(User user) {
		String sql = "update users set activation=1 where userName=? and activation_code=?";
		Object[] params = { user.getUserName(), user.getActivationCode() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#updatePassword(com.ragavan.model.User)
	 */
	@Override
	public int updatePassword(User user) {
		String sql = "update users set password=? where email_id=?";
		Object[] params = { user.getPassword(), user.getEmailId() };
		return jdbcTemplate.update(sql, params);
	}
	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#updateRole(com.ragavan.model.User)
	 */
	@Override
	public int updateRole(User user) {
		String sql = "update users set role_id=? where id=?";
		Object[] params = { user.getRoleId().getId(), user.getId() };
		System.out.println(user.getId());
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#delete(int)
	 */
	@Override
	public int delete(int id) {
		String sql = "delete from users where id=?";
		return jdbcTemplate.update(sql, id);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#list()
	 */
	@Override
	public List<User> list() {
		final String sql = "select users.id,username,password,email_id,role_name from users join role on role.id=users.role_id";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final User user = new User();
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEmailId(rs.getString("email_id"));
			Role role = new Role();
			role.setRoleName(rs.getString("role_name"));
			user.setRoleId(role);
			return user;
		});
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#listOne(java.lang.String)
	 */
	@Override
	public User listOne(String name) {
		final String sql = "select id,username,password,email_id,role_id from users where username=?";
		Object[] params = { name };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> fetchData(rs));
	}

	private User fetchData(ResultSet rs) throws SQLException {
		final User user = new User();
		user.setId(rs.getInt("id"));
		user.setUserName(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmailId(rs.getString("email_id"));
		Role role = new Role();
		role.setId(rs.getInt("role_id"));
		user.setRoleId(role);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionCheckUserName(java.lang.String)
	 */
	@Override
	public boolean functionCheckUserName(String name) {
		String sql = "select fn_is_valid_username(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Boolean.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionCheckPassword(java.lang.String)
	 */
	@Override
	public boolean functionCheckPassword(String name) {
		String sql = "select fn_is_valid_password(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Boolean.class);
	}
	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionIsUserActive(java.lang.String)
	 */
	@Override
	public boolean functionIsUserActive(String name) {
		String sql = "select fn_is_user_active(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Boolean.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionGetUserId(java.lang.String)
	 */
	@Override
	public int functionGetUserId(String name) {
		String sql = "select fn_get_user_id(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionLogin(com.ragavan.model.User)
	 */
	@Override
	public boolean functionLogin(User user) throws ValidationException {
		String sql = "select fn_is_valid_login(?,?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { user.getUserName(), user.getPassword() }, Boolean.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionGetRoleId(java.lang.String)
	 */
	@Override
	public int functionGetRoleId(String user) throws ValidationException {
		String sql = "select fn_get_role_id(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { user }, Integer.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionGetUserName(int)
	 */
	@Override
	public String functionGetUserName(int id) throws ValidationException {
		String sql = "select fn_get_user_name(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#functionGetUserEmail(int)
	 */
	@Override
	public String functionGetUserEmail(int id) throws ValidationException {
		String sql = "select email_id from users where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}
	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.UserDAOInterface#getHashedPassword(java.lang.String)
	 */
	@Override
	public String getHashedPassword(String userName) throws ValidationException {
		String sql = "select password from users where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { userName }, String.class);
	}

}
