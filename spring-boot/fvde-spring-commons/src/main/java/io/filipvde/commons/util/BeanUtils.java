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

package io.filipvde.commons.util;

import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <p>
 * beanRelatedOperationTools
 * </p>
 *
 */
@UtilityClass
public class BeanUtils extends org.springframework.beans.BeanUtils {

	/**
	 * beanUtilsBasedReplication
	 * @param <T> type
	 * @param source targetSource
	 * @param targetClass resultTypeToBeCopied
	 * @return result t
	 */
	public <T> T copy(Object source, Class<T> targetClass) {
		return copy(source, () -> instantiateClass(targetClass));
	}

	/**
	 * beanUtilsBasedReplication
	 * @param <T> type
	 * @param source targetSource
	 * @param supplier supplier
	 * @return result t
	 */
	public <T> T copy(Object source, Supplier<T> supplier) {
		if (supplier == null) {
			return null;
		}
		T t = supplier.get();
		if (source != null) {
			copyProperties(source, t);
		}
		return t;
	}

	/**
	 * listTypeCopy
	 * @param <T> type
	 * @param sourceList targetList
	 * @param targetClass classType
	 * @return result list
	 */
	public <T> List<T> copyList(Collection<?> sourceList, Class<T> targetClass) {
		return sourceList.stream().map(source -> copy(source, targetClass)).toList();
	}

	/**
	 * determineWhetherTheCurrentClassIsNULL,At the same time, determine whether all Fields of the current class are NULL.
	 * @param source 目标
	 * @return boolean
	 */
	public static boolean isFieldNull(Object source) {
		if (source == null) {
			return true;
		}
		return Stream.of(source.getClass().getDeclaredFields())
			.anyMatch(field -> ReflectionUtils.getDeclaredFieldValue(field, source) == null);
	}

	/**
	 * useBeanWrapperToConvertBeanIntoMap
	 * @param source bean
	 * @return Map
	 * @see BeanWrapper
	 */
	public static Map<String, Object> convert(Object source) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(source);
		PropertyDescriptor[] descriptors = beanWrapper.getPropertyDescriptors();
		Map<String, Object> map = new HashMap<>();
		for (PropertyDescriptor descriptor : descriptors) {
			if ("class".equals(descriptor.getName())) {
				continue;
			}
			Object propertyValue = beanWrapper.getPropertyValue(descriptor.getName());
			map.put(descriptor.getName(), propertyValue);
		}
		return map;
	}

}
