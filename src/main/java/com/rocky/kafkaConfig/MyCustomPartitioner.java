package com.rocky.kafkaConfig;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyCustomPartitioner implements Partitioner {

    @Override
    public void configure(Map<String, ?> configs) {
        // Configuration logic if needed
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        /// Example logic: route Key1 to partition 0, Key2 to 1, others to last
        if (key.toString().equalsIgnoreCase("Key1")) {
            return 0;
        } else if (key.toString().equalsIgnoreCase("Key2")) {
            return 1;
        } else {
            return 2; // fallback to last partition
        }
    }

    @Override
    public void close() {
        // Cleanup logic if needed
    }


}
