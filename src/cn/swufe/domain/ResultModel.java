package cn.swufe.domain;

import java.util.List;

/**
 * �Զ����ҳʵ����
 */
public class ResultModel {

    // �����б�
    private List<Article> articleList;
    // ��������
    private Long recordCount;
    // ��ҳ��
    private Long pageCount;
    // ��ǰҳ
    private long curPage;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public long getCurPage() {
        return curPage;
    }

    public void setCurPage(long curPage) {
        this.curPage = curPage;
    }
}
