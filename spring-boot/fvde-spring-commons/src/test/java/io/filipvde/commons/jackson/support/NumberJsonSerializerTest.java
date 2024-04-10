package io.filipvde.commons.jackson.support;

import com.fasterxml.jackson.databind.JsonNode;
import io.filipvde.commons.beans.BeanLambdaFunc;
import io.filipvde.commons.jackson.util.JsonMapperUtils;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 */
class NumberJsonSerializerTest {

	@Test
	void test() {
		Big big = new Big();
		big.l = 33L;
		big.d = 0.333333d;
		big.f = 0.333333f;
		big.count = BigDecimal.valueOf(0.333333);
		big.sunCount = BigDecimal.valueOf(0.333333);
		String json = JsonMapperUtils.writeValueAsString(big);
		Big bean = JsonMapperUtils.readValue(json, Big.class);
		assertNotNull(bean);
		assertEquals(33L, bean.l);
		assertEquals(0.33d, bean.d);
		assertEquals(0.3f, bean.f);
		assertEquals(0.33d, bean.count.doubleValue());
		assertEquals(0.333d, bean.sunCount.doubleValue());
		JsonNode jsonNode = JsonMapperUtils.readTree(json);
		assertEquals("0033", jsonNode.get(BeanLambdaFunc.fieldName(Big::getL)).asText());
		assertEquals(0.33d, jsonNode.get(BeanLambdaFunc.fieldName(Big::getD)).asDouble());
		assertEquals(0.3d, jsonNode.get(BeanLambdaFunc.fieldName(Big::getF)).asDouble());
		assertEquals(0.33d, jsonNode.get(BeanLambdaFunc.fieldName(Big::getCount)).asDouble());
		assertEquals(0.333d, jsonNode.get(BeanLambdaFunc.fieldName(Big::getSunCount)).asDouble());
	}

	@Getter
	private static class Big {

		@NumberJsonFormat(pattern = "0000")
		private long l;

		@NumberJsonFormat
		private double d;

		@NumberJsonFormat(pattern = "#0.0")
		private float f;

		@NumberJsonFormat
		private BigDecimal count;

		@NumberJsonFormat(pattern = "#0.000")
		private BigDecimal sunCount;

	}

}
