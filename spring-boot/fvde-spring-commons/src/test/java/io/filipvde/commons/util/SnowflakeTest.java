package io.filipvde.commons.util;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * SnowflakeTest
 * </p>
 */
class SnowflakeTest {

	static final Snowflake SNOWFLAKE = new Snowflake();

	@Test
	void nextId() throws InterruptedException {
		int num = 10000;
		List<Long> list = new CopyOnWriteArrayList<>();
		try (ExecutorService service = Executors.newFixedThreadPool(1000, Thread.ofVirtual().factory())) {
			CountDownLatch countDownLatch = new CountDownLatch(num);
			for (int i = 0; i < num; i++) {
				service.submit(() -> {
					list.add(SNOWFLAKE.nextId());
					countDownLatch.countDown();
				});
			}
			countDownLatch.await();
			service.shutdown();
			Set<Long> set = new HashSet<>(list);
			assertEquals(set.size(), list.size());
		}
	}

}
