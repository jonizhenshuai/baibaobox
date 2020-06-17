package com.baibaoxiang.serviceimpl;
import com.baibaoxiang.po.Article;
import com.baibaoxiang.po.ArticleType;
import com.baibaoxiang.service.ArticleService;
import com.baibaoxiang.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/**
 * @author chenlin
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    ArticleService articleService;
    @Autowired
    HttpSolrClient httpSolrClient;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<Article> searchIndex(String queryString) throws Exception {
        //1.定义一个搜索对象
        SolrQuery solrQuery = new SolrQuery();
        //2.设置查询条件
        solrQuery.setQuery(queryString);
        solrQuery.set("df", "article_keywords");
        //1.查询索引库
        QueryResponse response = httpSolrClient.query(solrQuery);
        //2.获取文章列表
        SolrDocumentList results = response.getResults();
        //3.封装商品列表
        List<Article> searchArticle = new ArrayList<>();
        Article article ;
        ArticleType articleType;
        for (SolrDocument solrDocument : results){
            article = new Article();
            article.setNo(solrDocument.get("id").toString());
            article.setTitle(solrDocument.get("title").toString());
            article.setCreateTime(Date.valueOf(solrDocument.get("create_time").toString()));
            article.setLikeNum(Integer.parseInt(solrDocument.get("like_num").toString()));
            articleType = new ArticleType();
            articleType.setType(solrDocument.get("type").toString());
            article.setArticleType(articleType);
            article.setArticleType(articleType);
            searchArticle.add(article);
        }
       return searchArticle;
    }

    @Override
    public void deleteIndex(String id) throws Exception {
            httpSolrClient.deleteById(id) ;
            httpSolrClient.commit();
    }

    @Override
    public void deleteAllIndex() throws Exception {
        httpSolrClient.deleteByQuery("*:*");
        httpSolrClient.commit();
    }

    @Override
    public void importAllIndex() throws Exception {
        List<Article> articles = articleService.selectAllArticles();
        SolrInputDocument document;
        for (Article article : articles) {
            document = new SolrInputDocument();
            document.addField("title", article.getTitle());
            document.addField("id", article.getNo());
            document.addField("create_time",sdf.format(article.getCreateTime()));
            document.addField("like_num", article.getLikeNum());
            document.addField("type", article.getArticleType().getType());
            httpSolrClient.add(document);
        }
        httpSolrClient.commit();
    }

    /**
     * 添加索引方法
     * @param article
     * @throws Exception
     */
    @Override
    public void addIndex(Article article) throws Exception {
        //新建document
        SolrInputDocument document = new SolrInputDocument();
        document.addField("title", article.getTitle());
        document.addField("id", article.getNo());
        document.addField("create_time", sdf.format(article.getCreateTime()));
        document.addField("like_num", article.getLikeNum());
        document.addField("type", article.getArticleType().getType());
        httpSolrClient.add(document);
        httpSolrClient.commit();
    }
}
