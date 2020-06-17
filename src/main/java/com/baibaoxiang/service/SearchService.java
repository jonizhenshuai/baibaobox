package com.baibaoxiang.service;
import com.baibaoxiang.po.Article;
import org.apache.solr.client.solrj.SolrServerException;
import java.io.IOException;
import java.util.List;

/**
 * @author chenlin
 */
public interface SearchService {
    /**
     * 查询
     * @param query
     * @throws SolrServerException
     * @throws IOException
     */
    List<Article> searchIndex(String query) throws Exception;

    /**
     * 删除单个索引
     * @param id
     * @throws Exception
     */
    void deleteIndex(String id) throws Exception;

    /**
     * 删除所有索引
     * @throws Exception
     */
    void deleteAllIndex() throws Exception;

    /**
     * 导入所有索引
     * @throws Exception
     */
    void importAllIndex() throws Exception;

    /**
     * 添加索引
     * @throws Exception
     */
    void addIndex(Article article) throws Exception;
}
