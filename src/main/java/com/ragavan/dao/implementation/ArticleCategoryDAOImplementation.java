package com.ragavan.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ragavan.dao.ArticleCategoryDAO;
import com.ragavan.model.Article;
import com.ragavan.model.ArticleCategory;
import com.ragavan.model.Category;
@Repository
public class ArticleCategoryDAOImplementation implements ArticleCategoryDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.ragavan.dao.ArticleCategoryDAOInterface#save(com.ragavan.model.ArticleCategory)
	 */
	@Override
	public int save(ArticleCategory articleCategory) {
		String sql = "insert into Article_Category(article_id,category_id)values (?,?)";
		Object[] params = { articleCategory.getArticleId().getId(), articleCategory.getCategoryId().getId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.ArticleCategoryDAOInterface#update(com.ragavan.model.ArticleCategory)
	 */
	@Override
	public int update(ArticleCategory articleCategory) {
		String sql = "update Article_Category set article_id=?,category_id=? where id=?";
		Object[] params = { articleCategory.getArticleId().getId(), articleCategory.getCategoryId().getId() };
		return jdbcTemplate.update(sql, params);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.ArticleCategoryDAOInterface#delete(int)
	 */
	@Override
	public int delete(int id) {
		String sql = "delete from Article_Category where article_id=?";
		return jdbcTemplate.update(sql, id);
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.ArticleCategoryDAOInterface#list()
	 */
	@Override
	public List<ArticleCategory> list() {
		final String sql = "select id,article_id,category_id from Article_Category";
		return jdbcTemplate.query(sql, (rs, rowNum) -> fetchData(rs));
	}

	/* (non-Javadoc)
	 * @see com.ragavan.dao.ArticleCategoryDAOInterface#listByCategory(int)
	 */
	@Override
	public List<ArticleCategory> listByCategory(int id) {
		final String sql = "select id,article_id,category_id from Article_Category where category_id=?";
		Object[] params = { id };
		return jdbcTemplate.query(sql, params, (rs, rowNum) -> fetchData(rs));
	}

	private ArticleCategory fetchData(ResultSet rs) throws SQLException {
		final ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setId(rs.getInt("id"));
		Article article = new Article();
		article.setId(rs.getInt("article_id"));
		articleCategory.setArticleId(article);
		Category category = new Category();
		category.setId(rs.getInt("category_id"));
		articleCategory.setCategoryId(category);
		return articleCategory;
	}
}
