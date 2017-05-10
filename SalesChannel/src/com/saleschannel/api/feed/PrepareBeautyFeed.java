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

import com.saleschannel.api.constants.SalesChannelConstants;
import com.saleschannel.api.flatfile.FlatFileJsonModel;
import com.saleschannel.api.flatfile.FlatFileServiceImpl;
import com.saleschannel.api.product.ProductFulfilledStatusJsonObject;
import com.saleschannel.api.product.ProductJsonObject;
import com.saleschannel.api.productcategory.ProductCategoryColumnValueJsonObject;
import com.saleschannel.api.productcategory.ProductCategoryDaoImpl;
import com.saleschannel.api.productcategory.ProductCategoryJsonModel;
import com.saleschannel.api.productcategory.ProductCategoryServiceImpl;
import com.saleschannel.api.utility.SalesChannelBeanLocator;
import com.saleschannel.api.utility.SalesChannelUtility;

public class PrepareBeautyFeed {



	/*method to generate a flat file with required fields for a product*/
	@SuppressWarnings("resource")
	public String getBeautyFeed(List<ProductJsonObject> productJsonObjectList) {
		String txtFilePath = null;
		String filePath = null;
		try {
			if(productJsonObjectList != null && productJsonObjectList.size() > 0) {
				/*prepare amazon flat file*/
				FlatFileServiceImpl flatfileservice = (FlatFileServiceImpl) SalesChannelBeanLocator.getInstance().findBean("flatFileService");
				ProductCategoryDaoImpl categoryDao = (ProductCategoryDaoImpl) SalesChannelBeanLocator.getInstance().findBean("categoryDao");
				ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId("0", productJsonObjectList.get(0).getProductCategory());
				if(productCategoryJsonModel != null) {
					if(productCategoryJsonModel.getParentId() != null && !productCategoryJsonModel.getParentId().equals("")) {
						productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId("0", productCategoryJsonModel.getParentId());
					}
					FlatFileJsonModel flatFile = flatfileservice.getFlatFile(productCategoryJsonModel.getId());
					filePath = SalesChannelConstants.FLATFILE_SOURCE_PATH + SalesChannelConstants.FILE_SEPERATOR 
			        		+ productJsonObjectList.get(0).getCustomerId() + SalesChannelConstants.FILE_SEPERATOR + 
			        		productCategoryJsonModel.getCategoryName() + SalesChannelConstants.FILE_SEPERATOR + UUID.randomUUID().toString().replace("-", "") 
			        		+ SalesChannelConstants.XLSX;
					SalesChannelUtility.prepareFlatFile(flatFile, filePath);
					XSSFSheet xlsxSheet = null;
					FileInputStream sourceFile = new FileInputStream(new File(filePath));
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
		        				ProductCategoryServiceImpl categoryService = (ProductCategoryServiceImpl) SalesChannelBeanLocator.getInstance().findBean("categoryService");
		    					List<ProductCategoryColumnValueJsonObject> columnValueJsonObjectList = categoryService.getProductCategoryColumnValuesByProductId(productJsonObject.getId());
		        				Row newRow = xlsxSheet.createRow(rowPosition);
		            			Iterator<Cell> cells = xlsxSheet.getRow(2).iterator();
		            			int i = 0;
		            			while (cells.hasNext() && xlsxSheet.getRow(2).getLastCellNum() > i) {
		            				cell = columnRow.getCell(i);
		                			if(cell != null && cell.toString().equals("item_sku")) {
		                				Cell newCell =newRow.createCell(i);
		                				newCell.setCellValue(productJsonObject.getSkuId());
		                			}
		                			else if(cell != null && cell.toString().equals("item_name")) {
		                				Cell newCell =newRow.createCell(i);
		                				newCell.setCellValue(productJsonObject.getProductName());
		                			}
		                			else if(cell != null && cell.toString().equals("standard_price")) {
		                				Cell newCell =newRow.createCell(i);
		                				newCell.setCellValue(productJsonObject.getCost());
		                			}
		                			else if(cell != null && cell.toString().equals("quantity")) {
		                				Cell newCell =newRow.createCell(i);
		                				newCell.setCellValue(productJsonObject.getQuantity());
		                			}
		                			else if(cell != null && cell.toString().equals("main_image_url")) {
		                				Cell newCell =newRow.createCell(i);
		                				newCell.setCellValue(productJsonObject.getImage());
		                			}
		                			else if(cell != null && cell.toString().equals("external_product_id")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("external_product_id")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("external_product_id_type")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("external_product_id_type")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("manufacturer")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("manufacturer")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("condition_type")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("condition_type")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("number_of_items")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("number_of_items")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("item_package_quantity")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("item_package_quantity")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("part_number")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("part_number")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("feed_product_type")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("feed_product_type")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("brand_name")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("brand_name")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("merchant_shipping_group_name")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("merchant_shipping_group_name")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("recommended_browse_nodes")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("recommended_browse_nodes")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else if(cell != null && cell.toString().equals("generic_keywords")) {
		                				Cell newCell =newRow.createCell(i);
		                				if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
		                					for(ProductCategoryColumnValueJsonObject columnValue : columnValueJsonObjectList) {
		                						if(columnValue.getCategoryColumnParameterName() != null 
		                								&& !columnValue.getCategoryColumnParameterName().isEmpty() 
		                								&& columnValue.getCategoryColumnParameterName().equals("generic_keywords")) {
		                							newCell.setCellValue(columnValue.getValue());
		                						}
		                					}
		                				}
		                			}
		                			else {
		                				newRow.createCell(i);
		                			}
		                			i++;
		            			}
		            			rowPosition++;
		        			}
		        		}
		        	}
		            sourceFile.close();
		            txtFilePath = filePath.replace(SalesChannelConstants.XLSX, SalesChannelConstants.TXT);
		            FileOutputStream outFile2 =new FileOutputStream(new File(filePath));
		            File inputFile = new File(filePath);
		            File outputFile = new File(txtFilePath);
		            xlsxWorkBook.write(outFile2);
		            outFile2.close();
		            SalesChannelUtility.xls(inputFile, outputFile);
				}
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return txtFilePath;
    }
	
	/*method to validate whether all the required fields are present for a product*/
	public ProductFulfilledStatusJsonObject checkBeautyFeed(List<ProductJsonObject> productJsonObjectList) {

		ProductFulfilledStatusJsonObject productFulfilledStatus = new ProductFulfilledStatusJsonObject();
		List<ProductJsonObject> unfulfilledProducts = new ArrayList<ProductJsonObject>();
		List<ProductJsonObject> fulfilledProducts = new ArrayList<ProductJsonObject>();
		try {
			if(productJsonObjectList != null && productJsonObjectList.size() > 0) {
				for(ProductJsonObject productJsonObject : productJsonObjectList) {
					boolean isExist = false;
					ProductCategoryDaoImpl categoryDao = (ProductCategoryDaoImpl) SalesChannelBeanLocator.getInstance().findBean("categoryDao");
					ProductCategoryJsonModel productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId("0", productJsonObject.getProductCategory());
					if(productCategoryJsonModel != null && productCategoryJsonModel.getParentId() != null && !productCategoryJsonModel.getParentId().equals("")) {
						productCategoryJsonModel = categoryDao.getProductCategoryByNameAndCustomerId("0", productCategoryJsonModel.getParentId());
					}
					ProductCategoryServiceImpl categoryService = (ProductCategoryServiceImpl) SalesChannelBeanLocator.getInstance().findBean("categoryService");
					List<ProductCategoryColumnValueJsonObject> columnValueJsonObjectList = categoryService.getProductCategoryColumnValuesByProductId(productJsonObject.getId());
					if(columnValueJsonObjectList != null && columnValueJsonObjectList.size() > 0) {
						for(ProductCategoryColumnValueJsonObject productCategoryColumnValueJsonObject : columnValueJsonObjectList) {
							isExist = false;
							/*mandatory fields for all the categories*/
							if(productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("external_product_id")) {
								isExist = true;
							} 
							if(productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("external_product_id_type")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("brand_name")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("manufacturer")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("condition_type")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("number_of_items")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("item_package_quantity")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("part_number")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("feed_product_type")) {
								isExist = true;
							} 
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("merchant_shipping_group_name")) {
								isExist = true;
							}
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("recommended_browse_nodes")) {
								isExist = true;
							}
							if (productCategoryColumnValueJsonObject.getCategoryColumnParameterName() != null 
									&& productCategoryColumnValueJsonObject.getCategoryColumnParameterName().equals("generic_keywords")) {
								isExist = true;
							}
							if(!isExist) {
								break;
							}
						}
					if(productJsonObject.getSkuId() != null && !productJsonObject.getSkuId().isEmpty()) { //item_sku
						isExist = true;
					}
					if(productJsonObject.getProductName() != null && !productJsonObject.getProductName().isEmpty()) { //item_name
						isExist = true;
					}
					if(productJsonObject.getImage() != null && !productJsonObject.getImage().isEmpty()) { //main_image_url
						isExist = true;
					}
					if(productJsonObject.getCost() != null && !productJsonObject.getCost().equals("")) { //standard_price
						isExist = true;
					}
					if(productJsonObject.getQuantity() != null && !productJsonObject.getQuantity().equals("")) { //quantity
						isExist = true;
					}
					
					/*adding unfulfilled products to list*/
					if(isExist) {
						fulfilledProducts.add(productJsonObject);
					} else {
						unfulfilledProducts.add(productJsonObject);
					}
				} else {
					unfulfilledProducts.add(productJsonObject);
				}
			}
			if(unfulfilledProducts != null && unfulfilledProducts.size() > 0)
				productFulfilledStatus.setUnfulfilledProducts(unfulfilledProducts);
			if(fulfilledProducts != null && fulfilledProducts.size() > 0)
				productFulfilledStatus.setUnfulfilledProducts(fulfilledProducts);
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
		return productFulfilledStatus;
	}

}
