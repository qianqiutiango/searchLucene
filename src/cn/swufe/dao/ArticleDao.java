package cn.swufe.dao;

import cn.swufe.domain.Article;

import java.util.List;

public interface ArticleDao {
    /**
     * ��ѯ���е�����
     * @return
     */
    public List<Article> queryArticleList();
}
