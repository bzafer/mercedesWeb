package com.mercedes.web.base;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.thoughtworks.gauge.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mercedes.web.util.WaitTool;


public class BasePageUtil extends BaseTest {

    public WebElement submitObjectBy(By by) {
        WaitTool.waitForElementClickable(driver, by, 5);
        WebElement element = driver.findElement(by);
        element.submit();
        return element;
    }

    public WebElement setObjectBy(By by, String value) {
        WebElement element = getElementBy(by);
        element.clear();
        element.click();
        element.sendKeys(value);

        return element;
    }

    public WebElement setElementByActions(WebElement element, String text) {
//		String browser = System.getProperty("browser");
//		if (!StringUtils.isEmpty(browser) && browser.contains("safari")) {
//			moveToElement(element);
//			javaScriptClicker(driver, element);
//			element.sendKeys(text);
//		} else {
        waitSeconds(15);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(text);
        element.sendKeys(text);
        actions.build().perform();
//		}
        return element;
    }
    @Step("Write to name \"<name>\" to \"<value>\" area ")
    public WebElement setObjectByName(String name, String value) {
        return setObjectBy(By.name(name), value);
    }

    @Step("Write \"<id>\" to \"<value>\" area ")
    public WebElement setObjectById(String id, String value) {
        Assert.assertTrue("Element was not display",isDisplayBy(By.id(id)));
        WebElement element = setObjectBy(By.id(id), value);
        return element;
    }

    @Step("Debug Step")
    public void debugStep() {
        waitSeconds(4);
    }

