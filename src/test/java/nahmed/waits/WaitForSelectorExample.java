package nahmed.waits;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitForSelectorExample {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com");

        // Wait for an element to be visible (default state)
        page.waitForSelector("#submit-button");
        System.out.println("Submit button is visible");

        page.click("#submit-button");

        // Wait for a loading spinner to disappear
        page.waitForSelector("#loading-spinner",
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
        System.out.println("Loading spinner is hidden");

        // Wait for an element to be attached to the DOM (may not be visible yet)
        page.waitForSelector("#result-container",
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED));
        System.out.println("Result container is attached to DOM");

        // Wait for an element to be removed from the DOM
        page.waitForSelector("#temp-message",
                new Page.WaitForSelectorOptions().setState(WaitForSelectorState.DETACHED));
        System.out.println("Temp message is detached from DOM");

        // Set globally for all actions
        page.setDefaultTimeout(10000);
        page.setDefaultNavigationTimeout(15000);

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}