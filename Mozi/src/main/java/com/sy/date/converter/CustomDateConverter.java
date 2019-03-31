package com.sy.date.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;

/**
 * 用户 在 SpringMVC 接收日期类型的数据时 自动转换成日期 类型的数据 该类不需要自己调用 会自动执行
 * 
 * @author DDM
 *
 */
public class CustomDateConverter implements Converter<String, Date> {

	SimpleDateFormat[] sdfs = { new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), new SimpleDateFormat("yyyy-MM-dd HH:mm"),
			new SimpleDateFormat("yyyy-MM-dd"), new SimpleDateFormat("HH:mm:ss"), new SimpleDateFormat("HH:mm") };

	// 创建 Pattern 对象
	String rex = "\\d";

	@Override
	public Date convert(String source) {
		System.out.println("时间转换：" + source);
		// 实现 将日期串转成日期类型(格式 yyyy-MM-dd HH:mm:ss )
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 现在创建 matcher 对象
		if (Pattern.matches(rex, source)) {
			Long time = Long.parseLong(source);

			// 转成直接返回
			return new Date(time);

			// new Date(source);

		} else {
			for (SimpleDateFormat sdf : sdfs) {
				try {
					// 转成直接返回
					return sdf.parse(source);
				} catch (ParseException e) {

				}
			}
		}

		// 如果参数绑定失败返回 null
		return null;
	}

}
