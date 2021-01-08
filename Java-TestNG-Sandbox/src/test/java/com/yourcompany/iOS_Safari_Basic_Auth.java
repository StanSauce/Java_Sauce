package com.yourcompany;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


//import com.sun.jna.platform.win32.Sspi.TimeStamp;
import static org.testng.Assert.assertEquals;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Array;
import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



public class iOS_Safari_Basic_Auth {
	

	  public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	  public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	  public static long beforeSession;
	  public static long afterSession;
	  
      public void login(WebDriver driver, String username, String password) {


      }
      
	@Test
    public static void main() throws IOException, JSONException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
         	caps.setCapability("name", "Safari_Basic_Auth");
	  		caps.setCapability("deviceName","iPhone.*");
		  	caps.setCapability("platformVersion", "14");
		  	caps.setCapability("platformName", "iOS");

//--------------------------------------------------------------------------------------------------------------------------------------
    
		  beforeSession = System.currentTimeMillis();
		  System.out.println("BEFORE SESSION " + beforeSession);
		  IOSDriver<WebElement> driver = new IOSDriver<WebElement>(new URL("https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com/wd/hub"), caps);
		//  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  System.out.println("SESSION HAS STARTED - SESSION CREATION TIME: " + (System.currentTimeMillis() - beforeSession));

//-----------------------------------------------------------------------------------------------------------------------------------

		driver.get("https://the-internet.herokuapp.com/basic_auth");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//Switch to the native context in order to access the login popup
		driver.context("NATIVE_APP");
		// These xPaths are for iOS 14+...
		// TODO build xPaths for previous iOS versions
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"SFDialogView\"]/XCUIElementTypeTextField")).sendKeys("admin");
		driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"SFDialogView\"]/XCUIElementTypeSecureTextField")).sendKeys("admin");
		driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Log In\"])[1]")).click();
		// The page has to load prior to switching back to Safari...
		try{
			Thread.sleep(10000);
		}catch (InterruptedException ie1) {
			//ie1.printStackTrace();
		}

		// Switch back to the webview
		Set<String> allContext = driver.getContextHandles();
		for (String context : allContext) {
			if (context.contains("WEBVIEW"))
				driver.context(context);
		}
		//
		// commands and such
		// end the test
       	driver.quit();
      }

}
   