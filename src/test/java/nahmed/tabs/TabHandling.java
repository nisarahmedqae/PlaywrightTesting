package nahmed.tabs;

import com.microsoft.playwright.*;

public class TabHandling {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page parentPage = context.newPage();

        parentPage.navigate("https://rahulshettyacademy.com/loginpagePractise/#");

        // Listen for popup page creation and trigger the click simultaneously
        Page childPage = parentPage.waitForPopup(() -> {
            parentPage.locator("//a[text()='Free Access to InterviewQues/ResumeAssistance/Material']").click();
        });

        // Get text from child window
        String infoText = childPage.locator(".im-para.red").textContent();
        System.out.println(infoText);

        // Extract email ID
        String emailId = infoText.split("at")[1].split("with")[0].trim();
        System.out.println(emailId);

        // Interact with parent window directly without switching focus
        parentPage.locator("#username").fill(emailId);
        parentPage.waitForTimeout(4000);

        playwright.close();
    }

}