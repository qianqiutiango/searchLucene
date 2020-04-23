package cn.swufe.service;

import cn.swufe.domain.ResultModel;

public interface SearchService {
    public ResultModel query(String queryString, String price, Integer page) throws Exception;

}
