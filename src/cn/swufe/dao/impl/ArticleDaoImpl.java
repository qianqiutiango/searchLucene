package cn.swufe.dao.impl;

import cn.swufe.dao.ArticleDao;
import cn.swufe.domain.Article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {

    @Override

    public List<Article> queryArticleList() {

        // ���ݿ�����
        Connection connection = null;
        // Ԥ����statement
        PreparedStatement preparedStatement = null;
        // �����
        ResultSet resultSet = null;
        // ��Ʒ�б�
        List<Article> list = new ArrayList<Article>();

        try {
            // �������ݿ�����
            Class.forName("com.mysql.jdbc.Driver");
            // �������ݿ�
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene", "root", "root");

            // SQL���
            String sql = "SELECT * FROM article";
            // ����preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // ��ȡ�����
            resultSet = preparedStatement.executeQuery();
            // ���������
            while (resultSet.next()) {
                Article article = new Article();
                article.setAID(resultSet.getInt("AID"));
                article.setTitle(resultSet.getString("title"));
                article.setText(resultSet.getString("text"));

                list.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
