package net.saleschannel.api.utility;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import net.saleschannel.api.constants.SalesChannelConstants;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public final class SalesChannelUtility {

	private static final Logger LOGGERS = Logger.getLogger(SalesChannelUtility.class);
	
	/**
	 * This member function will generate UUID.
	 *
	 * @param isHiphenRequired the is hiphen required
	 * @return String
	 */
	public static String getUUID(final boolean isHiphenRequired) {
		final String uuid = UUID.randomUUID().toString();
		if (!isHiphenRequired) {
			final String[] tokenizer = uuid.split("-");
			final StringBuffer uuidString = new StringBuffer();
			for (int i = 0; i < tokenizer.length; i++) {
				final String uid = tokenizer[i].toString();
				uuidString.append(uid);
			}
			return uuidString.toString();
		} else {
			return uuid;
		}
	}
	
	/**
	 * Method used to validate parameter values.
	 *
	 * @param paramName the param name
	 * @param paramValue the param value
	 * @return JSONObject
	 */
	public static JSONObject validateParameters(final String paramName, final String paramValue) {
		final JSONObject jsonObject = new JSONObject();
		if (paramValue == null) {
			try {
				jsonObject.put("1004", paramName + " value is empty.@#" + paramName + "#@");
			} catch (final JSONException e) {
				LOGGERS.error("Exception in forming the JSON: " + e.getMessage());
			}
		}
		return jsonObject;
	}
	
	/**
	 * Method used to getImage from URL.
	 *
	 * @param imageURL the image url link
	 * @param saveToPath the save to path in local
	 * @return path of the image saved
	 */
	public static String getImagefromUrl(String imageURL, String saveToPath) {
		BufferedImage image = null;	
		String imageName = null;
		boolean isProcessed = false;
		try {
			File theDir = new File(saveToPath);
			boolean result = true;
			// if the directory does not exist, create it
			if (!theDir.exists()) {
				LOGGERS.debug("creating directory: " + saveToPath);
				try{
					theDir.mkdirs();
			    } 
			    catch(Exception e){
			    	result = false;
			    	LOGGERS.debug("error creating directory: " + saveToPath);
			    	e.printStackTrace();
			    }        
			}
			if(result) {    
				URL url = new URL(imageURL);
				HttpURLConnection huc = (HttpURLConnection) url.openConnection();
				int responseCode = huc.getResponseCode();
				if (responseCode != 404) {
					image = ImageIO.read(url);
					imageName = saveToPath + SalesChannelConstants.FILE_SEPERATOR + getUUID(false) + SalesChannelConstants.DOT_SEPERATOR 
							+ SalesChannelConstants.PNG;
					ImageIO.write(image, SalesChannelConstants.PNG, new File(imageName));
					isProcessed = true;
				} else {
					LOGGERS.debug("error saving image : " + imageName);
				}
		    }
		} catch (Exception e) {
			LOGGERS.debug("error reading image from URL: " + imageURL);
			e.printStackTrace();
		}
		if(!isProcessed) {
			imageName = null;
		}
		return imageName;
	}
	
	/**
	 * Method used to delete image.
	 *
	 * @param imagePath the path of image saved in local
	 * @return boolean
	 */
	public static boolean deleteImage(String imagePath) {
		boolean isProcessed = false;
		try {
			File image = new File(imagePath);
			if (image.exists()) {
				try{
					isProcessed = image.delete();
			    } 
			    catch(Exception e){
			    	LOGGERS.debug("error while delete image: " + imagePath);
			    	e.printStackTrace();
			    }        
			}
		} catch (Exception e) {
			LOGGERS.debug("error delete image");
			e.printStackTrace();
		}
		return isProcessed;
	}
	
	/**
	 * Method used to convert image stream string into image.
	 *
	 * @param imageStream the image stream as string
	 * @param saveToPath the save to path in local
	 * @return path of the image saved
	 */
	public static String createImagefromStream(String imageStream, String saveToPath) {
		BufferedImage image = null;	
		String imageName = null;
		boolean isProcessed = false;
		try {
			File theDir = new File(saveToPath);
			boolean result = true;
			// if the directory does not exist, create it
			if (!theDir.exists()) {
				LOGGERS.debug("creating directory: " + saveToPath);
				try{
					theDir.mkdirs();
			    } 
			    catch(Exception e){
			    	result = false;
			    	LOGGERS.debug("error creating directory: " + saveToPath);
			    	e.printStackTrace();
			    }        
			}
			if(result) {    
				InputStream imageInputStream = new ByteArrayInputStream(imageStream.getBytes());
				image = ImageIO.read(imageInputStream);
				imageName = saveToPath + SalesChannelConstants.FILE_SEPERATOR + getUUID(false) + SalesChannelConstants.DOT_SEPERATOR 
						+ SalesChannelConstants.PNG;
				ImageIO.write(image, SalesChannelConstants.PNG, new File(imageName));
				isProcessed = true;
		    }
		} catch (Exception e) {
			LOGGERS.debug("error converting image stream into image");
			e.printStackTrace();
		}
		if(!isProcessed) {
			imageName = null;
		}
		return imageName;
	}
	
	/**
	 * Method used to convert image into InputStream.
	 *
	 * @param imagePath the path where image saved in local
	 * @return InputStream of image
	 */
	public static InputStream convertImageIntoStream(String imagePath) {
		InputStream imageStream = null;
		try {
			File image = new File(imagePath);
			if (image.exists()) {
				LOGGERS.info("image exist in:"+imagePath);
				try{
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					ImageIO.write(ImageIO.read(image), "png", os);
					imageStream = new ByteArrayInputStream(os.toByteArray());
			    } 
			    catch(Exception e){
			    	LOGGERS.debug("error while convert image into stream" + imagePath);
			    	e.printStackTrace();
			    }        
			} else {
				LOGGERS.info("image not exist in:"+imagePath);
			}
		} catch (Exception e) {
			LOGGERS.debug("error converting image into stream");
			e.printStackTrace();
		}
		return imageStream;
	}
	
	/**
	 * Method used to convertImage format.
	 *
	 * @param imagePath the image directory path
	 * @param actualPath the actual path
	 * @param imageName the image name
	 * @param toWidth the width value to convert
	 * @param toHeight the height value to convert
	 * @param totype the image type/extension to convert
	 * @return String image path
	 */
	public static String imageConvertion(String imagePath, String actualPath, String imageName, 
			int toWidth, int toHeight, String toType) {
		String convertedImage = null;
		boolean status = false;
		try {
			File image = new File(actualPath);
				if (image.exists()) {
					convertedImage = imagePath + SalesChannelConstants.FILE_SEPERATOR 
							+ imageName + SalesChannelConstants.NAME_SEPERATOR + toWidth 
							+ SalesChannelConstants.CROSS + toHeight + SalesChannelConstants.DOT_SEPERATOR
							+ toType;
					BufferedImage originalImage = ImageIO.read(new File(actualPath));
					int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
							: originalImage.getType();
					BufferedImage resizeImage = resizeImage(originalImage, type, toWidth, toHeight);
					ImageIO.write(resizeImage, toType, new File(convertedImage));
					status = true;
				} else {
					LOGGERS.debug("image not exist : "+actualPath);	
				}
			} catch (Exception e) {
				e.printStackTrace();
				LOGGERS.error("error occured while image convertion : "+actualPath);
			}
		if(!status) {
			convertedImage = null;
		}
		return convertedImage;
	}

	/**
	 * Method used to convertImage size.
	 *
	 * @param originalImage the buffered image
	 * @param toWidth the width value to convert
	 * @param toHeight the height value to convert
	 * @param type the image type/extension to convert
	 * @return BufferedImage
	 */
	public static BufferedImage resizeImage(BufferedImage originalImage, int type, 
			int toWidth, int toHeight) {
		BufferedImage resizedImage = new BufferedImage(toWidth, toHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, toWidth, toHeight, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		return resizedImage;
	}
	
	/**
	 * Method used to check whether the string contains special characters.
	 *
	 * @param String the string value
	 * @return boolean
	 */
	public static boolean checkSpecialCharacters(String string) {
		boolean isPresent = false;
		try {
			Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
			if(string != null && !string.isEmpty()) {
				Matcher matcher = pattern.matcher(string.replace(" ", ""));
			    isPresent = matcher.find();				
			}
		} catch(Exception e) {
			LOGGERS.error("error occured which check a string contains special character.");
			e.printStackTrace();
		}
		return isPresent;
	}
	
}
