package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import com.amazonaws.mws.model.CancelReportRequestsResponse;
import com.amazonaws.mws.model.GetReportCountResponse;
import com.amazonaws.mws.model.GetReportListByNextTokenResponse;
import com.amazonaws.mws.model.GetReportListResponse;
import com.amazonaws.mws.model.GetReportRequestCountResponse;
import com.amazonaws.mws.model.GetReportRequestListByNextTokenResponse;
import com.amazonaws.mws.model.GetReportRequestListResponse;
import com.amazonaws.mws.model.GetReportResponse;
import com.amazonaws.mws.model.GetReportScheduleCountResponse;
import com.amazonaws.mws.model.GetReportScheduleListByNextTokenResponse;
import com.amazonaws.mws.model.GetReportScheduleListResponse;
import com.amazonaws.mws.model.ManageReportScheduleResponse;
import com.amazonaws.mws.model.RequestReportResponse;
import com.amazonaws.mws.model.UpdateReportAcknowledgementsResponse;

public interface AmazonReportManagerService {

	public List<CancelReportRequestsResponse> cancelReportRequestsAsync(String merchantId
			, String sellerDevAuthToken);
	
	public CancelReportRequestsResponse cancelReportRequests(String merchantId
			, String sellerDevAuthToken);
	
	public List<GetReportResponse> getReportAsync(String merchantId
			, String sellerDevAuthToken);
	
	public GetReportResponse getReport(String merchantId, String sellerDevAuthToken);
	
	public List<GetReportCountResponse> getReportCountAsync(String merchantId
			, String sellerDevAuthToken);
	
	public GetReportCountResponse getReportCount(String merchantId, String sellerDevAuthToken);
	
	public List<GetReportListResponse> getReportListAsync(String merchantId, String sellerDevAuthToken);
	
	public GetReportListResponse getReportList(String merchantId, String sellerDevAuthToken);
	
	public List<GetReportListByNextTokenResponse> getReportListByNextTokenAsync(String merchantId
			, String sellerDevAuthToken);
	
	public GetReportListByNextTokenResponse getReportListByNextToken(String merchantId
			, String sellerDevAuthToken);
	
	public List<GetReportRequestCountResponse> getReportRequestCountAsync(String merchantId
    		, String sellerDevAuthToken);
	
	public GetReportRequestCountResponse getReportRequestCount(String merchantId, String sellerDevAuthToken);
	
	public List<GetReportRequestListResponse> getReportRequestListAsync(String merchantId
			, String sellerDevAuthToken);
	
	public GetReportRequestListResponse getReportRequestList(String merchantId, String sellerDevAuthToken);
	
	public List<GetReportRequestListByNextTokenResponse> getReportRequestListByNextTokenAsync(String merchantId
			, String sellerDevAuthToken);
	
	public GetReportRequestListByNextTokenResponse getReportRequestListByNextToken(String merchantId
			, String sellerDevAuthToken);
	
	public List<GetReportScheduleCountResponse> getReportScheduleCountAsync(String merchantId
			, String sellerDevAuthToken);
	
	public GetReportScheduleCountResponse getReportScheduleCount(String merchantId, String sellerDevAuthToken);
	
	public List<GetReportScheduleListResponse> getReportScheduleListAsync(String merchantId, String sellerDevAuthToken);
	
	public GetReportScheduleListResponse getReportScheduleList(String merchantId, String sellerDevAuthToken);
	
	public List<GetReportScheduleListByNextTokenResponse> getReportScheduleListByNextTokenAsync(String merchantId
    		, String sellerDevAuthToken);
	
	public GetReportScheduleListByNextTokenResponse getReportScheduleListByNextToken(String merchantId
    		, String sellerDevAuthToken);
	
	public List<ManageReportScheduleResponse> manageReportScheduleAsync(String merchantId
    		, String sellerDevAuthToken);
	
	public ManageReportScheduleResponse manageReportSchedule(String merchantId, String sellerDevAuthToken);
	
	public List<RequestReportResponse> requestReportAsync(String merchantId
    		, String sellerDevAuthToken, String marketplaceId);
	
	public RequestReportResponse requestReport(String merchantId, String sellerDevAuthToken
			, List<String> marketplacesIds);
	
	public List<UpdateReportAcknowledgementsResponse> updateReportAcknowledgementsAsync(String merchantId
    		, String sellerDevAuthToken);
	
	public UpdateReportAcknowledgementsResponse updateReportAcknowledgements(String merchantId
    		, String sellerDevAuthToken);
}
