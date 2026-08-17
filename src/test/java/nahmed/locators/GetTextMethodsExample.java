package nahmed.locators;

import com.microsoft.playwright.*;
import java.util.List;

public class GetTextMethodsExample {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com/form");

        // ---------- SINGLE ELEMENT METHODS ----------

        Locator heading = page.locator("h1");

        // textContent() - raw text, includes hidden text, whitespace as-is
        String textContent = heading.textContent();
        System.out.println("textContent: " + textContent);

        // innerText() - rendered/visible text only (respects CSS display:none)
        String innerText = heading.innerText();
        System.out.println("innerText: " + innerText);

        // innerHTML() - raw HTML markup inside the element
        String innerHtml = heading.innerHTML();
        System.out.println("innerHTML: " + innerHtml);

        // inputValue() - value of input/textarea/select elements only
        Locator inputBox = page.locator("#username");
        inputBox.fill("testuser123");
        String inputValue = inputBox.inputValue();
        System.out.println("inputValue: " + inputValue);

        // getAttribute() - get any HTML attribute (e.g. placeholder, value, id)
        String placeholder = inputBox.getAttribute("placeholder");
        System.out.println("placeholder attribute: " + placeholder);

        String valueAttr = inputBox.getAttribute("value");
        System.out.println("value attribute: " + valueAttr);

        // ---------- MULTIPLE ELEMENTS METHODS ----------

        Locator listItems = page.locator("ul#menu li");

        // allTextContents() - textContent() for every matched element, as a List
        List<String> allTextContents = listItems.allTextContents();
        System.out.println("allTextContents: " + allTextContents);

        // allInnerTexts() - innerText() for every matched element, as a List
        List<String> allInnerTexts = listItems.allInnerTexts();
        System.out.println("allInnerTexts: " + allInnerTexts);

        // Manual loop alternative (useful if you need index-based logic)
        int itemsCount = listItems.count();
        for (int i = 0; i < itemsCount; i++) {
            System.out.println("Item " + i + ": " + listItems.nth(i).textContent());
        }

        // First and Last Locators
        System.out.println("First Item: " + listItems.first().textContent());
        System.out.println("Last Item: " + listItems.last().textContent());

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}