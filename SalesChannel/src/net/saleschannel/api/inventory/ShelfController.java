package net.saleschannel.api.inventory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.representation.Representation;

import net.saleschannel.api.SalesChannelServerResource;
import net.saleschannel.api.warehouse.WarehouseServiceImpl;

public class ShelfController extends SalesChannelServerResource<ShelfJsonObject> {

	private static final Logger LOGGERS = Logger.getLogger(ShelfController.class);
	
	private InventoryServiceImpl inventoryService;
	
	private WarehouseServiceImpl warehouseService;
	
	private String warehouseId = null;
	
	private String inventoryId = null;
	
	private String shelfId = null;
	
	@Override
	public Representation fetchDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation insertOrUpdateDetails(Representation entity,
			ShelfJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation updateDetails(Representation entity,
			ShelfJsonObject obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Representation deleteDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject validate(ShelfJsonObject obj, String method,
			JSONObject jsonObject, Form form) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShelfJsonObject getJsonObject(InputStream stream)
			throws JsonParseException, JsonMappingException, IOException,
			JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getParametersList() {
		final ArrayList<String> paramList = new ArrayList<String>();
		paramList.add("warehouseId");
		paramList.add("inventoryId");
		paramList.add("shelfId");
		return paramList;
	}

	public InventoryServiceImpl getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryServiceImpl inventoryService) {
		this.inventoryService = inventoryService;
	}

	public WarehouseServiceImpl getWarehouseService() {
		return warehouseService;
	}

	public void setWarehouseService(WarehouseServiceImpl warehouseService) {
		this.warehouseService = warehouseService;
	}

}
