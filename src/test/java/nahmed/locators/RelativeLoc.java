package nahmed.locators;

import com.microsoft.playwright.*;

public class RelativeLoc {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://rahulshettyacademy.com/angularpractice/");

        //Click on Home
        page.getByText("Home").click();

        // Label above the Name edit box
        String labelAbove = page.locator("label:above(input[name='name']:nth-child(2))").first().textContent();
        System.out.println(labelAbove);

        // Input element below Date of Birth label
        page.locator("input:below(label[for='dateofBirth'])").first().click();

        // Checkbox to the left of "Check me out if you Love IceCreams!"
        page.locator("input:left-of(label:has-text('Check me out if you Love IceCreams!'))").first().click();

        // Label to the right of the radio button
        String radioLabel = page.locator("label:right-of(#inlineRadio1)").first().textContent();
        System.out.println(radioLabel);

        playwright.close();

    }

}