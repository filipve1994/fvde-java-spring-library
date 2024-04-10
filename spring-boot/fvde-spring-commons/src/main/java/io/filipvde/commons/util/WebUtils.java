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

import com.google.common.collect.Lists;
import io.filipvde.commons.jackson.util.JsonMapperUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedCaseInsensitiveMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * web、servletRelatedTools
 * </p>
 *
 */
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {

	private static final String UNKNOWN = "unknown";

	private static final String HTTP_IP_SPLIT = ",";

	private ServletRequestAttributes servletRequestAttributes() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		Assert.notNull(servletRequestAttributes, "attributes not null!");
		return servletRequestAttributes;
	}

	/**
	 * getTheRequestOfTheCurrentThread
	 * @return http servlet request
	 */
	public HttpServletRequest request() {
		return servletRequestAttributes().getRequest();
	}

	/**
	 * getTheResponseOfTheCurrentThread.
	 * @return http servlet response
	 */
	public HttpServletResponse response() {
		return servletRequestAttributes().getResponse();
	}

	/**
	 * willRequest convertHeaderToHttpHeaders
	 * @param request request
	 * @return http headers
	 */
	public HttpHeaders headers(HttpServletRequest request) {
		LinkedCaseInsensitiveMap<List<String>> insensitiveMap = new LinkedCaseInsensitiveMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			Enumeration<String> headers = request.getHeaders(headerName);
			insensitiveMap.put(headerName, Collections.list(headers));
		}
		return new HttpHeaders(CollectionUtils.toMultiValueMap(insensitiveMap));
	}

	/**
	 * getAllAttributesOfTheCurrentRequest
	 * @param request request
	 * @return attributes
	 */
	public Map<String, Object> attributes(HttpServletRequest request) {
		return BaseStreamUtils.convert(request.getAttributeNames())
			.collect(Collectors.toMap(Function.identity(), request::getAttribute));
	}

	/**
	 * parseTheParamOfRequest,And use delimiter to connect data with the same key
	 * @param request request
	 * @param delimiter joiner
	 * @return map
	 */
	public Map<String, String> paramMap(HttpServletRequest request, CharSequence delimiter) {
		return request.getParameterMap()
			.entrySet()
			.stream()
			.collect(Collectors.toMap(Map.Entry::getKey, entry -> String.join(delimiter, entry.getValue())));
	}

	/**
	 * Parse the param of request and convert it into Multi Value Map
	 * @param request request
	 * @return MultiValueMap
	 */
	public MultiValueMap<String, String> params(HttpServletRequest request) {
		Map<String, List<String>> map = request.getParameterMap()
			.entrySet()
			.stream()
			.collect(Collectors.toMap(Map.Entry::getKey, entry -> Lists.newArrayList(entry.getValue())));
		return new LinkedMultiValueMap<>(map);
	}

	/**
	 * parseTheRequestToGetTheRealIP
	 * @param request request
	 * @return ip
	 */
	public String realIp(HttpServletRequest request) {
		String[] ipHeaders = { "X-Real-IP", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP" };
		Optional<String> optional = Optional.empty();
		for (String header : ipHeaders) {
			String headerIp = request.getHeader(header);
			if (headerIp != null && !headerIp.isBlank() && !UNKNOWN.equalsIgnoreCase(headerIp)) {
				optional = Optional.of(headerIp);
				break;
			}
		}
		String ip = optional.orElse(request.getRemoteAddr());
		// handleMultipleIPSituations（onlyTakeTheFirstIP）
		return ip != null && ip.contains(HTTP_IP_SPLIT) ? ip.split(HTTP_IP_SPLIT)[0] : ip;
	}

	/**
	 * writeDataToResponseInJSONFormat
	 * @param response response
	 * @param data dataToBeWritten
	 * @see JsonMapperUtils
	 */
	public void outJson(HttpServletResponse response, Object data) {
		out(response, JsonMapperUtils.writeValueAsString(data), MediaType.APPLICATION_JSON_VALUE);
	}

	/**
	 * writeReturnValueBasedOnResponse
	 * @param response response
	 * @param message writtenInformation
	 * @param contentType contentType {@link MediaType}
	 */
	public void out(HttpServletResponse response, String message, String contentType) {
		response.setContentType(contentType);
		response.setCharacterEncoding("UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.print(message);
			out.flush();
		}
		catch (IOException exception) {
			throw new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, exception);
		}
	}

}
