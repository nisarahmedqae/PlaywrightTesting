package nahmed.testmu;

import com.microsoft.playwright.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Radiobuttons {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://letcode.in/radio");

        // Select any one
        Locator selectAnyOne = page.locator("#yes");
        assertThat(selectAnyOne).not().isChecked();

        selectAnyOne.check();
        assertThat(selectAnyOne).isChecked();

        playwright.close();
    }

}
