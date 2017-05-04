package com.saleschannel.api.flatfile;

import org.apache.log4j.Logger;

public class FlatFileServiceImpl implements FlatFileService {

	private static final Logger LOGGERS = Logger.getLogger(FlatFileServiceImpl.class);
	
	private FlatFileDaoImpl flatFileDao;
	
	@Override
	public FlatFileJsonModel getFlatFile(String categoryId) {
		FlatFileJsonModel flatFileJsonModel = null;
		try {
			flatFileJsonModel = flatFileDao.getFlatFile(categoryId);
		} catch (Exception e) {
			LOGGERS.error("error while getFlatFile");
			e.printStackTrace();
		}
		return flatFileJsonModel;
	}

	public FlatFileDaoImpl getFlatFileDao() {
		return flatFileDao;
	}

	public void setFlatFileDao(FlatFileDaoImpl flatFileDao) {
		this.flatFileDao = flatFileDao;
	}

}
