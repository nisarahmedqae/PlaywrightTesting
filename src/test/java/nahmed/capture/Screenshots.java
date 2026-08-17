package nahmed.capture;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;
import java.nio.file.Paths;

public class Screenshots {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://letcode.in/test");

        // Basic screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("./capture/screenshots/basic_sc.png")));

        // Full page screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true)
                .setPath(Paths.get("./capture/screenshots/fullPage_sc.png")));

        // Locator screenshot (PNG)
        Locator shadow = page.locator("//a[@href='/shadow']/..");
        shadow.screenshot(new Locator.ScreenshotOptions()
                .setPath(Paths.get("./capture/screenshots/locator_sc.png")));

        // Locator screenshot (JPEG with quality)
        shadow.screenshot(new Locator.ScreenshotOptions()
                .setPath(Paths.get("./capture/screenshots/locator_sc.jpeg"))
                .setType(ScreenshotType.JPEG)
                .setQuality(80)
        );

        playwright.close();
    }
}