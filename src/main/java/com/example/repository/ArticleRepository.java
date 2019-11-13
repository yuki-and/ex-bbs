package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

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
	
	public static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR= (rs) -> {
		List<Article> articleList = new ArrayList<>();
		Article article = null;
		List<Comment> commentList = null;
		int beforeArticleId = 0;
		while(rs.next()) {
			if(rs.getInt("id") != beforeArticleId) {
				article = new Article();
				article.setId(rs.getInt("id"));
				article.setName(rs.getString("name"));
				article.setContent(rs.getString("content"));
				commentList = new ArrayList<Comment>();
				article.setCommentList(commentList);
				articleList.add(article);
			} 
			
			if (rs.getInt("com_id") != 0)  {
				Comment comment = new Comment();
				comment.setId(rs.getInt("com_id"));
				comment.setName(rs.getString("com_name"));
				comment.setContent(rs.getString("com_content"));
				commentList.add(comment);
			}
			beforeArticleId = article.getId();
		}
		return articleList;
	};
	
	/**
	 * 記事情報をIDの降順で全件取得する.
	 * 
	 * @return　記事情報リスト
	 */
	public List<Article> findAll(){
		String sql = "SELECT a.id, a.name, a.content, com.id com_id, com.name com_name, com.content com_content,com.article_id "
				+ "FROM articles a LEFT JOIN comments com ON a.id = com.article_id ORDER BY a.id DESC, com.id;";
		return template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);
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
	
	/**
	 * IDから記事情報を削除する.
	 * 
	 * @param id　ID
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}
