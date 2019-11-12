package com.example.form;

/**
 * 記事を投稿時に使用するフォーム.
 * 
 * @author yukiando
 *
 */
public class InsertArticleForm {

	/**	ID */
	private String id;
	
	/**	名前 */
	private String name;
	
	/**	コンテント */
	private String content;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	@Override
	public String toString() {
		return "InsertArticleForm [id=" + id + ", name=" + name + ", content=" + content + "]";
	}
}
