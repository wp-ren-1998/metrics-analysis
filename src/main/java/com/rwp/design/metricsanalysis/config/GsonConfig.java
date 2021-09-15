package com.rwp.design.metricsanalysis.config;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: metrics-analysis
 * @description: gson
 * @author: wp.ren
 * @create: 2021-09-15 14:01
 **/
@Configuration
public class GsonConfig {
    //serializer
    final static JsonSerializer<LocalDateTime> jsonSerializerDateTime = (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    final static JsonSerializer<LocalDate> jsonSerializerDate = (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
    //deserializer
    final static JsonDeserializer<LocalDateTime> jsonDeserializerDateTime = (jsonElement, type, jsonDeserializationContext) -> LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    final static JsonDeserializer<LocalDate> jsonDeserializerDate = (jsonElement, type, jsonDeserializationContext) -> LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);

    @Bean
    public Gson getGson(){
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, jsonSerializerDateTime)
                .registerTypeAdapter(LocalDate.class, jsonSerializerDate)
                .registerTypeAdapter(LocalDateTime.class, jsonDeserializerDateTime)
                .registerTypeAdapter(LocalDate.class, jsonDeserializerDate)
                .create();
    }
}
