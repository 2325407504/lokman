package com.aripd.qrcode.service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.aripd.qrcode.service.impl.QRCodeServiceImpl.FormatImage;

public interface QRCodeService {

	/**
	 * FunciÃ³n NO vÃ¡lida para usar Zxing en AppEngine ya que usa el paquete java.awt.iamge.*
	 * Te escribe la imagen en un File
	 * @param content
	 * @param width
	 * @param height
	 * @param outputStream
	 * @param formatImage
	 * @throws IOException
	 */
	void writeQRCodeToFile(String content, int width, int height, File file,
			FormatImage formatImage) throws IOException;

	/**
	 * FunciÃ³n NO vÃ¡lida para usar Zxing en AppEngine ya que usa el paquete java.awt.iamge.*
	 * Te escribe la imagen en un Stream
	 * @param content
	 * @param width
	 * @param height
	 * @param outputStream
	 * @param formatImage
	 * @throws IOException
	 */
	void writeQRCodeToStream(String content, int width, int height,
			OutputStream outputStream, FormatImage formatImage)
			throws IOException;

	/**
	 * FunciÃ³n vÃ¡lida para usar Zxing en AppEngine ya que no usa el paquete java.awt.iamge.*
	 * Te escribe la imagen en un Stream
	 * @param content
	 * @param width
	 * @param height
	 * @param outputStream
	 * @param formatImage PNG es el Ãºnico formato funcionando
	 * @param compressionLevel 1..9
	 * @throws IOException
	 */
	void writeQRCodeToStream(String content, int width, int height,
			OutputStream outputStream, FormatImage formatImage,
			int compressionLevel) throws IOException;
}