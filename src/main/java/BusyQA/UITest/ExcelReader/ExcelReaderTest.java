package BusyQA.UITest.ExcelReader;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReaderTest {

   public static final Logger log = Logger.getLogger(ExcelReaderTest.class.getName());

    String filepath;
    String fullpath;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    FileInputStream fis = null; // actual reading of the data from the file
    FileOutputStream fout = null; // actual writing/adding of the data in the file
    int rowCount = 0;
    int columnCount = 0;
    ArrayList<String> loginValues = new ArrayList<String>();
    int i,j = 0;

    public ArrayList<String> getData(String filepath, String excelname, String sheetname, int rowNumber){

        fullpath = filepath + excelname;
        try {
            fis = new FileInputStream(fullpath); // this might give FileNotFoundException
            workbook = new XSSFWorkbook(fis); // this might give IOException
            sheet = workbook.getSheet(sheetname); // fetch the existing sheet
            rowCount = sheet.getLastRowNum(); // 0 - 4; 0 is the row header, 1 to 4 has the data
            log.info("Row Count: " + rowCount);
            columnCount = sheet.getRow(0).getLastCellNum(); // 1 to 3
            log.info("Column Count: " + columnCount);

            row = sheet.getRow(rowNumber); // TC01-> rowNumber = 1

            for(i = 0; i<columnCount-1; i++){ // loop for columns
                String var = row.getCell(i).getStringCellValue(); // actual fetching of data
                loginValues.add(var);
            }
            workbook.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginValues;
    }

    public void updateData(String filepath,String excelName,String sheetName, int rowNumber, String result) {

        try {
            fullpath = filepath+excelName;
            fis = new FileInputStream(fullpath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            columnCount = sheet.getRow(0).getLastCellNum(); // 1 to 3

            row = sheet.getRow(rowNumber);

            row.createCell(columnCount-1).setCellValue(result); // it will not be actually writing the data

            fout = new FileOutputStream(fullpath);
            workbook.write(fout); //actually the data is getting written into the sheet
            workbook.close();
            fis.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newSheet(String filepath,String excelName,String sheetName, String message) {

        try {

            fullpath = filepath+excelName;
            fis = new FileInputStream(fullpath);
            workbook = new XSSFWorkbook(fis);

            sheet = workbook.createSheet(sheetName);

            rowCount=sheet.getLastRowNum(); // row count = -1 (no rows)
            System.out.println(rowCount);


            if (rowCount == -1) {
                row = sheet.createRow(0); // creating a header row

                row.createCell(0).setCellValue("Output"); // column header

            }


            row = sheet.createRow(1); // second row, first data row
            row.createCell(0).setCellValue(message);

            fout = new FileOutputStream(fullpath);

            workbook.write(fout);
            workbook.close();
            fout.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newExcel(String filepath,String excelName,String sheetName) {

        try {
            fullpath = filepath+excelName;

            workbook = new XSSFWorkbook();

            sheet = workbook.createSheet(sheetName);

            for(i = 0; i<=5; i++) { // rows
                row = sheet.createRow(i);
                for(j = 0; j<3; j++) { // columns
                    row.createCell(j).setCellValue("Test");
                }
            }
            fout = new FileOutputStream(fullpath);
            workbook.write(fout);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}