package nahmed.others;

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
        /*
        page.setContent("""
            <html>
                <body>
                    <custom-widget id="closed-widget"></custom-widget>
                    <script>
                        class CustomWidget extends HTMLElement {
                            connectedCallback() {
                                // mode: 'closed' -> hides shadowRoot entirely, even from JS by default
                                const shadow = this.attachShadow({ mode: 'closed' });
                                shadow.innerHTML = '<span class="secret-text">Closed content</span>';
                                this._shadowRoot = shadow; // dev exposes it manually for internal use
                            }
                        }
                        customElements.define('custom-widget', CustomWidget);
                    </script>
                </body>
            </html>
        """);
         */

        // This will fail / return nothing - closed shadow roots are NOT exposed to page.locator()
        int count = page.locator(".secret-text").count();
        System.out.println("Locator count for closed shadow content: " + count); // likely 0

        // Fallback: only works if the app itself exposed the shadow root on the JS object (like _shadowRoot above)
        // This is app-specific and not guaranteed to work - closed shadow DOM is intentionally inaccessible
        Object closedText = page.evaluate(
                "() => document.querySelector('#closed-widget')._shadowRoot?.querySelector('.secret-text')?.textContent"
        );
        System.out.println("Closed shadow text (if exposed by app): " + closedText);

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}