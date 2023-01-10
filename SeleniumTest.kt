class SeleniumTest {
    private val seleniumAPI = SeleniumAPI()

    @Test
    fun testWithSeleniumAPI() {
        val excelAPI = ExcelAPI()
        val excelData = excelAPI.readExcel("path/to/excel/file", "Sheet1")
        for (row in excelData) {
            seleniumAPI.navigateTo("http://example.com/login")
            seleniumAPI.login(row[0], row[1])
            assertTrue(seleniumAPI.isLoggedIn())
            seleniumAPI.close()
        }
    }
}
