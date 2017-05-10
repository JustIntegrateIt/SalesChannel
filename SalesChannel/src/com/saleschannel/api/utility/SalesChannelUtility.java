package com.saleschannel.api.utility;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.flatfile.ColumnsJsonModel;
import com.saleschannel.api.flatfile.FlatFileJsonModel;
import com.saleschannel.api.flatfile.MergeCellsRowJsonModel;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
	
	/**
	 * Get MD5 Hash Value for a file stream.
	 *
	 * @param filePath the path of file
	 * @return hashValue
	 */
	public static String computeContentMD5Value(String filePath){
		String md5Content = null;
		DigestInputStream dis = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			dis = new DigestInputStream( fis,MessageDigest.getInstance( "MD5" ));

	        byte[] buffer = new byte[dis.available()];
	        while( dis.read( buffer ) > 0 );

	        md5Content = new String(
	            org.apache.commons.codec.binary.Base64.encodeBase64(
	                dis.getMessageDigest().digest()) ); 

	        // Effectively resets the stream to be beginning of the file
	        // via a FileChannel.
	        fis.getChannel().position( 0 );
		} catch(Exception e) {
			LOGGERS.error("error occured while get MD5 hashValue for file : "+filePath);
			e.printStackTrace();
		} finally {
			try{
				if(dis != null) {
					dis.close();
				}
			} catch(Exception e) {
				LOGGERS.error("error occured while close file inputstream");
				e.printStackTrace();
			}
		}
		return md5Content;
	}
	
	/**
	 * Consume the stream and return its Base-64 encoded MD5 checksum.
	 * @param inputStream
	 * @return hashValue
	 */
	public static String computeContentMD5Header(InputStream inputStream) {
	    // Consume the stream to compute the MD5 as a side effect.
	    DigestInputStream s;
	    String md5Content = null;
	    try {
	        s = new DigestInputStream(inputStream, MessageDigest.getInstance("MD5"));
	        // drain the buffer, as the digest is computed as a side-effect
	        byte[] buffer = new byte[8192];
	        while(s.read(buffer) > 0);
	        md5Content = new String(org.apache.commons.codec.binary.Base64.encodeBase64(
	                s.getMessageDigest().digest()), "UTF-8");
	    } catch (NoSuchAlgorithmException e) {
	    	LOGGERS.error("error occured while computeContentMD5Header");
	        e.printStackTrace();
	    } catch (IOException e) {
	    	LOGGERS.error("error occured while computeContentMD5Header");
	    	e.printStackTrace();
	    }
	    return md5Content;
	}
	
	/**
	 * Method used to create Amazon Flat File.
	 *
	 * @param flatFileJsonModel
	 * @return String filePath
	 */
	@SuppressWarnings("resource")
	public static String prepareFlatFile(FlatFileJsonModel flatFileJsonModel, String filePath) {
		if (flatFileJsonModel != null) {
			try {
				Workbook wb = new XSSFWorkbook();
				Sheet sheet = wb.createSheet(flatFileJsonModel.getType());
				if(flatFileJsonModel.getMergeCells() != null && flatFileJsonModel.getMergeCells().size() > 0) {
					for(int i = 0; flatFileJsonModel.getMergeCells().size() > i; i++) {
						Map<String, List<MergeCellsRowJsonModel>> obj = flatFileJsonModel.getMergeCells().get(i);
						for(String key : obj.keySet()) {
							List<MergeCellsRowJsonModel> mergeCellsRowJsonModelList = obj.get(key);
							if(mergeCellsRowJsonModelList != null && mergeCellsRowJsonModelList.size() > 0) {
								int fromColumn = 0;
								int toColumn = 0;
								if(key.equals("row1")) {
									for(MergeCellsRowJsonModel mergeCellsRowJsonModel : mergeCellsRowJsonModelList) {
										if(mergeCellsRowJsonModel.getFrom() != null && mergeCellsRowJsonModel.getFrom().length() > 1) {
											fromColumn = (26 * (getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(0)) + 1)) + getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(1));
										} else {
											fromColumn = getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(0));
										}
										if(mergeCellsRowJsonModel.getTo() != null && mergeCellsRowJsonModel.getTo().length() > 1) {
											toColumn = (26 * (getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(0)) + 1)) + getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(1));
										} else {
											toColumn = getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(0));
										}
										sheet.addMergedRegion(new CellRangeAddress(0, 0, fromColumn, toColumn));
									}
								}
								if(key.equals("row2")) {
									for(MergeCellsRowJsonModel mergeCellsRowJsonModel : mergeCellsRowJsonModelList) {
										if(mergeCellsRowJsonModel.getFrom() != null && mergeCellsRowJsonModel.getFrom().length() > 1) {
											fromColumn = (26 * (getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(0)) + 1)) + getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(1));
										} else {
											fromColumn = getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(0));
										}
										if(mergeCellsRowJsonModel.getTo() != null && mergeCellsRowJsonModel.getTo().length() > 1) {
											toColumn = (26 * (getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(0)) + 1)) + getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(1));
										} else {
											toColumn = getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(0));
										}
										sheet.addMergedRegion(new CellRangeAddress(1, 1, fromColumn, toColumn));
									}
								}
								if(key.equals("row3")) {
									for(MergeCellsRowJsonModel mergeCellsRowJsonModel : mergeCellsRowJsonModelList) {
										if(mergeCellsRowJsonModel.getFrom() != null && mergeCellsRowJsonModel.getFrom().length() > 1) {
											fromColumn = (26 * (getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(0)) + 1)) + getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(1));
										} else {
											fromColumn = getCharacterValue(mergeCellsRowJsonModel.getFrom().charAt(0));
										}
										if(mergeCellsRowJsonModel.getTo() != null && mergeCellsRowJsonModel.getTo().length() > 1) {
											toColumn = (26 * (getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(0)) + 1)) + getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(1));
										} else {
											toColumn = getCharacterValue(mergeCellsRowJsonModel.getTo().charAt(0));
										}
										sheet.addMergedRegion(new CellRangeAddress(2, 2, fromColumn, toColumn));
									}
								}
							}
						}
					}
				}

				// row numbering starts from 0
				int r = 0; //row
				do {
					Row row = sheet.createRow(r);
					int c = 0; //cell
					for(int i = 0; flatFileJsonModel.getColumns().size() > i; i++) {
						Map<String, ColumnsJsonModel> obj = flatFileJsonModel.getColumns().get(i);
						for(String key : obj.keySet()) {
							ColumnsJsonModel columnsJsonModel = obj.get(key);
							Cell cell = row.createCell(c);
							if(r == 0)
								cell.setCellValue(columnsJsonModel.getRow1());
							if(r == 1)
								cell.setCellValue(columnsJsonModel.getRow2());
							if(r == 2)
								cell.setCellValue(columnsJsonModel.getRow3());
						}
						c++;
					}
					r++;
				} while(r < 3);

				FileOutputStream fos = new FileOutputStream(filePath);
				wb.write(fos);
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return filePath;
	}
	
	/**
	 * Method used to prepare xlsx Amazon Flat File.
	 *
	 * @param File inputFile, File outputFile
	 */
	@SuppressWarnings("resource")
	public static void xls(File inputFile, File outputFile) {
		//For storing data into CSV files
		StringBuffer data = new StringBuffer();
	    try {
	    	FileOutputStream fos = new FileOutputStream(outputFile);

	    	//Get the workbook object for XLSX file
	    	XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(inputFile));
	    	//Get first sheet from the workbook
	    	XSSFSheet xlsxSheet = workbook.getSheetAt(0);
	    	Cell cell = null;
	    	Row row = null;

	    	//Iterate through each rows from first sheet
	    	Iterator<Row> rowIterator = xlsxSheet.iterator();
	    	while (rowIterator.hasNext()) {
	    		row = rowIterator.next();
	    		//For each row, iterate through each columns
	            Iterator<Cell> cellIterator = row.cellIterator();
	            while (cellIterator.hasNext()) {
	            	cell = cellIterator.next();
	            	switch (cell.getCellType()) {
	            	
	            	case Cell.CELL_TYPE_BOOLEAN:
	            		data.append(cell.getBooleanCellValue() + "\t");
	            		break;
	                            
	            	case Cell.CELL_TYPE_NUMERIC:
	            		data.append(cell.getNumericCellValue() + "\t");
	            		break;
	                            
	            	case Cell.CELL_TYPE_STRING:
	            		data.append(cell.getStringCellValue() + "\t");
	            		break;

	            	case Cell.CELL_TYPE_BLANK:
	            		data.append("" + "\t");
	            		break;
	                    
	            	default:
	            		data.append(cell + "\t");
	            	}
	            }
	            data.append('\n'); 
	    	}
	    	
	    	fos.write(data.toString().getBytes());
	    	fos.close();
	    }
	    catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * Method used to get ascending value for alphabets starts a=0.
	 *
	 * @param character
	 * @return integer character value
	 */
	public static int getCharacterValue(char character) {
		int value = 0;
		try {
			for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
			    if(alphabet == character) {
			    	break;
			    }
			    value++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * Method used to get brows nodes.
	 *
	 * @param List<String> browseNodeList
	 * @return String browseNodes
	 */
	public static String getBrowseNodes(List<String> browseNodeList) {
		String browseNodes = "";
		try {
			if(browseNodeList != null && browseNodeList.size() > 0) {
				int size = browseNodeList.size();
				int i = 0; 
				for(String browseNode : browseNodeList) {
					if(browseNode != null && !browseNode.isEmpty() && size > i) {
						browseNodes = browseNodes + browseNode + ",";
					} else if(browseNode != null && !browseNode.isEmpty()) {
						browseNodes = browseNodes + browseNode;
					}
					i++;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return browseNodes;
	}
	
	/**
	 * Method to find a string contains special character.
	 *
	 * @param String value
	 * @return boolean contains
	 */
	public static boolean isValidString(String value) {
		boolean contains = false;
		try {
			Pattern pattern = Pattern.compile("[^A-Za-z]");
			Matcher matcher = pattern.matcher(value);
			if(matcher.find()) {
				contains = false;
			} else {
				contains = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return contains;
	}
	
	/**
	 * Method to find a alphanumeric contains special character.
	 *
	 * @param String value
	 * @return boolean contains
	 */
	public static boolean isValidAlphanumeric(String value) {
		boolean contains = false;
		try {
			Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
			Matcher matcher = pattern.matcher(value);
			if(matcher.find()) {
				contains = false;
			} else {
				contains = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return contains;
	}
	
	/**
	 * Method to find a number contains special character.
	 *
	 * @param String value
	 * @return boolean contains
	 */
	public static boolean isValidNumber(String value) {
		boolean contains = false;
		try {
			Pattern pattern = Pattern.compile("[^0-9]");
			Matcher matcher = pattern.matcher(value);
			if(matcher.find()) {
				contains = false;
			} else {
				contains = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return contains;
	}
	
	/**
	 * Method to find a number contains special character.
	 *
	 * @param String value
	 * @return boolean contains
	 */
	public static boolean isValidDecimal(String value) {
		boolean contains = false;
		try {
			contains = value.matches("[0-9.]*");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return contains;
	}
}
