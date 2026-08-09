package nahmed.browser;

import com.microsoft.playwright.*;

public class BrowserNotifications {

    public static void main(String[] args) throws InterruptedException {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // Notifications are automatically disabled/blocked by default in a fresh Context
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://redbus.in");

        // Hard sleep replacement (Playwright's built-in wait method)
        page.waitForTimeout(3000);

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}