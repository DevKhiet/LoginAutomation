import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class TestClass {
    ChromeDriver chromeDriver;
    @BeforeMethod
    public void setUp(){
    // Declare Webdriver
        WebDriverManager.chromedriver().setup();
        // Declare Chrome Webdriver
        chromeDriver = new ChromeDriver();
    }
    @Test
    public void login() {
        // Declare function action
        Actions action = new Actions(chromeDriver);
        chromeDriver.get("https://www.saucedemo.com/");
        sleep(1000);
        System.out.println("Connect Website");
        // Account Array
        String data [][] = new String [8][8];
        // Account 1
        data[0][0]="standard_user";
        data[0][1]="secret_sauce";
        // Account 2
        data[1][0]="performance_glitch_user";
        data[1][1]="secret_sauce";
        // Account 3
        data[2][0]= "";
        data[2][1]="secret_sauce";
        // Account 4
        data[3][0]="standard_userkhiet";
        data[3][1]="secret_sauce";
        // Account 5
        data[4][0]="standard_user";
        data[4][1]="";
        // Account 6
        data[5][0]="standard_user";
        data[5][1]="secret_saucekhiet";
        // Account 7
        data[6][0]="Standard_user";
        data[6][1]="secret_sauce";
        // Account 8
        data[7][0]="standard_user";
        data[7][1]="Secret_sauce";

        for(int i = 0;i<data.length;i++)
        {
            // Clear Username and Password
            clearLogin("//*[@id=\"user-name\"]","//*[@id=\"password\"]");
            sleep(500);
                input(data[i]);
                // Successfully Case
                if (i == 0 || i == 1) {
                    WebElement menuButton = chromeDriver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
                    menuButton.click();
                    sleep(500);
                    WebElement logOutButton = chromeDriver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]"));
                    logOutButton.click();
                    sleep(500);
                }
                // Fail Case
                else {
                    sleep(2000);
                    WebElement xButton = chromeDriver.findElement(By.className("error-button"));
                    xButton.click();
                }
            System.out.println("Test Case "+ (i + 1) + " Pass");
            if (i+1 == data.length) // If account is the last.
            {
                System.out.println("Test Login Successfully");
            }
        }
    }
    @AfterMethod
    public void cleanUp(){
        chromeDriver.quit();
    }

    // Wait Function
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    // Input Text Function
    public void input(String data[]) {
        // Declare function action
        Actions action = new Actions(chromeDriver);
        // Find login text
        WebElement loginText = chromeDriver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        // Input value from array [0]
        action.sendKeys(loginText, data[0]).build().perform();
        // Find password text
        WebElement passwordText = chromeDriver.findElement(By.xpath("//*[@id=\"password\"]"));
        // Input value from array [1]
        action.sendKeys(passwordText, data[1]).build().perform();
        sleep(500);
        // Find login button.
        WebElement loginButton = chromeDriver.findElement(By.xpath("//*[@id=\"login-button\"]"));
        loginButton.click();
        sleep(2000);
    }
    public void clearLogin(String Username, String Password){
        // Find Login Text
        WebElement loginText = chromeDriver.findElement(By.xpath(Username));
        // Clear Login Text
        loginText.clear();
        // Find Password Text
        WebElement passwordText = chromeDriver.findElement(By.xpath(Password));
        // Clear Password Text
        passwordText.clear();
    }

}
