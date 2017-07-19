package com.ragavan.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ragavan.dao.ArticleCategoryDAO;
import com.ragavan.dao.ArticleDAO;
import com.ragavan.dao.CategoryDAO;
import com.ragavan.model.Article;
import com.ragavan.model.ArticleCategory;
import com.ragavan.model.Category;
import com.ragavan.model.User;
@Repository
public class CategoryDAOImplementation implements CategoryDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#save(com.ragavan.model.Category)
	 */
	@Override
	public int save(Category category) {
		String sql = "insert into category(name,user_id)values (?,?)";
		Object[] params = { category.getName(), category.getUserId().getId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#update(com.ragavan.model.Category)
	 */
	@Override
	public int update(Category category) {
		String sql = "update category set name=? where id=?";
		Object[] params = { category.getName(), category.getId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#delete(int)
	 */
	@Override
	public int delete(int id) {
		String sql = "delete from category where id=?";
		return jdbcTemplate.update(sql, id);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#list()
	 */
	@Override
	public List<Category> list() {
		final String sql = "select id,name,user_id from category";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#listCategory()
	 */
	@Override
	public List<Category> listCategory() {
		final String sql = "select distinct name from category";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			final Category category = new Category();
			category.setName(rs.getString("name"));
			return category;
		});
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#listByUserId(int)
	 */
	@Override
	public List<Category> listByUserId(int userId) {
		final String sql = "select id,name from category where user_id=?";
		Object[] params = { userId };
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
			final Category category = new Category();
			category.setId(rs.getInt("id"));
			category.setName(rs.getString("name"));
			return category;
		});
	}

	private Category fetchData(ResultSet rs) throws SQLException {
		final Category category = new Category();
		category.setId(rs.getInt("id"));
		User user = new User();
		user.setId(rs.getInt("user_id"));
		category.setName(rs.getString("name"));
		category.setUserId(user);
		return category;
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#functionGetCategoryId(java.lang.String, int)
	 */
	@Override
	public int functionGetCategoryId(String name, int userIdVar) {
		String sql = "select fn_get_category_id(?,?)";
		return jdbcTemplate.queryForObject(sql, new Object[] { name, userIdVar }, Integer.class);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#insertCategory(com.ragavan.model.Article, com.ragavan.model.Category)
	 */
	@Override
	public void insertCategory(Article article, Category category) throws DuplicateKeyException {
		ArticleDAO articleDAO = new ArticleDAOImplementation();
		int articleId = articleDAO.functionGetArticleId(article.getTitle(), article.getUserId().getId());
		System.out.println(articleId+"title "+article.getTitle()+"user Id "+article.getUserId().getId());
		save(category);
		int categoryId = functionGetCategoryId(category.getName(), article.getUserId().getId());
		ArticleCategory articleCategory = new ArticleCategory();
		article.setId(articleId);
		category.setId(categoryId);
		articleCategory.setArticleId(article);
		articleCategory.setCategoryId(category);
		ArticleCategoryDAO articleCategoryDAO = new ArticleCategoryDAOImplementation();
		articleCategoryDAO.save(articleCategory);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.implementation.CategoryDAOInterface#viewByCategory(java.lang.String)
	 */
	@Override
	public List<Article> viewByCategory(String nam) {
		List<Article> listArticle = null;
			String sql = "SELECT title,content,username,published_date,category.`NAME` FROM articles JOIN users ON users.id=articles.`USER_ID` JOIN category ON category.`USER_ID`=users.id JOIN article_category ON article_category.article_id=articles.`ID` WHERE category.`NAME`=?";
			Object[] params = { nam };
			listArticle=jdbcTemplate.query(sql, params, (rs, rowNum) -> {
				Article article = new Article();
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setPublishedDate(rs.getTimestamp("published_date").toLocalDateTime());
				User user=new User();
				user.setUserName(rs.getString("username"));
				article.setUserId(user);
				return article;
			});
		return listArticle;
	}

}
