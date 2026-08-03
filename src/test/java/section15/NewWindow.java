package section15;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class NewWindow {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();

            // Parent Page
            Page parentPage = context.newPage();
            parentPage.navigate("https://rahulshettyacademy.com/angularpractice/");

            // Open a new tab within the same context
            Page childPage = context.newPage();
            childPage.navigate("https://courses.rahulshettyacademy.com/");

            // Extract text from child page
            String courseName = childPage.locator("div[title*='Postman']:nth-child(2)").textContent();

            // Interact directly with parent page without any context switching
            parentPage.locator("input[name='name']").fill(courseName);

            browser.close();
        }
    }

}