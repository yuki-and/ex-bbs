package com.example.domain;

/**
 * Articlesテーブル表すドメイン.
 * 
 * @author yukiando
 *
 */
public class Article {

	/**	ID */
	private Integer id;
	
	/**	名前 */
	private String name;
	
	/**	コンテント */
	private String content;
	
	/**	投稿ID */
	private Integer articleId;
	
	public Article() {
	}

	public Article(Integer id, String name, String content, Integer articleId) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.articleId = articleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}
}
