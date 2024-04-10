package io.filipvde.commons.beans;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeanLambdaFuncTest {

	final Field field1 = Maker.class.getDeclaredField("no");

	final Field field2 = Maker.class.getDeclaredField("username");

	final Method method1 = Maker.class.getMethod("getNo");

	final Method method2 = Maker.class.getMethod("getUsername");

	BeanLambdaFuncTest() throws NoSuchFieldException, NoSuchMethodException {
	}

	@Test
	void method() {
		assertEquals(method1.getName(), BeanLambdaFunc.methodName(Maker::getNo));
		assertEquals(method2.getName(), BeanLambdaFunc.methodName(Maker::getUsername));

		assertEquals(method1, BeanLambdaFunc.method(Maker::getNo));
		assertEquals(method2, BeanLambdaFunc.method(Maker::getUsername));
	}

	@Test
	void field() {
		assertEquals(field1.getName(), BeanLambdaFunc.fieldName(Maker::getNo));
		assertEquals(field2.getName(), BeanLambdaFunc.fieldName(Maker::getUsername));

		assertEquals(field1, BeanLambdaFunc.field(Maker::getNo));
		assertEquals(field2, BeanLambdaFunc.field(Maker::getUsername));
	}

	@Data
	static class Maker {

		private Integer no;

		private String username;

	}

}
