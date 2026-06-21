package sdet;

import com.microsoft.playwright.*;

public class BootstrapDropDown {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.hdfcbank.com/");

        // Product Type
        page.locator("text=Select Product Type").click();
        Locator productTypes = page.locator("//ul[@class='dropdown1 dropdown-menu']/li");
        System.out.println(productTypes.count());

        // Select "Accounts" from the dropdown
        productTypes.filter(new Locator.FilterOptions().setHasText("Accounts")).click();

        // Product
        page.locator("text=Select Product").click();
        Locator products = page.locator("//ul[@class='dropdown2 dropdown-menu']/li");
        System.out.println(products.count());

        // Select "Savings Accounts" from the dropdown
        products.filter(new Locator.FilterOptions().setHasText("Savings Accounts")).click();

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}