package io.filipvde.commons.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p>
 * AnnotationAbstractPointcutTypeAdvisorTest
 * </p>
 */
class AnnotationAbstractPointcutTypeAdvisorTest {

	@Test
	void test() {
		MyAnnotationAbstractPointcutTypeAdvisor advisor = new MyAnnotationAbstractPointcutTypeAdvisor();

		assertEquals(AnnotationMatchingPointcut.class, advisor.getPointcut().getClass());
		assertEquals(AnnotationMatchingPointcut.forClassAnnotation(MyAnnotation.class), advisor.getPointcut());

		assertTrue(advisor.getPointcut().getClassFilter().matches(AopProxyClass.class));
		assertFalse(advisor.getPointcut()
			.getClassFilter()
			.matches(AnnotationAbstractPointcutAdvisorTest.AopProxyClass.class));
	}

	@MyAnnotation
	static class AopProxyClass {

		@SuppressWarnings("unused")
		void testAop() {
		}

	}

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@interface MyAnnotation {

	}

	static class MyAnnotationAbstractPointcutTypeAdvisor extends AnnotationAbstractPointcutTypeAdvisor<MyAnnotation> {

		@Override
		protected Object invoke(MethodInvocation invocation, MyAnnotation annotation) throws Throwable {
			return invocation.proceed();
		}

		@Override
		protected AnnotationPointcutType pointcutType() {
			return AnnotationPointcutType.TYPE;
		}

	}

}
