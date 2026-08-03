package section11;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.KeyboardModifier;

import java.util.Arrays;

public class LinkScope {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

            // Count of links on the entire page
            System.out.println(page.locator("a").count());

            // Limiting scope to footer section
            Locator footerSection = page.locator("#gf-BIG");
            System.out.println(footerSection.locator("a").count());

            // Links count of only 1st column of footer section
            Locator columnSection = page.locator("tr td:nth-child(1) ul");
            Locator columnLinks = columnSection.locator("a");
            int columnSectionCount = columnLinks.count();
            System.out.println(columnSectionCount);

            // Click each link (skipping the first one) to open in a new tab
            for (int i = 1; i < columnSectionCount; i++) {
                // Control/Meta + Click opens the link in a background tab
                columnLinks.nth(i).click(new Locator.ClickOptions()
                        .setModifiers(Arrays.asList(KeyboardModifier.CONTROL)));
            }

            // Iterate over all opened pages in the context and print their titles
            for (Page openedPage : context.pages()) {
                // Ensure the tab has loaded before getting title
                openedPage.waitForLoadState();
                System.out.println(openedPage.title());
            }

            browser.close();
        }
    }

}