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
     * ����������
     */
    public static void main(String[] args) throws IOException {
        //1. �ɼ�����
        ArticleDao articleDao = new ArticleDaoImpl();

        List<Article> articleList = articleDao.queryArticleList();

        //�ĵ�����
        List<Document> docList = new ArrayList<>();

        for (Article article : articleList) {
            //2. �����ĵ�����
            Document document = new Document();
            //����������ҷ����ĵ�������
            /**
             * �Ƿ�ִ�: ��, ��Ϊ�����ִʺ�������
             * �Ƿ�����: ��, �������id������ѯ, �ͱ�������
             * �Ƿ�洢: ��, ��Ϊ����id�Ƚ�����, ����ȷ��Ψһ��һ������, ��ҵ����һ������Ҫ����, ���Դ洢
             *      �洢��, �ſ��Ի�ȡ��id���������
             */
            document.add(new StringField("AID", article.getAID().toString(), Field.Store.YES));

            /**
             * �Ƿ�ִ�: ��, ��Ϊ�����ֶ���Ҫ��ѯ, ���ҷִʺ�������������Ҫ�ִ�
             * �Ƿ�����: ��, ��Ϊ��Ҫ���ݱ����ֶβ�ѯ
             * �Ƿ�洢: ��, ��Ϊҳ����Ҫչʾ���±���, ������Ҫ�洢
             */
            document.add(new TextField("title", article.getTitle(), Field.Store.YES));

            /**
             * �Ƿ�ִ�: ��, ��Ϊ�����ֶ���Ҫ��ѯ, ���ҷִʺ�������������Ҫ�ִ�
             * �Ƿ�����: ��, ��Ϊ��Ҫ���������ֶβ�ѯ
             * �Ƿ�洢: ��, ��Ϊҳ����Ҫչʾ����һ����, ������Ҫ�洢
             */
            document.add(new TextField("text", article.getText(), Field.Store.YES));

        }

        //3. �����ִ���, StandardAnalyzer��׼�ִ���, ��Ӣ�ķִ�Ч����, �������ǵ��ִַ�, Ҳ����һ���־���Ϊ��һ����.
        Analyzer analyzer = new StandardAnalyzer();
        //4. ����DirectoryĿ¼����, Ŀ¼�����ʾ�������λ��
        Directory dir = FSDirectory.open(Paths.get("D:\\dir"));
        //5. ����IndexWriterConfig����, ���������ָ���зִ�ʹ�õķִ���
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        //6. ����IndexWriter���������, ָ�������λ�ú�ʹ�õ�config��ʼ������
        IndexWriter indexWriter = new IndexWriter(dir, config);
        //7. д���ĵ���������
        for (Document doc : docList) {
            indexWriter.addDocument(doc);
        }
        //8. �ͷ���Դ
        indexWriter.close();
    }

}
