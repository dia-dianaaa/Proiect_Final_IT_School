package SharedData.Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeServiceBrowser implements IBrowserService{


    private WebDriver driver;

    @Override
    public void openBrowser() {
        ChromeOptions options = (ChromeOptions) browserOptions();
        driver = new ChromeDriver(options);
    }

    @Override
    public Object browserOptions() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--user-data-dir=C:\\SeleniumChromeProfile"); - nu imi mergea rulatul in paralel de la asta
        options.addArguments("start-maximized");
        options.addArguments("no-sandbox");
        options.addArguments("--host-resolver-rules="
                + "MAP googleads.g.doubleclick.net 0.0.0.0, "
                + "MAP doubleclick.net 0.0.0.0, "
                + "MAP securepubads.g.doubleclick.net 0.0.0.0, "
                + "MAP pagead2.googlesyndication.com 0.0.0.0, "
                + "MAP tpc.googlesyndication.com 0.0.0.0, "
                + "MAP googlesyndication.com 0.0.0.0, "
                + "MAP googleadservices.com 0.0.0.0, "
                + "MAP google_vignette 0.0.0.0");
        String ciCd = System.getProperty("ci_cd");

        if(Boolean.parseBoolean(ciCd))
        {
            options.addArguments("--headless");
        }
        return options;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
