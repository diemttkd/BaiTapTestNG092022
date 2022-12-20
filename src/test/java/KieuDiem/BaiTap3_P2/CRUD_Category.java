package KieuDiem.BaiTap3_P2;

import KieuDiem.BaiTap2.BaseTest;
import KieuDiem.KeyWords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CRUD_Category extends BaseTest {
    SoftAssert assertSoft = new SoftAssert();

    @BeforeClass
    public void loginCMS(){
        driver.get("https://demo.activeitzone.com/ecommerce/login");
        assertSoft.assertEquals(driver.getTitle(),"Active eCommerce | Demo of Active eCommerce CMS", "Sai tiêu đề: Active eCommerce | Demo of Active eCommerce CMS");
        //Login diaglog
        String loginDialog = "//div[@class='card text-left']";
        assertSoft.assertTrue(driver.findElement(By.xpath(loginDialog)).isDisplayed());
        //Title
        assertSoft.assertEquals(driver.findElement(By.xpath("//h1[normalize-space()='Welcome to Active eCommerce CMS']")).getText(),"Welcome to Active eCommerce CMS", "Sai tiêu đề");
        driver.findElement(By.xpath("//button[normalize-space()='Copy']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        WebUI.sleep(3);
        assertSoft.assertAll();
    }
    @Test(priority = 1)
    public void addCategory(){
        //Menu
        String productMenu = "//span[normalize-space()='Products']";
        assertSoft.assertTrue(driver.findElement(By.xpath(productMenu)).isDisplayed(), "Product is not shown");
        driver.findElement(By.xpath(productMenu)).click();

        String categoryMenu = "//span[normalize-space()='Category']";
        assertSoft.assertTrue(driver.findElement(By.xpath(categoryMenu)).isDisplayed(), "Category is not shown");
        driver.findElement(By.xpath(categoryMenu)).click();

        String categoryTitle = "//h1[normalize-space()='All Categories']";
        String textcategoryTitle = driver.findElement(By.xpath(categoryTitle)).getText();
        assertSoft.assertEquals(textcategoryTitle, "All Categories");

        String categoriesTitle = "//h5[normalize-space()='Categories']";
        String textcategoriesTitle = driver.findElement(By.xpath(categoriesTitle)).getText();
        assertSoft.assertEquals(textcategoriesTitle, "Categories");

        //Button Add New Category
        String newcategoryButton = "//span[normalize-space()='Add New category']";
        assertSoft.assertTrue(driver.findElement(By.xpath(newcategoryButton)).isDisplayed(),"New Category button is shown" );
        assertSoft.assertTrue(driver.findElement(By.xpath(newcategoryButton)).isEnabled(), "New Catefory button is disabled");
        driver.findElement(By.xpath(newcategoryButton)).click();

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
        // driver.findElement(By.xpath("textarea[name='meta_description']")).sendKeys("Category description 1");

        //Filltering Attributes
        driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
        driver.findElement(By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']")).sendKeys("Fabric", Keys.ENTER);

        //Save button
        String saveButton = "//button[normalize-space()='Save']";
        assertSoft.assertTrue(driver.findElement(By.xpath(saveButton)).isDisplayed(), "Save button is not shown");
        assertSoft.assertTrue(driver.findElement(By.xpath(saveButton)).isEnabled(), "Save button is disabled");
        driver.findElement(By.xpath(saveButton)).click();
        WebUI.sleep(3);

        //Searh function
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("MLB Shirt", Keys.ENTER);
        WebUI.sleep(2);
        String actualName = driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText();
        assertSoft.assertEquals(actualName, "MLB Shirt", "No results");

        WebUI.sleep(2);

    }
}
