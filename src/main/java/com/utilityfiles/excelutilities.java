package com.utilityfiles;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class excelutilities {
	
	 
public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
		public static XSSFCellStyle style;
		static String file="C:\\Users\\2304055\\eclipse-workspace\\Hackathon-Project-IdentifyNewBikes-main\\Hackathon-Project-IdentifyNewBikes-main\\HackathonProjectIdentify\\excelsheet\\findingbikes.xlsx";
		
		public void setcelldata(String Sheet,String text,int r,int c) throws IOException {
			fi=new FileInputStream(file);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(Sheet);
		     row = ws.createRow(r);
			cell=row.createCell(c);
			cell.setCellValue(text);
			fo=new FileOutputStream(file);
			wb.write(fo);
			wb.close();
			fi.close();
			fo.close();
		}
}

