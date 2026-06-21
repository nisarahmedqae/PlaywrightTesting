package sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;

public class Slider {

    public static void main(String[] args) {
        // Initialize Playwright and Launch Browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://jqueryui.com/slider/");

        // 1. Locate the frame (instead of driver.switchTo().frame())
        FrameLocator sliderFrame = page.frameLocator(".demo-frame");

        // 2. Locate the slider inside that frame
        Locator minSlider = sliderFrame.locator(".ui-slider-handle");

        // Print initial Location & Size using the correct BoundingBox import
        BoundingBox boxBefore = minSlider.boundingBox();
        if (boxBefore != null) {
            System.out.println("Before Drag - Location(x,y): (" + boxBefore.x + ", " + boxBefore.y + ")");
            System.out.println("Before Drag - Size(w,h): (" + boxBefore.width + ", " + boxBefore.height + ")");
        }

        // 3. Move the slider horizontally by 100 pixels (X=100, Y=0)
        // We use dragTo() on itself but apply a target position offset
        minSlider.dragTo(minSlider, new Locator.DragToOptions().setTargetPosition(100, 0));

        // Print final Location & Size after sliding
        BoundingBox boxAfter = minSlider.boundingBox();
        if (boxAfter != null) {
            System.out.println("After Drag - Location(x,y): (" + boxAfter.x + ", " + boxAfter.y + ")");
            System.out.println("After Drag - Size(w,h): (" + boxAfter.width + ", " + boxAfter.height + ")");
        }

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}