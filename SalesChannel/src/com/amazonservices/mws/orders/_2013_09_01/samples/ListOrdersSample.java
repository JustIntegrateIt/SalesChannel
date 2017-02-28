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

import javax.xml.datatype.XMLGregorianCalendar;

import com.amazonservices.mws.client.MwsUtl;
import com.amazonservices.mws.orders._2013_09_01.*;
import com.amazonservices.mws.orders._2013_09_01.model.*;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/** Sample call for ListOrders. */
public class ListOrdersSample {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    public static ListOrdersResponse invokeListOrders(
            MarketplaceWebServiceOrders client, 
            ListOrdersRequest request) {
        try {
            // Call the service.
            ListOrdersResponse response = client.listOrders(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return response;
        } catch (MarketplaceWebServiceOrdersException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }

    /**
     *  Command line entry point.
     */
    public ListOrdersResponse listOrders(ListOrdersRequest request) {

    	ListOrdersResponse listOrdersResponse = null;
    	
        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceOrdersSampleConfig.
        MarketplaceWebServiceOrdersClient client = MarketplaceWebServiceOrdersSampleConfig.getClient();

        // Create a request. Example code
        /*ListOrdersRequest request = new ListOrdersRequest();
        String sellerId = "A44435JW4FD32";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.4ea38b7b-f563-7709-4bae-87aeaEXAMPLE";
        request.setMWSAuthToken(mwsAuthToken);
        
        XMLGregorianCalendar createdAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setCreatedAfter(createdAfter);
        
        XMLGregorianCalendar createdBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setCreatedBefore(createdBefore);
        
        XMLGregorianCalendar tmStamp3 = new XMLGregorianCalendarImpl();
        tmStamp3.setYear(2013);
        tmStamp3.setMonth(Calendar.AUGUST);
        tmStamp3.setDay(01);
        tmStamp3.setHour(10);
        tmStamp3.setMinute(59); 
        tmStamp3.setSecond(30);
        request.setLastUpdatedAfter(tmStamp3);
        XMLGregorianCalendar lastUpdatedAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setLastUpdatedAfter(lastUpdatedAfter);
        
        XMLGregorianCalendar lastUpdatedBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setLastUpdatedBefore(lastUpdatedBefore);
        
        List<String> orderStatus = new ArrayList<String>();
        orderStatus.add("Unshipped");
        orderStatus.add("PendingAvailability");
        request.setOrderStatus(orderStatus);
        List<String> marketplaceId = new ArrayList<String>();
        marketplaceId.add("A21TJRUUN4KGV");
        request.setMarketplaceId(marketplaceId);
        List<String> fulfillmentChannel = new ArrayList<String>();
        fulfillmentChannel.add("MFN");
        request.setFulfillmentChannel(fulfillmentChannel);
        List<String> paymentMethod = new ArrayList<String>();
        paymentMethod.add("COD");
        request.setPaymentMethod(paymentMethod);
        String buyerEmail = "harikrishnan.r@justintegrateit.com";
        request.setBuyerEmail(buyerEmail);
        String sellerOrderId = "902-3159896-1390916";
        request.setSellerOrderId(sellerOrderId);
        Integer maxResultsPerPage = 1;
        request.setMaxResultsPerPage(maxResultsPerPage);
        List<String> tfmShipmentStatus = new ArrayList<String>();
        request.setTFMShipmentStatus(tfmShipmentStatus);*/

        // Make the call.
        listOrdersResponse = ListOrdersSample.invokeListOrders(client, request);
        return listOrdersResponse;
    }

}
