package io.filipvde.commons.util;

import com.google.auto.service.AutoService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@Disabled
class ProviderLoaderTest {

	@Test
	void loadTest() {
//		assertInstanceOf(Dog.class, ProviderLoader.SPRING_FACTORY.load(Animal.class).getFirst());
		assertInstanceOf(Cat.class, ProviderLoader.JDK_SERVICE.load(Animal.class).getFirst());

		ResolvableType type = ResolvableType.forClass(Animal.class);
//		assertInstanceOf(Dog.class, ProviderLoader.SPRING_FACTORY.load(type).getFirst());
		assertInstanceOf(Cat.class, ProviderLoader.JDK_SERVICE.load(type).getFirst());
	}

	public interface Animal {

	}

	//	@SpringFactories
	public static class Dog implements Animal {

	}

	@AutoService(Animal.class)
	public static class Cat implements Animal {

	}

}
