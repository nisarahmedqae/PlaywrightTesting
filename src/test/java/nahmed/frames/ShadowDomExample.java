package nahmed.frames;

import com.microsoft.playwright.*;

public class ShadowDomExample {

    /*
    Shadow DOM lets a component encapsulate its own internal markup, styles,
    and behavior — hidden from the regular document tree. It's used heavily
    by Web Components (custom elements) so that a component's internal structure
    doesn't leak into or get affected by the outer page's CSS/JS.
     */
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        // Simulating a page with a Web Component that uses OPEN shadow DOM
        /*
        page.setContent("""
            <html>
                <body>
                    <custom-datepicker id="my-picker"></custom-datepicker>

                    <script>
                        class CustomDatepicker extends HTMLElement {
                            connectedCallback() {
                                // mode: 'open' -> Playwright CAN pierce this automatically
                                const shadow = this.attachShadow({ mode: 'open' });
                                shadow.innerHTML = `
                                    <div class="calendar">
                                        <input class="date-input" placeholder="Pick a date" />
                                        <button class="next-month">Next</button>
                                    </div>
                                `;
                            }
                        }
                        customElements.define('custom-datepicker', CustomDatepicker);
                    </script>
                </body>
            </html>
        """);
         */

        // 1. NORMAL LOCATOR - Playwright auto-pierces open shadow DOM, no special syntax needed
        Locator dateInput = page.locator(".date-input");
        dateInput.fill("2026-08-16");
        System.out.println("Filled date input inside shadow DOM directly with normal locator");

        Locator nextButton = page.locator("button.next-month");
        nextButton.click();
        System.out.println("Clicked button inside shadow DOM directly with normal locator");

        // 2. CHAINING through the host element also works fine
        Locator inputViaHost = page.locator("custom-datepicker").locator(".date-input");
        System.out.println("Value via chained locator: " + inputViaHost.inputValue());

        // 3. CLOSED shadow DOM - Playwright CANNOT pierce this; must use evaluate() as a fallback

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}