package com.sy.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.sy.mapper.AlipayOrderMapper;
import com.sy.mapper.GroupPhoneMapper;
import com.sy.mapper.WxpayOrderMapper;
import com.sy.pojo.AlipayOrder;
import com.sy.pojo.GroupPhone;
import com.sy.pojo.WxpayOrder;
import com.sy.utils.AlipayConfig;
import com.sy.utils.ApiUtils;
import com.sy.utils.HttpUrlUtil;
import com.sy.utils.PayCommonUtil;
import com.sy.utils.WechatConfig;
import com.sy.utils.XMLUtil;
import com.sy.utils.XmlStrToMap;
import com.taobao.api.internal.toplink.logging.LogUtil;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

@Controller
@RequestMapping(value = "h5")
public class H5Controller {

	private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private final static Logger logger = LoggerFactory.getLogger(H5Controller.class);
	private static final String String = null;

	@Autowired
	private AlipayOrderMapper alipayOrderMapper;
	@Autowired
	private WxpayOrderMapper wxpayOrderMapper;
	@Autowired
	private GroupPhoneMapper groupPhoneMapper;

	/**
	 * 充值页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index")
	public ModelAndView index(String phone) {
		ModelAndView mo = new ModelAndView();
		mo.addObject("phone", phone);
		mo.setViewName("pay");
		return mo;
	}
	/**
	 * 
	 * 手机号码激活页面
	 * @return
	 */
	@RequestMapping(value = "jhindex")
	public ModelAndView jhindex(@RequestBody JSONObject json) {
		ModelAndView mo = new ModelAndView();
		mo.setViewName("jh");
		mo.addObject("phone", json.getString("phone"));
		mo.addObject("ICCIC", json.getString("ICCIC"));
		mo.addObject("PUK", json.getString("PUK"));
		return mo;
	}

	/**
	 * 协议页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "agreement")
	public ModelAndView agreement() {
		ModelAndView mo = new ModelAndView();
		mo.setViewName("agreement");
		return mo;
	}
	/**
	 * 健康说明页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "jksj")
	public ModelAndView jksj() {
		ModelAndView mo = new ModelAndView();
		mo.setViewName("jksj");
		return mo;
	}
	/**
	 * 微信同步返回页面returnurl
	 * 
	 * @return
	 */
	@RequestMapping(value = "returnurl")
	public ModelAndView returnurl() {
		ModelAndView mo = new ModelAndView();
		mo.setViewName("returnurl");
		return mo;
	}

	

