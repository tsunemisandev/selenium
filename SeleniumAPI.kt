import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SeleniumAPI {
    private lateinit var driver: WebDriver

    init {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver")
        driver = ChromeDriver()
    }

    fun navigateTo(url: String) {
        driver.get(url)
    }

    fun getTitle(): String {
        return driver.title
    }

    fun login(email: String, password: String) {
        val wait = WebDriverWait(driver, 10)
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")))
        driver.findElement(By.id("email")).sendKeys(email)
        driver.findElement(By.id("password")).sendKeys(password)
        driver.findElement(By.id("submit")).click()
    }

    fun isLoggedIn(): Boolean {
        try {
            val wait = WebDriverWait(driver, 10)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout")))
            driver.findElement(By.id("logout"))
            return true
        } catch (e: Exception) {
            return false
        }
    }
    
    fun clickMenuAndCheckTitle(menuId: String, title: String) {
        val wait = WebDriverWait(driver, 10)
        val mainWindow = driver.windowHandle
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(menuId)))
        driver.findElement(By.id(menuId)).click()

        val allWindows = driver.windowHandles
        allWindows.remove(mainWindow)
        val newTab = allWindows.iterator().next()
        driver.switchTo().window(newTab)

        wait.until(ExpectedConditions.titleContains(title))
        assertEquals(title, driver.title)
    }

    fun close() {
        driver.quit()
    }
}