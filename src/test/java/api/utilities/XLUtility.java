package api.utilities;




import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    public class XLUtility {

        String path;
        FileInputStream fis;
        Workbook workbook;
        Sheet sheet;
        Row row;
        Cell cell;

        public XLUtility(String path) throws IOException {
            this.path = path;
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
        }

        // Returns number of rows (excluding header logic handled in DataProvider)
        public int getRowCount(String sheetName) {
            sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();   // lastRowNum starts from 0
            return rowCount;
        }

        // Returns number of columns in a row
        public int getCellCount(String sheetName, int rownum) {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rownum);
            int colCount = row.getLastCellNum();   // total cells in row
            return colCount;
        }

        // Returns cell data as String
        public String getCellData(String sheetName, int rownum, int colnum) {
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rownum);
            cell = row.getCell(colnum);

            String data = "";

            try {
                DataFormatter formatter = new DataFormatter();
                data = formatter.formatCellValue(cell);
            } catch (Exception e) {
                data = "";
            }

            return data;
        }
    }

