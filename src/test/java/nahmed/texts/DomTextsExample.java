package nahmed.texts;

import com.microsoft.playwright.*;

public class DomTextsExample {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        // Setting HTML content directly so the DOM is predictable for this example
        /*
        page.setContent("""
                    <html>
                        <body>
                            <h1 id="heading">
                                Welcome <span style="display: none;">Hidden Badge</span> <b>Traveler</b>
                            </h1>

                            <div id="box" style="display: none;">
                                <p>I am hidden</p>
                            </div>
                        </body>
                    </html>
                """);
         */

        Locator heading = page.locator("#heading");

        // textContent() - raw text, INCLUDES hidden elements, whitespace exactly as in DOM
        String textContent = heading.textContent();
        System.out.println("textContent: [" + textContent + "]");

        // innerText() - only VISIBLE text (skips display:none), whitespace collapsed
        String innerText = heading.innerText();
        System.out.println("innerText: [" + innerText + "]");

        // innerHTML() - raw HTML markup INSIDE the element (tags included)
        String innerHtml = heading.innerHTML();
        System.out.println("innerHTML: [" + innerHtml + "]");

        System.out.println("----------");

        Locator box = page.locator("#box");

        // Fully hidden container - textContent/innerHTML still work, innerText does not
        System.out.println("box textContent: [" + box.textContent() + "]");
        System.out.println("box innerHTML: [" + box.innerHTML() + "]");

        try {
            System.out.println("box innerText: [" + box.innerText() + "]");
        } catch (Exception e) {
            System.out.println("box innerText: threw exception - element not visible");
        }

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}
