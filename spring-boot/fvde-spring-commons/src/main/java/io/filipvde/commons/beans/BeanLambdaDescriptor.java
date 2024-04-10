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

package io.filipvde.commons.beans;

import io.filipvde.commons.util.BeanUtils;
import io.filipvde.commons.util.ClassUtils;
import io.filipvde.commons.util.Pair;
import io.filipvde.commons.util.ReflectionUtils;
import lombok.Getter;
import lombok.SneakyThrows;

import java.beans.PropertyDescriptor;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * beanLambdaRelatedMethodsOrFieldProcessing
 *
 */
@Getter
final class BeanLambdaDescriptor {

	private static final Map<Pair<Class<?>, Method>, BeanLambdaDescriptor> cache = new ConcurrentHashMap<>(128);

	private final Class<?> type;

	private final Method method;

	private final PropertyDescriptor propertyDescriptor;

	private BeanLambdaDescriptor(Class<?> type, Method method) {
		this.type = type;
		this.method = method;
		this.propertyDescriptor = BeanUtils.findPropertyForMethod(method, type);
	}

	/**
	 * staticBuildBasedOn{@link BeanLambdaDescriptor}
	 * <p>
	 * useCachingToAvoidInvalidLoads
	 *
	 * @param <T>      relatedGenerics
	 * @param function beanLambdaFuncExpression
	 * @return BeanLambdaDescriptor
	 */
	@SneakyThrows
	public static <T> BeanLambdaDescriptor create(BeanLambdaFunc<T> function) {
		Method writeReplace = function.getClass().getDeclaredMethod("writeReplace");
		writeReplace.setAccessible(true);
		SerializedLambda serializedLambda = (SerializedLambda) writeReplace.invoke(function);
		String className = ClassUtils.convertResourcePathToClassName(serializedLambda.getImplClass());
		Class<?> type = ClassUtils.resolveClassName(className);
		Method method = ReflectionUtils.findMethod(type, serializedLambda.getImplMethodName());
		return cache.computeIfAbsent(Pair.of(type, method), pair -> new BeanLambdaDescriptor(pair.key(), pair.value()));
	}

	public String getFieldName() {
		return propertyDescriptor != null ? propertyDescriptor.getName() : null;
	}

	public Field getField() {
		if (propertyDescriptor != null) {
			String fieldName = propertyDescriptor.getName();
			Class<?> fieldType = propertyDescriptor.getPropertyType();
			return ReflectionUtils.findField(type, fieldName, fieldType);
		}
		return null;
	}

	public String getMethodName() {
		return method.getName();
	}

}
