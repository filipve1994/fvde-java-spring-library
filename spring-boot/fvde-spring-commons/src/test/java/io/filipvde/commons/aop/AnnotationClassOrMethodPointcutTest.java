package io.filipvde.commons.aop;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p>
 * AnnotationClassOrMethodPointcutTest
 * </p>
 */
class AnnotationClassOrMethodPointcutTest {

	@Test
	void test() throws NoSuchMethodException {
		AnnotationClassOrMethodPointcut pointcut = new AnnotationClassOrMethodPointcut(MyAnnotation.class);

		assertTrue(pointcut.getClassFilter().matches(AopProxyClass.class));
		assertTrue(pointcut.getMethodMatcher()
			.matches(AopProxyClass.class.getDeclaredMethod("testAop"), AopProxyClass.class));
	}

	@MyAnnotation
	static class AopProxyClass {

		@MyAnnotation
		void testAop() {
		}

	}

	@Target({ElementType.TYPE, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@interface MyAnnotation {

	}

}
