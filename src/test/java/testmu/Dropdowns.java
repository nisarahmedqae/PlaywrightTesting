package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Dropdowns {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) //cause by default it opens in headless
        );
        Page page = browser.newPage(); // New Page means new tab
        page.navigate("https://letcode.in/dropdowns");

        // Select by value or label
        Locator selectFruits = page.locator("select#fruits");

        // select by value
        selectFruits.selectOption("2");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        Locator selectedOption = page.locator(".notification");
        assertThat(selectedOption).containsText("Orange");
        System.out.println(selectedOption.textContent());

        // select by label
        selectFruits.selectOption("Mango");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        assertThat(selectedOption).containsText("Mango");
        System.out.println(selectedOption.textContent());

        // select by index
        selectFruits.selectOption(new SelectOption().setIndex(1));
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        assertThat(selectedOption).containsText("Apple");
        System.out.println(selectedOption.textContent());

        // select multiple


        playwright.close();
    }

}
