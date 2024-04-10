package io.filipvde.commons.aop;

import org.junit.jupiter.api.Test;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p>
 * AnnotationPointcutTypeTest
 * </p>
 */
class AnnotationPointcutTypeTest {

	@Test
	void test() throws NoSuchMethodException {
		Class<? extends Annotation> annotationType = MyAnnotation.class;
		Class<?> type = AopProxyClass.class;
		Method method = AopProxyClass.class.getDeclaredMethod("testAop");
		{
			AnnotationAutoPointcut pointcut = AnnotationPointcutType.AUTO;
			assertEquals(new AnnotationClassOrMethodPointcut(annotationType), pointcut.getPointcut(annotationType));
			assertTrue(pointcut.getPointcut(annotationType).getClassFilter().matches(type));
			assertTrue(pointcut.getPointcut(annotationType).getMethodMatcher().matches(method, type));
		}

		{
			AnnotationAutoPointcut pointcut = AnnotationPointcutType.TYPE;
			assertEquals(AnnotationMatchingPointcut.forClassAnnotation(annotationType),
				pointcut.getPointcut(annotationType));
			assertTrue(pointcut.getPointcut(annotationType).getClassFilter().matches(type));
			assertTrue(pointcut.getPointcut(annotationType).getMethodMatcher().matches(method, type));
		}

		{
			AnnotationAutoPointcut pointcut = AnnotationPointcutType.METHOD;
			assertEquals(AnnotationMatchingPointcut.forMethodAnnotation(annotationType),
				pointcut.getPointcut(annotationType));
			assertTrue(pointcut.getPointcut(annotationType).getClassFilter().matches(type));
			assertTrue(pointcut.getPointcut(annotationType).getMethodMatcher().matches(method, type));
		}

		{
			AnnotationAutoPointcut pointcut = AnnotationPointcutType.TYPE_OR_METHOD;
			assertEquals(new AnnotationClassOrMethodPointcut(annotationType), pointcut.getPointcut(annotationType));
			assertTrue(pointcut.getPointcut(annotationType).getClassFilter().matches(type));
			assertTrue(pointcut.getPointcut(annotationType).getMethodMatcher().matches(method, type));
		}

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
