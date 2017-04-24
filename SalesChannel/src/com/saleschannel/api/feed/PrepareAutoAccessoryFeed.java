package com.saleschannel.api.feed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonaws.mws.samples.SubmitFeedSample;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.product.ProductFulfilledStatusJsonObject;
import com.saleschannel.api.product.ProductJsonObject;
import com.saleschannel.api.utility.SalesChannelUtility;

public class PrepareAutoAccessoryFeed {

	@SuppressWarnings("resource")
	public String getAutoAccessoryFeed(List<ProductJsonObject> productJsonObjectList) {
		String txtFilePath = null;
		try {
			XSSFSheet xlsxSheet = null;
            FileInputStream sourceFile = new FileInputStream(new File(SalesChannelConstants.FLATFILE_SOURCE_PATH+SalesChannelConstants.FILE_AUTO_ACCESSORY));
        	XSSFWorkbook xlsxWorkBook = new XSSFWorkbook(sourceFile);
        	if(xlsxWorkBook != null) {
        		xlsxSheet = xlsxWorkBook.getSheetAt(0);
        	}
        	Cell cell = null;
        	if(xlsxSheet != null && productJsonObjectList != null && productJsonObjectList.size() > 0) {
        		Row columnRow = xlsxSheet.getRow(2);
        		if(columnRow != null) {
        			int rowPosition = 3;
        			for(ProductJsonObject productJsonObject : productJsonObjectList) {
        				Row newRow = xlsxSheet.createRow(rowPosition);
            			Iterator<Cell> cells = xlsxSheet.getRow(2).iterator();
            			int i = 0;
            			while (cells.hasNext() && xlsxSheet.getRow(2).getLastCellNum() > i) {
            				cell = columnRow.getCell(i);
                			if(cell != null && cell.toString().equals("item_sku")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getSkuId());
                			}
                			else if(cell != null && cell.toString().equals("external_product_id")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getId());
                			}
                			/*else if(cell != null && cell.toString().equals("external_product_id_type")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getProductIdType());
                			}*/
                			else if(cell != null && cell.toString().equals("item_name")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getProductName());
                			}
                			/*else if(cell != null && cell.toString().equals("brand_name")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getBranName());
                			}*/
                			/*else if(cell != null && cell.toString().equals("manufacturer")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getManufacturer());
                			}*/
                			else if(cell != null && cell.toString().equals("feed_product_type")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getProductCategory());
                			}
                			/*else if(cell != null && cell.toString().equals("condition_type")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getCondition());
                			}*/
                			else if(cell != null && cell.toString().equals("standard_price")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getCost());
                			}
                			else if(cell != null && cell.toString().equals("quantity")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getQuantity());
                			}
                			/*else if(cell != null && cell.toString().equals("recommended_browse_nodes")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(SalesChannelUtility.getBrowseNodes(productJsonObject.getBrowseNodes()));
                			}*/
                			else if(cell != null && cell.toString().equals("main_image_url")) {
                				Cell newCell =newRow.createCell(i);
                				newCell.setCellValue(productJsonObject.getImage());
                			} else {
                				newRow.createCell(i);
                			}
                			i++;
            			}
            			rowPosition++;
        			}
        		}
        	}
            sourceFile.close();
            String filePath = SalesChannelConstants.FLATFILE_SOURCE_PATH + SalesChannelConstants.FILE_SEPERATOR 
            		+ productJsonObjectList.get(0).getCustomerId() + SalesChannelConstants.FILE_SEPERATOR + UUID.randomUUID().toString().replace("-", "")+".xlsx";
            txtFilePath = SalesChannelConstants.FLATFILE_SOURCE_PATH + SalesChannelConstants.FILE_SEPERATOR 
            		+ productJsonObjectList.get(0).getCustomerId() + SalesChannelConstants.FILE_SEPERATOR + UUID.randomUUID().toString().replace("-", "")+".txt";
            FileOutputStream outFile2 =new FileOutputStream(new File(filePath));
            File inputFile = new File(filePath);
            File outputFile = new File(txtFilePath);
            xlsxWorkBook.write(outFile2);
            outFile2.close();
            xls(inputFile, outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return txtFilePath;
    }
	
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
	            		data.append(cell.getBooleanCellValue() + " ");
	            		break;
	                            
	            	case Cell.CELL_TYPE_NUMERIC:
	            		data.append(cell.getNumericCellValue() + " ");
	            		break;
	                            
	            	case Cell.CELL_TYPE_STRING:
	            		data.append(cell.getStringCellValue() + " ");
	            		break;

	            	case Cell.CELL_TYPE_BLANK:
	            		data.append("" + " ");
	            		break;
	                    
	            	default:
	            		data.append(cell + " ");
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
	
	public ProductFulfilledStatusJsonObject checkAutoAccessoryFeed(List<ProductJsonObject> productJsonObjectList) {
		ProductFulfilledStatusJsonObject productFulfilledStatus = new ProductFulfilledStatusJsonObject();
		List<ProductJsonObject> unfulfilledProducts = new ArrayList<ProductJsonObject>();
		List<ProductJsonObject> fulfilledProducts = new ArrayList<ProductJsonObject>();
		try {
			for(ProductJsonObject productJsonObject : productJsonObjectList) {
				if(productJsonObject.getSkuId() == null || productJsonObject.getSkuId().isEmpty()) {
					unfulfilledProducts.add(productJsonObject);
					break;
				} else if(productJsonObject.getId() == null || productJsonObject.getId().isEmpty()) { //external_product_id
					unfulfilledProducts.add(productJsonObject);
					break;
				} /*else if(productJsonObject.getProductIdType() == null || productJsonObject.getProductIdType().isEmpty()) { //external_product_id_type
					unfulfilledProducts.add(productJsonObject);
					break;
				}*/ else if(productJsonObject.getProductName() == null || productJsonObject.getProductName().isEmpty()) { //item_name
					unfulfilledProducts.add(productJsonObject);
					break;
				} /*else if(productJsonObject.getBranName() == null || productJsonObject.getBranName().isEmpty()) { //brand_name
					unfulfilledProducts.add(productJsonObject);
					break;
				} else if(productJsonObject.getManufacturer() == null || productJsonObject.getManufacturer().isEmpty()) { //manufacturer
					unfulfilledProducts.add(productJsonObject);
					break;
				}*/ else if(productJsonObject.getProductCategory() == null || productJsonObject.getProductCategory().isEmpty()) { //feed_product_type
					unfulfilledProducts.add(productJsonObject);
					break;
				} else if(productJsonObject.getImage() == null || productJsonObject.getImage().isEmpty()) { //main_image_url
					unfulfilledProducts.add(productJsonObject);
					break;
				} else {
					fulfilledProducts.add(productJsonObject);
				}
			}
			if(unfulfilledProducts != null && unfulfilledProducts.size() > 0)
				productFulfilledStatus.setUnfulfilledProducts(unfulfilledProducts);
			if(fulfilledProducts != null && fulfilledProducts.size() > 0)
				productFulfilledStatus.setUnfulfilledProducts(fulfilledProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return productFulfilledStatus;
    }
}
