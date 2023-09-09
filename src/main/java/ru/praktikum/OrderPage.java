package ru.praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OrderPage {

    private final WebDriver driver;

    private final By customerData = By.xpath("//div[(text()= 'Для кого самокат')]"); //заголовок страницы "Для кого самокат"
    private final By rentData = By.xpath("//div[(text()= 'Про аренду')]"); //заголовок страницы "Про аренду"
    private final By inputName = By.xpath("//input[@placeholder ='* Имя']"); //поле ввода имени
    private final By inputSurname = By.xpath("//input[@placeholder ='* Фамилия']"); //поле ввода фамилии
    private final By inputAddress = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']"); //поле ввода адреса
    private final By inputPhone = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']"); //поле ввода телефона
    private final By inputMetro = By.xpath("//input[@placeholder ='* Станция метро']"); //выпадающий список станций метро
    private final By buttonNext = By.xpath(".//div[starts-with(@class,'Order_NextButton')]//button[contains(text(), 'Далее')]"); // кнопка "Далее"
    private final By inputCalendar = By.xpath("//input[@placeholder ='* Когда привезти самокат']"); //выбор даты начала аренды
    private final By inputPeriod = By.xpath(".//span[@class = 'Dropdown-arrow']"); //выпадающий список количества дней аренды
    private final By InputComment = By.xpath("//input[@placeholder ='Комментарий для курьера']"); //поле ввода комментария для курьера
    private final By buttonOrder = By.xpath(".//div[starts-with(@class,'Order_Buttons')]//button[contains(text(), 'Заказать')]"); //кнопка "Заказать"
    private final By buttonYes = By.xpath("//button[contains(text(), 'Да')]"); //кнопка "Да" подтверждения заказа
    public By orderPlaced = By.xpath("//div[(text()= 'Заказ оформлен')]"); //окно "Заказ оформлен"

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(getTitleOrder())); //ожидание загрузки страницы "Для кого самокат"
    }

    public void setDataFieldsAndClickNext(String valueName, String valueSurname, String valueAddress,
                                          String valueMetro, String valuePhone) {
        //Заполнение формы на странице "Для кого самокат"
        getName().sendKeys(valueName);
        getSurname().sendKeys(valueSurname);
        getAddress().sendKeys(valueAddress);
        getMetro().sendKeys(valueMetro, Keys.ARROW_DOWN, Keys.ENTER);
        getPhoneNumber().sendKeys(valuePhone);
        getButtonNext().click();
    }

    public void waitForLoadRentPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(getTitleRent())); //ожидание загрузки страницы "Про аренду"
    }

    public void setOtherFieldsAndClickOrder(String valueDateOrder, String valuePeriod, String valueColor, String valueComment) {
        //Заполнение формы на странице "Про аренду"
        getCalendar().sendKeys(valueDateOrder, Keys.ARROW_DOWN, Keys.ENTER);
        getPeriod().click();
        getDays(valuePeriod).click();
        getColor(valueColor).click();
        getComment().sendKeys(valueComment);
        getButtonOrder().click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(driver -> (getButtonYes().isDisplayed())); //ожидание пока не появится кнопка "Да" подтверждения заказа
        getButtonYes().click();
    }

    public By getTitleOrder() {
        return customerData;
    }

    public By getTitleRent() {
        return rentData;
    }

    public WebElement getName() {
        return driver.findElement(inputName);
    }

    public WebElement getSurname() {
        return driver.findElement(inputSurname);
    }

    public WebElement getAddress() {
        return driver.findElement(inputAddress);
    }

    public WebElement getPhoneNumber() {
        return driver.findElement(inputPhone);
    }

    public WebElement getMetro() {
        return driver.findElement(inputMetro);
    }

    public WebElement getCalendar() {
        return driver.findElement(inputCalendar);
    }

    public WebElement getPeriod() {
        return driver.findElement(inputPeriod);
    }

    public WebElement getDays(String valueDays) {
        return driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='"+valueDays+"']"));
    }

    public WebElement getColor(String colorName) {
        return driver.findElement(By.id(colorName));
    }

    public WebElement getComment() {
        return driver.findElement(InputComment);
    }

    public WebElement getButtonNext() {
        return driver.findElement(buttonNext);
    }

    public WebElement getButtonOrder() {
        return driver.findElement(buttonOrder);
    }

    public WebElement getButtonYes() {
        return driver.findElement(buttonYes);
    }

}
