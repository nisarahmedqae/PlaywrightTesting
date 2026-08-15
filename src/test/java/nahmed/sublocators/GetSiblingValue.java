package nahmed.sublocators;

import com.microsoft.playwright.*;

public class GetSiblingValue {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://rahulshettyacademy.com/upload-download-test/index.html");
        page.waitForTimeout(3000);

        String fruitName = "Orange";
        Locator fruitNamesList = page.locator("//div[@id='cell-2-undefined']");
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

        playwright.close();

    }

}