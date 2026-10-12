package SharedData.Browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeServiceBrowser implements IBrowserService{
    private WebDriver driver;

    @Override
    public void openBrowser() {
        EdgeOptions options = (EdgeOptions) browserOptions();
        driver = new EdgeDriver(options);
    }

    @Override
    public Object browserOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("start-maximized");
        options.addArguments("no-sandbox");
        // block ad domains, the same way as in ChromeServiceBrowser
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
