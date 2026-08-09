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

        // 1. Get the matching page scope from context based on expected title fragment
        Page targetPage = getPageByTitle(context, "LetCode");

        // 2. Outside the loop: interact with the page scope and print its title/content
        if (targetPage != null) {
            // Print page title outside the loop
            System.out.println("Returned Page Title: " + targetPage.title());
            System.out.println("Returned Page URL: " + targetPage.url());
        } else {
            System.out.println("Page with expected title was not found.");
        }

        playwright.close();
    }

    /**
     * Helper method to search for a page by title fragment and return the Page scope.
     */
    public static Page getPageByTitle(BrowserContext context, String expectedTitleFragment) {
        List<Page> pages = context.pages();
        for (Page page : pages) {
            if (page.title().contains(expectedTitleFragment)) {
                page.bringToFront();
                return page; // Return the matching Page scope directly
            }
        }
        return null; // Return null if no match is found
    }
}