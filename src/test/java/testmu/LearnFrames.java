package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LearnFrames {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        Page page = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        page.navigate("https://letcode.in/frame");
        List<Frame> frames = page.frames();
        System.out.println("No. of frames available: " + frames.size());
        frames.forEach(frame -> System.out.println(frame.url()));

        FrameLocator firstFrame = page.frameLocator("#firstFr");
        firstFrame.getByPlaceholder("Enter name").fill("Nisar");

        FrameLocator nestedFrame = firstFrame.frameLocator("iframe.has-background-white");
        nestedFrame.getByPlaceholder("Enter email").fill("abc@xyz.com");

        playwright.close();
    }
}