	/**
	 * 微信异步通知
	 * 
	 * @return
	 */
	@RequestMapping(value = "notifyurl", method = RequestMethod.POST)
	public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("----进入到了该页面---");
        BufferedReader reader = request.getReader();
        String line = "";
        StringBuffer inputString = new StringBuffer();
        try{
        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        request.getReader().close();
        //System.out.println("----接收到的报文---"+inputString.toString());
        //logger.info("微信支付=================="+inputString.toString());
        
        String xml = inputString.toString();
        
        SortedMap<String, String> xmlToMap = XmlStrToMap.xmlToMap(xml, "UTF-8");
        
        String return_code = xmlToMap.get("return_code");
        String result_code = xmlToMap.get("result_code");
        
        String out_trade_no = xmlToMap.get("out_trade_no");//订单号
        
        String total_fee = xmlToMap.get("total_fee");//订单金额
        String total_amount = new BigDecimal(total_fee).multiply(new BigDecimal("0.01")).toString();
        String phone = xmlToMap.get("attach");
        if(return_code.equals("SUCCESS")){
        	
        	WxpayOrder wxorder = new WxpayOrder();
        	
        	wxorder.setTotal_amount(total_amount);
        	wxorder.setNotify_data(xml);
        	wxorder.setPhone(phone);
        	wxorder.setOut_trade_no(out_trade_no);
			WxpayOrder select = wxpayOrderMapper.select(wxorder);
			if(select==null){
				wxpayOrderMapper.insert(wxorder);
			}
        	
         if(result_code.equals("SUCCESS")){
        	 
        	 try{
					GroupPhone g = groupPhoneMapper.selectPhone(phone);
					
					//余额
					String balancestr = g.getBalance();
					if(!StringUtils.isNotBlank(balancestr.trim())){
						balancestr = "0.00";
					}
					BigDecimal balance = new BigDecimal(balancestr);
					
					//本次充值的金额
					BigDecimal totalamount = new BigDecimal(total_amount);
					
					//总的充值金额
					String totalMoneystr = g.getTotalMoney();
					if(!StringUtils.isNotBlank(totalMoneystr)){
						totalMoneystr = "0.00";
					}
					BigDecimal totalMoney = new BigDecimal(totalMoneystr).add(totalamount);
					
					
					//更新充值的总金额
					g.setTotalMoney(totalMoney.toString());
					
					//更新余额
					g.setBalance(balance.add(totalamount).toString());
					
					//如果余额大于0,就调用开机的接口
					//if(Double.valueOf(g.getBalance())>0){
					//	Map<String, String> paramValues = new HashMap<>();
					//	paramValues.put("msisdn",phone);
					//中国移动
					//	paramValues.put("method", "triopi.business.member.switch");
					//	paramValues.put("optType","2");
					//	JSONObject f = ApiUtils.doPost(paramValues);
					//  if(f.getString("code").equals("0")){
					//  logger.info(phone+"开机成功");
					//	}else{
					//	logger.info(phone+"开机失败");
					//	}
					//	}
					
					groupPhoneMapper.updateBalance(g);
					logger.info("订单号:" + out_trade_no + "支付成功");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
         	}
        }
        //告诉微信服务器，我收到信息了，不要在调用回调action了
        response.setContentType("text/xml");
        response.getWriter().write("success");
        //response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
        System.out.println("----结束---"+inputString.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
	}
	/**
	 * 异步通知
	 * 
	 * @return
	 */
	@RequestMapping(value = "notify_url", method = RequestMethod.POST)
	public void notify_url(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (Iterator iter = parameterMap.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) parameterMap.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		JSONObject fromObject = JSONObject.fromObject(params);
		//logger.info(fromObject.toString());
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		// 支付宝交易号
		String trade_no = request.getParameter("trade_no");
		// 交易状态
		String trade_status = request.getParameter("trade_status");
		
		
		String total_amount = request.getParameter("total_amount");
		String phone = request.getParameter("passback_params");
		
		
		
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		// 计算得出通知验证结果
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
				"RSA2");
		// 验证成功
		if (verify_result) {
			
			AlipayOrder ali = new AlipayOrder();
			ali.setTotal_amount(total_amount);
			ali.setNotify_data(fromObject.toString());
			ali.setPhone(phone);
			ali.setOut_trade_no(out_trade_no);
			ali.setOut_trade_no(out_trade_no);
			AlipayOrder select = alipayOrderMapper.select(ali);
			if(select==null){
				alipayOrderMapper.insert(ali);
			}
			
			if (trade_status.equals("TRADE_FINISHED")) {
				
				
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				
				try{
					GroupPhone g = groupPhoneMapper.selectPhone(phone);
					
					//余额
					String balancestr = g.getBalance();
					if(!StringUtils.isNotBlank(balancestr.trim())){
						balancestr = "0.00";
					}
					BigDecimal balance = new BigDecimal(balancestr);
					
					//本次充值的金额
					BigDecimal totalamount = new BigDecimal(total_amount);
					
					
					
					//总的充值金额
					String totalMoneystr = g.getTotalMoney();
					if(!StringUtils.isNotBlank(totalMoneystr)){
						totalMoneystr = "0.00";
					}
					BigDecimal totalMoney = new BigDecimal(totalMoneystr).add(totalamount);
					
					
					//更新充值的总金额
					g.setTotalMoney(totalMoney.toString());
					
					//更新余额
					g.setBalance(balance.add(totalamount).toString());
					
					//如果余额大于0,就调用开机的接口
					//if(Double.valueOf(g.getBalance())>0){
					//	Map<String, String> paramValues = new HashMap<>();
					//	paramValues.put("msisdn",phone);
					//中国移动
					//	paramValues.put("method", "triopi.business.member.switch");
					//	paramValues.put("optType","2");
					//	JSONObject f = ApiUtils.doPost(paramValues);
					//  if(f.getString("code").equals("0")){
					//  logger.info(phone+"开机成功");
					//	}else{
					//	logger.info(phone+"开机失败");
					//	}
					//	}
					
					groupPhoneMapper.updateBalance(g);
					
					logger.info("订单号:" + out_trade_no + "支付成功");
					response.getWriter().write("success");
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}else{
				
				logger.info("订单号:" + out_trade_no + "支付失败");
				response.getWriter().write("success");
			}
			response.getWriter().flush();
			response.getWriter().close();
			
		} else {// 验证失败
			logger.info("订单号:" + out_trade_no + "支付失败");
			response.getWriter().write("fail");
			// out.println("fail");
		}
	}

	/**
	 * 
	 * 同步通知页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "return_url")
	public ModelAndView return_url(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> parameterMap = request.getParameterMap();
		for (Iterator iter = parameterMap.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) parameterMap.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号
		String out_trade_no = request.getParameter("out_trade_no");
		// 支付宝交易号
		String trade_no = request.getParameter("trade_no");
		// 交易状态
		String trade_status = request.getParameter("trade_status");
		
		String total_amount = request.getParameter("total_amount");
		
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET,
				"RSA2");

		if (verify_result) {// 验证成功
			

		
		} else {
			
		}
		ModelAndView mo = new ModelAndView();
		//mo.setViewName("h5");
		mo.setViewName("returnurl");
		return mo;
	}
	
	

	/**
	 * 立即充值
	 * 
	 * @return
	 */
	@RequestMapping(value = "yesPay", method = RequestMethod.POST)
	public void yesPay(String phone, String totalAmount, String pay,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(pay.equals("zfb")){
			//支付宝支付  totalAmount 金额
			alipay(phone, totalAmount, request, response);
		}else{
			//微信支付
			wxpay(phone, totalAmount, request, response);
		}
	}

	/**
	 * 微信支付
	 * @param phone
	 * @param totalAmount
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void wxpay(String phone, String totalAmount, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		try {
			
		
					//随机数
					Integer randomNum = (int) ((Math.random() * 9 + 1) * 1000);
					String nonce_str = dateFormat.format(new Date()) + randomNum;
					
					// 价格 注意：价格的单位是分
					String order_price = new BigDecimal(0.01).multiply(new BigDecimal(100)).toString().split("\\.")[0];
					
					// 自己网站上的订单号
					String out_trade_no = nonce_str;
					
					// 获取发起电脑 ip
					String spbill_create_ip = PayCommonUtil.getIpAddr(request);
					System.out.println(spbill_create_ip);
					// 回调接口
					String notify_url = WechatConfig.notify_url;
					
					//交易类型 h5 固定值
					String trade_type = "MWEB";
		 
					// 设置package订单参数
					SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
					
					// 账号信息
					String appid = WechatConfig.appid; // appid
					String mch_id = WechatConfig.mch_id; // 商户号
					String key = WechatConfig.key; // key
					packageParams.put("appid", appid);
					packageParams.put("mch_id", mch_id);
					packageParams.put("nonce_str", nonce_str);
					packageParams.put("out_trade_no", out_trade_no);
					packageParams.put("total_fee", order_price);
					packageParams.put("spbill_create_ip", spbill_create_ip);
					packageParams.put("notify_url", notify_url);
					packageParams.put("trade_type", trade_type);
					packageParams.put("body", WechatConfig.body);
					packageParams.put("detail", phone+" "+"话费充值");
					packageParams.put("scene_info", WechatConfig.scene_info);
					packageParams.put("attach", phone);
					String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
					packageParams.put("sign", sign);
					String requestXML = PayCommonUtil.getRequestXml(packageParams);
					String resXml = HttpUrlUtil.postData(WechatConfig.url, requestXML);
					System.out.println(resXml);
					Map map = XMLUtil.doXMLParse(resXml);
					String urlCode = (String) map.get("code_url");
					//确认支付过后跳的地址,需要经过urlencode处理
					String urlString = WechatConfig.return_url;
					String mweb_url = map.get("mweb_url")+"&redirect_url="+urlString;
					response.sendRedirect(mweb_url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "checkPhoneNum", method = RequestMethod.POST)
	@ResponseBody
	public Boolean checkPhoneNum(String phone) {

		// 1.验证手机号码是否存在
		GroupPhone selectPhone = groupPhoneMapper.selectPhone(phone);
		if (selectPhone != null) {
			return true;
		}else{
			return false;
		}
	}
	
	private void alipay(String phone, String totalAmount,HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);

		// 2.生成订单编号,金额等
		Integer randomNum = (int) ((Math.random() * 9 + 1) * 1000);
		String sNow = dateFormat.format(new Date()) + randomNum;

		logger.info("==================支付宝下单,商户订单号为：" + sNow);
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = sNow;
		// 付款金额，必填
		totalAmount = "0.01";
		// 订单名称，必填
		String subject = "话费充值";
		// 商品描述，可空
		String body = "物联卡话费充值  充值金额 " + totalAmount + " 元";
		// 超时时间 可空
		// String timeout_express="2m";
		// 销售产品码 必填
		String product_code = "QUICK_WAP_WAY";
		
		//中途返回的页面
		String quit_url = AlipayConfig.QUIT__URL+phone;
		

			// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
			// 调用RSA签名方式
			AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
					AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
					AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
			AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

			// 封装请求支付信息
			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
			model.setOutTradeNo(out_trade_no);
			model.setSubject(subject);
			model.setGoodsType("0");
			//设置金额
			model.setTotalAmount(totalAmount);
			model.setBody(body);
			model.setPassbackParams(phone);
			// model.setTimeoutExpress(timeout_express);
			model.setProductCode(product_code);
			model.setQuitUrl(quit_url);
			
			alipay_request.setBizModel(model);
			// 设置异步通知地址
			alipay_request.setNotifyUrl(AlipayConfig.notify_url);
			// 设置同步地址
			alipay_request.setReturnUrl(AlipayConfig.return_url);

			// form表单生产
			String form = "";
			try {
				// 调用SDK生成表单
				form = client.pageExecute(alipay_request).getBody();
				/*String dosubmit =  "<script type='text/javascript'>"+"\r\n"+"document.forms[0].submit();"+"\r\n"+
						"var commitStatus = false;"+"\r\n"+
						" function dosubmit(){"+"\r\n"+
		                "  if(commitStatus==false){"+"\r\n"+
		                 " commitStatus = true;"+"\r\n"+
		                  "return true;"+"\r\n"+
		                " }else{"+
		                  "return false;"+"\r\n"+
		             " }"+"\r\n"+
		            " }"+"\r\n"+
		           " </script>"+"\r\n";
				String replace = form.replace("<script>document.forms[0].submit();</script>", "");
				replace = replace.replace("<form","<form onsubmit='return dosubmit();'");
				System.out.println(replace+dosubmit);*/
				response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
				response.getWriter().write(form);// 直接将完整的表单html输出到页面
				response.getWriter().flush();
				response.getWriter().close();

			} catch (AlipayApiException e) {
				logger.info("订单号" + sNow + "与支付宝交互出错，未能生成订单");
				e.printStackTrace();
				response.getWriter().write("<script>layer.open({content: 'Alipay充值失败',btn: 'OK'});</script>");
				response.getWriter().flush();
				response.getWriter().close();
			}
	}
	
}