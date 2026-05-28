package testmu;

import com.microsoft.playwright.*;

import java.util.List;

public class WindowHandling {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();
        Page page = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();

        page.navigate("https://www.testmuai.com/selenium-playground/window-popup-modal-demo/");

        Page tabs = page.waitForPopup(() -> {
            page.getByText("Follow All").click();
        });
        List<Page> pages = tabs.context().pages();
        System.out.println(pages.size());

        pages.forEach(tab -> System.out.println(tab.title()));

        playwright.close();
    }
}

