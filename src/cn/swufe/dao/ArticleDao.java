package cn.swufe.dao;

import cn.swufe.domain.Article;

import java.util.List;

public interface ArticleDao {
    /**
     * 查询所有的文章
     * @return
     */
    public List<Article> queryArticleList();
}
