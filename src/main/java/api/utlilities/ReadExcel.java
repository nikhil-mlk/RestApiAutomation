package api.utlilities;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
public class ReadExcel {
    static private File filePath;
    static private FileInputStream fileInputStream = null;
    static private XSSFWorkbook xssfWorkbook = null;
    static private XSSFSheet xssfSheet = null;
    static private int numberOfRows = 0;
    static private int numberOfCells = 0;

    public static Object[][] readUserDataFromExcelFile(String fileName, String sheetName, Integer startRowNumber) {
        Object[][] data = null;
        try {
            filePath = new File("testData/" + fileName + ".xlsx");
            fileInputStream = new FileInputStream(filePath.getAbsoluteFile());
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheet(sheetName);
            numberOfRows = xssfSheet.getPhysicalNumberOfRows();
            numberOfCells = xssfSheet.getRow(1).getLastCellNum();
            System.out.println("Rows: " + numberOfRows);
            System.out.println("Columns: " + numberOfCells);
            data = new Object[numberOfRows-1][numberOfCells];
            for (int currentRow = startRowNumber-1; currentRow < numberOfRows; currentRow++) {
                for (int currentCell = 0; currentCell < numberOfCells; currentCell++) {
                    data[currentRow-1][currentCell] = xssfSheet.getRow(currentRow).getCell(currentCell).toString();
                }
            }
            xssfWorkbook.close();
        } catch (Exception a) {
            System.out.println("Problem with Excel file location");
        }
        return data;
    }
    public static Object[] getUserNames(String fileName, String sheetName) {
        Object[] data = null;
        try {
            filePath = new File("testData/" + fileName + ".xlsx");
            fileInputStream = new FileInputStream(filePath.getAbsoluteFile());
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
            xssfSheet = xssfWorkbook.getSheet(sheetName);
            numberOfRows = xssfSheet.getPhysicalNumberOfRows();
            numberOfCells = xssfSheet.getRow(1).getLastCellNum();
            System.out.println("Rows: " + numberOfRows);
            data = new Object[numberOfRows-1];
            for (int currentRow = 1; currentRow < numberOfRows; currentRow++) {
                data[currentRow-1] = xssfSheet.getRow(currentRow).getCell(1).toString();
            }
            xssfWorkbook.close();
        } catch (Exception a) {
            System.out.println("Problem with Excel file location");
        }
        return data;
    }
}






