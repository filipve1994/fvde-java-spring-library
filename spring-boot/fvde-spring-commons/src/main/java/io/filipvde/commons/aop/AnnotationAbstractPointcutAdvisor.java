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

import io.filipvde.commons.util.AnnotationUtils;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.core.GenericTypeResolver;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * annotationPointcutProcessor
 *
 * @param <A> annotation
 */
public abstract class AnnotationAbstractPointcutAdvisor<A extends Annotation> extends AbstractPointcutAdvisor
		implements IntroductionInterceptor {

	/**
	 * 切点注解类型
	 */
	@SuppressWarnings("unchecked")
	protected final Class<A> annotationType = (Class<A>) GenericTypeResolver.resolveTypeArgument(this.getClass(),
			AnnotationAbstractPointcutAdvisor.class);

	@NonNull
	@Override
	public Object invoke(@NonNull MethodInvocation invocation) throws Throwable {
		Method method = invocation.getMethod();
		A annotation = AnnotationUtils.findAnnotation(method, annotationType);
		if (annotation == null && invocation.getThis() != null) {
			annotation = AnnotationUtils.findAnnotation(invocation.getThis().getClass(), annotationType);
		}
		return invoke(invocation, annotation);
	}

	/**
	 * howToPerformInterception
	 * @param invocation methodRelatedInformation
	 * @param annotation annotationInformation
	 * @return methodReturnsResult object
	 * @throws Throwable the throwable
	 */
	protected abstract Object invoke(MethodInvocation invocation, A annotation) throws Throwable;

	@Override
	public boolean implementsInterface(@NonNull Class<?> intf) {
		return annotationType != null && annotationType.isAssignableFrom(intf);
	}

	@NonNull
	@Override
	public Advice getAdvice() {
		return this;
	}

}
