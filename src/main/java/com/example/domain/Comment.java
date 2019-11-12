package com.example.domain;


/**
 * コメント情報を表すリポジトリ.
 * 
 * @author yukiando
 *
 */
public class Comment {

	
	/**	ID */
	private Integer id;
	
	/**	名前 */
	private String name;
	
	/** コンテント */
	private String content;
	
	public Comment() {
	}

	public Comment(Integer id, String name, String content) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
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

	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + "]";
	}
}
