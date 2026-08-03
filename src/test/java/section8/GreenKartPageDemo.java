package section8;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Arrays;
import java.util.List;

public class GreenKartPageDemo {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://rahulshettyacademy.com/seleniumPractise/#/");

            String[] itemsNeeded = { "Cucumber", "Brocolli" };

            // Add items to cart
            addItems(page, itemsNeeded);

            // Click on cart icon
            page.locator("img[alt='Cart']").click();

            // Click on PROCEED TO CHECKOUT
            page.locator("button:has-text('PROCEED TO CHECKOUT')").click();

            // Enter promo code
            page.locator("input.promoCode").fill("rahulshettyacademy");

            // Click on apply button
            page.locator(".promoBtn").click();

            // Wait till promo code text appears and print it
            Locator promoInfo = page.locator(".promoInfo");
            promoInfo.waitFor();
            System.out.println(promoInfo.textContent());

            browser.close();
        }
    }

    public static void addItems(Page page, String[] itemsNeeded) {
        int j = 0;
        Locator products = page.locator("h4.product-name");
        int count = products.count();
        List<String> itemsNeededList = Arrays.asList(itemsNeeded);

        for (int i = 0; i < count; i++) {
            String[] name = products.nth(i).textContent().split("-");
            String formattedName = name[0].trim();

            if (itemsNeededList.contains(formattedName)) {
                // Click on "ADD TO CART" for the matching product index
                page.locator("//div[@class='product-action']/button").nth(i).click();

                j++;
                if (j == itemsNeeded.length) {
                    break;
                }
            }
        }
    }
}