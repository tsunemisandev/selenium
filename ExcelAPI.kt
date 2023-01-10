import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.IOException

class ExcelAPI {
    fun readExcel(file: String, sheetName: String): List<Array<String>> {
        val excelData = mutableListOf<Array<String>>()
        try {
            val fileInputStream = FileInputStream(file)
            val workbook = XSSFWorkbook(fileInputStream)
            val sheet = workbook.getSheet(sheetName)
            for (row in sheet) {
                val data = arrayOf("", "")
                for (cell in row) {
                    if (cell.columnIndex == 0) {
                        data[0] = cell.stringCellValue
                    } else {
                        data[1] = cell.stringCellValue
                    }
                }
                excelData.add(data)
            }
            workbook.close()
            fileInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return excelData
    }
}

