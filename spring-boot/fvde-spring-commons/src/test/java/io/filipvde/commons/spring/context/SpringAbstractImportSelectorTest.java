package io.filipvde.commons.spring.context;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * SpringAbstractImportSelectorTest
 * </p>
 */
class SpringAbstractImportSelectorTest {

	@Test
	void testFindAnnotation() {
		MyAnnotationImportSelector selector = new MyAnnotationImportSelector();
		assertEquals(MyAnnotation.class, selector.annotationClass);
	}

	@interface MyAnnotation {

	}

	static class MyAnnotationImportSelector extends SpringAbstractImportSelector<MyAnnotation> {

	}

}
