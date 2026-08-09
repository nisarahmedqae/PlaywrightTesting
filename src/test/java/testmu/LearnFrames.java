package testmu;

import com.microsoft.playwright.*;

import java.util.List;

public class LearnFrames {

    public static void main(String[] args) {
        // Initialize Playwright and Browser instances
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://letcode.in/frame");
        List<Frame> frames = page.frames();
        System.out.println("No. of frames available: " + frames.size());
        frames.forEach(frame -> System.out.println(frame.url()));

        FrameLocator firstFrame = page.frameLocator("#firstFr");
        firstFrame.getByPlaceholder("Enter name").fill("Nisar");

        FrameLocator nestedFrame = firstFrame.frameLocator("//iframe[@title='Inner Frame']");
        nestedFrame.getByPlaceholder("Enter email").fill("abc@xyz.com");
        page.waitForTimeout(4000);

        playwright.close();
    }
}

