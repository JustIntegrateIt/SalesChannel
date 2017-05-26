package com.saleschannel.api.marketplacemanager.amazonmws;

import java.util.List;

import org.apache.log4j.Logger;

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
import com.amazonaws.mws.samples.CancelReportRequestsAsyncSample;
import com.amazonaws.mws.samples.CancelReportRequestsSample;
import com.amazonaws.mws.samples.GetReportAsyncSample;
import com.amazonaws.mws.samples.GetReportCountAsyncSample;
import com.amazonaws.mws.samples.GetReportCountSample;
import com.amazonaws.mws.samples.GetReportListAsyncSample;
import com.amazonaws.mws.samples.GetReportListByNextTokenAsyncSample;
import com.amazonaws.mws.samples.GetReportListByNextTokenSample;
import com.amazonaws.mws.samples.GetReportListSample;
import com.amazonaws.mws.samples.GetReportRequestCountAsyncSample;
import com.amazonaws.mws.samples.GetReportRequestCountSample;
import com.amazonaws.mws.samples.GetReportRequestListAsyncSample;
import com.amazonaws.mws.samples.GetReportRequestListByNextTokenAsyncSample;
import com.amazonaws.mws.samples.GetReportRequestListByNextTokenSample;
import com.amazonaws.mws.samples.GetReportRequestListSample;
import com.amazonaws.mws.samples.GetReportSample;
import com.amazonaws.mws.samples.GetReportScheduleCountAsyncSample;
import com.amazonaws.mws.samples.GetReportScheduleCountSample;
import com.amazonaws.mws.samples.GetReportScheduleListAsyncSample;
import com.amazonaws.mws.samples.GetReportScheduleListByNextTokenAsyncSample;
import com.amazonaws.mws.samples.GetReportScheduleListByNextTokenSample;
import com.amazonaws.mws.samples.GetReportScheduleListSample;
import com.amazonaws.mws.samples.ManageReportScheduleAsyncSample;
import com.amazonaws.mws.samples.ManageReportScheduleSample;
import com.amazonaws.mws.samples.RequestReportAsyncSample;
import com.amazonaws.mws.samples.RequestReportSample;
import com.amazonaws.mws.samples.UpdateReportAcknowledgementsAsyncSample;
import com.amazonaws.mws.samples.UpdateReportAcknowledgementsSample;

public class AmazonReportManagerServiceImpl implements AmazonReportManagerService {

	private static final Logger LOGGERS = Logger.getLogger(AmazonReportManagerServiceImpl.class);

