package org.ieforex.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bcics.sso.SSOHelper;
import org.bcics.sso.Token;
import org.bcics.sso.annotation.Permission;
import org.ieforex.entity.ECustomerAddress;
import org.ieforex.service.ECustomerAddressService;
import org.ieforex.utils.AesCryptUtil;
import org.ieforex.utils.MathUtil;
import org.ieforex.utils.RESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * 
 * @author zero
 * @email 15638559970
 * @date 2017-08-28 16:56:56
 */
@Controller
@RequestMapping("ecustomeraddress")
public class ECustomerAddressController extends AbstractController{

	@Autowired
	protected HttpServletResponse response;
	@Autowired
	private ECustomerAddressService eCustomerAddressService;
	
	/**
	 * 信息
	 */
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping("/info/{addressId}")
	public RESULT info(@PathVariable("addressId") Long addressId){
		response.setHeader("Access-Control-Allow-Origin", "*");
		ECustomerAddress eCustomerAddress = eCustomerAddressService.selectById(addressId);
		return RESULT.ok().put("customerAddress", eCustomerAddress);
	}
	
	/**
	 * 保存
	 */
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping("/save")
	public RESULT save(@RequestBody String params,HttpServletRequest request){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			try {
				Long customerId =MathUtil.parseLong(token.getUid());
				ECustomerAddress customerAddress = new ECustomerAddress();
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String receiver = paramMap.get("receiver").trim();
				String telephone = paramMap.get("telephone").trim();
				String prov = paramMap.get("prov").trim();
				String city = paramMap.get("city").trim();
				String area = paramMap.get("area").trim();
				String address = paramMap.get("address").trim();
				
				if(paramMap.get("isDefault")==null||"".equals(paramMap.get("isDefault").trim())){
					customerAddress.setIsDefault("0");
				}else{
					customerAddress.setIsDefault(paramMap.get("isDefault").trim());
				}
				customerAddress.setCustomerId(customerId);
				customerAddress.setProvince(prov);
				customerAddress.setCity(city);
				customerAddress.setCounty(area);
				customerAddress.setAddress(address);
				customerAddress.setTelephone(telephone);
				customerAddress.setName(receiver);
				
				eCustomerAddressService.save(customerAddress);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return RESULT.ok();
		}
		return null;
	}
		
	/**
	 * 修改
	 */
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping("/update")
	public RESULT update(@RequestBody String params){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Token token = SSOHelper.attrToken(request);
		if(token!=null){
			try {
				Long customerId =MathUtil.parseLong(token.getUid());
				ECustomerAddress customerAddress = new ECustomerAddress();
				Map<String, String> paramMap = AesCryptUtil.decrypt2JSON(params);
				String receiver = paramMap.get("receiver").trim();
				String telephone = paramMap.get("telephone").trim();
				String prov = paramMap.get("prov").trim();
				String city = paramMap.get("city").trim();
				String area = paramMap.get("area").trim();
				String address = paramMap.get("address").trim();
				
				if(paramMap.get("isDefault")==null||"".equals(paramMap.get("isDefault").trim())){
					customerAddress.setIsDefault("0");
				}else{
					customerAddress.setIsDefault(paramMap.get("isDefault").trim());
				}
				if(paramMap.get("addressId")!=null&&!"".equals(paramMap.get("addressId").trim())){
					customerAddress.setAddressId(MathUtil.parseLong(paramMap.get("addressId").trim()));
				}
				customerAddress.setCustomerId(customerId);
				customerAddress.setProvince(prov);
				customerAddress.setCity(city);
				customerAddress.setCounty(area);
				customerAddress.setAddress(address);
				customerAddress.setTelephone(telephone);
				customerAddress.setName(receiver);
				eCustomerAddressService.updateById(customerAddress);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return RESULT.ok();
		}
		return null;
	}
	
	/**
	 * 删除
	 */
	@Permission("usr:logined")
	@ResponseBody
	@RequestMapping("/delete/{addressId}")
	public RESULT delete(@PathVariable("addressId") Long addressId){
		response.setHeader("Access-Control-Allow-Origin", "*");
		int i = eCustomerAddressService.deleteById(addressId);
		if(i==1){
			return RESULT.ok();
		}
		return RESULT.error("删除失败！");
	}
	
}
