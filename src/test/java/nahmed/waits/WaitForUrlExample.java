package nahmed.waits;

import com.microsoft.playwright.*;
import java.util.regex.Pattern;

public class WaitForUrlExample {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com/login");

        // Fill login form and submit
        page.fill("#username", "testuser");
        page.fill("#password", "password123");
        page.click("#login-button");

        // Wait until the URL changes to the dashboard page
        page.waitForURL("**/dashboard");

        System.out.println("Successfully navigated to: " + page.url());

        // Exact match with glob pattern
        page.waitForURL("https://example.com/dashboard");

        // Wildcard glob pattern (most common)
        page.waitForURL("**/dashboard/**");

        // Using a regex Pattern for more control
        page.waitForURL(Pattern.compile(".*\\/dashboard\\?user=\\d+"));

        // With a custom timeout
        page.waitForURL("**/dashboard",
                new Page.WaitForURLOptions().setTimeout(15000));

        // Using a predicate function
        page.waitForURL(url -> url.contains("dashboard") && url.contains("success"));

    }
}