package nahmed.sdet;

import com.microsoft.playwright.*;

public class ToolTip {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://jqueryui.com/tooltip/");

        // Locate the iframe using its class name
        FrameLocator tooltipFrame = page.frameLocator(".demo-frame");

        // Target the link inside that specific frame using text selection
        Locator toolTip = tooltipFrame.locator("text=Tooltips");

        // Fetch the tooltip description stored inside the 'title' attribute
        String toolTipText = toolTip.getAttribute("title");
        System.out.println(toolTipText);

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}