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

import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;

/** Sample async call for GetLowestOfferListingsForASIN. */
public class GetLowestOfferListingsForASINAsyncSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    public static List<Object> invokeGetLowestOfferListingsForASIN(
            MarketplaceWebServiceProductsAsync client, 
            List<GetLowestOfferListingsForASINRequest> requestList) {
        // Call the service async.
        List<Future<GetLowestOfferListingsForASINResponse>> futureList = 
            new ArrayList<Future<GetLowestOfferListingsForASINResponse>>();
        for (GetLowestOfferListingsForASINRequest request : requestList) {
            Future<GetLowestOfferListingsForASINResponse> future = 
                client.getLowestOfferListingsForASINAsync(request);
            futureList.add(future);
        }
        List<Object> responseList = new ArrayList<Object>();
        for (Future<GetLowestOfferListingsForASINResponse> future : futureList) {
            Object xresponse;
            try {
                GetLowestOfferListingsForASINResponse response = future.get();
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
    public List<GetLowestOfferListingsForASINResponse> getLowestOfferListingsForASINAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, ASINListType asinList, List<String> asin, String itemCondition, Boolean excludeMe) {

    	List<GetLowestOfferListingsForASINResponse> getLowestOfferListingsForASINResponseList = null;
        // Get a client connection.
        MarketplaceWebServiceProductsAsyncClient client = MarketplaceWebServiceProductsSampleConfig.getAsyncClient();

        // Create a request list.
        List<GetLowestOfferListingsForASINRequest> requestList = new ArrayList<GetLowestOfferListingsForASINRequest>();
        GetLowestOfferListingsForASINRequest request = new GetLowestOfferListingsForASINRequest();
        request.setSellerId(sellerId);
        request.setMWSAuthToken(mwsAuthToken);
        request.setMarketplaceId(marketplaceId);
        asinList = new ASINListType();
        asin = new ArrayList<String>();
        asin.add("B01N6WRGP5");
        asinList.setASIN(asin);
        request.setASINList(asinList);
        itemCondition = "New";
        request.setItemCondition(itemCondition);
        excludeMe = Boolean.valueOf(true);
        request.setExcludeMe(excludeMe);
        requestList.add(request);

        // Make the calls.
        List<Object> getLowestOfferListingsForASINResponseObj = GetLowestOfferListingsForASINAsyncSample.invokeGetLowestOfferListingsForASIN(client, requestList);
        if(getLowestOfferListingsForASINResponseObj != null && getLowestOfferListingsForASINResponseObj.size() > 0) {
        	getLowestOfferListingsForASINResponseList = new ArrayList<GetLowestOfferListingsForASINResponse>();
        	for(Object obj : getLowestOfferListingsForASINResponseObj) {
        		if(obj instanceof GetLowestOfferListingsForASINResponse) {
        			GetLowestOfferListingsForASINResponse getLowestOfferListingsForASINResponse = (GetLowestOfferListingsForASINResponse) obj;
        			if(getLowestOfferListingsForASINResponse != null) {
        				getLowestOfferListingsForASINResponseList.add(getLowestOfferListingsForASINResponse);
        			}
        		}
        	}
        }
        return getLowestOfferListingsForASINResponseList;
    }

}
