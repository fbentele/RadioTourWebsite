package ch.hsr.ba.tourlive.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class SafeImageUtil {
	public static String safe(CommonsMultipartFile multipartImage, String absolutePath,
			String relativePath, String filename) {

		if (!multipartImage.isEmpty()) {
			InputStream iStream = null;
			try {
				iStream = multipartImage.getInputStream();
				BufferedImage sourceImage = ImageIO.read(iStream);
				File serverPath = new File(absolutePath + relativePath);
				if (!serverPath.exists()) {
					serverPath.mkdir();
				}
				ImageIO.write(sourceImage, "png", new File(serverPath, filename));
			} catch (IOException e) {
				// catch exception
			} catch (IllegalArgumentException e) {
				// no image specified
			} finally {
			}
			return relativePath + "/" + filename;
		}
		return null;
	}
}
