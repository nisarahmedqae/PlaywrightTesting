package sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.util.List;

public class HandleDropdown {

    public static void main(String[] args) {
        // Initialize Playwright and Launch Browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://rahulshettyacademy.com/AutomationPractice/");

        // Locate the select dropdown element
        Locator dropdown = page.locator("#dropdown-class-example");

        // Playwright handles selection directly via value, label, or index
        // To select by text "Option2":
        dropdown.selectOption(new SelectOption().setLabel("Option2"));

        // 1. Get ALL options text as a List<String>
        List<String> allOptions = page.locator("#dropdown-class-example option").allTextContents();

        System.out.println("Available options: " + allOptions);

        // 2. Get the text of a specific option by its index (e.g., the second option)
        String secondOptionText = page.locator("#dropdown-class-example option").nth(1).innerText();
        System.out.println("Second option is: " + secondOptionText);

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}