package org.ieforex.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("diancha")
public class DianChaController extends AbstractController {
	
	@RequestMapping(value={"dcTable",}, method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		HashMap<String, Object> modelMap =new HashMap<String, Object>();
		String dealerId = request.getQueryString();
		if(dealerId!=null && !dealerId.equals("")){
			modelMap.put("dealerId", dealerId);
		}else{
			modelMap.put("dealerId", "pros&0");
		}
		return new ModelAndView("brokers_diancha",modelMap);
	}
	
	/**跨域请求交易商返现*/
	@ResponseBody
	@RequestMapping("/cashBack")
	public String Cashback(){
		String url = "http://www.ibrebates.com/home/front/dianchacalc/selectAll";
		String json = loadJSON(url);
		return "["+json+"]";
	}
	
	@ResponseBody
	@RequestMapping(value="/cashBackById",method=RequestMethod.GET)
	public String CashBackById(){
		String accttypes=request.getParameter("accttypes");
		String url = "http://123.59.52.95:8080/quotechart/getSwap.do?accttypes="+accttypes;
		String json = loadJSON(url);
		return "["+json+"]";
	}
	
	@ResponseBody
	@RequestMapping(value="/quotechart",method=RequestMethod.GET)
	public String QuoteChart(){
		String url = "http://123.59.52.95:8080/quotechart/dchac.do";
		String json = loadJSON(url);
		return "["+json+"]";
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
