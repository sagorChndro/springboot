package com.sagor.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarCodeGen {
	public static void main(String[] args) throws IOException {
//		Code39Bean bean1 = new Code39Bean();
//		final int dpi1 = 150;
//		// configure the barcade generator
//		bean1.setModuleWidth(UnitConv.in2mm(1.0f / dpi1)); // makes the narrow bar
//		// width exactly one pixel
//		bean1.setWideFactor(3);
//		bean1.doQuietZone(false);
//		// open output file
//		File outputFile1 = new File("");
//		OutputStream out1 = new FileOutputStream(outputFile1);
//		try {
//			// set the canvas provider for monochrome PNG output
//			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out1, "image/x.png", dpi1,
//					BufferedImage.TYPE_BYTE_BINARY, false, 0);
//			// generate the bar code
//			bean1.generateBarcode(canvas, "Sagor");
//			// Signal end of generation
//			canvas.finish();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			out1.close();
//		}
//
//		createBarCode128("0129287342");
		// return outputFile;
	}

	public static void createBarCode128(String fileName) {
		try {
			Code128Bean bean = new Code128Bean();
			final int dpi = 160;
			bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));
			bean.doQuietZone(false);
			File outputFile = new File("G:\\barcode image\\out.png");
			if (!outputFile.exists()) {
				outputFile.mkdir();
			}
			FileOutputStream out = new FileOutputStream(outputFile);
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
			bean.generateBarcode(canvas, fileName);
			canvas.finish();

			System.out.println("Bar code is generated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
