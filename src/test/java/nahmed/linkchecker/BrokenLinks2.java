package nahmed.linkchecker;

import com.microsoft.playwright.*;
import org.testng.asserts.SoftAssert;

public class BrokenLinks2 {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

        SoftAssert softAssert = new SoftAssert();

        // Get all footer links
        Locator links = page.locator("li[class='gf-li'] a");
        links.nth(1).scrollIntoViewIfNeeded();
        int linkCount = links.count();

        for (int i = 0; i < linkCount; i++) {
            Locator link = links.nth(i);
            String url = link.getAttribute("href");

            if (url != null && !url.isEmpty() && url.startsWith("http")) {
                // Perform lightweight HTTP HEAD request using Playwright's native API client
                APIResponse response = page.request().head(url);
                int statusCode = response.status();
                System.out.println("Link = " + url + " & Status Code = " + statusCode);

                softAssert.assertTrue(statusCode < 400,
                        "Broken Link = " + url + " & Status Code = " + statusCode);
            }
        }

        softAssert.assertAll();
        playwright.close();

    }

}