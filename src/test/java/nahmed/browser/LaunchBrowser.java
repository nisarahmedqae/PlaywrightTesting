package nahmed.browser;

import com.microsoft.playwright.*;

public class LaunchBrowser {

    public static void main(String[] args) {
        // Initialize Playwright
        Playwright playwright = Playwright.create();

        //1. Launch Standard Bundled Browsers
        //Playwright comes with three open-source engine builds:

        // Chromium (Default)
        Browser chromeBrowser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // Firefox
        Browser firefoxBrowser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // WebKit (Safari Engine)
        Browser safariBrowser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));

        //2. Launch Branded Browsers (Google Chrome / Microsoft Edge)
        //To use locally installed branded browsers instead of the bundled Chromium binaries, set the channel in LaunchOptions:

        // Google Chrome
        Browser brandedChromeBrowser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("chrome")
                .setHeadless(false));

        // Microsoft Edge
        Browser brandedEdgeBrowser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setChannel("msedge")
                .setHeadless(false));

        playwright.close();
    }
}
