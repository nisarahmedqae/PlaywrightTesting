package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;
import com.microsoft.playwright.Page.ScreenshotOptions;

import java.nio.file.Paths;
import java.time.Duration;

public class Screenshots {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true) //cause by default it opens in headless
        );
        Page page = browser.newPage(); // New Page means new tab
        page.navigate("https://letcode.in/test");

        // screenshots
        ScreenshotOptions screenshotOptions = new ScreenshotOptions();
        page.screenshot(screenshotOptions.setPath(Paths.get("./screenshots/basic_sc.png")));

        // full page screenshot
        page.screenshot(screenshotOptions.setFullPage(true).setPath(Paths.get("./screenshots/fullPage_sc.png")));

        // locator screenshot
        Locator shadow = page.locator("//p[text()=' Shadow ']/../..");
        shadow.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("./screenshots/locator_sc.png")));

        playwright.close();
    }

}
