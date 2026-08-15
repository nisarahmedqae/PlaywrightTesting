package nahmed.testmu;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.options.*;

import java.nio.file.Paths;

public class VideoRecording {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext context = browser.newContext(
                new NewContextOptions().setRecordVideoDir(Paths.get("./videos"))
                        .setRecordVideoSize(new RecordVideoSize(1280, 720))
        );
        Page page = context.newPage();

        page.navigate("https://www.testmuai.com/selenium-playground/input-form-demo/");

        // mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen demo.playwright.dev/todomvc"

        playwright.close();
    }
}

