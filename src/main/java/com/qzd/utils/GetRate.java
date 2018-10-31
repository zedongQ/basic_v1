package com.qzd.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetRate {

	public static void main(String[] args) {
		String url ="http://srh.bankofchina.com/search/whpj/search.jsp?erectDate=&nothing=&pjname=1316";
		System.out.println(exchangeRate(url,2,3));
	}
	
	public static String exchangeRate(String url,int tr,int td){
		try {
			 Document doc = Jsoup.parse(new URL(url),8000);
			 Elements trs = doc.select(".publish").select("table").select("tr");
		     Elements tds = trs.get(tr-1).select("td");
		     String text = tds.get(td-1).text();
			 return text;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

}