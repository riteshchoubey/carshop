package com.azure.carshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

import redis.clients.jedis.Jedis;


@SpringBootApplication
@EnableCaching
public class CarshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarshopApplication.class, args);
		testAzureRedisConnection();
	}
	public static void testAzureRedisConnection() {
        String host = "product-redis.redis.cache.windows.net";
        int port = 6380; // Azure Redis Cache port
        String password = "paxoaixlDJx5zYc9Nzt0WTO2IRBM4hnt5AzCaJlsOaM=";

        Jedis jedis = null;

        try {
            // Create a Jedis connection to Azure Redis Cache
            jedis = new Jedis(host, port);
            jedis.auth(password);

            // Test the connection by performing a simple operation
            String pingResponse = jedis.ping();

            if ("PONG".equalsIgnoreCase(pingResponse)) {
                System.out.println("Connected to Azure Redis Cache successfully.");
            } else {
                System.err.println("Failed to connect to Azure Redis Cache.");
            }
        } catch (Exception e) {
            System.err.println("Failed to connect to Azure Redis Cache. Error: " + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

}
