package org.selenium.pom.base;

import io.qameta.allure.Allure;
import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.DriverManagerOriginal;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.utils.CookieUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class BaseTest {

    private ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriverManager(DriverManagerAbstract driverManager) {
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager() {
        return this.driverManager.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public synchronized void startDriver(@Optional String browser) {
        browser = System.getProperty("browser", browser);
        if (browser == null) {
            browser = "CHROME";
        }
        //driver = new DriverManager().initializeDriver();
        //setDriver(new DriverManagerOriginal().initializeDriver(browser));
        //setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        //setDriver(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)).getDriver());
        setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        System.out.println("CURRENT THREAD : " + Thread.currentThread().getId() + ", DRIVER = " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod(alwaysRun = true)
    public synchronized void quitDriver(@Optional String browser, ITestResult result) throws InterruptedException, IOException {
        Thread.sleep(500);
        if (result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("screenshots" + File.separator + browser
                    + File.separator + result.getTestClass().getRealClass().getSimpleName()
                    + "_" + result.getMethod().getMethodName() + ".png");
            takeScreenshot(destFile);
            //takeScreenshotUsingAShot(destFile);
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(destFile));
        }
        getDriver().quit();
    }

    public void injectCookiesToBroowser(Cookies cookies) {
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie : seleniumCookies) {
            getDriver().manage().addCookie(cookie);
        }
    }

    public File takeScreenshot(File destFile) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);

        return srcFile;
    }

    public void takeScreenshotUsingAShot(File destFile) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());

        try {
            ImageIO.write(screenshot.getImage(), "PNG", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Parameters("browser")
    public void addAttachmentToAllureReport(@Optional String browser, String description) throws IOException {
        File destFile = new File("screenshots" + File.separator + browser + File.separator + description + "_" + getCurrentTimeStamp() + ".png");
        takeScreenshot(destFile);
        Allure.addAttachment("Screenshot", FileUtils.openInputStream(destFile));
    }

    public String getCurrentTimeStamp() {
        return "" + Calendar.getInstance().getTimeInMillis();
    }
}
