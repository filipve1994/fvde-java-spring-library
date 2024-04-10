package io.filipvde.commons.jackson.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * TypeFactoryUtilsTest
 * </p>
 *

 */
class TypeFactoryUtilsTest {

	@Test
	void instance() {
		assertEquals(new ObjectMapper().getTypeFactory(), TypeFactoryUtils.instance());
	}

	@Test
	void javaType() {
		SimpleType strType = SimpleType.constructUnsafe(String.class);
		{
			assertEquals(strType, TypeFactoryUtils.javaType(String.class));
			assertEquals(strType, TypeFactoryUtils.javaType(new TypeReference<String>() {

			}));
			assertEquals(strType, TypeFactoryUtils.javaType(ResolvableType.forClass(String.class)));
		}

		{
			TypeBindings bindings = TypeBindings.createIfNeeded(List.class, strType);
			CollectionType collectionType = CollectionType.construct(List.class, bindings,
				SimpleType.constructUnsafe(Collection.class), null, strType);
			assertEquals(collectionType, TypeFactoryUtils.javaType(List.class, String.class));
			assertEquals(collectionType, TypeFactoryUtils.javaType(List.class, strType));
			assertEquals(collectionType, TypeFactoryUtils.javaType(new TypeReference<List<String>>() {
			}));
			assertEquals(collectionType,
				TypeFactoryUtils.javaType(ResolvableType.forClassWithGenerics(List.class, String.class)));
		}

		{
			TypeBindings bindings = TypeBindings.createIfNeeded(Map.class, new JavaType[] { strType, strType });
			MapType mapType = MapType.construct(Map.class, bindings, TypeFactory.unknownType(), null, strType, strType);
			assertEquals(mapType, TypeFactoryUtils.javaType(Map.class, String.class, String.class));
			assertEquals(mapType, TypeFactoryUtils.javaType(Map.class, strType, strType));
			assertEquals(mapType, TypeFactoryUtils.javaType(new TypeReference<Map<String, String>>() {
			}));
			assertEquals(mapType, TypeFactoryUtils
				.javaType(ResolvableType.forClassWithGenerics(Map.class, String.class, String.class)));
		}

	}

	@Test
	void collectionType() {
		SimpleType strType = SimpleType.constructUnsafe(String.class);
		TypeBindings bindings = TypeBindings.createIfNeeded(Collection.class, strType);
		CollectionType collectionType = CollectionType.construct(Collection.class, bindings,
			SimpleType.constructUnsafe(Iterable.class), null, strType);
		assertEquals(collectionType, TypeFactoryUtils.collectionType(String.class));
		assertEquals(collectionType, TypeFactoryUtils.collectionType(strType));
	}

	@Test
	void mapType() {
		SimpleType strType = SimpleType.constructUnsafe(String.class);
		TypeBindings bindings = TypeBindings.createIfNeeded(Map.class, new JavaType[] { strType, strType });
		MapType mapType = MapType.construct(Map.class, bindings, TypeFactory.unknownType(), null, strType, strType);
		assertEquals(mapType, TypeFactoryUtils.mapType(String.class, String.class));
		assertEquals(mapType, TypeFactoryUtils.mapType(strType, strType));
	}

}
