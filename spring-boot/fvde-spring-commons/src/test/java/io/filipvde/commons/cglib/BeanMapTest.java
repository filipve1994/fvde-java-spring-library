package io.filipvde.commons.cglib;

import io.filipvde.commons.beans.BeanLambdaFunc;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
class BeanMapTest {

	@Test
	void test() {
		List<Bean> list = List.of(new Bean().setId(1L).setUsername("root"), new Bean().setId(2L).setUsername("root"),
			new Bean().setId(3L).setUsername("root"));
		Bean bean = new Bean().setId(0L).setUsername("fve").setBeans(list);

		BeanMap beanMap = BeanMap.create(bean);
		assertEquals(0L, beanMap.get(BeanLambdaFunc.fieldName(Bean::getId)));
		assertEquals("fve", beanMap.get(BeanLambdaFunc.fieldName(Bean::getUsername)));
		assertEquals(Set.of(list), Set.of(beanMap.get(BeanLambdaFunc.fieldName(Bean::getBeans))));

		assertEquals(1L, beanMap.get(list.getFirst(), BeanLambdaFunc.fieldName(Bean::getId)));

		assertEquals(Long.class, beanMap.getPropertyType(BeanLambdaFunc.fieldName(Bean::getId)));
		assertEquals(String.class, beanMap.getPropertyType(BeanLambdaFunc.fieldName(Bean::getUsername)));
		assertEquals(List.class, beanMap.getPropertyType(BeanLambdaFunc.fieldName(Bean::getBeans)));
	}

	@Data
	@Accessors(chain = true)
	static class Bean {

		private Long id;

		private String username;

		private List<Bean> beans;

	}

}
