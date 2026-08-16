package nahmed.others;

import com.microsoft.playwright.*;

public class KeyboardActionsExample {

    /*
    Playwright's Keyboard API simulates real key presses at the OS/browser level,
    not just JS events - so it works even with native browser behavior
    (like text selection, form submission via Enter, autocomplete navigation).

    Two main methods:
    - keyboard.press("Key")       -> presses and releases a key (or combo like "Control+A")
    - keyboard.type("text")       -> types text character by character (triggers real keydown/keyup per char)
    - locator.press("Key")        -> same as keyboard.press() but scoped to a focused element first
     */
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com");

        Locator searchBox = page.locator("#search-input");
        searchBox.click();

        // 1. Enter - submit forms / search boxes
        searchBox.fill("playwright java");
        page.keyboard().press("Enter");
        System.out.println("Pressed Enter to submit search");

        // 2. Tab - move focus to next field (useful for testing tab order / accessibility)
        page.keyboard().press("Tab");
        System.out.println("Pressed Tab to move focus to next field");

        // Shift+Tab - move focus backward
        page.keyboard().press("Shift+Tab");
        System.out.println("Pressed Shift+Tab to move focus back");

        // 3. Escape - close modals, dropdowns, autocomplete suggestions
        page.keyboard().press("Escape");
        System.out.println("Pressed Escape to close a dropdown/modal");

        // 4. Select all + Delete/Backspace - clear a field's existing text before typing new text
        Locator textField = page.locator("#username");
        textField.click();
        page.keyboard().press("Control+A"); // use "Meta+A" on Mac if targeting macOS specifically
        page.keyboard().press("Backspace");
        System.out.println("Cleared field using Ctrl+A then Backspace");

        // 5. Copy / Paste - Ctrl+C, Ctrl+V (useful when testing clipboard-dependent flows)
        page.keyboard().press("Control+C");
        System.out.println("Copied selected text");
        page.keyboard().press("Control+V");
        System.out.println("Pasted clipboard content");

        // 6. Arrow keys - navigate dropdown/autocomplete suggestions, sliders, custom widgets
        page.keyboard().press("ArrowDown");
        page.keyboard().press("ArrowDown");
        page.keyboard().press("Enter"); // select highlighted suggestion
        System.out.println("Navigated dropdown suggestions with arrow keys and selected one");

        // 7. Typing with a delay - simulates real human typing speed (helps with JS-heavy
        // autocomplete/search-as-you-type fields that need per-keystroke events, unlike fill())
        Locator liveSearchBox = page.locator("#live-search");
        liveSearchBox.click();
        liveSearchBox.press("Control+A");
        page.keyboard().type("playwright", new Keyboard.TypeOptions().setDelay(100));
        System.out.println("Typed text with delay to trigger live search suggestions");

        // 8. Holding a modifier while clicking (e.g. Ctrl+Click to open in new tab, Shift+Click to multi-select)
        page.keyboard().down("Control");
        page.locator("#some-link").click();
        page.keyboard().up("Control");
        System.out.println("Ctrl+Clicked a link (e.g. to open in new tab)");

        // 9. locator.press() - shortcut that focuses element AND presses key in one call
        page.locator("#comment-box").press("Enter");
        System.out.println("Pressed Enter directly on a specific locator");

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}