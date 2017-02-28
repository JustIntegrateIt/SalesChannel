/*******************************************************************************
 * Copyright 2009-2015 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Orders
 * API Version: 2013-09-01
 * Library Version: 2015-09-24
 * Generated: Fri Sep 25 20:06:20 GMT 2015
 */
package com.amazonservices.mws.orders._2013_09_01.samples;

import java.util.*;
import java.util.concurrent.*;

import com.amazonservices.mws.orders._2013_09_01.*;
import com.amazonservices.mws.orders._2013_09_01.model.*;

/** Sample async call for GetOrder. */
public class GetOrderAsyncSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    public static List<Object> invokeGetOrder(
            MarketplaceWebServiceOrdersAsync client, 
            List<GetOrderRequest> requestList) {
        // Call the service async.
        List<Future<GetOrderResponse>> futureList = new ArrayList<Future<GetOrderResponse>>();
        for (GetOrderRequest request : requestList) {
            Future<GetOrderResponse> future = 
                client.getOrderAsync(request);
            futureList.add(future);
        }
        List<Object> responseList = new ArrayList<Object>();
        for (Future<GetOrderResponse> future : futureList) {
            Object xresponse;
            try {
                GetOrderResponse response = future.get();
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
                if (cause instanceof MarketplaceWebServiceOrdersException) {
                    // Exception properties are important for diagnostics.
                    MarketplaceWebServiceOrdersException ex = 
                        (MarketplaceWebServiceOrdersException)cause;
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
    public List<GetOrderResponse> getOrderAsync(String sellerId, String mwsAuthToken
    		, List<String> amazonOrderId) {
    	List<GetOrderResponse> orderResponseList = null;
        // Get a client connection.
        MarketplaceWebServiceOrdersAsyncClient client = MarketplaceWebServiceOrdersSampleConfig.getAsyncClient();

        // Create a request list.
        List<GetOrderRequest> requestList = new ArrayList<GetOrderRequest>();
        GetOrderRequest request = new GetOrderRequest();
        request.setSellerId(sellerId);
        request.setMWSAuthToken(mwsAuthToken);
        request.setAmazonOrderId(amazonOrderId);
        requestList.add(request);

        // Make the calls.
        List<Object> orderResponseObj = GetOrderAsyncSample.invokeGetOrder(client, requestList);
        if(orderResponseObj != null && orderResponseObj.size() > 0) {
        	orderResponseList = new ArrayList<GetOrderResponse>();
            for(Object obj : orderResponseObj) {
            	if(obj instanceof GetOrderResponse) {
            		GetOrderResponse orderResponse = (GetOrderResponse) obj;
            		if(orderResponse != null) {
            			orderResponseList.add(orderResponse);
            		}
            	}
            }
        }
        return orderResponseList;
    }

}
