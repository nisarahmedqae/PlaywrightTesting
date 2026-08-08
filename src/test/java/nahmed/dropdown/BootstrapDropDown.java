package nahmed.dropdown;

import com.microsoft.playwright.*;

public class BootstrapDropDown {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.hdfc.bank.in/");

        // Product Type
        page.locator("(//div[@class='sBtn-text'])[1]").click();
        Locator productTypes = page.locator("//ul[@class='options']//li");
        System.out.println(productTypes.count());

        productTypes.allInnerTexts().forEach(System.out::println);

        // Select "Accounts" from the dropdown
        productTypes.filter(new Locator.FilterOptions().setHasText("Accounts")).click();
        page.waitForTimeout(6000);

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}