package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * Articlesテーブルを操作するリポジトリ.
 * 
 * @author yukiando
 *
 */
@Repository
public class ArticleRepository {

	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		return article;
	};
	
	/**
	 * 記事情報をIDの降順で全件取得する.
	 * 
	 * @return　記事情報リスト
	 */
	public List<Article> findAll(){
		String sql = "SELECT id, name, content FROM articles ORDER BY id DESC";
		return template.query(sql, ARTICLE_ROW_MAPPER);
	}
	
	/**
	 * 記事情報を挿入する.
	 * 
	 * @param article　記事情報
	 */
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String sql = "INSERT INTO articles(name, content) VALUES(:name, :content)";
		template.update(sql, param);
	}
}
