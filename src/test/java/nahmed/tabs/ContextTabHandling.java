package nahmed.tabs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.KeyboardModifier;

import java.util.Arrays;

public class ContextTabHandling {

    /*
    context.waitForPage() is tied to the browser context (i.e., the whole "browser session").
    It catches a new page regardless of which page within that context triggered it —
    could be mainPage, could be a popup that itself opens another popup, etc.
     */
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

        playwright.close();
    }
}