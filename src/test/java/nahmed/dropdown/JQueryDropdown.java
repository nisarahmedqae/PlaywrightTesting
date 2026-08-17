package nahmed.dropdown;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;

import java.time.Duration;

public class JQueryDropdown {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.testmuai.com/selenium-playground/jquery-dropdown-search-demo/");

        // multiple options
        Locator selectCountry = page.locator("//span[@aria-labelledby='select2-country-container']");
        selectCountry.click();

        Locator searchCountry = page.locator("//span[contains(@class,'select2-search--dropdown')]//input[@class='select2-search__field']");
        searchCountry.fill("in");
        page.keyboard().press("Enter");
        page.waitForTimeout(3000);

        playwright.close();
    }

}
