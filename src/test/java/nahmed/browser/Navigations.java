package nahmed.browser;

import com.microsoft.playwright.*;

public class Navigations {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        // Navigate to the first URL (Replaces driver.get())
        page.navigate("https://flipkart.com");

        // Navigate to the second URL
        page.navigate("https://amazon.in");

        // Navigate backward in history (Replaces driver.navigate().back())
        page.goBack();

        // Navigate forward in history (Replaces driver.navigate().forward())
        page.goForward();

        // Refresh the current page (Replaces driver.navigate().refresh())
        page.reload();

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
