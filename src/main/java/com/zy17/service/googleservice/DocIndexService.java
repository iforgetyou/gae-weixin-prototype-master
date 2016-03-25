package com.zy17.service.googleservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.appengine.api.search.Cursor;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.GetRequest;
import com.google.appengine.api.search.GetResponse;
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
 * Created by zy17 on 2016/3/11.
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
                .setLimit(3)
                .build();
        Query query = Query.newBuilder().setOptions(options).build(queryString);
        Results<ScoredDocument> results = index.search(query);
        for (ScoredDocument result : results) {
            return result;
        }
        return null;
    }

    /**
     * 擅长索引
     *
     * @param indexName
     */
    public void deleteIndex(String indexName) {
        IndexSpec indexSpec = IndexSpec.newBuilder().setName(indexName).build();
        Index index = SearchServiceFactory.getSearchService().getIndex(indexSpec);

        List<String> docIds = new ArrayList<>();

        // Return a set of doc_ids.
        GetResponse<Document> response =
                index.getRange(GetRequest.newBuilder().setReturningIdsOnly(true));
        int totalSize = response.getResults().size();
        if (response == null || totalSize <= 0) {
            return;
        }

        for (Document doc : response) {
            docIds.add(doc.getId());
        }

        // 分页批量删除
        int pageSize = 200;
        for (int i = 0; i < totalSize; ) {
            int limit;
            if (i + pageSize > totalSize) {
                limit = totalSize;
            } else {
                limit = i + pageSize;
            }
            // 范围删除
            index.delete(docIds.subList(i, limit));
            i += pageSize;
        }

    }

}
