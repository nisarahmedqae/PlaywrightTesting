package nahmed.tabs;

import com.microsoft.playwright.*;

import java.util.List;

public class SwitchTabsByTitle {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();

        // Open 4 tabs
        Page page1 = context.newPage();
        page1.navigate("https://playwright.dev/java/");

        Page page2 = context.newPage();
        page2.navigate("https://letcode.in/test");

        Page page3 = context.newPage();
        page3.navigate("https://github.com");

        Page page4 = context.newPage();
        page4.navigate("https://www.google.com");

        // 1. Search for the page whose title contains the expected fragment
        String expectedTitleFragment = "LetCode";
        Page targetPage = null;

        List<Page> pages = context.pages();
        for (Page page : pages) {
            if (page.title().contains(expectedTitleFragment)) {
                page.bringToFront();
                targetPage = page;
                break; // stop once we find the match
            }
        }

        // 2. Interact with the matched page scope
        if (targetPage != null) {
            System.out.println("Returned Page Title: " + targetPage.title());
            System.out.println("Returned Page URL: " + targetPage.url());
        } else {
            System.out.println("Page with expected title was not found.");
        }

        playwright.close();
    }
}