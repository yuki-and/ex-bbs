package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.InsertArticleForm;
import com.example.form.InsertCommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

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
	
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public InsertArticleForm insertArticleSetUpForm() {
		return new InsertArticleForm();
	}
	
	@ModelAttribute
	public InsertCommentForm insertCommentSetUpForm() {
		return new InsertCommentForm();
	}
	
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
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/article";
	}
	
	/**
	 * コメントを投稿する.
	 * 
	 * @param form　コメント投稿時に使用するフォーム
	 * @return　記事一覧画面
	 */
	@RequestMapping("/insertComment")
	public String insertComment(InsertCommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(Integer.parseInt(form.getArticleId()));
		commentRepository.insert(comment);
		return "redirect:/article";
	}
	
	/**
	 * 投稿とコメントを削除する.
	 * 
	 * @param articleId ID
	 * @return 記事一覧画面
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(Integer articleId) {
		commentRepository.deleteByArticleId(articleId);
		articleRepository.deleteById(articleId);
		return "redirect:/article";
	}
}
