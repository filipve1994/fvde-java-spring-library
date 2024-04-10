package io.filipvde.commons.spring.context;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.ResolvableType;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * SpringContextHolderTest
 * </p>
 */
@SpringBootTest("spring.data.redis.host=fve.com")
class SpringContextHolderTest {

	BeanTest bean = new BeanTest();

	@Test
	void getBean() {
		SpringContextHolder.registerBean(bean, "test");
		assertEquals(bean, SpringContextHolder.getBean("test"));
		assertEquals(bean, SpringContextHolder.getBean(BeanTest.class));
		assertEquals(bean, SpringContextHolder.getBean("test", BeanTest.class));
		if (SpringContextHolder.getApplicationContext() instanceof GenericApplicationContext context) {
			context.removeBeanDefinition("test");
		}
	}

	@Test
	void getBeanProvider() {
		SpringContextHolder.registerBean(bean, "test");
		ResolvableType resolvableType = ResolvableType.forClass(BeanTest.class);
		assertEquals(bean, SpringContextHolder.getBeanProvider(BeanTest.class).getIfAvailable());
		assertEquals(bean, SpringContextHolder.getBeanProvider(resolvableType).getIfAvailable());
		if (SpringContextHolder.getApplicationContext() instanceof GenericApplicationContext context) {
			context.removeBeanDefinition("test");
		}
	}

	@Test
	void getBeansOfType() {
		SpringContextHolder.registerBean(bean, "test");
		assertEquals(Map.of("test", bean), SpringContextHolder.getBeansOfType(BeanTest.class));
		if (SpringContextHolder.getApplicationContext() instanceof GenericApplicationContext context) {
			context.removeBeanDefinition("test");
		}
	}

	@Test
	void getProperty() {
		assertEquals("fve.com", SpringContextHolder.getProperty("spring.data.redis.host"));
		assertEquals("fve.com", SpringContextHolder.getProperty("spring.data.redis.host", String.class));
		assertEquals("fve.com", SpringContextHolder.getProperty("spring.data.redis.host", String.class, "fve.cn"));
		assertEquals("fve.cn", SpringContextHolder.getProperty("spring.data.redisson.host", String.class, "fve.cn"));
	}

	@Test
	void resolvePlaceholders() {
		assertEquals("fve.com", SpringContextHolder.resolvePlaceholders("${spring.data.redis.host}"));
	}

	@Test
	@SuppressWarnings("unchecked")
	void registerBean() {
		SpringContextHolder.registerBean(bean, "test1");
		RootBeanDefinition beanDefinition = new RootBeanDefinition((Class<BeanTest>) bean.getClass(), () -> bean);
		SpringContextHolder.registerBean(beanDefinition, "test2");
		assertEquals(Map.of("test1", bean, "test2", bean), SpringContextHolder.getBeansOfType(BeanTest.class));
		if (SpringContextHolder.getApplicationContext() instanceof GenericApplicationContext context) {
			context.removeBeanDefinition("test1");
			context.removeBeanDefinition("test2");
		}
	}

	@Data
	static class BeanTest {

		private final Long id = 1L;

	}

}
