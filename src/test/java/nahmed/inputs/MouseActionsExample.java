package nahmed.inputs;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.KeyboardModifier;
import com.microsoft.playwright.options.MouseButton;

import java.util.List;

public class MouseActionsExample {

    /*
    Two ways to do mouse actions in Playwright:

    1. Locator-based (RECOMMENDED) - locator.click(), locator.hover(), etc.
       These auto-wait for the element (visible, stable, enabled) before acting -
       safer and less flaky.

    2. page.mouse() - LOW-LEVEL raw mouse control using x/y coordinates.
       No auto-waiting, no element targeting - you're moving a virtual mouse cursor
       on the page like a real user would. Needed for things locators can't express:
       drag-and-drop with custom logic, canvas drawing, hover-then-move sequences,
       right-click context menus, precise pixel-based interactions.
     */
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://example.com");

        // ---------- LOCATOR-BASED (preferred for normal actions) ----------

        // 1. Normal click
        page.locator("#submit-button").click();
        System.out.println("Clicked submit button");

        // 2. Double click
        page.locator("#editable-cell").dblclick();
        System.out.println("Double-clicked cell");

        // 3. Right click (context menu) - via ClickOptions
        page.locator("#file-item").click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        System.out.println("Right-clicked file item - context menu should open");

        // 4. Hover - triggers CSS :hover states, dropdown menus, tooltips
        page.locator("#menu-item").hover();
        System.out.println("Hovered over menu item - submenu should appear");

        // 5. Click with modifier key (Ctrl+Click, Shift+Click)
        page.locator("#some-link").click(
                new Locator.ClickOptions().setModifiers(List.of(KeyboardModifier.CONTROL)));
        System.out.println("Ctrl+Clicked link");

        // 6. Built-in drag and drop between two locators
        page.locator("#drag-source").dragTo(page.locator("#drop-target"));
        System.out.println("Dragged source element onto drop target");

        // ---------- LOW-LEVEL page.mouse() (for cases locators can't handle) ----------

        // 7. Move mouse to exact coordinates
        page.mouse().move(100, 200);
        System.out.println("Moved mouse to (100, 200)");

        // 8. Manual down/move/up sequence - custom drag-and-drop
        // useful when dragTo() doesn't work correctly (e.g. custom JS drag libraries
        // that need real intermediate mousemove events, not just start+end)
        page.mouse().move(50, 50);
        page.mouse().down();
        page.mouse().move(50, 150, new Mouse.MoveOptions().setSteps(10)); // steps = intermediate movements
        page.mouse().move(300, 150, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();
        System.out.println("Performed manual drag using mouse down/move/up");

        // 9. Scroll wheel (covered in scrolling example too) - also part of Mouse API
        page.mouse().wheel(0, 500);
        System.out.println("Scrolled down using mouse wheel");

        // Cleanup
        context.close();
        browser.close();
        playwright.close();
    }
}