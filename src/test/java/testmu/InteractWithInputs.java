package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.time.Duration;

public class InteractWithInputs {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) //cause by default it opens in headless
        );
        Page page = browser.newPage(); // New Page means new tab
        page.navigate("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        // fill text
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Please enter your Message")).fill("Hey Tester");
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));

        page.locator("id=showInput").click();
        String message = page.locator("#message").textContent();
        System.out.println(message);

        // get input values
        page.navigate("https://letcode.in/edit");
        String inputValue = page.locator("#getMe").inputValue();
        System.out.println(inputValue);

        String placeHolderValue = page.locator("#fullName").getAttribute("placeholder");
        System.out.println(placeHolderValue);

        Locator fullNameLocator = page.locator("#fullName");
        assertThat(fullNameLocator).hasAttribute("placeholder", "Enter first & last name");

        page.locator("id=clearMe").clear();

        playwright.close();
    }

}
