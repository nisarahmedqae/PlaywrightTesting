package sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class HandleMultipleDropDowns {

    public static void main(String[] args) {
        // Initialize Playwright and Launch Browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://demoqa.com/select-menu");

        // Locate elements using unique selectors
        Locator greenEle = page.locator("#oldSelectMenu");
        selectOptionFromDropDown(greenEle, "Green");

        Locator carsEle = page.locator("select[name='cars']");
        selectOptionFromDropDown(carsEle, "Volvo"); // Case-sensitive matching by default

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }

    /**
     * Helper method to select an option from a dropdown by its visible text (label)
     */
    public static void selectOptionFromDropDown(Locator selectDropdown, String selectValue) {
        // Directly select the option by its label
        selectDropdown.selectOption(new SelectOption().setLabel(selectValue));
    }
}
