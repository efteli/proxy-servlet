/*
 * Copyright 2010 Woonoz S.A.S.
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.woonoz.proxy.servlet;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;


class ClientHeadersHandler extends AbstractHeadersHandler {

	protected ClientHeadersHandler(UrlRewriter urlRewriter, HeadersToSubstitute[] headersToSubstitute) {
		super(urlRewriter, createHeadersArray(headersToSubstitute));
	}
	
	private static HeadersToSubstitute[] createHeadersArray(final HeadersToSubstitute[] headersToSubstitute) {
		ArrayList<HeadersToSubstitute> headers = new ArrayList<HeadersToSubstitute>();
		headers.addAll(Arrays.asList(HeaderToSubstitute.values()));
		headers.addAll(Arrays.asList(headersToSubstitute));
		return headers.toArray(new HeadersToSubstitute[0]);
		
	}
	
	public ClientHeadersHandler(UrlRewriter urlRewriter) {
		super(urlRewriter, HeaderToSubstitute.values());
	}
	
	private enum HeaderToSubstitute implements HeadersToSubstitute {
		Host {
			public String handleValue(String headerValue, UrlRewriter urlRewriter) throws URISyntaxException, MalformedURLException {
				return urlRewriter.rewriteHost(headerValue);
			}
			
			public String getHeader() {
				return "host";
			}
		},
		Referer {
			public String handleValue(String headerValue, UrlRewriter urlRewriter) throws URISyntaxException {
				return null;
			}
			
			public String getHeader() {
				return "referer";
			}
		},
		ContentLenght {
			public String handleValue(String headerValue, UrlRewriter urlRewriter) throws URISyntaxException {
				return null;
			}
			
			public String getHeader() {
				return "content-length";
			}
		};
	}
}