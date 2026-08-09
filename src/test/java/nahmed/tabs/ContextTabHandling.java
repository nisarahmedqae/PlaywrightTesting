package nahmed.tabs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.KeyboardModifier;

import java.util.Arrays;

public class ContextTabHandling {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://www.testmuai.com/selenium-playground/window-popup-modal-demo/");

        Locator facebook = page.locator("a:has-text('Like us On Facebook')");

        // 1. Listen for the new page on the context level
        Page tab = context.waitForPage(() -> {
            facebook.click(new Locator.ClickOptions()
                    .setModifiers(Arrays.asList(KeyboardModifier.META))
            );
        });

        tab.waitForLoadState();
        System.out.println("New Tab Title: " + tab.title());

        //Use page.waitForPopup() when a specific action inside the current page directly triggers a new window/tab (such as clicking a target="_blank" link or executing window.open()).

        //Use context.waitForPage() when the new tab is triggered at the browser context level without a direct parent page event

        playwright.close();
    }
}