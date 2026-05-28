package testmu;

import com.google.common.util.concurrent.Uninterruptibles;
import com.microsoft.playwright.*;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.options.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

public class Codegen {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));
        BrowserContext context = browser.newContext(
                new NewContextOptions().setRecordVideoDir(Paths.get("./videos"))
                        .setRecordVideoSize(new RecordVideoSize(1280, 720))
        );
        Page page = context.newPage();
        page.navigate("https://www.testmuai.com/selenium-playground/input-form-demo/");

        // mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen demo.playwright.dev/todomvc"

        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Name")).fill("Nisar");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email*")).fill("abc@xyz.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password*")).fill("Test@2026");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Company")).fill("Coforge");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Website")).fill("www.nisar.com");
        page.getByRole(AriaRole.COMBOBOX).selectOption("IN");
        page.getByRole(AriaRole.COMBOBOX).press("Tab");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("City").setExact(true)).fill("Noida");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Address 1")).fill("Address");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Address 2")).fill("Address 2");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("City* State*")).fill("Uttar Pradesh");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Zip Code*")).fill("123456");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();

        Locator successMessage = page.getByText("Thanks for contacting us, we");
        assertThat(successMessage).isVisible();
        System.out.println(successMessage.textContent());

        playwright.close();
    }
}