	@Override
	public List<CancelReportRequestsResponse> cancelReportRequestsAsync(
			String merchantId, String sellerDevAuthToken) {
		List<CancelReportRequestsResponse> cancelReportRequestsResponseList = null; 
		try {
			CancelReportRequestsAsyncSample cancelReportRequestsAsyncSample = new CancelReportRequestsAsyncSample();
			cancelReportRequestsResponseList = cancelReportRequestsAsyncSample.cancelReportRequestsAsync(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while cancelReportRequestsAsync");
			e.printStackTrace();
		}
		return cancelReportRequestsResponseList;
	}

	@Override
	public CancelReportRequestsResponse cancelReportRequests(String merchantId,
			String sellerDevAuthToken) {
		CancelReportRequestsResponse cancelReportRequestsResponse = null; 
		try {
			CancelReportRequestsSample cancelReportRequestsSample = new CancelReportRequestsSample();
			cancelReportRequestsResponse = cancelReportRequestsSample.cancelReportRequests(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while cancelReportRequests");
			e.printStackTrace();
		}
		return cancelReportRequestsResponse;
	}

	@Override
	public List<GetReportResponse> getReportAsync(String merchantId,
			String sellerDevAuthToken) {
		List<GetReportResponse> getReportResponseList = null; 
		try {
			GetReportAsyncSample getReportAsyncSample = new GetReportAsyncSample();
			getReportResponseList = getReportAsyncSample.getReportAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportAsync");
			e.printStackTrace();
		}
		return getReportResponseList;
	}
	
	@Override
	public GetReportResponse getReport(String merchantId,
			String sellerDevAuthToken, String reportPath, String reportId) {
		GetReportResponse getReportResponse = null; 
		try {
			GetReportSample getReportSample = new GetReportSample();
			getReportResponse = getReportSample.getReport(merchantId, sellerDevAuthToken, reportPath, reportId);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReport");
			e.printStackTrace();
		}
		return getReportResponse;
	}

	@Override
	public List<GetReportCountResponse> getReportCountAsync(String merchantId,
			String sellerDevAuthToken) {
		List<GetReportCountResponse> getReportCountResponseList = null; 
		try {
			GetReportCountAsyncSample getReportCountAsyncSample = new GetReportCountAsyncSample();
			getReportCountResponseList = getReportCountAsyncSample.getReportCountAsync(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportCountAsync");
			e.printStackTrace();
		}
		return getReportCountResponseList;
	}

	@Override
	public GetReportCountResponse getReportCount(String merchantId,
			String sellerDevAuthToken) {
		GetReportCountResponse getReportCountResponse = null; 
		try {
			GetReportCountSample getReportCountSample = new GetReportCountSample();
			getReportCountResponse = getReportCountSample.getReportCount(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportCount");
			e.printStackTrace();
		}
		return getReportCountResponse;
	}

	@Override
	public List<GetReportListResponse> getReportListAsync(String merchantId,
			String sellerDevAuthToken) {
		List<GetReportListResponse> getReportListResponseList = null; 
		try {
			GetReportListAsyncSample getReportListAsyncSample = new GetReportListAsyncSample();
			getReportListResponseList = getReportListAsyncSample.getReportListAsync(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportListAsync");
			e.printStackTrace();
		}
		return getReportListResponseList;
	}
	
	@Override
	public GetReportListResponse getReportList(String merchantId,
			String sellerDevAuthToken) {
		GetReportListResponse getReportListResponse = null; 
		try {
			GetReportListSample getReportListSample = new GetReportListSample();
			getReportListResponse = getReportListSample.getReportList(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportList");
			e.printStackTrace();
		}
		return getReportListResponse;
	}

	@Override
	public List<GetReportListByNextTokenResponse> getReportListByNextTokenAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetReportListByNextTokenResponse> getReportListByNextTokenResponseList = null; 
		try {
			GetReportListByNextTokenAsyncSample getReportListByNextTokenAsyncSample = new GetReportListByNextTokenAsyncSample();
			getReportListByNextTokenResponseList = getReportListByNextTokenAsyncSample.getReportListByNextTokenAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportListByNextTokenAsync");
			e.printStackTrace();
		}
		return getReportListByNextTokenResponseList;
	}
	
	@Override
	public GetReportListByNextTokenResponse getReportListByNextToken(
			String merchantId, String sellerDevAuthToken) {
		GetReportListByNextTokenResponse getReportListByNextTokenResponse = null; 
		try {
			GetReportListByNextTokenSample getReportListByNextTokenSample = new GetReportListByNextTokenSample();
			getReportListByNextTokenResponse = getReportListByNextTokenSample.getReportListByNextToken(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportListByNextToken");
			e.printStackTrace();
		}
		return getReportListByNextTokenResponse;
	}

	@Override
	public List<GetReportRequestCountResponse> getReportRequestCountAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetReportRequestCountResponse> getReportRequestCountResponseList = null; 
		try {
			GetReportRequestCountAsyncSample getReportRequestCountAsyncSample = new GetReportRequestCountAsyncSample();
			getReportRequestCountResponseList = getReportRequestCountAsyncSample.getReportRequestCountAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportRequestCountAsync");
			e.printStackTrace();
		}
		return getReportRequestCountResponseList;
	}
	
	@Override
	public GetReportRequestCountResponse getReportRequestCount(
			String merchantId, String sellerDevAuthToken) {
		GetReportRequestCountResponse getReportRequestCountResponse = null; 
		try {
			GetReportRequestCountSample getReportRequestCountSample = new GetReportRequestCountSample();
			getReportRequestCountResponse = getReportRequestCountSample.getReportRequestCount(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportRequestCount");
			e.printStackTrace();
		}
		return getReportRequestCountResponse;
	}

	@Override
	public List<GetReportRequestListResponse> getReportRequestListAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetReportRequestListResponse> getReportRequestListResponseList = null; 
		try {
			GetReportRequestListAsyncSample getReportRequestListAsyncSample = new GetReportRequestListAsyncSample();
			getReportRequestListResponseList = getReportRequestListAsyncSample.getReportRequestListAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportRequestListAsync");
			e.printStackTrace();
		}
		return getReportRequestListResponseList;
	}
	
	@Override
	public GetReportRequestListResponse getReportRequestList(
			String merchantId, String sellerDevAuthToken, List<String> reportRequestIds) {
		GetReportRequestListResponse getReportRequestListResponse = null; 
		try {
			GetReportRequestListSample getReportRequestListSample = new GetReportRequestListSample();
			getReportRequestListResponse = getReportRequestListSample.getReportRequestList(merchantId, sellerDevAuthToken,
					reportRequestIds);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportRequestList");
			e.printStackTrace();
		}
		return getReportRequestListResponse;
	}

	@Override
	public List<GetReportRequestListByNextTokenResponse> getReportRequestListByNextTokenAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetReportRequestListByNextTokenResponse> getReportRequestListByNextTokenResponseList = null; 
		try {
			GetReportRequestListByNextTokenAsyncSample getReportRequestListByNextTokenAsyncSample = new GetReportRequestListByNextTokenAsyncSample();
			getReportRequestListByNextTokenResponseList = getReportRequestListByNextTokenAsyncSample.getReportRequestListByNextTokenAsync(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportRequestListByNextTokenAsync");
			e.printStackTrace();
		}
		return getReportRequestListByNextTokenResponseList;
	}
	
	@Override
	public GetReportRequestListByNextTokenResponse getReportRequestListByNextToken(
			String merchantId, String sellerDevAuthToken) {
		GetReportRequestListByNextTokenResponse getReportRequestListByNextTokenResponse = null; 
		try {
			GetReportRequestListByNextTokenSample getReportRequestListByNextTokenSample = new GetReportRequestListByNextTokenSample();
			getReportRequestListByNextTokenResponse = getReportRequestListByNextTokenSample.getReportRequestListByNextToken(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportRequestListByNextToken");
			e.printStackTrace();
		}
		return getReportRequestListByNextTokenResponse;
	}

	@Override
	public List<GetReportScheduleCountResponse> getReportScheduleCountAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetReportScheduleCountResponse> getReportScheduleCountResponseList = null; 
		try {
			GetReportScheduleCountAsyncSample getReportScheduleCountAsyncSample = new GetReportScheduleCountAsyncSample();
			getReportScheduleCountResponseList = getReportScheduleCountAsyncSample.getReportScheduleCountAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportScheduleCountAsync");
			e.printStackTrace();
		}
		return getReportScheduleCountResponseList;
	}
	
	@Override
	public GetReportScheduleCountResponse getReportScheduleCount(
			String merchantId, String sellerDevAuthToken) {
		GetReportScheduleCountResponse getReportScheduleCountResponse = null; 
		try {
			GetReportScheduleCountSample getReportScheduleCountSample = new GetReportScheduleCountSample();
			getReportScheduleCountResponse = getReportScheduleCountSample.getReportScheduleCount(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportScheduleCount");
			e.printStackTrace();
		}
		return getReportScheduleCountResponse;
	}

	@Override
	public List<GetReportScheduleListResponse> getReportScheduleListAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetReportScheduleListResponse> getReportScheduleListResponseList = null; 
		try {
			GetReportScheduleListAsyncSample getReportScheduleListAsyncSample = new GetReportScheduleListAsyncSample();
			getReportScheduleListResponseList = getReportScheduleListAsyncSample.getReportScheduleListAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportScheduleListAsync");
			e.printStackTrace();
		}
		return getReportScheduleListResponseList;
	}
	
	@Override
	public GetReportScheduleListResponse getReportScheduleList(
			String merchantId, String sellerDevAuthToken) {
		GetReportScheduleListResponse getReportScheduleListResponse = null; 
		try {
			GetReportScheduleListSample getReportScheduleListSample = new GetReportScheduleListSample();
			getReportScheduleListResponse = getReportScheduleListSample.getReportScheduleList(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportScheduleList");
			e.printStackTrace();
		}
		return getReportScheduleListResponse;
	}

	@Override
	public List<GetReportScheduleListByNextTokenResponse> getReportScheduleListByNextTokenAsync(
			String merchantId, String sellerDevAuthToken) {
		List<GetReportScheduleListByNextTokenResponse> getReportScheduleListByNextTokenResponseList = null; 
		try {
			GetReportScheduleListByNextTokenAsyncSample getReportScheduleListByNextTokenAsyncSample = new GetReportScheduleListByNextTokenAsyncSample();
			getReportScheduleListByNextTokenResponseList = getReportScheduleListByNextTokenAsyncSample.getReportScheduleListByNextTokenAsync(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportScheduleListByNextTokenAsync");
			e.printStackTrace();
		}
		return getReportScheduleListByNextTokenResponseList;
	}
	
	@Override
	public GetReportScheduleListByNextTokenResponse getReportScheduleListByNextToken(
			String merchantId, String sellerDevAuthToken) {
		GetReportScheduleListByNextTokenResponse getReportScheduleListByNextTokenResponse = null; 
		try {
			GetReportScheduleListByNextTokenSample getReportScheduleListByNextTokenSample = new GetReportScheduleListByNextTokenSample();
			getReportScheduleListByNextTokenResponse = getReportScheduleListByNextTokenSample.getReportScheduleListByNextToken(merchantId
					, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while getReportScheduleListByNextToken");
			e.printStackTrace();
		}
		return getReportScheduleListByNextTokenResponse;
	}

	@Override
	public List<ManageReportScheduleResponse> manageReportScheduleAsync(
			String merchantId, String sellerDevAuthToken) {
		List<ManageReportScheduleResponse> manageReportScheduleResponseList = null; 
		try {
			ManageReportScheduleAsyncSample manageReportScheduleAsyncSample = new ManageReportScheduleAsyncSample();
			manageReportScheduleResponseList = manageReportScheduleAsyncSample.manageReportScheduleAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while manageReportScheduleAsync");
			e.printStackTrace();
		}
		return manageReportScheduleResponseList;
	}
	
	@Override
	public ManageReportScheduleResponse manageReportSchedule(
			String merchantId, String sellerDevAuthToken) {
		ManageReportScheduleResponse manageReportScheduleResponse = null; 
		try {
			ManageReportScheduleSample manageReportScheduleSample = new ManageReportScheduleSample();
			manageReportScheduleResponse = manageReportScheduleSample.manageReportSchedule(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while manageReportSchedule");
			e.printStackTrace();
		}
		return manageReportScheduleResponse;
	}

	@Override
	public List<RequestReportResponse> requestReportAsync(String merchantId,
			String sellerDevAuthToken, String marketplaceId) {
		List<RequestReportResponse> requestReportResponseList = null; 
		try {
			RequestReportAsyncSample requestReportAsyncSample = new RequestReportAsyncSample();
			requestReportResponseList = requestReportAsyncSample.requestReportAsync(merchantId, sellerDevAuthToken, marketplaceId);
		} catch(Exception e) {
			LOGGERS.error("error occured while requestReportAsync");
			e.printStackTrace();
		}
		return requestReportResponseList;
	}
	
	@Override
	public RequestReportResponse requestReport(String merchantId, String reportType,
			String sellerDevAuthToken, List<String> marketplacesIds) {
		RequestReportResponse requestReportResponse = null; 
		try {
			RequestReportSample requestReportSample = new RequestReportSample();
			requestReportResponse = requestReportSample.requestReport(merchantId, reportType, sellerDevAuthToken, marketplacesIds);
		} catch(Exception e) {
			LOGGERS.error("error occured while requestReport");
			e.printStackTrace();
		}
		return requestReportResponse;
	}

	@Override
	public List<UpdateReportAcknowledgementsResponse> updateReportAcknowledgementsAsync(
			String merchantId, String sellerDevAuthToken) {
		List<UpdateReportAcknowledgementsResponse> updateReportAcknowledgementsResponseList = null; 
		try {
			UpdateReportAcknowledgementsAsyncSample updateReportAcknowledgementsAsyncSample = new UpdateReportAcknowledgementsAsyncSample();
			updateReportAcknowledgementsResponseList = updateReportAcknowledgementsAsyncSample.updateReportAcknowledgementsAsync(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while updateReportAcknowledgementsAsync");
			e.printStackTrace();
		}
		return updateReportAcknowledgementsResponseList;
	}
	
	@Override
	public UpdateReportAcknowledgementsResponse updateReportAcknowledgements(
			String merchantId, String sellerDevAuthToken) {
		UpdateReportAcknowledgementsResponse updateReportAcknowledgementsResponse = null; 
		try {
			UpdateReportAcknowledgementsSample updateReportAcknowledgementsSample = new UpdateReportAcknowledgementsSample();
			updateReportAcknowledgementsResponse = updateReportAcknowledgementsSample.updateReportAcknowledgements(merchantId, sellerDevAuthToken);
		} catch(Exception e) {
			LOGGERS.error("error occured while updateReportAcknowledgements");
			e.printStackTrace();
		}
		return updateReportAcknowledgementsResponse;
	}
}
