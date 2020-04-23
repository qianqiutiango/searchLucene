package cn.swufe.test;
import cn.swufe.Util.dbconnection;
import cn.swufe.dao.ArticleDao;
import cn.swufe.dao.impl.ArticleDaoImpl;
import cn.swufe.domain.Article;

import java.sql.Connection;
import java.util.List;

public class TestDB {

    public static void main(String[] args) {
        dbconnection db = new dbconnection();
        Connection connection = db.getConnection();
        db.closeConnection(connection);
        ArticleDao articleDao = new ArticleDaoImpl();
        List<Article> articles = articleDao.queryArticleList();
        for (Article article : articles) {
            System.out.println(article.getAID());
            System.out.println(article.getText());
            System.out.println(article.getTitle());
        }

    }
}