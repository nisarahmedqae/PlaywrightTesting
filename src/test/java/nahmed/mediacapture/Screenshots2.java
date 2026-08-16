package nahmed.mediacapture;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;
import java.util.Arrays;

public class Screenshots2 {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.testmuai.com/selenium-playground/input-form-demo/");

        // masking locator
        Locator password = page.locator("input#inputPassword4");
        password.fill("something");
        password.scrollIntoViewIfNeeded();

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("./screenshots/masked_locator_sc.png"))
                .setMask(Arrays.asList(password))
        );

        // caret screenshot
        password.click();
        page.screenshot(new Page.ScreenshotOptions()
                .setCaret(ScreenshotCaret.HIDE)
                .setPath(Paths.get("./screenshots/caret_hide.png"))
        );

        page.screenshot(new Page.ScreenshotOptions()
                .setCaret(ScreenshotCaret.INITIAL)
                .setPath(Paths.get("./screenshots/caret_initial.png"))
        );

        playwright.close();
    }

}
