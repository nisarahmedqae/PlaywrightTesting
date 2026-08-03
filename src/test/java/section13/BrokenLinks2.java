package section13;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.asserts.SoftAssert;

public class BrokenLinks2 {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

            SoftAssert softAssert = new SoftAssert();

            // Get all footer links
            Locator links = page.locator("li[class='gf-li'] a");
            int linkCount = links.count();

            for (int i = 0; i < linkCount; i++) {
                Locator link = links.nth(i);
                String url = link.getAttribute("href");
                String linkText = link.textContent();

                if (url != null && !url.isEmpty()) {
                    // Perform lightweight HTTP HEAD request using Playwright's native API client
                    APIResponse response = page.request().head(url);
                    int statusCode = response.status();
                    System.out.println("Status Code: " + statusCode);

                    softAssert.assertTrue(statusCode < 400,
                            "Broken Link = " + linkText + " & Status Code = " + statusCode);
                }
            }

            softAssert.assertAll();
            browser.close();
        }
    }

}