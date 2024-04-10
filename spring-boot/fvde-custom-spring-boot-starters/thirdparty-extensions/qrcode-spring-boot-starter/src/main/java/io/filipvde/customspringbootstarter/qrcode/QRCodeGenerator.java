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

package io.filipvde.customspringbootstarter.qrcode;

import com.google.zxing.client.j2se.MatrixToImageConfig;

import java.awt.image.BufferedImage;

/**
 * The interface Qr code generator.
 *
 */
public interface QRCodeGenerator {

	/**
	 * generateQRCode
	 * @param entity qrCodeEntity
	 * @return BufferedImage
	 */
	BufferedImage generateQRCode(QRCodeEntity<?> entity);

	/**
	 * generateQRCode
	 * @param content qrCodeContent
	 * @param width qrCodeWidth
	 * @param height qrCodeHeight
	 * @param type pictureType
	 * @return BufferedImage
	 */
	default BufferedImage generateQRCode(String content, int width, int height, PicType type) {
		return generateQRCode(content, width, height, new MatrixToImageConfig(), type);
	}

	/**
	 * generateQRCode
	 * @param content qrCodeContent
	 * @param width qrCodeWidth
	 * @param height qrCodeHeight
	 * @param config qrCodeColorConfiguration
	 * @param type pictureType
	 * @return BufferedImage
	 */
	BufferedImage generateQRCode(String content, int width, int height, MatrixToImageConfig config, PicType type);

}
