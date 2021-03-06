/*******************************************************************************
 * Copyright 2009-2016 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Products
 * API Version: 2011-10-01
 * Library Version: 2016-06-01
 * Generated: Mon Jun 13 10:07:47 PDT 2016
 */
package com.amazonservices.mws.products.samples;

import java.util.*;
import java.util.concurrent.*;

import java.math.BigDecimal;

import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;

/** Sample async call for GetMyFeesEstimate. */
public class GetMyFeesEstimateAsyncSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    public static List<Object> invokeGetMyFeesEstimate(
            MarketplaceWebServiceProductsAsync client, 
            List<GetMyFeesEstimateRequest> requestList) {
        // Call the service async.
        List<Future<GetMyFeesEstimateResponse>> futureList = 
            new ArrayList<Future<GetMyFeesEstimateResponse>>();
        for (GetMyFeesEstimateRequest request : requestList) {
            Future<GetMyFeesEstimateResponse> future = 
                client.getMyFeesEstimateAsync(request);
            futureList.add(future);
        }
        List<Object> responseList = new ArrayList<Object>();
        for (Future<GetMyFeesEstimateResponse> future : futureList) {
            Object xresponse;
            try {
                GetMyFeesEstimateResponse response = future.get();
                ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
                // We recommend logging every the request id and timestamp of every call.
                System.out.println("Response:");
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
                String responseXml = response.toXML();
                System.out.println(responseXml);
                xresponse = response;
            } catch (ExecutionException ee) {
                Throwable cause = ee.getCause();
                if (cause instanceof MarketplaceWebServiceProductsException) {
                    // Exception properties are important for diagnostics.
                    MarketplaceWebServiceProductsException ex = 
                        (MarketplaceWebServiceProductsException)cause;
                    ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
                    System.out.println("Service Exception:");
                    System.out.println("RequestId: "+rhmd.getRequestId());
                    System.out.println("Timestamp: "+rhmd.getTimestamp());
                    System.out.println("Message: "+ex.getMessage());
                    System.out.println("StatusCode: "+ex.getStatusCode());
                    System.out.println("ErrorCode: "+ex.getErrorCode());
                    System.out.println("ErrorType: "+ex.getErrorType());
                    xresponse = ex;
                } else {
                    xresponse = cause;
                }
            } catch (Exception e) {
                xresponse = e;
            }
            responseList.add(xresponse);
        }
        return responseList;
    }

    /**
     *  Command line entry point.
     */
    public List<GetMyFeesEstimateResponse> getMyFeesEstimateAsync(String sellerId
    		, String mwsAuthToken, FeesEstimateRequestList feesEstimateRequestList
    		, FeesEstimateRequest fer, PriceToEstimateFees pte, MoneyType mt, Points points
    		, List<FeesEstimateRequest> feesEstimateRequest) {

    	List<GetMyFeesEstimateResponse> getMyFeesEstimateResponseList = null;
    	
        // Get a client connection.
        MarketplaceWebServiceProductsAsyncClient client = MarketplaceWebServiceProductsSampleConfig.getAsyncClient();

        // Create a request list.
        List<GetMyFeesEstimateRequest> requestList = new ArrayList<GetMyFeesEstimateRequest>();
        GetMyFeesEstimateRequest request = new GetMyFeesEstimateRequest();
        request.setSellerId(sellerId);
        request.setMWSAuthToken(mwsAuthToken);
        feesEstimateRequestList = new FeesEstimateRequestList();
        fer = new FeesEstimateRequest();
        fer.setIdentifier("request1");
        fer.setIdType("ASIN");
        fer.setIdValue("B01N6WRGP5");
        fer.setIsAmazonFulfilled(true);
        fer.setMarketplaceId("A21TJRUUN4KGV");
        pte = new PriceToEstimateFees();
        mt = new MoneyType();
        points = new Points();
        mt.setCurrencyCode("USD");
        mt.setAmount(new BigDecimal(200));
        pte.setListingPrice(mt);
        points.setPointsNumber(0);
        pte.setPoints(points);
        pte.setShipping(mt);
        fer.setPriceToEstimateFees(pte);
        feesEstimateRequest = new ArrayList<FeesEstimateRequest>();
        feesEstimateRequest.add(fer);
        feesEstimateRequestList.setFeesEstimateRequest(feesEstimateRequest);
        request.setFeesEstimateRequestList(feesEstimateRequestList);
        requestList.add(request);

        // Make the calls.
        List<Object> getMyFeesEstimateResponseObj = GetMyFeesEstimateAsyncSample.invokeGetMyFeesEstimate(client, requestList);
        if(getMyFeesEstimateResponseObj != null && getMyFeesEstimateResponseObj.size() > 0) {
        	getMyFeesEstimateResponseList = new ArrayList<GetMyFeesEstimateResponse>();
        	for(Object obj : getMyFeesEstimateResponseList) {
        		if(obj instanceof GetMyFeesEstimateResponse) {
        			GetMyFeesEstimateResponse getMyFeesEstimateResponse = (GetMyFeesEstimateResponse) obj;
        			if(getMyFeesEstimateResponse != null) {
        				getMyFeesEstimateResponseList.add(getMyFeesEstimateResponse);
        			}
        		}
        	}
        }
        return getMyFeesEstimateResponseList;
    }

}
