package nahmed.frames;

import com.microsoft.playwright.*;

import java.util.List;

public class FramesExample {

    /*
    An iframe embeds one HTML document inside another - each iframe has its own
    separate DOM tree. page.locator() by default only searches the MAIN page's DOM,
    NOT inside iframes - so elements inside an iframe are invisible to normal locators
    unless you explicitly target the frame first.

    Common real scenarios where you hit this:
    - Payment widgets (Stripe, PayPal, Razorpay) embed their card input fields in an iframe
      for PCI security - the parent page's JS can't directly touch card number/CVV fields
    - Third-party embeds (YouTube video player, Google Maps, chat widgets, ad units)
    - Legacy multi-frame websites (older enterprise apps, some CMS admin panels)
    - "Sandboxed" widgets that isolate their JS/CSS from the host page

    Two ways to get a Frame in Playwright:
    - page.frameLocator("iframe selector") -> returns a FrameLocator (recommended, auto-waits)
    - page.frame("name") or page.frames() -> returns a Frame object (older API, less auto-waiting)
     */
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com/checkout");

        // 1. frameLocator() - MODERN, RECOMMENDED approach
        // works like a normal locator but scoped to inside the iframe, auto-waits for iframe to load
        FrameLocator paymentFrame = page.frameLocator("iframe#payment-frame");

        paymentFrame.locator("#card-number").fill("4242424242424242");
        paymentFrame.locator("#expiry").fill("12/28");
        paymentFrame.locator("#cvv").fill("123");
        System.out.println("Filled card details inside payment iframe");

        // 2. Chaining - a locator scoped inside a frame, still gets the same auto-wait/retry behavior
        paymentFrame.locator("button#pay-now").click();
        System.out.println("Clicked pay button inside iframe");

        // 3. frameLocator by name attribute (some iframes are identified by name, not id/class)
        FrameLocator namedFrame = page.frameLocator("iframe[name='chat-widget']");
        namedFrame.locator("#chat-input").fill("Hello support!");

        // 4. Nested iframes (iframe inside iframe) - just chain frameLocator() calls
        FrameLocator outerFrame = page.frameLocator("iframe#outer-frame");
        FrameLocator innerFrame = outerFrame.frameLocator("iframe#inner-frame");
        innerFrame.locator("#deep-field").fill("nested frame value");
        System.out.println("Filled field inside nested iframe");

        // 5. Older Frame API - useful when you need frame-level info (url, name) not just locators
        for (Frame frame : page.frames()) {
            System.out.println("Frame name: " + frame.name() + " | URL: " + frame.url());
        }

        // Get a specific frame by its name attribute
        Frame specificFrame = page.frame("payment-frame");
        if (specificFrame != null) {
            System.out.println("Found frame directly: " + specificFrame.url());
        }

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}
