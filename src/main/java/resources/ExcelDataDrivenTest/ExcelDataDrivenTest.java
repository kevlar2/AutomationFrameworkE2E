package resources.ExcelDataDrivenTest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataDrivenTest {

    public ArrayList<String> getData(String testCaseName) throws IOException {
        // Accepts a file input stream argument
        String excelFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\ExcelDataDrivenTest\\ExcelTestData.xlsx";
        FileInputStream fis = new FileInputStream(excelFilePath);

        // Object created to use excel file
        XSSFWorkbook workBook = new XSSFWorkbook(fis);

        // Arraylist to store data from file
        ArrayList<String> a=new ArrayList<String>();

        // Loop to count and select the sheets in a wookBook
        int sheetCount = workBook.getNumberOfSheets();
        for(int i=0; i<sheetCount;i++) {
            //System.out.println(workBook.getSheetName(i));
            if(workBook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheets =workBook.getSheetAt(i);
                Iterator<Row> rows=sheets.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> ce=firstRow.cellIterator();
                int k=0;
                int column=0;
                while(ce.hasNext()){
                    Cell value = ce.next();
                    if(value.getStringCellValue().equalsIgnoreCase("Testcases")){
                        column =k;
                        //System.out.println(column + ": " + value.getStringCellValue());
                    }
                    k++;
                }
                //System.out.println(column);
                // once the column is identified then scan the entire testcase column to
                // identify purchase testcase row
                while(rows.hasNext()){
                    Row r=rows.next();
                    if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)){
                        Iterator<Cell> cv =r.cellIterator();
                        while(cv.hasNext()){
                            Cell cellValue = cv.next();
                            if(cellValue.getCellType()== CellType.STRING){
                                String cellvAlue =cellValue.getStringCellValue();
                                a.add(cellvAlue);
                                //System.out.println(cellvalue);
                            }else {

                                a.add(NumberToTextConverter.toText(cv.next().getNumericCellValue()));
                            }


                        }

                    }

                }
            }
            return a;
        }

        return a;
    }

}
