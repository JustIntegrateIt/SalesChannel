package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import com.amazonaws.mws.model.CancelFeedSubmissionsResponse;
import com.amazonaws.mws.model.GetFeedSubmissionCountResponse;
import com.amazonaws.mws.model.GetFeedSubmissionListByNextTokenResponse;
import com.amazonaws.mws.model.GetFeedSubmissionListResponse;
import com.amazonaws.mws.model.GetFeedSubmissionResultResponse;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;

public interface AmazonFeedManagerService {

	public List<CancelFeedSubmissionsResponse> cancelFeedSubmissionsAsync(String merchantId
    		, String sellerDevAuthToken);
	
	public CancelFeedSubmissionsResponse cancelFeedSubmissions(String merchantId
    		, String sellerDevAuthToken);
	
	public List<GetFeedSubmissionCountResponse> getFeedSubmissionCountAsync(String merchantId
			, String sellerDevAuthToken);
	 
	public GetFeedSubmissionCountResponse getFeedSubmissionCount(String merchantId
			, String sellerDevAuthToken);
	
	public List<GetFeedSubmissionListResponse> getFeedSubmissionListAsync(String merchantId
    		, String sellerDevAuthToken);
	
	public GetFeedSubmissionListResponse getFeedSubmissionList(String merchantId
			, String sellerDevAuthToken);
	
	public List<GetFeedSubmissionListByNextTokenResponse> getFeedSubmissionListByNextTokenAsync(String merchantId
    		, String sellerDevAuthToken);
	
	public GetFeedSubmissionListByNextTokenResponse getFeedSubmissionListByNextToken(String merchantId
			, String sellerDevAuthToken);
	
	public List<GetFeedSubmissionResultResponse> getFeedSubmissionResultAsync(String merchantId
			, String sellerDevAuthToken);
	
	public GetFeedSubmissionResultResponse getFeedSubmissionResult(String merchantId, String sellerDevAuthToken);
	
	public List<SubmitFeedResponse> submitFeedAsync(List<SubmitFeedRequest> requests);
	
	public SubmitFeedResponse submitFeed(String merchantId, String sellerDevAuthToken, List<String> marketplacesIds
    		, String feedType, String feedContentPath);
}
