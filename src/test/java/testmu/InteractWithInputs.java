package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import java.time.Duration;

public class InteractWithInputs {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        Page page = browser.newPage();
        page.navigate("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Please enter your Message")).fill("Hey Tester");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

        page.locator("id=showInput").click();
        String message = page.locator("#message").textContent();
        System.out.println(message);

        playwright.close();
    }

}
