package section13;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrokenLinks {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

            // Extract href attribute using Playwright locator
            String url = page.getByRole(com.microsoft.playwright.options.AriaRole.LINK,
                    new Page.GetByRoleOptions().setName("SoapUI")).getAttribute("href");
            System.out.println(url);

            // Make HTTP HEAD request directly via Playwright's built-in APIRequestContext
            APIResponse response = page.request().head(url);
            int statusCode = response.status();
            System.out.println("Status Code: " + statusCode);

            browser.close();
        }
    }

}