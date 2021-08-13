package com.dayue.userservice.external.feign.service.coder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.TimeZone;


/**
 * 自定义原生Feign decode解码组件，响应http请求自定义解码
 * @author zhengdayue
 */
@Configuration
public class JacksonDecoder implements Decoder {

    private final ObjectMapper mapper;

    public JacksonDecoder() {
        this(Collections.<Module>emptyList());
    }

    public JacksonDecoder(Iterable<Module> modules) {
        this(new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModules(modules));
    }

    public JacksonDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
        this.mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        this.mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        this.mapper.registerModule(javaTimeModule);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (response.body() == null)
            return null;
        Reader reader = response.body().asReader(Util.UTF_8);
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader, 1);
        }
        try {
            // Read the first byte to see if we have any data
            reader.mark(1);
            if (reader.read() == -1) {
                return null; // Eagerly returning null avoids "No content to map due to end-of-input"
            }
            reader.reset();
            return mapper.readValue(reader, mapper.constructType(type));
        } catch (RuntimeJsonMappingException e) {
            if (e.getCause() != null && e.getCause() instanceof IOException) {
                throw IOException.class.cast(e.getCause());
            }
            throw e;
        }
    }
}
