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

package io.filipvde.commons.io;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * resourceOperationTools
 *
 * @see Resource
 * @see PathMatchingResourcePatternResolver
 */
@UtilityClass
public class ResourceUtils extends org.springframework.util.ResourceUtils {

	private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

	/**
	 * getASingleResource
	 * @param location resourceAddress
	 * @return the resource
	 */
	public Resource getResource(String location) {
		return resourceResolver.getResource(location);
	}

	/**
	 * getMultipleResources
	 * @param location resourceAddress
	 * @return the resource[]
	 * @throws IOException the io exception
	 */
	public Resource[] getResources(String location) throws IOException {
		return resourceResolver.getResources(location);
	}

}
