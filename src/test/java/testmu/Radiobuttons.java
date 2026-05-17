package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import java.time.Duration;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Radiobuttons {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false) //cause by default it opens in headless
        );
        Page page = browser.newPage(); // New Page means new tab
        page.navigate("https://letcode.in/radio");

        // Select any one
        Locator selectAnyOne = page.locator("#yes");
        assertThat(selectAnyOne).not().isChecked();

        selectAnyOne.check();
        assertThat(selectAnyOne).isChecked();

        playwright.close();
    }

}
