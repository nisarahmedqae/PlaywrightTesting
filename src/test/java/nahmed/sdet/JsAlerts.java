package nahmed.sdet;

import com.microsoft.playwright.*;

public class JsAlerts {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        // --- 1. Alert window with OK button ---
        // Register a one-time dialog listener to handle the incoming alert
        page.onceDialog(dialog -> {
            System.out.println("Alert text: " + dialog.message());
            dialog.accept(); // Clicks OK
        });
        page.locator("//button[text()='Click for JS Alert']").click();
        System.out.println(page.locator("#result").innerText());


        // --- 2. Alert window with OK & Cancel button ---
        // Handle Dismiss (Cancel)
        page.onceDialog(dialog -> {
            System.out.println("Confirm text: " + dialog.message());
            dialog.dismiss(); // Clicks Cancel
        });
        page.locator("//button[text()='Click for JS Confirm']").click();
        System.out.println(page.locator("#result").innerText());

        // Handle Accept (OK)
        page.onceDialog(dialog -> dialog.accept()); // Clicks OK
        page.locator("//button[text()='Click for JS Confirm']").click();
        System.out.println(page.locator("#result").innerText());


        // --- 3. Alert window with input box ---
        page.onceDialog(dialog -> {
            // Read and print message inside the prompt
            System.out.println("Prompt text: " + dialog.message());
            // Accept the alert while passing a custom input value
            dialog.accept("Message putted on alert");
        });
        page.locator("//button[text()='Click for JS Prompt']").click();
        System.out.println(page.locator("#result").innerText());


        //page.onceDialog()
        // Unregisters automatically after handling one dialog.

        //page.onDialog()
        // Persists for the entire duration of the Page session.

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}