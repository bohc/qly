package com.pj.antfetch;

public class FetchFly {

	public static void main(String[] args) {
		String reqUrl = "http://flight.qunar.com/twell/flight/Search.jsp";
		String reqData = "from=flight_dom_search&searchType=OnewayFlight&fromCity=北京(BJS)&toCity=昆明(KMG)&fromDate=2015-04-16&toDate=2015-04-19";
		String sc = HttpClientUtil.sendPostRequest(reqUrl, reqData, "UTF-8");
		System.out.println(sc);
	}

}
