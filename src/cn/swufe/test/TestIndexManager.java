package cn.swufe.test;

import cn.swufe.dao.ArticleDao;
import cn.swufe.dao.impl.ArticleDaoImpl;
import cn.swufe.domain.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestIndexManager {
    /**
     * 创建索引库
     */
    public static void main(String[] args) throws IOException {
        //1. 采集数据
        ArticleDao articleDao = new ArticleDaoImpl();

        List<Article> articleList = articleDao.queryArticleList();

        //文档集合
        List<Document> docList = new ArrayList<>();

        for (Article article : articleList) {
            //2. 创建文档对象
            Document document = new Document();
            //创建域对象并且放入文档对象中
            /**
             * 是否分词: 否, 因为主键分词后无意义
             * 是否索引: 是, 如果根据id主键查询, 就必须索引
             * 是否存储: 是, 因为主键id比较特殊, 可以确定唯一的一条数据, 在业务上一般有重要所用, 所以存储
             *      存储后, 才可以获取到id具体的内容
             */
            document.add(new StringField("AID", article.getAID().toString(), Field.Store.YES));

            /**
             * 是否分词: 是, 因为标题字段需要查询, 并且分词后有意义所以需要分词
             * 是否索引: 是, 因为需要根据标题字段查询
             * 是否存储: 是, 因为页面需要展示文章标题, 所以需要存储
             */
            document.add(new TextField("title", article.getTitle(), Field.Store.YES));

            /**
             * 是否分词: 是, 因为正文字段需要查询, 并且分词后有意义所以需要分词
             * 是否索引: 是, 因为需要根据正文字段查询
             * 是否存储: 是, 因为页面需要展示正文一部分, 所以需要存储
             */
            document.add(new TextField("text", article.getText(), Field.Store.YES));

        }

        //3. 创建分词器, StandardAnalyzer标准分词器, 对英文分词效果好, 对中文是单字分词, 也就是一个字就认为是一个词.
        Analyzer analyzer = new StandardAnalyzer();
        //4. 创建Directory目录对象, 目录对象表示索引库的位置
        Directory dir = FSDirectory.open(Paths.get("D:\\dir"));
        //5. 创建IndexWriterConfig对象, 这个对象中指定切分词使用的分词器
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //6. 创建IndexWriter输出流对象, 指定输出的位置和使用的config初始化对象
        IndexWriter indexWriter = new IndexWriter(dir, config);
        //7. 写入文档到索引库
        for (Document doc : docList) {
            indexWriter.addDocument(doc);
        }
        //8. 释放资源
        indexWriter.close();
    }

}
