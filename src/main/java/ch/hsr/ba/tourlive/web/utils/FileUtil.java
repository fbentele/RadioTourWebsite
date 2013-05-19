package ch.hsr.ba.tourlive.web.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;

public class FileUtil {
	private final static Logger LOG = LoggerFactory.getLogger(FileUtil.class);

	public static String safePng(CommonsMultipartFile multipartImage, String absolutePath,
			String relativePath, String filename) {
		if (!multipartImage.isEmpty()) {
			InputStream iStream = null;
			try {
				iStream = multipartImage.getInputStream();
				BufferedImage sourceImage = ImageIO.read(iStream);
				File serverPath = new File(absolutePath + relativePath);
				if (!serverPath.exists()) {
					LOG.info("create server path:" + serverPath.mkdir());
				}
				ImageIO.write(sourceImage, "png", new File(serverPath, filename));
			} catch (IOException e) {
				LOG.info("File could not be saved");
			} catch (IllegalArgumentException e) {
				LOG.info("one argument was illegal");
			}
			return relativePath + "/" + filename;
		}
		return null;
	}

	public static String safeVideo(CommonsMultipartFile video, String absolutePath,
			String relativePath, String filename) {
		File filePath = new File(absolutePath + relativePath);
		String extension = video.getOriginalFilename().substring(
				video.getOriginalFilename().lastIndexOf('.'));
		if (!filePath.exists()) {
			LOG.info("new folder: " + filePath.mkdir());
		}
		File dest = new File(filePath, filename + extension);
		try {
			video.transferTo(dest);
			return relativePath + "/" + filename;
		} catch (IOException e) {
			LOG.info("File could not be saved");
		} finally {
			convertTo(absolutePath + relativePath + "/" + filename, extension, ".ogg");
		}
		return "";
	}

	public static File safeCsv(CommonsMultipartFile file, String absolutePath, String relativePath) {
		file.getOriginalFilename();
		File filePath = new File(absolutePath + relativePath);
		if (!filePath.exists()) {
			LOG.info("new folder: " + filePath.mkdir());
		}
		File dest = new File(filePath, file.getOriginalFilename());
		try {
			file.transferTo(dest);
			return dest;
		} catch (IllegalStateException e) {
			LOG.info("one argument was illegal");
		} catch (IOException e) {
			LOG.info("File could not be saved");
		}
		return null;
	}

	public static void deleteFile(String file) {
		File theFile = new File(file);
		try {
			theFile.delete();
			LOG.info("File deleted " + file);
		} catch (SecurityException e) {
			LOG.info("File could not be deleted");
		}
	}

	public static void convertTo(String filePath, String extension, String target) {
		IMediaReader reader = ToolFactory.makeReader(filePath + extension);
		IMediaWriter writer = ToolFactory.makeWriter(filePath + target, reader);
		reader.addListener(writer);
		try {
			while (reader.readPacket() == null) {
			}
		} catch (RuntimeException e) {
			LOG.error("videofile not found");
		}
	}
}
