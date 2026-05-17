package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.time.Duration;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SelectMultiples {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) //cause by default it opens in headless
        );
        Page page = browser.newPage(); // New Page means new tab
        page.navigate("https://letcode.in/selectable");

        // multiple options
        Locator selectAll = page.locator("//div[@class='list-container']//div[@class='ng-star-inserted']");
        System.out.println(selectAll.count());

        List<String> allInnerTexts = selectAll.allInnerTexts();
        allInnerTexts.forEach(System.out::println);

        page.locator("//div[text()=' Playwright ']").click();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        page.locator("//div[text()=' Selenium ']").click();
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));

        playwright.close();
    }

}
