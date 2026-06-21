package sdet;

import com.microsoft.playwright.*;

public class OpenLinkInNewTab {

    public static void main(String[] args) {
        // Initialize Playwright and Launch Browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://jqueryui.com/tooltip/");

        // 1. Locate the link
        Locator draggableLink = page.locator("text=Draggable");

        // 2. Focus on the link to target keyboard actions
        draggableLink.focus();

        // 3. Wait for the new page while hitting the Control+Enter shortcut
        Page newTab = context.waitForPage(() -> {
            page.keyboard().press("Control+Enter");
        });

        // Optional: Interact with or assert on the new tab directly
        System.out.println("Original Page Title: " + page.title());
        System.out.println("New Tab Page Title: " + newTab.title());

        // Keep browser open briefly to see the execution (Replaces Thread.sleep)
        page.waitForTimeout(3000);

        // Clean up resources manually
        newTab.close();
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
