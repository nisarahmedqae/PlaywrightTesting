package nahmed.linkchecker;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SSLCheck {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false)
                //.setProxy(new Proxy("http://myproxy.com:3128"))
        );

        // Accept insecure SSL certificates at the BrowserContext level
        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setIgnoreHTTPSErrors(true));

        Page page = context.newPage();
        page.navigate("https://expired.badssl.com/");

        System.out.println(page.title());

        page.waitForTimeout(3000);
        playwright.close();

    }

}