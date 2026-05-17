package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;

import java.time.Duration;
import java.util.List;

public class JQueryDropdown {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) //cause by default it opens in headless
        );
        Page page = browser.newPage(); // New Page means new tab
        page.navigate("https://www.testmuai.com/selenium-playground/jquery-dropdown-search-demo/");

        // multiple options
        Locator selectCountry = page.locator("//span[@aria-labelledby='select2-country-container']");
        selectCountry.click();

        Locator searchCountry = page.locator("//span[contains(@class,'select2-search--dropdown')]//input[@class='select2-search__field']");
        searchCountry.fill("in");
        page.keyboard().press("Enter");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));

        playwright.close();
    }

}
