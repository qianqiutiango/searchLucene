package cn.swufe.service.impl;


import cn.swufe.domain.Article;
import cn.swufe.domain.ResultModel;
import cn.swufe.service.SearchService;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.MMapDirectory;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SearchServiceImpl implements SearchService {

    //ÿҳ��ѯ20������
    public final static Integer PAGE_SIZE = 20;

    @Override
    public ResultModel query(String queryString, String price, Integer page) throws Exception {
        long startTime = System.currentTimeMillis();

        //1. ��Ҫʹ�õĶ����װ
        ResultModel resultModel = new ResultModel();
        //�ӵڼ�����ʼ��ѯ
        int start = (page - 1) * PAGE_SIZE;
        //��ѯ��������Ϊֹ
        Integer end = page * PAGE_SIZE;
        //�����ִ���
        Analyzer analyzer = new StandardAnalyzer();
//        //������ϲ�ѯ����
//        BooleanQuery.Builder builder = new BooleanQuery.Builder();

        //2. ���ݲ�ѯ�ؼ��ַ�װ��ѯ����
        //text��ʾ�����ı����ݲ�ѯ
        QueryParser queryParser = new QueryParser("text", analyzer);
        Query query = null;
        //�жϴ���Ĳ�ѯ�ؼ����Ƿ�Ϊ��, ���Ϊ�ղ�ѯ����, �����Ϊ��, ����ݹؼ��ֲ�ѯ
        if (queryString.isEmpty()) {
            query = queryParser.parse("*:*");
        } else {
            query = queryParser.parse(queryString);
        }
//        //���ؼ��ֲ�ѯ����, ��װ����ϲ�ѯ������
//        builder.add(query, BooleanClause.Occur.MUST);

//        //3. ���ݼ۸�Χ��װ��ѯ����
//        if (!price.isEmpty()) {
//            String[] split = price.split("-");
//            Query query2 = IntPoint.newRangeQuery("price", Integer.parseInt(split[0]), Integer.parseInt(split[1]));
//            //���۸��ѯ����, ��װ����ϲ�ѯ������
//            builder.add(query2, BooleanClause.Occur.MUST);
//        }




        //4. ����DirectoryĿ¼����, ָ���������λ��
        /**
         * ʹ��MMapDirectory���ĵĲ�ѯʱ��

         */
        Directory directory = FSDirectory.open(Paths.get("E:\\dir"));
        //5. ��������������
        IndexReader reader = DirectoryReader.open(directory);
        //6. ������������
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //7. ��������ȡ�������
        TopDocs topDocs = indexSearcher.search(query, end);
        //8. ��ȡ��ѯ����������
        resultModel.setRecordCount(topDocs.totalHits);
        //9. ��ȡ��ѯ���Ľ����
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        long endTime = System.currentTimeMillis();
        System.out.println("====����ʱ��Ϊ=========" + (endTime - startTime) + "ms");

        //10. �����������װ���ص�����
        List<Article> articleList = new ArrayList<>();
        if (scoreDocs != null) {
            for (int i = start; i < end; i ++) {
                //ͨ����ѯ�����ĵ����, �ҵ���Ӧ���ĵ�����
                Document document = reader.document(scoreDocs[i].doc);
                //��װSku����
                Article article = new Article();
                article.setAID(Integer.parseInt(document.get("AID")));
                article.setTitle(document.get("title"));
                article.setText(document.get("text"));
                articleList.add(article);

            }
        }
        //��װ��ѯ���Ľ����
        resultModel.setArticleList(articleList);
        //��װ��ǰҳ
        resultModel.setCurPage(page);
        //��ҳ��
        Long pageCount = topDocs.totalHits % PAGE_SIZE > 0 ? (topDocs.totalHits/PAGE_SIZE) + 1 : topDocs.totalHits/PAGE_SIZE;
        resultModel.setPageCount(pageCount);
        return resultModel;
    }
}
