package nahmed.waits;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitForLocatorExample {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com");

        // Wait for an element to be visible (default state)
        Locator submitButton = page.locator("#submit-button");
        submitButton.waitFor();
        System.out.println("Submit button is visible");

        submitButton.click();

        // Wait for a loading spinner to appear
        Locator spinner = page.locator("#loading-spinner");
        spinner.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        System.out.println("Loading spinner is visible");

        // Wait for a loading spinner to disappear
        spinner.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        System.out.println("Loading spinner is hidden");

        // Wait for an element to be attached to the DOM (may not be visible yet)
        Locator resultContainer = page.locator("#result-container");
        resultContainer.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.ATTACHED));
        System.out.println("Result container is attached to DOM");

        // Wait for an element to be removed from the DOM
        Locator tempMessage = page.locator("#temp-message");
        tempMessage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.DETACHED));
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