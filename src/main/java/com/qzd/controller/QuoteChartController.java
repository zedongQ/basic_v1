package org.ieforex.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("quotechart")
public class QuoteChartController {
	
	@Autowired
	protected HttpServletResponse response;

	@ResponseBody
	@RequestMapping(value={"/gettokenl"})
	public String quoteChart(){
		response.setHeader("Access-Control-Allow-Origin", "*");
		String url = "http://123.59.52.95:8080/quotechart/gettokenl.do?mc=000&sv=000&av=000&mt=pc";
		String json = loadJSON(url);
		return json;
	}
	
	public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL req = new URL(url);
            URLConnection yc = req.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        	
        } catch (IOException e) {
        	
        }
        return json.toString();
    }
	
}
