package org.xlb.automation.pub.writer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.xlb.automation.pub.bean.ConfigBean;
import org.xlb.automation.pub.bean.ResultPubBean;

/**
 * 
 * 写日志类Excel
 * 
 * @author Lingbo Xie
 * @date 2018-08-25
 * @version V1.0
 *
 */
public class RMIWriterExcel implements RMIWriter {
	
	private OutputStream os;
	
	private int sheetNum;
	
	private int row;
	
	private WritableCellFormat cellFormat1;
	
	private WritableCellFormat cellFormat2;
	
	private WritableCellFormat font_success;
	
	private WritableCellFormat font_fail;
	
	private WritableWorkbook wb;
	
	private WritableSheet ws;
	
	private CellView cvr;
	
	private CellView cv;
	
	public RMIWriterExcel(){
		sheetNum = 0;
		row = 0;
		init();
	}

	public void writeLog(ResultPubBean bean) throws RowsExceededException, WriteException {
		WritableCellFormat format ;
		if(!"success".equals(bean.getResult())){
			format = font_fail;
		}else{
			format = cellFormat2;

		}	
		row ++;
		//ws.setRowGroup(0, 1800, false);
		ws.addCell(new Label(0, row , bean.getProject_name(),
				format));
		ws.addCell(new Label(1, row , bean.getFunction_name(),
				format));
		ws.addCell(new Label(2, row , bean.getOper_name(),
				format));
		ws.addCell(new Label(3, row , bean.getResult(),
				format));
		ws.addCell(new Label(4, row , bean.getCost_time(),
				format));
		ws.addCell(new Label(5, row , bean.getDescription(),
				format));
		ws.addCell(new Label(6, row , bean.getDeal_time(),
				format));
	}

	private void init(){
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			File file = new File(ConfigBean.log_file_path+"autotest_log_"+sf.format(new Date())+".xls");
			if(!file.exists()){
				file.createNewFile();
			}
			os = new FileOutputStream(file);
			
			
			wb = Workbook.createWorkbook(new BufferedOutputStream(os));// 创建可写工作薄
	
			// 设置表头格式
			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 12,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.WHITE);
			cellFormat1 = new WritableCellFormat(font1);
			cellFormat1.setBackground(Colour.DARK_GREEN);
			cellFormat1.setBorder(Border.ALL, BorderLineStyle.DASH_DOT);
			cellFormat1.setWrap(true);
			cellFormat1.setAlignment(Alignment.CENTRE);
			cellFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
	
			// 设置内容格式
			WritableFont font2 = new WritableFont(WritableFont.ARIAL, 8,
					WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
					Colour.BLACK);
			cellFormat2 = new WritableCellFormat(font2);
			cellFormat2.setBackground(Colour.WHITE);
			cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat2.setWrap(false);
			cellFormat2.setAlignment(Alignment.LEFT);
			cellFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			font_success = new WritableCellFormat(font2);
			font_success.setBackground(Colour.GREEN);
			font_success.setBorder(Border.ALL, BorderLineStyle.THIN);
			font_success.setWrap(false);
			font_success.setAlignment(Alignment.LEFT);
			font_success.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			font_fail = new WritableCellFormat(font2);
			font_fail.setBackground(Colour.RED);
			font_fail.setBorder(Border.ALL, BorderLineStyle.THIN);
			font_fail.setWrap(false);
			font_fail.setAlignment(Alignment.LEFT);
			font_fail.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			cv = new CellView();
			cv.setAutosize(true);//自动宽度
			cv.setSize(18); //最小宽度	
			
			cvr = new CellView();
			cvr.setAutosize(true);//自动宽度
			cvr.setSize(8); //最小宽度	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initSheet(String sheetName) throws RowsExceededException, WriteException{
		List<String> headList = this.getHeadList();
		row = 0;
		sheetNum++;
		ws = wb.createSheet(sheetName, sheetNum);
		// 写表头
		for (int i = 0; i < headList.size(); i++) {
			//列宽
			ws.setColumnView(i,cv);
			Label label1 = new Label(i, 0, (String) headList.get(i), cellFormat1);
			ws.addCell(label1);
			//System.out.println("---------add head :"
			//		+ (String) headList.get(i) + "-------------");
		}
	}
	
	public void flush(){		
		try {
			wb.write(); // 写入Exel工作表
			wb.close();
			os.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 关闭Excel工作薄对象
	}
	
	private List<String> getHeadList(){
		List<String> headList = new ArrayList<String>();
		headList.add("应用名");
		headList.add("功能名");
		headList.add("操作名");	
		headList.add("操作结果");
		headList.add("操作耗时");
		headList.add("备注");
		headList.add("操作时间");
		return headList;
	}
}
