package io.filipvde.commons.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.lang.NonNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <p>
 * AnnotationAbstractPointcutAdvisorTest
 * </p>
 */
class AnnotationAbstractPointcutAdvisorTest {

	@Test
	void test() throws NoSuchMethodException {
		MyAnnotationAbstractPointcutAdvisor advisor = new MyAnnotationAbstractPointcutAdvisor();

		assertEquals(MyAnnotation.class, advisor.annotationType);
		assertTrue(advisor.implementsInterface(MyAnnotation.class));
		assertEquals(AnnotationClassOrMethodPointcut.class, advisor.getPointcut().getClass());
		assertEquals(new AnnotationClassOrMethodPointcut(MyAnnotation.class), advisor.getPointcut());

		assertTrue(advisor.getPointcut().getClassFilter().matches(AopProxyClass.class));
		assertTrue(advisor.getPointcut()
			.getMethodMatcher()
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

	static class MyAnnotationAbstractPointcutAdvisor extends AnnotationAbstractPointcutAdvisor<MyAnnotation> {

		@Override
		protected Object invoke(MethodInvocation invocation, MyAnnotation annotation) throws Throwable {
			return invocation.proceed();
		}

		@NonNull
		@Override
		public Pointcut getPointcut() {
			return new AnnotationClassOrMethodPointcut(annotationType);
		}

	}

}
