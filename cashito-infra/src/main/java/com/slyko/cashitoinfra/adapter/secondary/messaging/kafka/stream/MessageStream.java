package com.slyko.cashitoinfra.adapter.secondary.messaging.kafka.stream;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class MessageStream {

    @Value("${spring.kafka.streams.input-topic}")
    private String inputTopic;

    @Value("${spring.kafka.streams.output-topic}")
    private String outputTopic;

    @Bean
    public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream(inputTopic);

        stream
            .filter((key, value) -> value.contains("filter-criteria"))
            .mapValues(value -> value.toUpperCase())
            .to(outputTopic);

        return stream;
    }
}