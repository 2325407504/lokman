package com.aripd.qrcode.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.qrcode.service.QRCodeService;
import com.aripd.qrcode.utils.ByteMatrixPngEncoder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.ByteMatrix;

/*
 * Copyright (C) 2009 Francois Masurel
 *
 * This program is free software: you can redistribute it and/or
 modify
 * it under the terms of the GNU General Public License as published
 by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/
 >.
 */

@Service("qrCodeService")
@Transactional
public class QRCodeServiceImpl implements QRCodeService {

	private BitMatrix getBitMatrix(String content, int width, int height) {

		QRCodeWriter qRCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = null;

		try {
			bitMatrix = qRCodeWriter.encode(content, BarcodeFormat.QR_CODE,
					width, height, null);
		} catch (WriterException e) {
			e.printStackTrace();
		}

		return bitMatrix;
	}

	public enum FormatImage {
		png
	};

	@Override
	public void writeQRCodeToFile(String content, int width, int height,
			File file, FormatImage formatImage) throws IOException {
		BitMatrix bitMatrix = getBitMatrix(content, width, height);
		MatrixToImageWriter
				.writeToFile(bitMatrix, formatImage.toString(), file);
	}

	@Override
	public void writeQRCodeToStream(String content, int width, int height,
			OutputStream outputStream, FormatImage formatImage)
			throws IOException {
		BitMatrix bitMatrix = getBitMatrix(content, width, height);
		MatrixToImageWriter.writeToStream(bitMatrix, formatImage.toString(),
				outputStream);
	}

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private ByteMatrix toByteMatrix(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		ByteMatrix byteMatrix = new ByteMatrix(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				byteMatrix.set(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return byteMatrix;
	}

	private void writeToStream(BitMatrix matrix, String format,
			OutputStream stream, int compressionLevel) throws IOException {
		ByteMatrix byteMatrix = toByteMatrix(matrix);
		ByteMatrixPngEncoder byteMatrixPngEncoder = new ByteMatrixPngEncoder(
				byteMatrix, compressionLevel);
		stream.write(byteMatrixPngEncoder.pngEncode());
	}

	@Override
	public void writeQRCodeToStream(String content, int width, int height,
			OutputStream outputStream, FormatImage formatImage,
			int compressionLevel) throws IOException {
		if (FormatImage.png != formatImage) {
			throw new IllegalArgumentException(
					"Solo se puede generar en formato PNG");
		}
		BitMatrix bitMatrix = getBitMatrix(content, width, height);
		this.writeToStream(bitMatrix, formatImage.toString(), outputStream,
				compressionLevel);
	}

}