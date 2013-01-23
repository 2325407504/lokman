package com.aripd.qrcode.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

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
>
*/
public class ByteMatrixPngEncoder extends Object {

	/** IHDR tag. */
	protected static final byte IHDR[] = { 73, 72, 68, 82 };

	/** IDAT tag. */
	protected static final byte IDAT[] = { 73, 68, 65, 84 };

	/** IEND tag. */
	protected static final byte IEND[] = { 73, 69, 78, 68 };

	/** Constants for filter (NONE) */
	protected static final int FILTER_NONE = 0;

	protected static final byte BLACK = (byte) 0x00;
	protected static final byte WHITE = (byte) 0xFF;

	/** The png bytes. */
	protected byte[] pngBytes;

	/** The prior row. */
	protected byte[] priorRow;

	/** The left bytes. */
	protected byte[] leftBytes;

	/** The width. */
	protected int width, height;

	/** The byte position. */
	protected int bytePos, maxPos;

	/** CRC. */
	protected CRC32 crc = new CRC32();

	/** The CRC value. */
	protected long crcValue;

	/** The bytes-per-pixel. */
	protected int bytesPerPixel;

	/** The compression level. */
	protected int compressionLevel;

	private ByteMatrix matrix;

	/**
	 * Class constructor specifying Image source to encode, whether to encode
	 * alpha, filter to use, and compression level.
	 * 
	 * @param image
	 *            A Java Image object
	 * @param encodeAlpha
	 *            Encode the alpha channel? false=no; true=yes
	 * @param whichFilter
	 *            0=none, 1=sub, 2=up
	 * @param compLevel
	 *            0..9
	 * @see java.awt.Image
	 */
	public ByteMatrixPngEncoder(ByteMatrix matrix, int compLevel) {
		this.matrix = matrix;
		this.width = matrix.getWidth();
		this.height = matrix.getHeight();
		this.bytesPerPixel = 1;
		if (compLevel >= 0 && compLevel <= 9) {
			this.compressionLevel = compLevel;
		}
	}

	/**
	 * Creates an array of bytes that is the PNG equivalent of the current
	 * image, specifying whether to encode alpha or not.
	 * 
	 * @param encodeAlpha
	 *            boolean false=no alpha, true=encode alpha
	 * @return an array of bytes, or null if there was a problem
	 */
	public byte[] pngEncode() {

		byte[] pngIdBytes = { -119, 80, 78, 71, 13, 10, 26, 10 };

		/*
		 * start with an array that is big enough to hold all the pixels (plus
		 * filter bytes), and an extra 200 bytes for header info
		 */
		pngBytes = new byte[((width + 1) * height * 3) + 200];

		/*
		 * keep track of largest byte written to the array
		 */
		maxPos = 0;

		bytePos = writeBytes(pngIdBytes, 0);
		// hdrPos = bytePos;
		writeHeader();
		// dataPos = bytePos;
		if (writeImageData()) {
			writeEnd();
			pngBytes = resizeByteArray(pngBytes, maxPos);
		} else {
			pngBytes = null;
		}
		return pngBytes;
	}

	/**
	 * Set the compression level to use
	 * 
	 * @param level
	 *            0 through 9
	 */
	public void setCompressionLevel(int level) {
		if (level >= 0 && level <= 9) {
			this.compressionLevel = level;
		}
	}

	/**
	 * Retrieve compression level
	 * 
	 * @return int in range 0-9
	 */
	public int getCompressionLevel() {
		return compressionLevel;
	}

	/**
	 * Increase or decrease the length of a byte array.
	 * 
	 * @param array
	 *            The original array.
	 * @param newLength
	 *            The length you wish the new array to have.
	 * @return Array of newly desired length. If shorter than the original, the
	 *         trailing elements are truncated.
	 */
	protected byte[] resizeByteArray(byte[] array, int newLength) {
		byte[] newArray = new byte[newLength];
		int oldLength = array.length;

		System.arraycopy(array, 0, newArray, 0, Math.min(oldLength, newLength));
		return newArray;
	}

	/**
	 * Write an array of bytes into the pngBytes array. Note: This routine has
	 * the side effect of updating maxPos, the largest element written in the
	 * array. The array is resized by 1000 bytes or the length of the data to be
	 * written, whichever is larger.
	 * 
	 * @param data
	 *            The data to be written into pngBytes.
	 * @param offset
	 *            The starting point to write to.
	 * @return The next place to be written to in the pngBytes array.
	 */
	protected int writeBytes(byte[] data, int offset) {
		maxPos = Math.max(maxPos, offset + data.length);
		if (data.length + offset > pngBytes.length) {
			pngBytes = resizeByteArray(pngBytes,
					pngBytes.length + Math.max(1000, data.length));
		}
		System.arraycopy(data, 0, pngBytes, offset, data.length);
		return offset + data.length;
	}

