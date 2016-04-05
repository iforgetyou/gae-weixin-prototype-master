package com.zy17.controller.jsonview;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

/**
 *
 */
public class ViewAwareJsonMessageConverter extends
        MappingJacksonHttpMessageConverter {

    public ViewAwareJsonMessageConverter() {
        super();
        ObjectMapper defaultMapper = new ObjectMapper();
        defaultMapper.configure(
                SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, true);
        defaultMapper
                .setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        setObjectMapper(defaultMapper);
    }
}
