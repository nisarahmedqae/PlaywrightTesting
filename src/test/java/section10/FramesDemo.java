package section10;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FramesDemo {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            page.navigate("https://jqueryui.com/");

            // Click on the "Droppable" link
            page.getByRole(com.microsoft.playwright.options.AriaRole.LINK,
                    new Page.GetByRoleOptions().setName("Droppable")).click();

            // Count frames on main page
            System.out.println(page.locator("iframe").count());

            // Target iframe using FrameLocator (no need to manually switch back to default content)
            FrameLocator frame = page.frameLocator(".demo-frame");

            // Perform drag and drop inside the frame
            frame.locator("#draggable").dragTo(frame.locator("#droppable"));

            // Count frames inside the iframe
            System.out.println(frame.locator("iframe").count());

            browser.close();
        }
    }

}