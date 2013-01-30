package com.aripd.qrcode.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aripd.qrcode.service.QRCodeService;
import com.aripd.qrcode.service.impl.QRCodeServiceImpl.FormatImage;

@Controller
@RequestMapping("/qrcode")
public class QRCodeController {

	protected static Logger logger = Logger.getLogger(QRCodeController.class);

	@Resource(name = "qrCodeService")
	private QRCodeService qrCodeService;

	@RequestMapping(value = "/generateQRCode", method = RequestMethod.GET)
	public ResponseEntity<byte[]> generateQRCode(
			final HttpServletRequest request) throws IOException {

		String key = request.getParameter("key");
		FormatImage formatImage = FormatImage.png;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		qrCodeService.writeQRCodeToStream(key, 200, 200, byteArrayOutputStream,
				formatImage, 9);

		logger.info("Se ha generado el array de este tamaÃ±o "
				+ byteArrayOutputStream.size());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Disposition", "attachment; filename="
				+ key + "." + formatImage.toString());

		switch (formatImage) {
		case png:
			responseHeaders.setContentType(MediaType.IMAGE_PNG);
			break;
		}

		responseHeaders.setContentLength(byteArrayOutputStream.size());

		logger.info("El ToString de responseHeaders es "
				+ responseHeaders.toString());

		return new ResponseEntity<byte[]>(
				(byte[]) byteArrayOutputStream.toByteArray(), responseHeaders,
				HttpStatus.CREATED);
	}

}