package nahmed.others;

import com.microsoft.playwright.*;

public class JavaScriptActionsExample {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com");

        // 1. Scroll an element into view with custom behavior (smooth/center)
        // Playwright's scrollIntoViewIfNeeded() only does instant/nearest - use JS for smooth/center control
        page.locator("#footer").evaluate("el => el.scrollIntoView({ behavior: 'smooth', block: 'center' })");

        // 2. Force-click an element that's covered/intercepted by an overlay
        // Native click() fails with "element intercepts pointer events" - JS click bypasses that check
        page.locator("#hidden-behind-overlay-btn").evaluate("el => el.click()");

        // 3. Check a value inside Shadow DOM that locators can't easily pierce in some edge cases
        String shadowText = (String) page.evaluate(
                "() => document.querySelector('#shadow-host').shadowRoot.querySelector('span').textContent");
        System.out.println("Shadow DOM text: " + shadowText);

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}