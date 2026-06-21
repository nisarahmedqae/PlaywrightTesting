package sdet;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;

import java.util.Collections;
import java.util.List;

public class HandlingCookies {

    public static void main(String[] args) {
        // Initialize Playwright and Launch Browser
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        // Context isolates cookies, sessions, and permissions automatically
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://demo.nopcommerce.com/");

        // 1. How to capture cookies from browser
        List<Cookie> cookies = context.cookies();
        System.out.println("Size of cookies: " + cookies.size());

        // 2. How to print cookies from browser
        for (Cookie cookie : cookies) {
            System.out.println(cookie.name + " : " + cookie.value);
        }

        // 3. How to add cookie to the browser
        // Playwright requires you to define the name, value, and URL/domain when adding a cookie
        Cookie cookieObj = new Cookie("myCookie", "thisIsCookie");
        cookieObj.setUrl("https://demo.nopcommerce.com/");

        context.addCookies(Collections.singletonList(cookieObj));
        cookies = context.cookies();
        System.out.println("Size of cookies after adding: " + cookies.size());

        // 4. How to delete a specific cookie from the browser?
        // Playwright doesn't have a single "deleteCookieNamed" method. You clear existing cookies and re-add if needed,
        // or clear cookies for the entire context if updating states. However, to target a fresh clean state:
        context.clearCookies();
        cookies = context.cookies();
        System.out.println("Size after deleting all cookies (Playwright clears at context level): " + cookies.size());

        // Clean up resources manually
        page.close();
        context.close();
        browser.close();
        playwright.close();
    }
}