package com.felipejanser.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.felipejanser.cursomc.services.exceptions.FileException;

@Service
public class ImageService {

	
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String extensao = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if(!extensao.equals("png") && !extensao.equals("jpg")) {
			throw new FileException("Somente imagens no formato JPG e PNG são permitidas.");
		}
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if(extensao.equals("png")) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo.");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extensao) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extensao, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo.");
		}
	}
	
}
