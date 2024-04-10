/*
 * Copyright 2021-2024 spring-boot-extension the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.filipvde.commons.jackson.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import io.filipvde.commons.jackson.core.JacksonSupport;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Map;

/**
 * jsonMapperToolClass，useDefaultJsonMapper
 *

 */
@UtilityClass
public class JsonMapperUtils {

	private static final JacksonSupport JSON = new JacksonSupport(new JsonMapper());

	/**
	 * Read json data from obj and convert it into corresponding entity class
	 * @param <T> generics
	 * @param obj dataToBeRead
	 * @param type returnRelatedType
	 * @return relatedExamples
	 */
	public static <T> T readValue(Object obj, Class<T> type) {
		return JSON.readValue(obj, type);
	}

	/**
	 * Read json data from obj and convert it into corresponding entity class
	 * @param <T> generics
	 * @param obj dataToBeRead
	 * @param type relatedTypes
	 * @return relatedExamples
	 */
	public static <T> T readValue(Object obj, JavaType type) {
		return JSON.readValue(obj, type);
	}

	/**
	 * Read json data from obj and convert it into corresponding entity class
	 * @param <T> 泛型
	 * @param obj 待读取的数据
	 * @param typeReference typeReferenceWrappedType
	 * @return 相关实例
	 */
	public static <T> T readValue(Object obj, TypeReference<T> typeReference) {
		return JSON.readValue(obj, typeReference);
	}

	/**
	 * obj序列化成string
	 * @param obj obj
	 * @return json string
	 */
	public static String writeValueAsString(Object obj) {
		return JSON.writeValueAsString(obj);
	}

	/**
	 * obj序列化成byte[]
	 * @param obj the obj
	 * @return the byte [ ]
	 */
	public static byte[] writeValueAsBytes(Object obj) {
		return JSON.writeValueAsBytes(obj);
	}

	/**
	 * json反序列化成List
	 * <p>
	 * 也可以看看{@link JacksonSupport#readValue(Object, TypeReference)} ,
	 * <p>
	 * {@link JacksonSupport#convertValue(Object, JavaType)}
	 * @param <T> 泛型
	 * @param obj the obj
	 * @param type 类型
	 * @return the list
	 */
	public static <T> List<T> readValueList(Object obj, Class<T> type) {
		CollectionType collectionType = TypeFactoryUtils.collectionType(type);
		return JSON.readValue(obj, collectionType);
	}

	/**
	 * json反序列化成Map
	 * <p>
	 * 也可以看看{@link JacksonSupport#readValue(Object, TypeReference)} ,
	 * <p>
	 * {@link JacksonSupport#convertValue(Object, JavaType)}
	 * @param <K> the type parameter
	 * @param <V> the type parameter
	 * @param obj the obj
	 * @param keyClass K Class
	 * @param valueClass V Class
	 * @return the map
	 */
	public static <K, V> Map<K, V> readValueMap(Object obj, Class<K> keyClass, Class<V> valueClass) {
		MapType mapType = TypeFactoryUtils.mapType(keyClass, valueClass);
		return JSON.readValue(obj, mapType);
	}

	/**
	 * objReadsDataAndConvertsItIntoJsonNode
	 * @param obj the obj
	 * @return the json node
	 */
	public static JsonNode readTree(Object obj) {
		return JSON.readTree(obj);
	}

	/**
	 * jacksonDataConversion
	 * @param <T> 泛型
	 * @param fromValue 待转换数据
	 * @param type 返回类型
	 * @return 相关实例
	 */
	public static <T> T convertValue(Object fromValue, Class<T> type) {
		return JSON.convertValue(fromValue, type);
	}

	/**
	 * jackson数据转换
	 * @param <T> 泛型
	 * @param fromValue 待转换数据
	 * @param typeReference the type reference
	 * @return 相关实例
	 */
	public static <T> T convertValue(Object fromValue, TypeReference<T> typeReference) {
		return JSON.convertValue(fromValue, typeReference);
	}

	/**
	 * jacksonDataConversion
	 * @param <T> 泛型
	 * @param fromValue 待转换数据
	 * @param javaType 相关类型
	 * @return 相关实例
	 */
	public static <T> T convertValue(Object fromValue, JavaType javaType) {
		return JSON.convertValue(fromValue, javaType);
	}

	/**
	 * jacksonDataConversionList
	 * @param <T> 泛型
	 * @param fromValue dataToBeConverted
	 * @param type returnType
	 * @return List
	 */
	public static <T> List<T> convertValueList(Object fromValue, Class<T> type) {
		CollectionType collectionType = TypeFactoryUtils.collectionType(type);
		return JSON.convertValue(fromValue, collectionType);
	}

	/**
	 * jacksonDataConversionMap
	 * @param <K> the type parameter
	 * @param <V> the type parameter
	 * @param fromValue the fromValue
	 * @param keyClass the key class
	 * @param valueClass the value class
	 * @return MAP
	 */
	public static <K, V> Map<K, V> convertValueMap(Object fromValue, Class<K> keyClass, Class<V> valueClass) {
		MapType mapType = TypeFactoryUtils.mapType(keyClass, valueClass);
		return JSON.convertValue(fromValue, mapType);
	}

}
