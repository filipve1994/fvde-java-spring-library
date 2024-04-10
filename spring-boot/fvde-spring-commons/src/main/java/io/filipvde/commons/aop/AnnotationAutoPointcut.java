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

package io.filipvde.commons.aop;

import org.springframework.aop.Pointcut;

import java.lang.annotation.Annotation;

/**
 * buildBasedOnAnnotationType{@link Pointcut}commonInterface
 *

 */
@FunctionalInterface
interface AnnotationAutoPointcut {

	/**
	 * getTheCutPointBasedOnTheAnnotation
	 * @param annotationType annotationInformation
	 * @return 切点
	 */
	Pointcut getPointcut(Class<? extends Annotation> annotationType);

}
