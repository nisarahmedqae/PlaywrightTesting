package nahmed.inputs;

import com.microsoft.playwright.*;

public class ScrollingExample {

    /*
    Scrolling in Playwright comes up in a few different flavors:

    1. scrollIntoViewIfNeeded() - built-in Locator method, scrolls just enough
       to bring the element into the viewport (instant, no easing options).
       This is what most actions (click, fill) already do internally before acting.

    2. mouse.wheel() - simulates an actual mouse wheel scroll at the current
       mouse position. Useful for infinite-scroll pages, custom scroll containers,
       or when you need to trigger scroll-based lazy loading.

    3. keyboard scrolling (Page Down, End, etc.) - simulates a real user pressing
       keys to scroll, useful when testing keyboard accessibility.

    4. JS-based scroll via evaluate() - needed when you want smooth scroll behavior,
       scroll to exact pixel coordinates, or scroll a specific inner container
       (not the whole page) that built-in methods don't target well.
     */
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com");

        // 1. scrollIntoViewIfNeeded() - simplest built-in option
        // does nothing if the element is already visible, otherwise jumps it into view
        Locator footerLink = page.locator("#footer-link");
        footerLink.scrollIntoViewIfNeeded();
        System.out.println("Scrolled footer link into view");

        // Note: click(), fill(), etc. already call this internally -
        // you rarely need to call it manually before an action

        // 2. mouse.wheel() - simulates real scroll wheel input
        // good for infinite scroll / lazy-loaded content that listens for scroll events
        page.mouse().wheel(0, 1000); // scrollX, scrollY (positive Y = scroll down)
        System.out.println("Scrolled down 1000px using mouse wheel");

        // scroll back up
        page.mouse().wheel(0, -500);
        System.out.println("Scrolled up 500px using mouse wheel");

        // hovering over a specific element first, then wheel-scrolling from that position
        // useful for scrolling INSIDE a specific container (e.g. a chat box, dropdown list)
        page.locator("#chat-container").hover();
        page.mouse().wheel(0, 300);
        System.out.println("Scrolled inside chat container via wheel");

        // 3. Keyboard-based scrolling - simulates real user key presses
        page.keyboard().press("End");
        System.out.println("Pressed End key - jumped to bottom of page");

        page.keyboard().press("Home");
        System.out.println("Pressed Home key - jumped to top of page");

        page.keyboard().press("PageDown");
        System.out.println("Pressed Page Down");

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}