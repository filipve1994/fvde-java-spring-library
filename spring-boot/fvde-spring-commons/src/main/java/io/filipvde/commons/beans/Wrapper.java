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

/**
 * universalWrapper
 */
public interface Wrapper {

	/**
	 * getRelatedInstancesBasedOnType
	 * <p>
	 * Throws an exception if conversion cannot be performed{@link ClassCastException}
	 * @param <T> relatedGenerics
	 * @param type classInformation
	 * @return relatedExamples
	 */
	default <T> T unwrap(Class<T> type) {
		if (isWrapperFor(type)) {
			return type.cast(unwrap());
		}
		throw new ClassCastException("cannot be converted to " + type);
	}

	/**
	 * determineWhetherConversionCanBePerformed
	 * @param type 类信息
	 * @return bool
	 */
	default boolean isWrapperFor(Class<?> type) {
		return type.isInstance(unwrap());
	}

	/**
	 * 解析获取相关实例
	 * @return 相关实例
	 */
	Object unwrap();

}