	/**
	 * Write an array of bytes into the pngBytes array, specifying number of
	 * bytes to write. Note: This routine has the side effect of updating
	 * maxPos, the largest element written in the array. The array is resized by
	 * 1000 bytes or the length of the data to be written, whichever is larger.
	 * 
	 * @param data
	 *            The data to be written into pngBytes.
	 * @param nBytes
	 *            The number of bytes to be written.
	 * @param offset
	 *            The starting point to write to.
	 * @return The next place to be written to in the pngBytes array.
	 */
	protected int writeBytes(byte[] data, int nBytes, int offset) {
		maxPos = Math.max(maxPos, offset + nBytes);
		if (nBytes + offset > pngBytes.length) {
			pngBytes = resizeByteArray(pngBytes,
					pngBytes.length + Math.max(1000, nBytes));
		}
		System.arraycopy(data, 0, pngBytes, offset, nBytes);
		return offset + nBytes;
	}

	/**
	 * Write a two-byte integer into the pngBytes array at a given position.
	 * 
	 * @param n
	 *            The integer to be written into pngBytes.
	 * @param offset
	 *            The starting point to write to.
	 * @return The next place to be written to in the pngBytes array.
	 */
	protected int writeInt2(int n, int offset) {
		byte[] temp = { (byte) ((n >> 8) & 0xff), (byte) (n & 0xff) };
		return writeBytes(temp, offset);
	}

	/**
	 * Write a four-byte integer into the pngBytes array at a given position.
	 * 
	 * @param n
	 *            The integer to be written into pngBytes.
	 * @param offset
	 *            The starting point to write to.
	 * @return The next place to be written to in the pngBytes array.
	 */
	protected int writeInt4(int n, int offset) {
		byte[] temp = { (byte) ((n >> 24) & 0xff), (byte) ((n >> 16) & 0xff),
				(byte) ((n >> 8) & 0xff), (byte) (n & 0xff) };
		return writeBytes(temp, offset);
	}

	/**
	 * Write a single byte into the pngBytes array at a given position.
	 * 
	 * @param b
	 *            The integer to be written into pngBytes.
	 * @param offset
	 *            The starting point to write to.
	 * @return The next place to be written to in the pngBytes array.
	 */
	protected int writeByte(int b, int offset) {
		byte[] temp = { (byte) b };
		return writeBytes(temp, offset);
	}

	/**
	 * Write a PNG "IHDR" chunk into the pngBytes array.
	 */
	protected void writeHeader() {
		int startPos;

		startPos = bytePos = writeInt4(13, bytePos);
		bytePos = writeBytes(IHDR, bytePos);
		bytePos = writeInt4(width, bytePos);
		bytePos = writeInt4(height, bytePos);
		bytePos = writeByte(8, bytePos); // bit depth
		bytePos = writeByte(0, bytePos); // gray scale
		bytePos = writeByte(0, bytePos); // compression method
		bytePos = writeByte(0, bytePos); // filter method
		bytePos = writeByte(0, bytePos); // no interlace
		crc.reset();
		crc.update(pngBytes, startPos, bytePos - startPos);
		crcValue = crc.getValue();
		bytePos = writeInt4((int) crcValue, bytePos);
	}

	/**
	 * Write the image data into the pngBytes array. This will write one or more
	 * PNG "IDAT" chunks. In order to conserve memory, this method grabs as many
	 * rows as will fit into 32K bytes, or the whole image; whichever is less.
	 * 
	 * 
	 * @return true if no errors; false if error grabbing pixels
	 */
	protected boolean writeImageData() {

		byte[] compressedLines; // the resultant compressed lines
		int nCompressed; // how big is the compressed area?

		byte[] pixels; // storage area for byte-sized pixels

		Deflater scrunch = new Deflater(compressionLevel);
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream(1024);

		DeflaterOutputStream compBytes = new DeflaterOutputStream(outBytes,
				scrunch);

		try {
			int index = 0;
			pixels = new byte[this.width * this.height + this.height];
			for (int y = 0; y < this.height; y++) {
				pixels[index++] = 0; // No filter
				for (int x = 0; x < this.width; x++) {
					byte pixel = this.matrix.get(x, y);
					if (pixel == 0) { // BLACK
						pixels[index++] = BLACK;
					} else { // WHITE
						pixels[index++] = WHITE;
					}
				}
			}

			compBytes.write(pixels, 0, pixels.length);

			compBytes.close();

			/*
			 * Write the compressed bytes
			 */
			compressedLines = outBytes.toByteArray();
			nCompressed = compressedLines.length;

			crc.reset();
			bytePos = writeInt4(nCompressed, bytePos);
			bytePos = writeBytes(IDAT, bytePos);
			crc.update(IDAT);
			bytePos = writeBytes(compressedLines, nCompressed, bytePos);
			crc.update(compressedLines, 0, nCompressed);

			crcValue = crc.getValue();
			bytePos = writeInt4((int) crcValue, bytePos);
			scrunch.finish();
			return true;
		} catch (IOException e) {
			System.err.println(e.toString());
			return false;
		}
	}

	/**
	 * Write a PNG "IEND" chunk into the pngBytes array.
	 */
	protected void writeEnd() {
		bytePos = writeInt4(0, bytePos);
		bytePos = writeBytes(IEND, bytePos);
		crc.reset();
		crc.update(IEND);
		crcValue = crc.getValue();
		bytePos = writeInt4((int) crcValue, bytePos);
	}

}