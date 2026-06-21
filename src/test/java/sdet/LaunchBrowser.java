package sdet;

import com.microsoft.playwright.*;

public class LaunchBrowser {

    public static void main(String[] args) {
        // Initialize Playwright
        Playwright playwright = Playwright.create();

        // Launch specific browser engine and channel (Edge)
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setChannel("msedge") // Forces Playwright to use Microsoft Edge instead of vanilla Chromium
        );

        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.nopcommerce.com/en");

        // Capture page details
        System.out.println(page.title());      // Selenium's getTitle()
        System.out.println(page.url());        // Selenium's getCurrentUrl()
        System.out.println(page.content());    // Selenium's getPageSource()

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
