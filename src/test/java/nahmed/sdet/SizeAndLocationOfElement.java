package nahmed.sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;

public class SizeAndLocationOfElement {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://rahulshettyacademy.com/loginpagePractise/#");

        // Locate the target element
        Locator usernameEle = page.locator("#username");

        // Capture the bounding box (includes position and size coordinates)
        BoundingBox box = usernameEle.boundingBox();

        if (box != null) {
            // Location coordinates (relative to the main page viewport)
            System.out.println("Location(x,y) : (" + box.x + "," + box.y + ")");
            System.out.println("Location(x) : " + box.x);
            System.out.println("Location(y) : " + box.y);

            // Size metrics
            System.out.println("Size(Width,Height) : (" + box.width + "," + box.height + ")");
            System.out.println("Size(Width) : " + box.width);
            System.out.println("Size(Height) : " + box.height);
        } else {
            System.out.println("Element is not visible or lacks a layout box.");
        }

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}
