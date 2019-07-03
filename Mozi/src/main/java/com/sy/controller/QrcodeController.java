package com.sy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sy.common.ResultData;
import com.sy.utils.DataRow;

@Controller
@RequestMapping(value = "qrcode")
public class QrcodeController {
	/**生产二维码
	 * @param m
	 * @return
	 * 二维码操作事例http://192.168.1.108:8080/Mozi/usereq/addqrcodObserved?userId=5&imei=425325&authorized=%E5%B7%B2%E6%8E%88%E6%9D%83
	 */
	@RequestMapping(value = "generateqrcode")
	@ResponseBody
	public  ResultData<String> generateqrcode(@RequestBody DataRow map){
		ResultData<String> re = new ResultData<String>();
			re.setData("http://www.mzstar.cn:8080/Mozi/usereq/addqrcodObserved?imei="+map.getString("imei")+"&authorized=已授权&userid=");
			re.setCode(200);
			re.setMessage("生成二维码！！！");
		return re;
	}
}
