package com.tu.study.serviceimpl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.tu.study.dto.Medcl;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author tuyongjian
 * @date 2023/4/3 15:27
 */
@Service
@Slf4j
public class EsSearchHighLevelClientService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     */
    public void createIndex(){
        IndexRequest request = new IndexRequest("medcl");
        request.id("1");
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "tutu");
        request.source(map);
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("-------",e);
        }
        assert indexResponse != null;
        assertEquals(DocWriteResponse.Result.CREATED, indexResponse.getResult());
    }

    /**
     * 批量插入
     */
    public void bulkRequestTest(List<Medcl> list){
        BulkRequest request = new BulkRequest();
        for (int i = 0; i < list.size(); i++) {
            Medcl medcl = list.get(i);
            request.add(new IndexRequest("medcl").id(medcl.getId())
                    .source(XContentType.JSON,"id", medcl.getId(), "name", medcl.getName()));
        }
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("------",e);
        }
        assert bulkResponse != null;
        assertFalse(bulkResponse.hasFailures());
    }

    /**
     * 更新
     */
    public void update(){
        Medcl medcl = Medcl.builder().name("tutututu").build();
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(medcl);
        Map<String, Object> map = gson.fromJson(jsonElement.getAsJsonObject(), HashMap.class);
        UpdateRequest request = new UpdateRequest("medcl", "1").doc(map,XContentType.JSON);
        UpdateResponse updateResponse = null;
        try {
            updateResponse = restHighLevelClient.update(request,  RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("-----",e);
        }
        assert updateResponse != null;
        assertEquals(DocWriteResponse.Result.UPDATED, updateResponse.getResult());
    }

    /**
     * 根据主键id
     */
    public void delete(){
        DeleteRequest deleteRequest = new DeleteRequest("medcl");
        deleteRequest.id("1");
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("----",e);
        }
        assert deleteResponse != null;
        assertEquals(DocWriteResponse.Result.DELETED, deleteResponse.getResult());
    }

    /**
     * 根据某个参数删除
     */
    public void deleteByQueryRequestTest(String name) {
        DeleteByQueryRequest request = new DeleteByQueryRequest("medcl");
        request.setConflicts("proceed");
        request.setQuery(new TermQueryBuilder("name", name));
        try {
            restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("----",e);
        }
    }

    /**
     * 多种操作类型
     */
    public void bulkDiffRequestTest(){
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest("medcl", "2"));
        request.add(new UpdateRequest("medcl", "3")
                .doc(XContentType.JSON,"name", "孙权吊毛"));
        BulkResponse bulkResponse = null;
        try {
            bulkResponse = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("----",e);
        }
        assert bulkResponse != null;
        BulkItemResponse[]  bulkItemResponses = bulkResponse.getItems();
        for (BulkItemResponse item : bulkItemResponses){
            DocWriteResponse itemResponse = item.getResponse();
            switch (item.getOpType()) {
                case UPDATE:
                    UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                    log.info(updateResponse.toString());
                    break;
                case DELETE:
                    DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                    log.info(deleteResponse.toString());
                    break;
                default:
                    log.error("非法操作");
            }
            assertEquals(RestStatus.OK, item.status());
        }
    }

    public SearchHit[] selectByName(String name){
        SearchRequest request = new SearchRequest("medcl");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //精准匹配
        //builder.query(new TermQueryBuilder("name", name));
        //模糊匹配
        MatchQueryBuilder  queryBuilder = QueryBuilders.matchQuery("name", name)
                .fuzziness(Fuzziness.TWO)
                .prefixLength(0);
        builder.query(queryBuilder);
        // 相当于mysql里边的limit 10；
        builder.size(10);
        request.source(builder);
        try {
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            return response.getHits().getHits();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
