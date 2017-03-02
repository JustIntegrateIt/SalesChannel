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

/** Sample async call for GetMyPriceForSKU. */
public class GetMyPriceForSKUAsyncSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    public static List<Object> invokeGetMyPriceForSKU(
            MarketplaceWebServiceProductsAsync client, 
            List<GetMyPriceForSKURequest> requestList) {
        // Call the service async.
        List<Future<GetMyPriceForSKUResponse>> futureList = 
            new ArrayList<Future<GetMyPriceForSKUResponse>>();
        for (GetMyPriceForSKURequest request : requestList) {
            Future<GetMyPriceForSKUResponse> future = 
                client.getMyPriceForSKUAsync(request);
            futureList.add(future);
        }
        List<Object> responseList = new ArrayList<Object>();
        for (Future<GetMyPriceForSKUResponse> future : futureList) {
            Object xresponse;
            try {
                GetMyPriceForSKUResponse response = future.get();
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
    public List<GetMyPriceForSKUResponse> getMyPriceForSKUAsync(String sellerId, String mwsAuthToken
    		, String marketplaceId, SellerSKUListType sellerSKUList, List<String> sellerSKU) {

    	List<GetMyPriceForSKUResponse> getMyPriceForSKUResponseList = null;
    	
        // Get a client connection.
        MarketplaceWebServiceProductsAsyncClient client = MarketplaceWebServiceProductsSampleConfig.getAsyncClient();

        // Create a request list.
        List<GetMyPriceForSKURequest> requestList = new ArrayList<GetMyPriceForSKURequest>();
        GetMyPriceForSKURequest request = new GetMyPriceForSKURequest();
        request.setSellerId(sellerId);
        request.setMWSAuthToken(mwsAuthToken);
        request.setMarketplaceId(marketplaceId);
        sellerSKUList = new SellerSKUListType();
        sellerSKU = new ArrayList<String>();
        sellerSKU.add("JIIT00002");
        sellerSKUList.setSellerSKU(sellerSKU);
        request.setSellerSKUList(sellerSKUList);
        requestList.add(request);

        // Make the calls.
        List<Object> getMyPriceForSKUResponseObj = GetMyPriceForSKUAsyncSample.invokeGetMyPriceForSKU(client, requestList);
        if(getMyPriceForSKUResponseObj != null && getMyPriceForSKUResponseObj.size() > 0) {
        	getMyPriceForSKUResponseList = new ArrayList<GetMyPriceForSKUResponse>();
        	for(Object obj : getMyPriceForSKUResponseObj) {
        		if(obj instanceof GetMyPriceForSKUResponse) {
        			GetMyPriceForSKUResponse getMyPriceForSKUResponse = (GetMyPriceForSKUResponse) obj;
        			if(getMyPriceForSKUResponse != null) {
        				getMyPriceForSKUResponseList.add(getMyPriceForSKUResponse);
        			}
        		}
        	}
        }
        return getMyPriceForSKUResponseList;
    }

}
