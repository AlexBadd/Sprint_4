package ru.praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {

    public static WebDriver driver;
    public static MainPage objMainPage;
    public OrderPage objOrderPage;
    private final int dataIndex;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String dateOrder;
    private final String period;
    private final String color;
    private final String comment;

    public OrderTest(int dataIndex, String name, String surname, String address, String metro,
                     String phone, String dateOrder, String period, String color, String comment) {
        this.dataIndex = dataIndex;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.period = period;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Оформление заказа: " +
            "Способ вызова: {0}; " +
            "Имя: {1}; " +
            "Фамилия: {2}; " +
            "Адрес: {3}; " +
            "Метро: {4}; " +
            "Телефон: {5}; " +
            "Когда нужен: {6}; " +
            "Срок аренды: {7}; " +
            "Цвет: {8}; " +
            "Комментарий: {9}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {0, "Сергей", "Сергеев", "ул. 8 Марта, д.8", "Динамо", "+79991112233", "10.09.2023", "сутки", "black", "Комментарий 1"},
                {1, "Андрей", "Андреев", "ул. Флотская, д.6", "Речной вокзал", "+79993332211", "09.09.2023", "двое суток", "grey", "Комментарий 2"},
                {0, "Иван", "Иванов", "Ленинградский проспект, д.56", "Сокол", "89992221133", "08.09.2023", "трое суток", "black", "Комментарий 3"},
                {1, "Олег", "Олегов", "Ленинский проспект, д.65", "Шаболовка", "89991213344", "08.09.2023", "трое суток", "grey", "Комментарий 4"}
        };
    }

    @Before
    public void initialOrder() {

        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testOrder() {

        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage = new MainPage(driver);
        objMainPage.waitForLoadPage();
        objMainPage.clickGetCookie();

        objMainPage.clickOrder(dataIndex);
        objOrderPage = new OrderPage(driver);
        objOrderPage.waitForLoadOrderPage();
        objOrderPage.setDataFieldsAndClickNext(name, surname, address, metro, phone);
        objOrderPage.waitForLoadRentPage();
        objOrderPage.setOtherFieldsAndClickOrder(dateOrder, period, color, comment);

        assertTrue("Отсутствует сообщение об успешном завершении заказа", objMainPage.isElementExist(objOrderPage.orderPlaced));
    }

    @After
    public void teardown() {
        driver.quit();
    }

}