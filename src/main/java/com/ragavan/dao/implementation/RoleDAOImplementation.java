package com.ragavan.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ragavan.dao.RoleDAO;
import com.ragavan.model.Role;
@Repository
public class RoleDAOImplementation implements RoleDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RoleDAOInterface#save(com.ragavan.model.Role)
	 */
	@Override
	public int save(Role role) {
		String sql = "insert into Role(role_name)values (?,?)";
		Object[] params = { role.getRoleName() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RoleDAOInterface#update(com.ragavan.model.Role)
	 */
	@Override
	public int update(Role role) {
		String sql = "update Role set role_name=? where id=?";
		Object[] params = { role.getRoleName(), role.getId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RoleDAOInterface#delete(int)
	 */
	@Override
	public int delete(int id) {
		String sql = "delete from Role where id=?";
		return jdbcTemplate.update(sql, id);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RoleDAOInterface#list()
	 */
	@Override
	public List<Role> list() {
		final String sql = "select id,role_name from Role";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	private Role fetchData(ResultSet rs) throws SQLException {
		final Role role = new Role();
		role.setId(rs.getInt("id"));
		role.setRoleName(rs.getString("role_name"));
		return role;
	}
}
