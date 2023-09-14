package com.azure.carshop;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.exceptions.JedisConnectionException;






@SpringBootApplication
@EnableCaching
public class CarshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarshopApplication.class, args);	
	}
		
//		String host = "carshop.redis.cache.windows.net";
//        int port = 6380; // Default Redis port
//        String password = "SegFqYC0GFl5TFISEEwLc5WynRyEKk03UAzCaJtotxY=";
//
//        // Configure Jedis pool
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(10); // Max connections
//
//        // Create a Jedis pool
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, 10000, password);
//
//        int maxRetries = 3;
//        int retryCount = 0;
//
//        while (retryCount < maxRetries) {
//            try (Jedis jedis = jedisPool.getResource()) {
//                // Perform Redis operations
//                jedis.set("myKey", "Hello, Azure Redis!");
//                String value = jedis.get("myKey");
//                System.out.println("Value from Redis: " + value);
//                break; // If successful, exit the retry loop
//            } catch (JedisConnectionException e) {
//                // Handle connection exception and retry
//                e.printStackTrace();
//                System.err.println("Failed to connect to Redis. Retrying...");
//                retryCount++;
//            } catch (Exception e) {
//                // Handle other exceptions
//                e.printStackTrace();
//                System.err.println("An unexpected error occurred.");
//                break; // Exit the retry loop on unexpected errors
//            }
//        }
//
//        jedisPool.close();
//    }
    }
    
	
	
    



	


