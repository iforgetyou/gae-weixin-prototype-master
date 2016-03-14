package com.zy17.service.googleservice;

import org.springframework.stereotype.Component;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.PutException;
import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.StatusCode;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhangyan53 on 2016/3/11.
 */
@Slf4j
@Component
public class DocIndexService {
    public static final String TAGS_DOC = "tags";

    public void indexADocument(String indexName, Document document) {
        IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build();
        Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);

        try {
            index.put(document);
        } catch (PutException e) {
            log.error("index error", e);
            if (StatusCode.TRANSIENT_ERROR.equals(e.getOperationResult().getCode())) {
                // retry putting the document
            }
        }
    }

    public ScoredDocument search(String indexName, String queryString) {
        IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build();
        Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);
        // Build the QueryOptions
        QueryOptions options = QueryOptions.newBuilder()
                .setLimit(1)
                .build();
        Query query = Query.newBuilder().setOptions(options).build(queryString);
        Results<ScoredDocument> results = index.search(query);
        for (ScoredDocument result : results) {
            return result;
        }
        return null;
    }
}
