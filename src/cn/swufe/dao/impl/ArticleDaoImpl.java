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

        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;
        // 商品列表
        List<Article> list = new ArrayList<Article>();

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene", "root", "root");

            // SQL语句
            String sql = "SELECT * FROM article";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
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
