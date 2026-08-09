package nahmed.actions;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.MouseButton;

public class MouseActions {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

            // Hover over element (mousehover)
            page.locator("#mousehover").hover();

            // Focus input field, hold Shift, type in uppercase, and double-click
            Locator nameInput = page.locator("#name");
            nameInput.click();
            page.keyboard().down("Shift");
            page.keyboard().type("hello");
            page.keyboard().up("Shift");
            nameInput.dblclick();

            // Context click (Right-click) on element
            nameInput.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
            page.waitForTimeout(4000);

            browser.close();
        }
    }

}