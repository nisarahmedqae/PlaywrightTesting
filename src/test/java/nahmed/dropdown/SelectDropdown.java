package nahmed.dropdown;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.time.Duration;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SelectDropdown {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://letcode.in/dropdowns");

        Locator selectFruits = page.locator("select#fruits");

        // Select by value attribute
        selectFruits.selectOption(new SelectOption().setValue("2"));
        page.waitForTimeout(3000);
        Locator selectedOption = page.locator("//p[@class='text-sm font-medium']");
        assertThat(selectedOption).containsText("Orange");
        System.out.println(selectedOption.textContent());

        // Select by visible label (text)
        selectFruits.selectOption(new SelectOption().setLabel("Mango"));
        page.waitForTimeout(3000);
        assertThat(selectedOption).containsText("Mango");
        System.out.println(selectedOption.textContent());

        // select by index
        selectFruits.selectOption(new SelectOption().setIndex(1));
        page.waitForTimeout(3000);
        assertThat(selectedOption).containsText("Apple");
        System.out.println(selectedOption.textContent());

        // 1. Get ALL options text as a List<String>
        List<String> allOptions = selectFruits.locator("option").allTextContents();
        System.out.println("Available options: " + allOptions);

        // 2. Get the text of a specific option by its index (e.g., the second option)
        String secondOptionText = selectFruits.locator("option").nth(1).innerText();
        System.out.println("Second option is: " + secondOptionText);

        playwright.close();
    }

}
