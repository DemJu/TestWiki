package page;

import Exceptions.CheckingSiteResults;
import Exceptions.ErrorSiteProcessing;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotEquals;

public class WikiPageTest {
    private final String url = "https://ru.wikipedia.org";
    private WebDriver driver;

    @BeforeClass
    public static void initProperties() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @Before
    public void initWebDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("marionette", true);

        driver = new ChromeDriver(capabilities);

        driver.manage()
                .window()
                .maximize();
        driver.manage()
                .timeouts()
                .implicitlyWait(1, TimeUnit.SECONDS);
        driver.navigate().to(url);
    }

    @Test
    public void Test() {
        try {
            WebElement searchInputElements = driver.findElement(By.cssSelector("input[name='search']"));
            searchInputElements.sendKeys("sys");

            WebElement searchButton = driver.findElement(By.cssSelector("input[name='go']"));
            searchButton.click();

            List<WebElement> searchCountResults = driver.findElements(By.cssSelector("div[class='searchresults mw-searchresults-has-iw'] ul[class='mw-search-results'] li"));
            System.out.println(ErrorSiteProcessing.numberQueriesFound(searchCountResults));

            String currentUrl = driver.getCurrentUrl();
            assertNotEquals(url, currentUrl);
        } catch (CheckingSiteResults e) {
            Assert.fail(e.getMessage());
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
