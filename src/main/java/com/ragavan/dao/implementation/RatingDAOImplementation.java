package com.ragavan.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ragavan.dao.RatingDAO;
import com.ragavan.model.Article;
import com.ragavan.model.Rating;
import com.ragavan.model.User;
@Repository
public class RatingDAOImplementation implements RatingDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RatingDAOInterface#save(com.ragavan.model.Rating)
	 */
	@Override
	public int save(Rating rating) {
		String sql = "insert into rating(article_id,user_id,rating)values (?,?,?)";
		Object[] params = { rating.getArticleId(), rating.getUserId(), rating.getRating() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RatingDAOInterface#update(com.ragavan.model.Rating)
	 */
	@Override
	public int update(Rating rating) {
		String sql = "update Rating set rating=?,like=? where user_id=? and article_id=?,";
		Object[] params = { rating.getRating(), rating.getLike(), rating.getUserId(), rating.getArticleId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RatingDAOInterface#delete(int)
	 */
	@Override
	public int delete(int id) {
		String sql = "delete from rating where id=?";
		return jdbcTemplate.update(sql, id);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.RatingDAOInterface#list()
	 */
	@Override
	public List<Rating> list() {
		final String sql = "select id,article,user_id,rating,like from rating";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	private Rating fetchData(ResultSet rs) throws SQLException {
		final Rating rating = new Rating();
		rating.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		rating.setArticleId(article);
		rating.setUserId(user);
		rating.setRating(rs.getInt("rating"));
		rating.setLike(rs.getInt("like"));
		return rating;
	}
}
