package com.sy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 封装对excel的操作，包括本地读写excel和流中输出excel<br/>
 * 关联jar poi-3.5-beta5-20090219.jar<br/>
 * 有参构造函数参数为excel的全路径<br/>
 */
public class ExcelUtils {

	// excel文件路径

	private String path = "";

	/**
	 * 无参构造函数 默认
	 */
	public ExcelUtils() {

	}

	/**
	 * 有参构造函数
	 * 
	 * @param path
	 *            excel路径
	 */
	public ExcelUtils(String path) {
		this.path = path;
	}

	/**
	 * 在磁盘生成一个含有内容的excel,路径为path属性
	 * 
	 * @param sheetName
	 *            导出的sheet名称
	 * @param fieldName
	 *            列名数组
	 * @param data
	 *            数据组
	 * @throws IOException
	 */
	public void makeExcel(String sheetName, String[] fieldName, List<String[]> data) throws IOException {
		// 在内存中生成工作薄

		HSSFWorkbook workbook = makeWorkBook(sheetName, fieldName, data);
		// 截取文件夹路径

		String filePath = path.substring(0, path.lastIndexOf("\\"));
		// 如果路径不存在，创建路径

		File file = new File(filePath);
		// System.out.println(path+"-----------"+file.exists());

		if (!file.exists())
			file.mkdirs();
		FileOutputStream fileOut = new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();
	}

	/**
	 * 在输出流中导出excel
	 * 
	 * @param excelName
	 *            导出的excel名称 包括扩展名
	 * @param sheetName
	 *            导出的sheet名称
	 * @param fieldName
	 *            列名数组
	 * @param data
	 *            数据组
	 * @param response
	 *            response
	 */
	public void makeStreamExcel(String excelName, String sheetName, String[] fieldName, List<String[]> data,
			HttpServletResponse response) {
		OutputStream os = null;
		try {
			response.reset(); // 清空输出流

			os = response.getOutputStream(); // 取得输出流

			// response.setHeader("Content-disposition",
			// "attachment; filename=" + new String(excelName.getBytes(),
			// "ISO-8859-1")); // 设定输出文件头
			response.setHeader("Content-disposition",
					"attachment; filename=" + java.net.URLEncoder.encode(excelName, "UTF-8")); // 设定输出文件头

			response.setContentType("application/msexcel"); // 定义输出类型

		} catch (IOException ex) {// 捕捉异常

			System.out.println("流操作错误:" + ex.getMessage());
		}
		// 在内存中生成工作薄

		HSSFWorkbook workbook = makeWorkBook(sheetName, fieldName, data);
		try {
			os.flush();
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Output is closed");
		}
	}

	/**
	 * 根据条件，生成工作薄对象到内存
	 * 
	 * @param sheetName
	 *            工作表对象名称
	 * @param fieldName
	 *            首列列名称
	 * @param datas
	 *            数据
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook makeWorkBook(String sheetName, String[] fieldName, List<String[]> data) {
		// 产生工作薄对象

		HSSFWorkbook workbook = new HSSFWorkbook();
		// 产生工作表对象

		HSSFSheet sheet = workbook.createSheet();
		// 为了工作表能支持中文,设置字符集为UTF_16

		sheet.autoSizeColumn(1, true);

		workbook.setSheetName(0, sheetName);
		// 产生一行

		HSSFRow row = sheet.createRow(0);
		// 产生单元格

		HSSFCell cell;
		// 写入各个字段的名称

		for (int i = 0; i < fieldName.length; i++) {
			// 创建第一行各个字段名称的单元格

			cell = row.createCell(i);
			// 设置单元格内容为字符串型

			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 给单元格内容赋值

			cell.setCellValue(new HSSFRichTextString(fieldName[i]));
		}
		// 写入各条记录,每条记录对应excel表中的一行

		for (int i = 0; i < data.size(); i++) {
			String[] tmp = data.get(i);
			// 生成一行

			row = sheet.createRow(i + 1);
			for (int j = 0; j < tmp.length; j++) {
				cell = row.createCell(j);
				// 设置单元格字符类型为String

				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(new HSSFRichTextString((tmp[j] == null) ? "" : tmp[j]));
			}
		}
		return workbook;
	}

	/**
	 * 根据工作区序号，读取该工作去下的所有记录，每一条记录是一个String[]<br/>
	 * 注意如果单元格中的数据为数字将会被自动转换为字符串<br/>
	 * 如果单元格中存在除数字，字符串以外的其他类型数据，将会产生错误
	 * 
	 * @param sheetOrder
	 *            工作区序号 @return @throws IOException @throws
	 */
	public List<String[]> getDataFromSheet(int sheetOrder) throws Exception {
		Workbook workbook = null;
		//Workbook wb = null;
		File file = new File(path);
		if(!file.exists()){
			throw new Exception("文件不存在");
		}
		String excelExtName = file.getName();
		System.out.println(excelExtName);
		if (excelExtName.endsWith(".xls")) {
			workbook = new HSSFWorkbook(new FileInputStream(path));
        } else if (excelExtName.endsWith(".xlsx")) {
        	workbook = new XSSFWorkbook(new FileInputStream(path));
        } else {
            throw new Exception("当前文件不是excel文件");
        }
		
		
		Sheet sheet = workbook.getSheetAt(sheetOrder);
		List<String[]> strs = new ArrayList<String[]>();
		// 注意得到的行数是基于0的索引 遍历所有的行

		// System.out.println(sheet.getLastRowNum());

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row rows = sheet.getRow(i);
			String[] str = new String[rows.getLastCellNum()];
			// 遍历每一列

			for (int k = 0; k < rows.getLastCellNum(); k++) {
				Cell cell = rows.getCell(k);
				if (cell == null) {
					str[k] = "";
					continue;
				}

				// 数字类型时

				if (0 == cell.getCellType()) {
					// 用于格式化数字，只保留数字的整数部分

					DecimalFormat df = new DecimalFormat("########");
					str[k] = df.format(cell.getNumericCellValue());
				} else
					str[k] = cell.getStringCellValue();
				// System.out.println(cell.getCellType()+"-------------"+str[k]);

			}
			strs.add(str);
		}
		return strs;
	}
	
	

}
