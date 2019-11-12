package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.InsertArticleForm;
import com.example.repository.ArticleRepository;

/**
 * 記事投稿関連機能の処理の制御を行うコントローラ.
 * 
 * @author yukiando
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 * 記事一覧を表示する.
	 * 
	 * @param model　リクエストスコープ
	 * @return　記事一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList", articleList);
		return "bbs";
	}

	/**
	 * 記事を投稿する.
	 * 
	 * @param form　記事投稿時に使用するフォーム
	 * @return　記事一覧画面
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(InsertArticleForm form) {
		Article article = new Article();
		article.setName(form.getName());
		article.setContent(form.getContent());
		articleRepository.insert(article);
		return "redirect:/article";
	}
}
