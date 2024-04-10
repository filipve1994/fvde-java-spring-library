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

import com.google.common.base.Splitter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Used to split a string into an iterable Multi Value Map object，isThreadSafeAndImmutable
 * <p>
 * {@link Splitter.MapSplitter}的补充版
 * <p>
 * 返回数据是 {@link MultiValueMap}
 *

 * @see Splitter
 * @see Splitter.MapSplitter
 * @see MultiValueMap
 */
public final class MultiValueMapSplitter {

	private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";

	private final Splitter outerSplitter;

	private final Splitter entrySplitter;

	private MultiValueMapSplitter(Splitter outerSplitter, Splitter entrySplitter) {
		this.outerSplitter = outerSplitter; // only "this" is passed
		this.entrySplitter = checkNotNull(entrySplitter);
	}

	/**
	 * 静态构造器
	 * @param outerSplitter the outer splitter
	 * @param entrySplitter the entry splitter
	 * @return the multi value map splitter
	 */
	public static MultiValueMapSplitter of(Splitter outerSplitter, Splitter entrySplitter) {
		return new MultiValueMapSplitter(outerSplitter, entrySplitter);
	}

	/**
	 * 静态构造器
	 * @param outerSplitter the outer splitter
	 * @param entrySplitter the entry splitter
	 * @return the multi value map splitter
	 */
	public static MultiValueMapSplitter of(Splitter outerSplitter, String entrySplitter) {
		return new MultiValueMapSplitter(outerSplitter, Splitter.on(entrySplitter));
	}

	/**
	 * 静态构造器
	 * @param outerSplitter the outer splitter
	 * @param entrySplitter the entry splitter
	 * @return the multi value map splitter
	 */
	public static MultiValueMapSplitter of(String outerSplitter, String entrySplitter) {
		return new MultiValueMapSplitter(Splitter.on(outerSplitter), Splitter.on(entrySplitter));
	}

	/**
	 * 拆分CharSequence成一个MultiValueMap
	 * <p>
	 * 示例: String str = "root=1,2,3&amp;root=4&amp;a=b&amp;a=c"
	 * <p>
	 * MultiValueMapSplitter.of("&amp;", "=").split(str) -> {root=["1,2,3", "4"], a=["b",
	 * "c"]}
	 * @param sequence 待分割的字符串
	 * @return MultiValueMap
	 */
	public MultiValueMap<String, String> split(CharSequence sequence) {
		return split(sequence, "^$");
	}

	/**
	 * 拆分CharSequence成一个MultiValueMap
	 * <p>
	 * 示例: String str = "root=1,2,3&amp;root=4&amp;a=b&amp;a=c"
	 * <p>
	 * MultiValueMapSplitter.of("&amp;", "=").split(str,",") -> {root=["1", "2", "3",
	 * "4"], a=["b", "c"]}
	 * @param sequence 待分割的字符串
	 * @param regex value分割符
	 * @return MultiValueMap
	 */
	public MultiValueMap<String, String> split(CharSequence sequence, String regex) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		for (String entry : outerSplitter.split(sequence)) {
			Iterator<String> entryFields = entrySplitter.split(entry).iterator();

			checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
			String key = entryFields.next();

			checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);

			map.addAll(key, Arrays.asList(entryFields.next().split(regex)));

			checkArgument(!entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
		}
		return CollectionUtils.unmodifiableMultiValueMap(map);
	}

}
