package sdet;

import com.microsoft.playwright.*;

public class KeyboardActions {

    public static void main(String[] args) {
        // Initialize Playwright and Launch Browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://text-compare.com/");

        // Locate input elements
        Locator input1 = page.locator("//textarea[@id='inputText1']");
        Locator input2 = page.locator("//textarea[@id='inputText2']");

        // Type initial text into the first box
        input1.fill("Welcome to Selenium");

        // Focus on the first element to ensure keyboard events target it
        input1.focus();

        // Playwright uses the 'Control' key or 'Meta' modifier shortcuts seamlessly
        // CTRL+A (Select All)
        page.keyboard().press("Control+A");

        // CTRL+C (Copy)
        page.keyboard().press("Control+C");

        // Shift focus to the next input box using TAB
        page.keyboard().press("Tab");

        // CTRL+V (Paste)
        page.keyboard().press("Control+V");

        // Compare values directly using .inputValue()
        if (input1.inputValue().equals(input2.inputValue())) {
            System.out.println("text copied");
        } else {
            System.out.println("text not copied");
        }

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
