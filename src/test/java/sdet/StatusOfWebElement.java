package sdet;

import com.microsoft.playwright.*;

public class StatusOfWebElement {

    public static void main(String[] args) {
        // Initialize Playwright and Launch Browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

        // Locate autocomplete field and check its display/enable status
        Locator selectCountry = page.locator("#autocomplete");
        System.out.println(selectCountry.isVisible()); // Replaces isDisplayed()
        System.out.println(selectCountry.isEnabled()); // Replaces isEnabled()

        // Locate checkbox and check its selected status
        Locator checkBoxOption1 = page.locator("#checkBoxOption1");
        System.out.println(checkBoxOption1.isChecked()); // Replaces isSelected()

        checkBoxOption1.click();
        System.out.println(checkBoxOption1.isChecked());

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}