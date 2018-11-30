package com.geo.source.kafka.test;

public interface KafkaConfig {

	interface Config {
		// localhost ip port
		String IPPORT = "192.168.10.102:9092";
		
		// Assign localhost id
		String SERVER = "bootstrap.servers";
		
		// Set acknowledgements for producer requests.
		String ACKS = "acks";
		
		// If the request fails, the producer can automatically retry,
		String RETRIES = "retries";
		
		// Specify buffer size in config
		String SIZE = "batch.size";
		
		// Reduce the no of requests less than 0
		String MS = "linger.ms";
		
		// The buffer.memory controls the total amount of memory available to the producer for buffering.
		String MEMORY = "buffer.memory";
		
		String GROUPID = "group.id";
		String COMMIT = "enable.auto.commit";
		String INTERVAL = "auto.commit.interval.ms";
		String TIMEOUT = "session.timeout.ms";
	}
}
