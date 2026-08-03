package section31;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class GetSiblingValue {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://rahulshettyacademy.com/upload-download-test/index.html");

            String fruitName = "Orange";
            Locator fruitNamesList = page.locator("div.rdt_TableBody div[role='row'] div:nth-child(2)");
            int count = fruitNamesList.count();

            for (int i = 0; i < count; i++) {
                Locator ele = fruitNamesList.nth(i);
                if (ele.textContent().trim().equalsIgnoreCase(fruitName)) {
                    // Relative XPath locator to fetch sibling cell value in Playwright
                    String fruitPrice = ele.locator("xpath=following-sibling::div[@id='cell-4-undefined']")
                            .textContent();
                    System.out.println(fruitName + " Price = " + fruitPrice);
                    break;
                }
            }

            browser.close();
        }
    }

}