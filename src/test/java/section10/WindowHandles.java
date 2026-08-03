package section10;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class WindowHandles {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page parentPage = browser.newPage();

            parentPage.navigate("https://rahulshettyacademy.com/loginpagePractise/#");

            // Listen for popup page creation and trigger the click simultaneously
            Page childPage = parentPage.waitForPopup(() -> {
                parentPage.locator(".blinkingText").click();
            });

            // Get text from child window
            String infoText = childPage.locator(".im-para.red").textContent();
            System.out.println(infoText);

            // Extract email ID
            String emailId = infoText.split("at")[1].split("with")[0].trim();
            System.out.println(emailId);

            // Interact with parent window directly without switching focus
            parentPage.locator("#username").fill(emailId);

            browser.close();
        }
    }

}