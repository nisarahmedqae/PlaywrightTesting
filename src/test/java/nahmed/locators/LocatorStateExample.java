package nahmed.locators;

import com.microsoft.playwright.*;

public class LocatorStateExample {

    /*
    Playwright locators expose several "state check" methods that return a
    boolean immediately (no waiting/retrying) - useful for conditional logic
    like "if checkbox is already checked, skip clicking it".

    NOTE: these are DIFFERENT from assertions (assertThat(locator).isVisible()).
    - State check methods (isVisible(), isChecked() etc.) -> return boolean NOW, no retry, no auto-wait
    - Web-first assertions (assertThat()...) -> auto-retry until condition is true or timeout, used for verification in tests

    Use state checks for IF/ELSE decision-making in your script logic.
    Use assertions for actually verifying/asserting something is true in a test.
     */
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com/form");

        // 1. isVisible() - element exists in DOM AND is visible (not display:none, not zero size)
        boolean visible = page.locator("#submit-button").isVisible();
        System.out.println("Is submit button visible: " + visible);

        // 2. isHidden() - opposite of isVisible() - true if NOT in DOM or not visible
        boolean hidden = page.locator("#error-message").isHidden();
        System.out.println("Is error message hidden: " + hidden);

        // 3. isEnabled() - element is not disabled (can be interacted with)
        boolean enabled = page.locator("#submit-button").isEnabled();
        System.out.println("Is submit button enabled: " + enabled);

        // 4. isDisabled() - opposite of isEnabled()
        boolean disabled = page.locator("#submit-button").isDisabled();
        System.out.println("Is submit button disabled: " + disabled);

        // 5. isChecked() - for checkboxes/radio buttons only
        Locator agreeCheckbox = page.locator("#agree-terms");
        boolean checked = agreeCheckbox.isChecked();
        System.out.println("Is checkbox checked: " + checked);

        // conditional click pattern - avoid unchecking an already-checked box
        if (!checked) {
            agreeCheckbox.check();
            System.out.println("Checkbox was unchecked - checked it now");
        }

        // 6. isEditable() - element can be edited (not readonly, not disabled) - for inputs/textareas
        boolean editable = page.locator("#username").isEditable();
        System.out.println("Is username field editable: " + editable);

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}