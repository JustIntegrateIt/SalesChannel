package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import org.apache.log4j.Logger;

import com.amazonaws.mws.model.CancelFeedSubmissionsResponse;
import com.amazonaws.mws.model.GetFeedSubmissionCountResponse;
import com.amazonaws.mws.model.GetFeedSubmissionListByNextTokenResponse;
import com.amazonaws.mws.model.GetFeedSubmissionListResponse;
import com.amazonaws.mws.model.GetFeedSubmissionResultResponse;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonaws.mws.samples.CancelFeedSubmissionsAsyncSample;
import com.amazonaws.mws.samples.CancelFeedSubmissionsSample;
import com.amazonaws.mws.samples.GetFeedSubmissionCountAsyncSample;
import com.amazonaws.mws.samples.GetFeedSubmissionCountSample;
import com.amazonaws.mws.samples.GetFeedSubmissionListAsyncSample;
import com.amazonaws.mws.samples.GetFeedSubmissionListByNextTokenAsyncSample;
import com.amazonaws.mws.samples.GetFeedSubmissionListByNextTokenSample;
import com.amazonaws.mws.samples.GetFeedSubmissionListSample;
import com.amazonaws.mws.samples.GetFeedSubmissionResultAsyncSample;
import com.amazonaws.mws.samples.GetFeedSubmissionResultSample;
import com.amazonaws.mws.samples.SubmitFeedAsyncSample;
import com.amazonaws.mws.samples.SubmitFeedSample;

public class AmazonFeedManagerServiceImpl implements AmazonFeedManagerService {

	private static final Logger LOGGERS = Logger.getLogger(AmazonFeedManagerServiceImpl.class);

	@Override
	public List<CancelFeedSubmissionsResponse> cancelFeedSubmissionsAsync(
			String merchantId, String sellerDevAuthToken) {
		List<CancelFeedSubmissionsResponse> cancelFeedSubmissionsResponseList = null;
		try {
			CancelFeedSubmissionsAsyncSample cancelFeedSubmissionsAsyncSample = new CancelFeedSubmissionsAsyncSample();
			cancelFeedSubmissionsResponseList = cancelFeedSubmissionsAsyncSample.cancelFeedSubmissionsAsync(merchantId
					, sellerDevAuthToken); 
		} catch(Exception e) {
			LOGGERS.error("error occured while cancelFeedSubmissionsAsync");
			e.printStackTrace();
		}
		return cancelFeedSubmissionsResponseList;
	}
	
	@Override
	public CancelFeedSubmissionsResponse cancelFeedSubmissions(
			String merchantId, String sellerDevAuthToken) {
		CancelFeedSubmissionsResponse cancelFeedSubmissionsResponse = null;
		try {
			CancelFeedSubmissionsSample cancelFeedSubmissionsSample = new CancelFeedSubmissionsSample();
			cancelFeedSubmissionsResponse = cancelFeedSubmissionsSample.cancelFeedSubmissions(merchantId
					, sellerDevAuthToken); 
		} catch(Exception e) {
			LOGGERS.error("error occured while cancelFeedSubmissions");
			e.printStackTrace();
		}
		return cancelFeedSubmissionsResponse;
	}

