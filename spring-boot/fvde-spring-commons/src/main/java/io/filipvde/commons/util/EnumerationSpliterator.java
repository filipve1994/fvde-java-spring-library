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

import java.util.Enumeration;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * <p>
 * 用于Enumeration转换Stream
 * </p>
 *
 * @param <T> the type parameter

 * @see BaseStreamUtils#convert(Enumeration)
 */
final class EnumerationSpliterator<T> extends Spliterators.AbstractSpliterator<T> {

	private final Enumeration<T> enumeration;

	private EnumerationSpliterator(Enumeration<T> enumeration, int additionalCharacteristics) {
		super(Long.MAX_VALUE, additionalCharacteristics);
		this.enumeration = enumeration;
	}

	/**
	 * 根据Enumeration构造Spliterator
	 * @param <T> type parameter
	 * @param enumeration enumeration
	 * @return spliterator
	 */
	public static <T> Spliterator<T> spliteratorUnknownSize(Enumeration<T> enumeration) {
		return new EnumerationSpliterator<>(Objects.requireNonNull(enumeration), Spliterator.ORDERED);
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		if (enumeration.hasMoreElements()) {
			action.accept(enumeration.nextElement());
			return true;
		}
		return false;
	}

	@Override
	public void forEachRemaining(Consumer<? super T> action) {
		while (enumeration.hasMoreElements()) {
			action.accept(enumeration.nextElement());
		}
	}

}
