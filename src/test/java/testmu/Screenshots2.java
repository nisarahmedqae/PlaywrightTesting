package testmu;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;
import java.util.Arrays;

public class Screenshots2 {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true) //cause by default it opens in headless
        );
        Page page = browser.newPage(); // New Page means new tab
        page.navigate("https://www.testmuai.com/selenium-playground/input-form-demo/");

        // screenshots
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();

        // masking locator
        Locator password = page.locator("input#inputPassword4");
        password.fill("something");
        password.scrollIntoViewIfNeeded();
        page.screenshot(screenshotOptions.setPath(Paths.get("./screenshots/masked_locator_sc.png"))
                .setMask(Arrays.asList(password))
        );

        // caret screenshot
        password.click();
        page.screenshot(new ScreenshotOptions().setCaret(ScreenshotCaret.HIDE)
                .setPath(Paths.get("./screenshots/caret_hide.png"))
        );

        page.screenshot(new ScreenshotOptions().setCaret(ScreenshotCaret.INITIAL)
                .setPath(Paths.get("./screenshots/caret_initial.png"))
        );

        playwright.close();
    }

}
