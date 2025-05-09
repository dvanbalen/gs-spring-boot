package com.example.springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import com.example.springboot.TutorialsConnectionHelper;

@SpringBootApplication
public class Application {
    static RemoteCacheManager cacheManager;
    static RemoteCache<String, String> cache;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Let's do some cache stuff!");

		connectToInfinispan();
        manipulateCache();
        disconnect();

		// String[] beanNames = ctx.getBeanDefinitionNames();
		// Arrays.sort(beanNames);
		// for (String beanName : beanNames) {
		// 	System.out.println(beanName);
		// }
	}

	static void manipulateCache() {
        // Store a value
        cache.put("key", "value");
        // Retrieve the value and print it out
        System.out.printf("key = %s\n", cache.get("key"));
    }

    static void connectToInfinispan() {
        // Connect to the server
        cacheManager = TutorialsConnectionHelper.connect();
        // Obtain the remote cache
        cache = cacheManager.getCache(TutorialsConnectionHelper.TUTORIAL_CACHE_NAME);
    }

    static void disconnect() {
        // Stop the cache manager and release all resources
        TutorialsConnectionHelper.stop(cacheManager);
    }
	
}
