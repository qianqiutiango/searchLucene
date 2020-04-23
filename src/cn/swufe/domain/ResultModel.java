package cn.swufe.domain;

import java.util.List;

/**
 * 自定义分页实体类
 */
public class ResultModel {

    // 文章列表
    private List<Article> articleList;
    // 文章总数
    private Long recordCount;
    // 总页数
    private Long pageCount;
    // 当前页
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
