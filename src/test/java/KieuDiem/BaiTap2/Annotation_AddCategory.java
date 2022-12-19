package KieuDiem.BaiTap2;

import KieuDiem.KeyWords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Annotation_AddCategory extends BaseTest {

    @BeforeClass
    public static void loginCMS(){
        driver.get("https://demo.activeitzone.com/ecommerce/login");
        driver.findElement(By.xpath("//button[normalize-space()='Copy']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        WebUI.sleep(3);
    }

    @Test (priority = 1)
    public static void addCategory(){
        //Menu
        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();
        //Button Add New Category
        driver.findElement(By.xpath("//span[normalize-space()='Add New category']")).click();

        //Name textbox
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("MLB Shirt");
        //Parent Categoty dropdown
        driver.findElement(By.xpath("//div[@class='filter-option-inner']/div[contains(text(),'No Parent')]")).click();
        driver.findElement(By.xpath("//div[@class = 'dropdown-menu show']//input[@aria-label = 'Search']")).sendKeys("Women Clothing & Fashion");
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
        //Ordering Number textbox
        driver.findElement(By.xpath("//input[@placeholder='Order Level']")).sendKeys("100");
        //Type dropdown
        String selectType = ("//select[@name = 'digital']");
        Select type = new Select(driver.findElement(By.xpath(selectType)));
        type.selectByValue("1");
        //Banner
        driver.findElement(By.xpath("//label[normalize-space()='Banner (200x200)']/following-sibling::div")).click();
        WebUI.sleep(1);
        driver.findElement(By.xpath("//input[@placeholder='Search your files']")).sendKeys("standard");
        WebUI.sleep(25);
        driver.findElement(By.xpath("//img[@class='img-fit']")).click();
        WebUI.sleep(2);
        driver.findElement(By.xpath("//button[contains(normalize-space(),'Add Files')]")).click();
        WebUI.sleep(1);
        //Meta textbox
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Category_2811A2");
        WebUI.sleep(2);
        //Meta Description textbox
//        driver.findElement(By.xpath("textarea[name='meta_description']")).sendKeys("Category description 1");
        //Filltering Attributes
        driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
        driver.findElement(By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']")).sendKeys("Fabric", Keys.ENTER);
        //Save button
        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
        WebUI.sleep(3);
        //Searh function
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("MLB Shirt", Keys.ENTER);
        WebUI.sleep(2);
        String actualName = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();

        if (actualName.equals("MLB Shirt")){
            System.out.println("Result is: " + actualName);
        }
        else {
            System.out.println("No result");
        }
        WebUI.sleep(2);
    }
}