	@Override
	public List<GetFeedSubmissionCountResponse> getFeedSubmissionCountAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetFeedSubmissionCountResponse> cancelFeedSubmissionsResponseList = null;
		try {
			GetFeedSubmissionCountAsyncSample getFeedSubmissionCountAsyncSample = new GetFeedSubmissionCountAsyncSample();
			cancelFeedSubmissionsResponseList = getFeedSubmissionCountAsyncSample.getFeedSubmissionCountAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionCountAsync");
			e.printStackTrace();
		}
		return cancelFeedSubmissionsResponseList;
	}
	
	@Override
	public GetFeedSubmissionCountResponse getFeedSubmissionCount(
			String merchantId, String sellerDevAuthToken) {
		GetFeedSubmissionCountResponse cancelFeedSubmissionsResponse = null;
		try {
			GetFeedSubmissionCountSample getFeedSubmissionCountSample = new GetFeedSubmissionCountSample();
			cancelFeedSubmissionsResponse = getFeedSubmissionCountSample.getFeedSubmissionCount(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionCount");
			e.printStackTrace();
		}
		return cancelFeedSubmissionsResponse;
	}

	@Override
	public List<GetFeedSubmissionListResponse> getFeedSubmissionListAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetFeedSubmissionListResponse> getFeedSubmissionListResponseList = null;
		try {
			GetFeedSubmissionListAsyncSample getFeedSubmissionListAsyncSample = new GetFeedSubmissionListAsyncSample();
			getFeedSubmissionListResponseList = getFeedSubmissionListAsyncSample.getFeedSubmissionListAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionListAsync");
			e.printStackTrace();
		}
		return getFeedSubmissionListResponseList;
	}

	@Override
	public GetFeedSubmissionListResponse getFeedSubmissionList(
			String merchantId, String sellerDevAuthToken) {
		GetFeedSubmissionListResponse getFeedSubmissionListResponse = null;
		try {
			GetFeedSubmissionListSample getFeedSubmissionListSample = new GetFeedSubmissionListSample();
			getFeedSubmissionListResponse = getFeedSubmissionListSample.getFeedSubmissionList(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionList");
			e.printStackTrace();
		}
		return getFeedSubmissionListResponse;
	}

	@Override
	public List<GetFeedSubmissionListByNextTokenResponse> getFeedSubmissionListByNextTokenAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetFeedSubmissionListByNextTokenResponse> getFeedSubmissionListByNextTokenResponseList = null;
		try {
			GetFeedSubmissionListByNextTokenAsyncSample getFeedSubmissionListByNextTokenAsyncSample = new GetFeedSubmissionListByNextTokenAsyncSample();
			getFeedSubmissionListByNextTokenResponseList = getFeedSubmissionListByNextTokenAsyncSample.getFeedSubmissionListByNextTokenAsync(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionListAsync");
			e.printStackTrace();
		}
		return getFeedSubmissionListByNextTokenResponseList;
	}

	@Override
	public GetFeedSubmissionListByNextTokenResponse getFeedSubmissionListByNextToken(
			String merchantId, String sellerDevAuthToken) {
		GetFeedSubmissionListByNextTokenResponse getFeedSubmissionListByNextTokenResponse = null;
		try {
			GetFeedSubmissionListByNextTokenSample getFeedSubmissionListByNextTokenSample = new GetFeedSubmissionListByNextTokenSample();
			getFeedSubmissionListByNextTokenResponse = getFeedSubmissionListByNextTokenSample.getFeedSubmissionListByNextToken(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionListAsync");
			e.printStackTrace();
		}
		return getFeedSubmissionListByNextTokenResponse;
	}

	@Override
	public List<GetFeedSubmissionResultResponse> getFeedSubmissionResultAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetFeedSubmissionResultResponse> getFeedSubmissionResultResponseList = null;
		try {
			GetFeedSubmissionResultAsyncSample getFeedSubmissionResultAsyncSample = new GetFeedSubmissionResultAsyncSample();
			getFeedSubmissionResultResponseList = getFeedSubmissionResultAsyncSample.getFeedSubmissionResultAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionResultAsync");
			e.printStackTrace();
		}
		return getFeedSubmissionResultResponseList;
	}

	@Override
	public GetFeedSubmissionResultResponse getFeedSubmissionResult(
			String merchantId, String sellerDevAuthToken) {
		GetFeedSubmissionResultResponse getFeedSubmissionResultResponse = null;
		try {
			GetFeedSubmissionResultSample getFeedSubmissionResultSample = new GetFeedSubmissionResultSample();
			getFeedSubmissionResultResponse = getFeedSubmissionResultSample.getFeedSubmissionResult(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getFeedSubmissionResult");
			e.printStackTrace();
		}
		return getFeedSubmissionResultResponse;
	}

	@Override
	public List<SubmitFeedResponse> submitFeedAsync(
			List<SubmitFeedRequest> requests) {
		List<SubmitFeedResponse> submitFeedResponseList = null;
		try {
			SubmitFeedAsyncSample submitFeedAsyncSample = new SubmitFeedAsyncSample();
			submitFeedResponseList = submitFeedAsyncSample.submitFeedAsync(requests);
		} catch(Exception e) {
			LOGGERS.error("error occured while submitFeedAsync");
			e.printStackTrace();
		}
		return submitFeedResponseList;
	}

	@Override
	public SubmitFeedResponse submitFeed(String merchantId,
			String sellerDevAuthToken, List<String> marketplacesIds,
			String feedType, String feedContentPath) {
		SubmitFeedResponse submitFeedResponse = null;
		try {
			SubmitFeedSample submitFeedSample = new SubmitFeedSample();
			submitFeedResponse = submitFeedSample.submitFeed(merchantId, sellerDevAuthToken
					, marketplacesIds, feedType, feedContentPath);
		} catch(Exception e) {
			LOGGERS.error("error occured while submitFeed");
			e.printStackTrace();
		}
		return submitFeedResponse;
	}
}
