package com.zy17.controller.jsonview;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-18 Time: 下午1:49 To
 * change this template use File | Settings | File Templates.
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

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        super.writeInternal(object, outputMessage);
    }


    private ObjectMapper getMapperForView(Class<?> view) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION,
                false);
        mapper.setSerializationConfig(mapper.getSerializationConfig().withView(
                view));
        return mapper;
    }

}
