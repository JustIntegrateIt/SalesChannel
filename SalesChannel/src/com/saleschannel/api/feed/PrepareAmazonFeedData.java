package com.saleschannel.api.feed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonaws.mws.samples.SubmitFeedSample;
import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.flatfile.FlatFileJsonModel;
import com.saleschannel.api.flatfile.FlatFileServiceImpl;
import com.saleschannel.api.utility.SalesChannelBeanLocator;
import com.saleschannel.api.utility.SalesChannelUtility;

public class PrepareAmazonFeedData {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		SubmitFeedResponse submitFeedResponse = null;
		try {
			/*prepare amazon flat file*/
			/*FlatFileServiceImpl flatfileservice = (FlatFileServiceImpl) SalesChannelBeanLocator.getInstance().findBean("flatFileService");
			FlatFileJsonModel flatFile = flatfileservice.getFlatFile("someId");
			SalesChannelUtility.prepareFlatFile(flatFile);*/
            
			XSSFSheet xlsxSheet = null;
            FileInputStream sourceFile = new FileInputStream(new File(SalesChannelConstants.FLATFILE_SOURCE_PATH+"/autofile.xlsx"));
        	XSSFWorkbook xlsxWorkBook = new XSSFWorkbook(sourceFile);
        	if(xlsxWorkBook != null) {
        		xlsxSheet = xlsxWorkBook.getSheetAt(0);
        	}
        	Cell cell = null;
        	if(xlsxSheet != null) {
        		Row columnRow = xlsxSheet.getRow(2);
        		if(columnRow != null) {
        			Row newRow = xlsxSheet.createRow(3);
        			Iterator<Cell> cells = xlsxSheet.getRow(2).iterator();
        			int i = 0;
        			while (cells.hasNext() && xlsxSheet.getRow(2).getLastCellNum() > i) {
        				cell = columnRow.getCell(i);
            			if(cell != null && cell.toString().equals("item_sku")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("19APR2017TYRE3"); //19APR2017TYRE2
            			}
            			else if(cell != null && cell.toString().equals("external_product_id")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("B005AWD9B0"); //generate a proper value B005AWD9B0
            			}
            			else if(cell != null && cell.toString().equals("external_product_id_type")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("ASIN"); // ASIN UPC (12 digits), EAN (13 digits), or ISBN (10 digits, sometimes ending with an X) numbers
            			}
            			else if(cell != null && cell.toString().equals("item_name")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("Bike Tyre Test"); //Bike Tyre Test
            			}
            			else if(cell != null && cell.toString().equals("brand_name")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("JustIntegrateIt"); //JustIntegrateIt
            			}
            			else if(cell != null && cell.toString().equals("manufacturer")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("justintegrateit.com"); //justintegrateit.com
            			}
            			else if(cell != null && cell.toString().equals("feed_product_type")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("Tyres"); //Tyres
            			}
            			else if(cell != null && cell.toString().equals("main_image_url")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("http://www.team-bhp.com/forum/attachments/motorbikes/1534864d1469704209t-motorcycle-tyres-compared-mrf2wheelertyresnylogripsdl3527644781bd380.jpg");
            			}//http://www.team-bhp.com/forum/attachments/motorbikes/1534864d1469704209t-motorcycle-tyres-compared-mrf2wheelertyresnylogripsdl3527644781bd380.jpg
            			else if(cell != null && cell.toString().equals("part_number")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("BikeTyre016"); //BikeTyre016
            			}
            			else if(cell != null && cell.toString().equals("condition_type")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("New"); //New
            			}
            			else if(cell != null && cell.toString().equals("standard_price")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("2980"); //1980
            			}
            			else if(cell != null && cell.toString().equals("quantity")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("22"); //122
            			}
            			else if(cell != null && cell.toString().equals("recommended_browse_nodes")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("5257496031"); //5257496031
            			}
            			else if(cell != null && cell.toString().equals("section_width")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("185 millimeters"); //185 millimeters
            			}
            			else if(cell != null && cell.toString().equals("rim_size")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("7"); //7
            			}
            			else if(cell != null && cell.toString().equals("rim_size_unit_of_measure")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("IN"); //IN
            			}
            			else if(cell != null && cell.toString().equals("included_components")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("One Front Tyre New"); //One Front Tyre New
            			}
            			else if(cell != null && cell.toString().equals("tire_aspect_ratio")) {
            				Cell newCell =newRow.createCell(i);
            				newCell.setCellValue("8"); //8
            			}
            			else {
            				newRow.createCell(i);
            			}
            			i++;
        			}
        		}
        	}
            sourceFile.close();
            FileOutputStream outFile2 =new FileOutputStream(new File("/home/system6/Documents/SC-AmazonMWS/FlatFiles/autofile.xlsx"));
            File inputFile = new File("/home/system6/Documents/SC-AmazonMWS/FlatFiles/autofile.xlsx");
            File outputFile = new File("/home/system6/Documents/SC-AmazonMWS/FlatFiles/autofile1.txt");
            xlsxWorkBook.write(outFile2);
            outFile2.close();
            xls(inputFile, outputFile);
            SubmitFeedSample feedSample = new SubmitFeedSample();
            submitFeedResponse = feedSample.submitFeed(null, null, null, null, 
            		"/home/system6/Documents/SC-AmazonMWS/FlatFiles/autofile1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
