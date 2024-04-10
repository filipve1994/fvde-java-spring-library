package io.filipvde.commons.io;

import com.fasterxml.jackson.databind.JsonNode;
import io.filipvde.commons.jackson.util.JsonMapperUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.buffer.DataBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

/**
 *
 */
class DataBufferUtilsTest {

	@Test
	void transform() throws IOException {
		String text = "123456";
		StepVerifier.create(toMonoStr(DataBufferUtils.transform(text.getBytes()))).expectNext(text).verifyComplete();

		ClassPathResource resource = new ClassPathResource("input.json");
		StepVerifier
			.create(toMonoStr(DataBufferUtils.transform(resource.getInputStream())).map(JsonMapperUtils::readTree))
			.expectNext(JsonMapperUtils.readValue(resource.getInputStream(), JsonNode.class))
			.verifyComplete();

		StepVerifier
			.create(toMonoStr(DataBufferUtils.transform(Mono.just(resource.getInputStream())))
				.map(JsonMapperUtils::readTree))
			.expectNext(JsonMapperUtils.readValue(resource.getInputStream(), JsonNode.class))
			.verifyComplete();
	}

	private Mono<String> toMonoStr(Flux<DataBuffer> bufferFlux) {
		return DataBufferUtils.transformByte(bufferFlux).map(String::new);
	}

}
