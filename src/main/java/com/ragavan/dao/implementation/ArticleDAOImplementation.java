package com.ragavan.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ragavan.dao.ArticleDAO;
import com.ragavan.dao.CategoryDAO;
import com.ragavan.dao.UserDAO;
import com.ragavan.model.Article;
import com.ragavan.model.User;
@Repository
public class ArticleDAOImplementation implements ArticleDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#save(com.ragavan.model.Article)
	 */
	@Override
	public int save(Article article) {
		String sql = "insert into articles(user_id,title,content)values (?,?,?)";
		Object[] params = { article.getUserId().getId(), article.getTitle(), article.getContent() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#update(com.ragavan.model.Article, java.lang.String)
	 */
	@Override
	public int update(Article article, String title) {
		String sql = "update articles set title=?,content=? where user_id=? and title=?";
		Object[] params = { article.getTitle(), article.getContent(), article.getUserId().getId(), title };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#updateById(com.ragavan.model.Article)
	 */
	@Override
	public int updateById(Article article) {
		String sql = "update articles set title=?,content=?,modified_date=? where id=?";
		Object[] params = { article.getTitle(), article.getContent(), LocalDateTime.now(), article.getId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#getUserNameByArticleId(int)
	 */
	@Override
	public String getUserNameByArticleId(int articleId) {
		String sql = "SELECT username FROM users JOIN articles ON users.`ID`=user_id WHERE articles.`ID`=?";
		Object[] params = { articleId };
		return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
			return rs.getString("username");
		});
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#delete(int)
	 */
	@Override
	public int delete(int id) {
		String sql = "delete from articles where id=?";
		return jdbcTemplate.update(sql, id);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#list()
	 */
	@Override
	public List<Article> list() {
		final String sql = "select articles.id,users.username,title,content,published_date,modified_date,status from articles join users on users.id=articles.user_id";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final Article article = new Article();
			article.setId(rs.getInt("id"));
			User user = new User();
			user.setUserName(rs.getString("username"));
			article.setUserId(user);
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setPublishedDate(rs.getTimestamp("published_date").toLocalDateTime());
			article.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
			article.setStatus(rs.getInt("status"));
			return article;
		});
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#listByUser(int)
	 */
	@Override
	public List<Article> listByUser(int userId) {
		final String sql = "select articles.id,users.username,title,content,published_date,modified_date,status from articles join users on users.id=articles.user_id where user_id=?";
		Object[] p = { userId };
		return jdbcTemplate.query(sql, p, (rs, rowNum) -> {
			final Article article = new Article();
			article.setId(rs.getInt("id"));
			User user = new User();
			user.setUserName(rs.getString("username"));
			article.setUserId(user);
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setPublishedDate(rs.getTimestamp("published_date").toLocalDateTime());
			article.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
			article.setStatus(rs.getInt("status"));
			return article;
		});
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#listOtherUser(int)
	 */
	@Override
	public List<Article> listOtherUser(int userId) {
		final String sql = "select id,user_id,title,content,published_date,modified_date,status from articles where user_id!=?";
		Object[] p = { userId };
		return jdbcTemplate.query(sql, p, (rs, rowNum) -> fetchData(rs));
	}

	private Article fetchData(ResultSet rs) throws SQLException {
		final Article article = new Article();
		article.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		article.setUserId(user);
		article.setTitle(rs.getString("title"));
		article.setContent(rs.getString("content"));
		article.setPublishedDate(rs.getTimestamp("published_date").toLocalDateTime());
		article.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
		article.setStatus(rs.getInt("status"));
		return article;
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#functionGetArticleId(java.lang.String, int)
	 */
	@Override
	public int functionGetArticleId(String name, int userIdVar) {
		String sql = "select fn_get_article_id(?,?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name, userIdVar }, Integer.class);
	}
	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#functionGetEmailIdByArticleId(int)
	 */
	@Override
	public String functionGetEmailIdByArticleId(int articleIdVar) {
		String sql = "select fn_get_email_by_article(?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { articleIdVar }, String.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#publishArticle(com.ragavan.model.Article, com.ragavan.model.User)
	 */
	@Override
	public boolean publishArticle(Article article, User user) {
		boolean success = true;
		UserDAO userDao = new UserDAOImplementation();
		int articleId = functionGetArticleId(article.getTitle(), userDao.functionGetUserId(user.getUserName()));
		if (articleId != 0) {
			success = false;
		} else {
			save(article);
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#updateArticle(com.ragavan.model.Article, com.ragavan.model.User, java.lang.String)
	 */
	@Override
	public boolean updateArticle(Article article, User user, String title) {
		boolean success = true;
		UserDAO userDao = new UserDAOImplementation();
		int articleId = functionGetArticleId(title, userDao.functionGetUserId(user.getUserName()));
		if (articleId == 0) {
			success = false;
		} else {
			update(article, title);
		}
		return success;
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.ArticleDAOInterface#deleteArticle(com.ragavan.model.Article)
	 */
	@Override
	public boolean deleteArticle(Article article) {
		boolean success = true;
		CategoryDAO categoryDAO = new CategoryDAOImplementation();
		int articleId = article.getId();
		if (articleId == 0) {
			success = false;
		} else {
			delete(articleId);
			categoryDAO.delete(articleId);
		}
		return success;
	}

}
