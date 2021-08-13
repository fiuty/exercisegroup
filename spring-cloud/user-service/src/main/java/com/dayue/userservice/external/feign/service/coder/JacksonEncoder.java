package com.dayue.userservice.external.feign.service.coder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.RequestTemplate;
import feign.Util;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.Collections;


/**
 * 自定义原生Feign encode编码组件，发起http请求自定义编码
 * @author zhengdayue
 */
@Configuration
public class JacksonEncoder implements Encoder {

    private final ObjectMapper mapper;

    public JacksonEncoder() {
        this(Collections.<Module>emptyList());
    }

    public JacksonEncoder(Iterable<Module> modules) {
        this(new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.INDENT_OUTPUT, true)
                .registerModules(modules));
    }

    public JacksonEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructType(bodyType);
            template.body(mapper.writerFor(javaType).writeValueAsBytes(object), Util.UTF_8);
        } catch (JsonProcessingException e) {
            throw new EncodeException(e.getMessage(), e);
        }
    }
}
