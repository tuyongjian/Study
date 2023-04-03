package com.tu.study.serviceimpl;

import com.tu.study.dto.EsStudentDto;
import com.tu.study.dto.Medcl;
import com.tu.study.esmapper.EsStudentMapper;
import com.tu.study.esmapper.EsUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用 类似于jpa的模型或者是使用 restTemplate的模式进行操作
 * @author tuyongjian
 * @date 2023/3/13 10:21
 */
@Service
@Slf4j
public class EsSearchService {

    @Resource
    private EsStudentMapper esStudentMapper;

    @Resource
    private EsUserMapper esUserMapper;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public EsStudentDto addStudent(EsStudentDto esStudentDto){
        return esStudentMapper.save(esStudentDto);
    }

    public List<EsStudentDto> getAllStudent(){
        Iterable<EsStudentDto> all = esStudentMapper.findAll();
        List<EsStudentDto> list = new ArrayList<>();
        all.forEach(c->{
            list.add(c);
        });
        return list;
    }

    public EsStudentDto findStudent(String sName){
        return esStudentMapper.findBysName(sName);
    }

    public List<EsStudentDto> fuzzyStudent(String sName){
        /*fuzziness：表示输入的关键字通过几次操作可以转变成为ES库里面的对应field的字段
        操作是指：新增一个字符，删除一个字符，修改一个字符，每次操作可以记做编辑距离为1，
        如中文集团到中威集团编辑距离就是1，只需要修改一个字符；
        该参数默认值为0，即不开启模糊查询，一样的，
        如果fuzziness值在这里设置成2，会把编辑距离为2的东东集团也抓出来。

        prefix_length：表示限制输入关键字和ES对应查询field的内容开头的第n个字符必须完全匹配，不允许错别字匹配
        如这里等于1，则表示开头的中字必须匹配，不匹配则不返回
                默认值也是0
        加大prefix_length的值可以提高效率和准确率。*/
        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("sName",sName).prefixLength(0).fuzziness(Fuzziness.ZERO);
        Iterable<EsStudentDto> search = esStudentMapper.search(queryBuilder);
        List<EsStudentDto> list = new ArrayList<>();
        search.forEach((c)-> list.add(c));
        return list;
    }


    /**
     * and 搜索 二个条件都要满足
     */
    public List<EsStudentDto> searchStudent(EsStudentDto esStudentDto){
        BoolQueryBuilder queryBuilders = QueryBuilders.boolQuery();
        queryBuilders.must(QueryBuilders.matchPhraseQuery("sName", esStudentDto.getSName()));
        queryBuilders.must(QueryBuilders.matchPhraseQuery("sAddress", esStudentDto.getSAddress()));
        Iterable<EsStudentDto> search = esStudentMapper.search(queryBuilders);
        List<EsStudentDto> list = new ArrayList<>();
        search.forEach((c)-> list.add(c));
        return list;
    }

    /**
     * 不用全部满足 or的关系
     */
    public List<EsStudentDto> restTemplateStudent(EsStudentDto esStudentDto){
        BoolQueryBuilder esQuery=QueryBuilders.boolQuery()
                .should(QueryBuilders.matchPhraseQuery("sName", esStudentDto.getSName()))
                .should(QueryBuilders.matchPhraseQuery("sAddress", esStudentDto.getSAddress()));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                //查询条件
                .withQuery(esQuery)
                //分页
                .withPageable(PageRequest.of(0, 10))
                .build();
        SearchHits<EsStudentDto> search = elasticsearchRestTemplate.search(nativeSearchQuery, EsStudentDto.class);
        List<EsStudentDto> list = new ArrayList<>();
        search.get().forEach(c->{
            forEachAdd(list,c.getContent());
        });
        return list;
    }

    /**
     * 搜索词 高亮展示
     */
    public List<EsStudentDto> highlightBuilder(EsStudentDto esStudentDto){
        BoolQueryBuilder esQuery=QueryBuilders.boolQuery()
                .should(QueryBuilders.termQuery("sName.keyword", esStudentDto.getSName()));
        //高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("sName");
        //多个单词高亮的话，要把这个设置为trues
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                //查询条件
                .withQuery(esQuery)
                .withHighlightBuilder(highlightBuilder)
                //分页
                .withPageable(PageRequest.of(0, 10))
                .build();
        SearchHits<EsStudentDto> search = elasticsearchRestTemplate.search(nativeSearchQuery, EsStudentDto.class);
        List<EsStudentDto> list = new ArrayList<>();
        for (SearchHit r : search.getSearchHits()) {
            //原始数据
            EsStudentDto content = (EsStudentDto)r.getContent();
            //高亮数据
            Map<String, List<String>> highlightFields = r.getHighlightFields();
            //处理name字段
            List<String> names = highlightFields.get("sName");
            if (names != null && names.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (String s : names) {
                    buffer.append(s);
                }
                content.setSName(buffer.toString());
            }
            list.add(content);
        }
        return list;
    }


    public Medcl addUser(Medcl medcl){
       return elasticsearchRestTemplate.save(medcl);
    }

    public List<Medcl> searchPinYin(String keyword){
        String field = "name.pinyin";
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery(field, keyword))
                .build();
        Iterable<Medcl> search = esUserMapper.search(searchQuery);
        List<Medcl> list = new ArrayList<>();
        search.forEach((c)-> list.add(c));
        return list;
    }

    private void forEachAdd(List<EsStudentDto> list,EsStudentDto c){
        list.add(c);
    }
}
