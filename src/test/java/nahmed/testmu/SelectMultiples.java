package nahmed.testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;

import java.time.Duration;
import java.util.List;

public class SelectMultiples {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

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
