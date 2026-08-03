package section13;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.Proxy;

public class SSLCheck {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {

            // Configure browser launch options (including proxy if needed)
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setHeadless(false);

			/*
			// Use of proxy in Playwright
			launchOptions.setProxy(new Proxy("http://ipAddress:4444"));
			*/

            Browser browser = playwright.chromium().launch(launchOptions);

            // Accept insecure SSL certificates at the BrowserContext level
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setIgnoreHTTPSErrors(true));

            Page page = context.newPage();
            page.navigate("https://expired.badssl.com/");

            System.out.println(page.title());

            browser.close();
        }
    }

}