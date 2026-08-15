package nahmed.linkchecker;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class BrokenLinks {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

        // Extract href attribute using Playwright locator
        String url = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("SoapUI"))
                .getAttribute("href");
        System.out.println(url);

        // Make HTTP HEAD request directly via Playwright's built-in APIRequestContext
        APIResponse response = page.request().head(url);
        int statusCode = response.status();
        System.out.println("Status Code: " + statusCode);

        playwright.close();
    }

}