    @Step("Close pop-up")
    public void closePopupClickaccept(){
        waitSeconds(4);
        Assert.assertTrue("Frame did not find",isDisplayBy(By.cssSelector("iframe[src='https://998766.fls.doubleclick.net/activityi;src=998766;type=096pb641;cat=shop-664;ord=9393470956388;gtm=2od3b2;auiddc=1845980474.1553503929;u10=cell-phones;u11=desktop;u12=prospect;u13=www%252Fcell-phones;u15=0.2333;u16=0;u17=1;u19=prod%3Atmobile.nextgen%3Aut4.42.201903202205;u27=%7B%22m%22%3A%22c%3A0%2Ca%3A0%2Cl%3A0%2Cv%3A0%22%2C%22t%22%3A%22c%3A0%2Ca%3A0%2Cl%3A1%2Cv%3A1%22%7D;u28=Non%20Cellular%7CGlobal%20Iletisim%20Frankfurt%20Network;~oref=https%3A%2F%2Fwww.t-mobile.com%2Fcell-phones%3Ficid%3DWMM_TM_HMPGRDSGNA_P7QGF8N5L3B5877?']")));
           driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src='https://998766.fls.doubleclick.net/activityi;src=998766;type=096pb641;cat=shop-664;ord=9393470956388;gtm=2od3b2;auiddc=1845980474.1553503929;u10=cell-phones;u11=desktop;u12=prospect;u13=www%252Fcell-phones;u15=0.2333;u16=0;u17=1;u19=prod%3Atmobile.nextgen%3Aut4.42.201903202205;u27=%7B%22m%22%3A%22c%3A0%2Ca%3A0%2Cl%3A0%2Cv%3A0%22%2C%22t%22%3A%22c%3A0%2Ca%3A0%2Cl%3A1%2Cv%3A1%22%7D;u28=Non%20Cellular%7CGlobal%20Iletisim%20Frankfurt%20Network;~oref=https%3A%2F%2Fwww.t-mobile.com%2Fcell-phones%3Ficid%3DWMM_TM_HMPGRDSGNA_P7QGF8N5L3B5877?']")));
        Assert.assertTrue("Element was not display",isDisplayBy(By.cssSelector("svg[class='fsrButton fsrButton__inviteClose fsrAbandonButton']")));
           driver.findElement(By.cssSelector("svg[class='fsrButton fsrButton__inviteClose fsrAbandonButton']")).click();
           waitSeconds(1);
           driver.switchTo().defaultContent();

    }

    @Step("Close the survey")
    public void closeSurvey(){
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src='https://d6tizftlrpuof.cloudfront.net/live/i/55e6a3497d57425f7e918b59/73708ae96ec7b25cbb861e64582b276924f247a5.html?tags=right']")));
        driver.findElement(By.id("close")).click();
        waitSeconds(1);
        driver.switchTo().defaultContent();
    }

    @Step("Enter <time> in time field.")
    public void enterTimefierl(String time){
        driver.findElement(By.id("time-input")).click();
        waitSeconds(1);
        List<WebElement> timetList = driver.findElements(By.cssSelector("#timepicker-times>li>button"));
        for (WebElement webElement : timetList) {
            System.out.print("Get time  text" +webElement.getText());
            if (webElement.getText().contains((time))) {
                hoverToElement(webElement);
                waitSeconds(1);
                webElement.click();
                waitSeconds(2);
                break;
            }
        }


    }

    @Step("Write cssSelector \"<cssSelector>\" to \"<value>\" area")
    public void setObjectByCssSelector(String cssSelector, String value) {
       // WebElement element = setObjectBy(By.cssSelector(cssSelector),value);
     driver.findElement(By.cssSelector(cssSelector)).sendKeys(value);

    }

    @Step("Enter <cityname> in field 'Naar'.")
    public void setObjectByCssSelectorGetindexNear(String cityname) {
        Assert.assertTrue("Element was not display in field van",isClickable(driver.findElements(By.cssSelector("input[class='textInput formfield__textInput ng-untouched ng-pristine ng-valid']")).get(2)));
        waitSeconds(1);
       driver.findElements(By.cssSelector("input[class='textInput formfield__textInput ng-untouched ng-pristine ng-valid']")).get(2).sendKeys(cityname);
       waitSeconds(2);


    }

    @Step("Enter  <citynameVan> in field 'Van")
    public void setObjectByCssSelectorGetindexVan(String citynameVan) {
        waitSeconds(2);
        Assert.assertTrue("Element was not display in field van",isClickable(driver.findElements(By.cssSelector("input[class='textInput formfield__textInput ng-untouched ng-pristine ng-valid']")).get(1)));
        waitSeconds(1);
        driver.findElements(By.cssSelector("input[class='textInput formfield__textInput ng-untouched ng-pristine ng-valid']")).get(1).sendKeys(citynameVan);
        waitSeconds(2);

    }

    public WebElement setObjectByClassName(String className, String value) {
        return setObjectBy(By.className(className), value);
    }

    public WebElement selectValueObjectBy(By by, String value) {
        WebElement element = driver.findElement(by);
        new Select(element).selectByVisibleText(value);
        return element;
    }

    public WebElement selectValueObjectById(String id, String value) {
        WebElement element = selectValueObjectBy(By.id(id), value);
        return element;
    }
    public WebElement selectValueObjectByName(String name, String value) {
        WebElement element = selectValueObjectBy(By.name(name), value);
        return element;
    }

    public WebElement selectIndexObjectById(String name, int index) {
        WebElement element = driver.findElement(By.id(name));
        new Select(element).selectByIndex(index);
        return element;
    }

    public WebElement selectIndexObjectByName(String name, int index) {
        WebElement element = driver.findElement(By.name(name));
        new Select(element).selectByIndex(index);
        return element;
    }

    @Step("click on  <id> ")
    public void clickObjectById(String id) {
        Assert.assertTrue("Element was not found",isDisplayBy(By.id(id)));
        driver.findElement(By.id(id)).click();
    }

    @Step("Click on the <css> value")
    public void clickObjectByCss(String css) {
    Assert.assertTrue("Element was not display",isDisplayBy(By.cssSelector(css)));
    waitSeconds(2);
      driver.findElement(By.cssSelector(css)).click();
      waitSeconds(2);
    }
   @Step("Click on css <css> with javascrip")
   public void javascripClickerwithCss(String css){
       Assert.assertTrue("Element was not display",isDisplayBy(By.cssSelector(css)));
        javaScriptClicker(driver,driver.findElement(By.cssSelector(css)));

   }

    @Step("Click on classname \"<className>\"")
    public WebElement clickObjectByClassName(String className) {

        return clickObjectBy(By.className(className));
    }
    @Step("Click on name  \"<Name>\"")
    public WebElement clickObjectByName(String Name) {

        return clickObjectBy(By.name(Name));
    }

    @Step("Click on the <linkText>")
    public WebElement clickObjectByLinkText(String linkText) {
        Assert.assertTrue("element was not display",isDisplayBy(By.linkText(linkText)));
        waitSeconds(1);
        return clickObjectBy(By.partialLinkText(linkText));
    }

    @Step("\"<xpath>\" click on xpath")
    public WebElement clickObjectByXpath(String xpath) {

        return clickObjectBy(By.xpath(xpath));
    }

    public WebElement clickObjectBy(By by) {
        WaitTool.waitForElementClickable(driver, by, 10);
        WebElement element = driver.findElement(by);
        element.click();
        return element;
    }

    public WebElement getElementBy(By by) {
        return driver.findElement(by);
    }

    public WebElement getElementById(String id) {
        return getElementBy(By.id(id));
    }

    public WebElement getElementByClassName(String className) {
        return getElementBy(By.className(className));
    }

    public static String convertTurkishChar(String string) {
        string = string.replace("ç", "c");
        string = string.replace("ö", "o");
        string = string.replace("ş", "s");
        string = string.replace("ğ", "g");
        string = string.replace("ü", "u");
        string = string.replace("ı", "i");
        string = string.replace("Ç", "C");
        string = string.replace("Ö", "O");
        string = string.replace("Ş", "S");
        string = string.replace("Ğ", "G");
        string = string.replace("Ü", "U");
        string = string.replace("İ", "I");
        return string;
    }

    public List<WebElement> getElementsBy(By by) {
        return driver.findElements(by);
    }

    public List<WebElement> getElementsById(String id) {
        return getElementsBy(By.id(id));
    }

    public List<WebElement> getElementsByCss(String css) {
        return getElementsBy(By.cssSelector(css));
    }

    public List<WebElement> getElementsByClassName(String className) {
        return getElementsBy(By.className(className));
    }

    public List<WebElement> getSelectOptionListById(String id) {
        Select select = new Select(getElementBy(By.id(id)));
        return select.getOptions();
    }

    public List<WebElement> getSelectOptionListByName(String name) {
        Select select = new Select(getElementBy(By.name(name)));
        return select.getOptions();
    }

    public boolean isDisplayBy(By by) {
        return driver.findElement(by).isDisplayed();
    }

    public boolean isDisplayByPassException(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayById(String id) {
        try {
            return driver.findElement(By.id(id)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayByCss(String css) {
        return driver.findElement(By.cssSelector(css)).isDisplayed();
    }

    public boolean isDisplayByName(String name) {
        return driver.findElement(By.name(name)).isDisplayed();
    }

    public boolean isDisplayByClassName(String className) {
        return driver.findElement(By.className(className)).isDisplayed();

    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl().trim().toString();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public void moveMouse(By by, By validateDisplayWebObject, int count) {
        WaitTool.waitElementPresentAndDisplay(driver, by, 10);
        int i = 0;
        do {
            WebElement we = driver.findElement(by);
            Locatable hoverItem = (Locatable) we;
            Mouse mouse = ((HasInputDevices) driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());
            sleep(1000);
            if (count == i++)
                break;
        } while (!isDisplayByPassException(validateDisplayWebObject));

    }

    public void moveMouseWithJQuery(String id, By validateDisplayWebObject, int count) {
        WaitTool.waitElementPresentAndDisplay(driver, By.id(id), 10);
        int i = 0;
        do {
            ((JavascriptExecutor) driver).executeScript("$('" + id + "').mouseover();");
            sleep(1000);
            if (count == i++)
                break;
            System.out.println(i + ". mouseover deneme...");
        } while (!isDisplayByPassException(validateDisplayWebObject));

    }

    public void moveMouseWithJavascript(String id, By validateDisplayWebObject, int count) {
        WaitTool.waitElementPresentAndDisplay(driver, By.id(id), 10);
        int i = 0;
        do {
            ((JavascriptExecutor) driver).executeScript("$('" + id + "').mouseover();");
            String strJavaScript = "var element = arguments[0];"
                    + "var mouseEventObj = document.createEvent('MouseEvents');"
                    + "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";

            executeJavascript(strJavaScript, getElementBy(By.id(id)));
            sleep(1000);
            if (count == i++)
                break;
            System.out.println(i + ". mouseover deneme...");
        } while (!isDisplayByPassException(validateDisplayWebObject));

    }

    public void executeJavascript(String script, Object... obj) {
        ((JavascriptExecutor) driver).executeScript(script, obj);
    }

    public void callPage(String page) {
        driver.get(getCurrentUrl() + page);
    }

    public boolean isTextPresent(By by, String text) {
        try {
            return driver.findElement(by).getText().contains(text);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isTextPresent(String text) {
        try {
            return driver.getPageSource().contains(text);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(By by, WebElement element) {
        try {
            element.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean areElementsPresent(By by) {
        try {
            driver.findElements(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresentAndDisplay(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresentAndDisplay(By by, WebElement element) {
        try {
            return element.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void javaScriptClicker(WebDriver driver, WebElement element) {

        JavascriptExecutor jse = ((JavascriptExecutor) driver);
        jse.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", element);
    }

    public void waitForElement(WebDriver driver, int seconds, By elementBy) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
            wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        } catch (TimeoutException e) {
            System.out.println("Beklenen element= " + elementBy + ", " + seconds + " saniye içerisinde bulunamadı.");
        }
    }

    public String convertToLink(String name) {
        String link = name.replace(" ", "-");
        link += "/";
        convertTurkishChar(link);
        return link;
    }

    public List<String> getCategoryNamesFromPage() {
        List<String> namesFromPage = new LinkedList<String>();
        namesFromPage.add(getElementsByClassName("SelectedItem").get(0).getText());
        List<WebElement> tabs = getElementsByClassName("Item");
        for (int i = 0; i < tabs.size(); i++)
            namesFromPage.add(tabs.get(i).getText());
        return namesFromPage;
    }

    public void scrollToElement(WebElement element) {
        if (element != null) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY()- 100);
            System.out.println("Y kord = " + element.getLocation().getY());
        }

        }


    public List<String> getVerticalMenuTitles() {
        List<String> titles = new LinkedList<String>();
        List<WebElement> verticalMenu = getElementsByCss(".VerticalMenu>li");
        for (int i = 0; i < verticalMenu.size(); i++)
            titles.add(verticalMenu.get(i).getText());
        return titles;
    }

    public boolean compareContents(List<String> contentFromPage, List<String> contentFromDB) {
        Assert.assertTrue("DB'den gelen kategori sayÄ±sÄ±yla sayfadaki kategori sayÄ±sÄ± farklÄ±!",
                contentFromPage.size() == contentFromDB.size());
        boolean equal = contentFromPage.size() == contentFromDB.size();
        for (int i = 0; i < contentFromPage.size(); i++) {
            System.out.println(contentFromPage.get(i) + " " + contentFromDB.get(i));
            if (!contentFromPage.get(i).equalsIgnoreCase(contentFromDB.get(i))) {
                equal = false;
            }
        }
        return equal;
    }

    public static String Md5(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public boolean isClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void moveToElement(WebElement element) {
//		String browser = System.getProperty("browser");
//		System.out.println("Browser = " + browser);
//		if (browser.contains("safari")) {
//			String strJavaScript = "var element = arguments[0];"
//					+ "var mouseEventObj = document.createEvent('MouseEvents');"
//					+ "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
//			executeJavascript(strJavaScript, element);
//		} else {

        if (element != null) {
            scrollTo(element.getLocation().getX(), element.getLocation().getY() - 100);
        }
//			Actions action = new Actions(driver);
//			action.moveToElement(element).perform();
//		}
    }

    protected void scrollTo(int x, int y) {
        String jsStmt = String.format("window.scrollTo(%d, %d);", x, y);
        executeJS(jsStmt, true);

    }

    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    protected Object executeJS(String jsStmt, boolean wait) {
        return wait ? getJSExecutor().executeScript(jsStmt, "") : getJSExecutor().executeAsyncScript(jsStmt, "");
    }

    public boolean isExistElement(int sec, By by) {
        try {
            waitForElement(sec, by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement waitForElement(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    public boolean isExistElementContains(int sec, By by, String str) {
        return waitForElement(sec, by).getText().contains(str);
    }

    public boolean isExistElementEquals(int sec, By by, String str) {
        return waitForElement(sec, by).getText().trim().equals(str);
    }

    public String getText(By by) {
        return waitForElement(30, by).getText();
    }

    public Object getRandomContent(List<?> contentList) {
        Random rand = new Random();
        int n = rand.nextInt(contentList.size());
        return contentList.get(n);
    }

    public void hoverToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void moveToElementByJavasript(WebElement element) {
        String strJavaScript = "var element = arguments[0];"
                + "var mouseEventObj = document.createEvent('MouseEvents');"
                + "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
        executeJavascript(strJavaScript, element);
    }

    public void switchToiFrameBy(By by) {
        driver.switchTo().frame(driver.findElement(by));
    }


    @Step("<second> times waiting")
    public void waitSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Step("Click on the Area de clientes")
    public void clickAreadeClientesbutton() {
        waitSeconds(5);
        WebElement areadeclientes = driver.findElements(By.cssSelector(".VS-button-wrapper .VS-button-text")).get(0);
        areadeclientes.click();
        waitSeconds(5);
    }

    @Step("Focus new tab")
    public void focusNewTab() {
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabList.get(tabList.size() - 1));

    }


    @Step("The control of the switch to do english on page")
    public void controlOftheSwicthtoDoenglishOnpage() {
        WebElement element = driver.findElement(By.cssSelector("a[class='headerLink headerLink--nlLang']>span"));
        System.out.print("Language contol :" + element.getText());
        Assert.assertTrue("Switch to language on english was not succesful" ,element.getText().equals("Nederlands"));
    }
    @Step("Do to the control of login button")
    public void DotoTheheadersControl() {
        WebElement element = driver.findElement(By.cssSelector("div[class='headerItem__expander']>a"));
        System.out.print("Login button :" + element.getText());
        Assert.assertTrue("Login button was not display" ,element.getText().equals("Inloggen Consumenten"));
    }

    @Step("Do to the control  <keyword> on search result")
    public void DotoThesearchResult(String keyword) {
        List<WebElement> elementList = driver.findElements(By.cssSelector("ul[class='search-results__list']>li>h3>a>span"));
        for (WebElement webElement : elementList) {
            System.out.print("Keyword contol :" + webElement.getText());
            Assert.assertTrue("Search result  was not display" ,webElement.getText().equals(keyword));
        }

    }






    @Step("Click on search buttom")
    public void searchButton() {
        WebElement search = driver.findElement(By.cssSelector("[type=\"submit\"]"));
        javaScriptClicker(driver, search);
    }

    @Step("Click on <classname> get  <index>")
    public void chooseProducttype(String classname,int index) {
        waitSeconds(3);
        WebElement productType = driver.findElements(By.className(classname)).get(index);
        productType.click();
        System.out.println("click on the Choose a product type ");
        waitSeconds(5);

    }


    @Step("Click on all Brands")
    public void clickOnallBrands() {
        waitSeconds(3);
        WebElement productType = driver.findElements(By.className("hmcChoiceButton")).get(3);
        productType.click();
        System.out.println("Click on all Brands");
        waitSeconds(5);
    }

@Step("Write css <cssValue> value to text <TextName>")
    public void writeCssvalueAndtext(String cssValue,String TextName){
    List<WebElement> productList = driver.findElements(By.cssSelector(cssValue));
    for (WebElement webElement : productList) {
        System.out.print("Text !!!!!"  +webElement.getText());
        if (webElement.getText().contains((TextName))) {
            hoverToElement(webElement);
            waitSeconds(1);
            webElement.click();
            waitSeconds(2);
            break;
        }
    }
}
@Step("write <keyword> and searching")
public void writeKeywordandSearching(String keyword){
        driver.findElement(By.cssSelector("input[id='headerSearchField']")).sendKeys(keyword);
        waitSeconds(1);
        driver.findElement(By.cssSelector("input[id='headerSearchField']")).sendKeys(Keys.ENTER);
}



@Step("Write css <cssvalue> value click on index <index>")
    public void clickOncssAndIndex(String cssvalue, int index){
    waitSeconds(3);
    WebElement productType = driver.findElements(By.cssSelector(cssvalue)).get(index);
    productType.click();
    System.out.println("click on the Choose a product type ");
    waitSeconds(5);

}
@Step("Scroll  element to be find css  <cssvalue> get index <index>")
    public void scrollToelementWithcssValueandGetindex(String cssvalue,int index){
        WebElement scroll = driver.findElements(By.cssSelector(cssvalue)).get(index);
        scrollToElement(scroll);

}
 @Step("Scrol  element to be find css <cssvalue> get text <text> value")
    public void scrollToelementWithcssValuegetText(String cssvalue,String text){
    List<WebElement> productList = driver.findElements(By.cssSelector(cssvalue));
    for (WebElement webElement : productList) {
        if (webElement.getText().equals(text)) {
            scrollToElement(webElement);
            waitSeconds(2);
            break;
        }
    }

     }



@Step("Basket count control")
public int basketCountcontrol(){
    waitSeconds(5);
    int intBasket = 0;
    WebElement basket = null;
    try {
        basket = driver.findElement(By.id("nav-cart-count"));
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (basket != null) {

        String strBasket = basket.getText();
        if (StringUtils.isNotEmpty(strBasket)) {
            intBasket = Integer.parseInt(strBasket);
            System.out.println("Number of product in basket = " + intBasket);
            if (intBasket > 0) {
                System.out.println("Click on the basket ımage");
                driver.findElement(By.id("nav-cart-count")).click();
                waitSeconds(10);
                driver.findElement(By.cssSelector("input[value='Delete']")).click();
                waitSeconds(1);
                Assert.assertTrue("Do not delete basket", isElementPresent(By.className("sc-cart-header")));
            }
        }

    }
    return intBasket;
}
    @Step("Scroll to element to be find <id>")
    public void scrollToelementWithid(String id){
        WebElement scroll = driver.findElement(By.id(id));
        Assert.assertTrue("element was not found",isDisplayBy(By.id(id)));
        scrollToElement(scroll);
}
    @Step("Scroll to element to be find css <css>")
    public void scrollToelementWithCSSselector(String css){
        WebElement scroll = driver.findElement(By.cssSelector(css));
        //moveToElement(scroll);
        scrollToElement(scroll);
        waitSeconds(2);
    }
@Step("Click on the View button")
    public void clickViewbutton(){
        driver.findElement(By.cssSelector("#miniBasketContainer>a")).click();
}
@Step("Select <name>  from the  dropdown index <index> ")
    public void selectGenderdropdown(String name,int index){
        new Select(driver.findElement(By.name(name)))
                .selectByIndex(index);
}
    @Step("Select css <cssvalue>  from the  dropdown index <index> ")
    public void selectClasnamefromdropdown(String cssvalue,int index){
        new Select(driver.findElement(By.cssSelector(cssvalue)))
                .selectByIndex(index);
    }
    @Step("Open  frame")
    public void openFrame(){
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[id='lightningjs-frame-usabilla_live']")));
        }
    @Step("Close to  frame")
    public void openMain(){
        driver.switchTo().defaultContent();

    }
    @Step("Payment control")
public void paymentControl(){
        WebElement erormessage = driver.findElement(By.id("pageErrorsVisible"));
        scrollToElement(erormessage);
        waitSeconds(1);
    Assert.assertTrue("the purchase was not successful" , isDisplayBy(By.id("pageErrorsVisible")));
}

@Step("Search page control")
    public void searchControl(){
        Assert.assertTrue(" product search was not successful",isElementPresent(By.id("SeoTitle")));
}

@Step("Select rondom product")
    public void selectRondomproduct(){
    List<WebElement> lastNewsTitle = driver.findElements(By.cssSelector("div[class='tile col-lg-3 col-md-4 col-sm-6 col-xs-12']>div:nth-of-type(2)>a>img"));
    System.out.println("product list"+lastNewsTitle.size());
    WebElement randomNews = (WebElement) getRandomContent(lastNewsTitle);
    scrollToElement(randomNews);
    //hoverToElement(randomNews);
    waitSeconds(5);
    //javaScriptClicker(driver,randomNews);
    randomNews.click();
    System.out.println("Random product clicked");
    waitSeconds(10);
}
    @Step("Select random <css> element")
    public void selectRondomproductCss(String css){
        List<WebElement> lastNewsTitle = driver.findElements(By.cssSelector(css));
        System.out.println("product list"+lastNewsTitle.size());
        WebElement randomNews = (WebElement) getRandomContent(lastNewsTitle);
        scrollToElement(randomNews);
        //hoverToElement(randomNews);
        waitSeconds(5);
        //javaScriptClicker(driver,randomNews);
        randomNews.click();
        System.out.println("Random product clicked");
        waitSeconds(10);
    }

@Step("Address control")
    public void addressControl(){
        WebElement address = driver.findElements(By.className("a-button-inner")).get(9);
    if (isClickable(address)){
        driver.findElements(By.className("a-button-inner")).get(11).click();
        waitSeconds(2);
        scrollToElement(driver.findElement(By.id("enterAddressFullName")));
    }else{
        scrollToElement(driver.findElement(By.id("enterAddressFullName")));

    }


}
@Step("Click pay button")
public void paybutton(){
        if (isDisplayBy(By.cssSelector(".m44-bill-selector__submit .button--primary"))){
            javaScriptClicker(driver,driver.findElement(By.cssSelector(".m44-bill-selector__submit .button--primary")));
            //driver.findElement(By.cssSelector(".m44-bill-selector__submit .button--primary")).click();
        }else{
            driver.findElement(By.className("amount_label")).click();
        }
    }



    @Step("Keys up <upLoop>")
    protected void keysUp(int upLoop) {
        waitSeconds(1);
        for (int i = 0; i < upLoop; i++) {
            driver.findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.DOWN));

        }
    }

@Step("Hover to elemen <css> and get index <index>")
    public void hoverToElemencss(String css,int index){
        WebElement element =driver.findElements(By.cssSelector(css)).get(index);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

}
@Step("Select month <month> in date selection.")
    public void selectMonth(String month){
    List <WebElement> listofItems = driver.findElements(By.cssSelector("select[class='pika-select pika-select-month']>option"));

    for (int i=0; i<=listofItems.size(); i++)
    {
        listofItems.get(i).getText().contains(month);

        for (WebElement webElement : listofItems)
            webElement.click();



    }

}
@Step("Page is loaded correctly if list of travel options is displayed.")
    public void travelOptionsControl(){
        waitSeconds(1);
        Assert.assertTrue("Travel option was not displayed",isDisplayBy(By.cssSelector("ol[class='rp-reismogelijkhedenList']")));

}

@Step("Page is loaded correctly if the displayed text is 'Goedendag, J. Jansen'.")
    public void loginControl(){
        waitSeconds(4);
        Assert.assertTrue("Text was not displayed",driver.getPageSource().contains(" Goedendag, J. Jansen "));
}

@Step("Click on pencil icon next to field 'Telefoonnummer'.")
    public void clickPencilTelefoonnummer(){
      WebElement pencilList =  driver.findElements(By.cssSelector("i[class='icon icon--edit icon--nsBlue']")).get(4);
      hoverToElement(pencilList);
      waitSeconds(1);
      pencilList.click();
     waitSeconds(2);
}

@Step("In the pop-up that appears, enter '0123456789'.")
    public void controlTelefoonnummer(){
    Assert.assertTrue("Telefoonnummer number was not appear",isDisplayBy(By.id("phoneNumber")));

}

@Step("Page is loaded correctly if the field 'Telefoonnummer' contains '0123456789'.")
    public void controlContainstelefoonnummer(){
        waitSeconds(2);
        Assert.assertTrue("Telefoonnummer'was not  contains '0123456789'.",driver.findElements(By.cssSelector("div[class='infofield__data usabilla-dont-show']")).get(8).getText().contains("0123456789"));
}
    @Step("The control of search resul contains <key>")
    public void controlContainsSearchingText(String key){
        waitSeconds(2);
        Assert.assertTrue("'Search resul was not display",driver.getPageSource().contains(key));
    }



    @Step("Click <textname> button at the left menu ")
    public void clickonLeftMenüandTextt(String TextName){
        List<WebElement> productList = driver.findElements(By.cssSelector("ul[class='mainMenu__list']>li>a"));
        for (WebElement webElement : productList) {
            if (webElement.getText().contains((TextName))) {
                hoverToElement(webElement);
                webElement.click();
                waitSeconds(2);
                break;
            }
        }
    }


    @Step("Go to <url> ")
    public void goTourl(String url){
        waitSeconds(1);
        driver.get(url);
        waitSeconds(2);
        System.out.print(url + "was open");

    }
    @Step("Keys down to <downLoop> ")
    protected void keysDown(int downLoop) {
        for (int i = 0; i < downLoop; i++) {
            driver.findElement(By.tagName("body")).sendKeys(Keys.chord(Keys.DOWN));
            waitSeconds(1);
        }
    }
}







