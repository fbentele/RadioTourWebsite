package ch.hsr.ba.tourlive.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {
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

	public static File safeCsv(CommonsMultipartFile file, String absolutePath, String relativePath) {
		file.getOriginalFilename();
		File filePath = new File(absolutePath + relativePath);
		if (!filePath.exists()) {
			filePath.mkdir();
		}
		File dest = new File(filePath, file.getOriginalFilename());
		try {
			file.transferTo(dest);
			return dest;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public static void deleteFile(String file) {
		File theFile = new File(file);
		try {
			theFile.delete();
		} catch (SecurityException e) {
			// no worries
		}
	}

}
