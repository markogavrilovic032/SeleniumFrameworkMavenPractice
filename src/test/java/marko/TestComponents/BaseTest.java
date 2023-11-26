package marko.TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import marko.pageobjects.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public static LandingPage landingPage;
    public static WebDriver initializeDriver() throws IOException {

//        Properties prop = new Properties();
        Properties properties = new Properties();
//        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");

        properties.load(fileInputStream);
//        String browserName = properties.getProperty("browser");
//        read browser name from GlobalData.properties
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        // read browser name from terminal if is null will read from GlobalData.properties

        if(browserName.contains("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")){
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            //Firefox
            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.gecko.driver", path + "\\src\\main\\java\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            //Edge
            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.edge.driver", path + "\\src\\main\\java\\resources\\drivers\\edge.exe");
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public static LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
//        driver.close();
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);
        //string to hashmap + jakson databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
        // -> {map, map1}
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }
}
