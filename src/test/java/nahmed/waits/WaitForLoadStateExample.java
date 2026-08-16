package nahmed.waits;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

public class WaitForLoadStateExample {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com");

        // Wait for the full 'load' event (default) - images, css, etc. all loaded
        page.waitForLoadState();
        System.out.println("Page fully loaded (LOAD state)");

        // Click something that triggers a partial page update / SPA navigation
        page.click("#load-more-button");

        // Wait for DOM to be ready only (faster - doesn't wait for images/css)
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        System.out.println("DOM content loaded");

        // Wait for network to go idle (use cautiously - can be flaky)
        page.waitForLoadState(LoadState.NETWORKIDLE);
        System.out.println("Network is idle");

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}