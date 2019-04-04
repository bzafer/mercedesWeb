package com.mercedes.web.step;

import com.mercedes.web.base.BaseTest;
import com.mercedes.web.helper.ElementHelper;
import com.mercedes.web.helper.StoreHelper;
import com.mercedes.web.model.ElementInfo;
import com.thoughtworks.gauge.Step;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.text.Document;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepImplementation extends BaseTest {


  private String teklifNo;

  private WebElement findElement(String key){
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
    WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
    WebElement webElement = webDriverWait
        .until(ExpectedConditions.presenceOfElementLocated(infoParam));
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
        webElement);
    return webElement;
  }

  private List<WebElement> findElements(String key){
    System.out.println("333333");
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    System.out.println("44444");
    By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
    System.out.println("55555");
    return driver.findElements(infoParam);
  }

  private void clickElementBy(String key){
    findElement(key).click();
  }

  private void hoverElementBy(String key){
    WebElement webElement = findElement(key);
    Actions actions = new Actions(driver);
    actions.moveToElement(webElement).build().perform();
  }

  private void sendKeyESC(String key){
    findElement(key).sendKeys(Keys.ESCAPE);

  }

  private void sendKeyTAB(String key){
    findElement(key).sendKeys(Keys.TAB);
  }

  @Step("<key> elementine tıkla")
  public void clickToElement(String key){
    hoverElementBy(key);
    clickElementBy(key);
  }

  @Step("<key1> elementi varsa, <key2> elementine tıkla")
  public void clickToElementIfAnotherExists(String key1, String key2){
    try {
      findElement(key1);
    } catch (Exception e) {
      return;
    }
    clickToElement(key2);
  }

  @Step("<key> elementine <text> değerini yaz")
  public void sendKeys(String key, String text){
    findElement(key).sendKeys(text);
  }

  @Step("Şuanki URL <value> değerini içeriyor mu, tekrarlı kontrol et")
  public void checkURLContainsRepeat(String value){
    int againCount = 0;
    boolean isUrlContains = false;
    String takenUrl = "";
    while (!isUrlContains) {
      waitByMilliSeconds(250);
      if (againCount == 50) {
        Assert.fail("Beklenen URL uyuşmadı");
        System.out.println("Beklenen: " + value + ", Varolan: " + driver.getCurrentUrl());
      }
      takenUrl = driver.getCurrentUrl();
      if (takenUrl != null) {
        isUrlContains = takenUrl.contains(value);
      }
      System.out.println("\n\n\n" + value + "\n" + isUrlContains + "\n\n\n");
      againCount++;
    }
  }

  @Step("<key> elementi var mı")
  public void checkElement(String key){
    try {
      findElement(key);
    } catch (Exception e) {
      Assert.fail("Element bulunamadı.");
    }
  }

  @Step("<key> elementi yok mu")
  public void checkElementDoesNotExist(String key){
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By by = ElementHelper.getElementInfoToBy(elementInfo);
    boolean isElementInvisible = false;
    int countAgain = 0;
    int elementCount;
    while (!isElementInvisible) {
      waitByMilliSeconds(250);
      if (countAgain == 25) {
        Assert.fail("Element halen mevcut");
        break;
      }
      elementCount = driver.findElements(by).size();
      System.out.println("\n\n" + elementCount + "\n\n");
      if (elementCount == 0) {
        isElementInvisible = true;
      }
      countAgain++;
    }
  }

  @Step("<key> elementine TAB keyi yolla")
  public void sendTABKeyToElement(String key){
    findElement(key).sendKeys(Keys.TAB);
  }

  @Step("<key> elementine BACKSPACE keyi yolla")
  public void sendBACKSPACEKeyToElement(String key){
    findElement(key).sendKeys(Keys.BACK_SPACE);
  }

  @Step("<seconds> saniye bekle")
  public void waitBySeconds(int seconds){
    waitByMilliSeconds(seconds * 1000);
  }

  @Step("<seconds> milisaniye bekle")
  public void waitByMilliSeconds(long milliseconds){
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Step("<key> elementinin <attributeType> niteliği <expectedValue> değerine eşit mi")
  public void checkElementAttribute(String key, String attributeType, String expectedValue){
    String attributeValue = findElement(key).getAttribute(attributeType).trim();
    Assert.assertNotNull("Elementin değeri bulunamadi", attributeValue);
//    Assert.assertTrue("Elementin değeri eşleşmedi",
//        attributeValue.contains(expectedValue));
    Assert.assertEquals("Elementin değeri eşleşmedi", expectedValue, attributeValue);
  }

  @Step("<key> elementinin <attributeType> niteliğine <value> değerini yaz")
  public void setElementAttribute(String key, String attributeType, String value){
    String attributeValue = findElement(key).getAttribute(attributeType);
    findElement(key).sendKeys(attributeType, value);
  }

  @Step("<key> elementinin çocuk sayısı <expectedCount> değerine eşit mi")
  public void checkChildrenCount(String key, int expectedCount){
    int actualChildrenCount = findElements(key).size();
    System.out.println(actualChildrenCount
    );
    Assert.assertEquals("", expectedCount, actualChildrenCount);
  }

  @Step("<key> elementinin text alanını temizle")
  public void clearInputArea(String key){
    findElement(key).clear();
  }

  @Step("<key> elementinin text değeri <expectedText> değerine eşit mi")
  public void checkElementAttribute(String key, String expectedText){
    String actualText = findElement(key).getText().trim();
    Assert.assertEquals("Elementin text değeri eşleşmedi.", expectedText, actualText);
  }

  @Step("<key> elementinin text değerini sakla")
  public void checkElementAttribute(String key){
    String text = findElement(key).getText();
    teklifNo = text.substring(0, text.indexOf("N") - 1);
    System.out.println(teklifNo);
  }

  @Step("%60 Zoom out yap")
  public void chromeZoomOut(){
    JavascriptExecutor selam = (JavascriptExecutor) driver;
    selam.executeScript("document.body.style.zoom = '60%'");
  }

  @Step("Zoom resetle")
  public void chromeZoomReset(){
    JavascriptExecutor selam = (JavascriptExecutor) driver;
    selam.executeScript("document.body.style.zoom = '100%'");
  }

  @Step("<key> elementine tıkla <path> dosya yükle")
  public void sendFile(String key, String path) throws Exception{

    String myString = System.getProperty("user.dir") + "/" + path;
    System.out.println(myString);
    StringSelection stringSelection = new StringSelection(myString);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
    waitBySeconds(2);
    Robot robot = new Robot();
    robot.delay(1000);
    robot.keyPress(KeyEvent.VK_DOWN);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_DOWN);
    robot.keyRelease(KeyEvent.VK_ENTER);
    robot.delay(1000);

    //Fullscreen kontrolü
    Dimension dimension2 = Toolkit.getDefaultToolkit().getScreenSize();
    System.out.println(dimension2.width + " " + dimension2.height);
    org.openqa.selenium.Dimension dimension3 = driver.manage().window().getSize();
    System.out.println(dimension3.width + " " + dimension3.height);

    if (dimension2.width == dimension3.width && dimension2.height == dimension3.height) {
      robot.delay(1000);
      robot.keyPress(KeyEvent.VK_META);
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_META);
    }

    clickElementBy(key);

    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    robot.delay(3000);
    robot.mouseMove(dimension.width / 2, dimension.height / 2);
    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    robot.delay(2000);
    robot.keyPress(KeyEvent.VK_META);
    robot.keyPress(KeyEvent.VK_SHIFT);
    robot.keyPress(KeyEvent.VK_G);
    robot.keyRelease(KeyEvent.VK_G);
    robot.keyRelease(KeyEvent.VK_SHIFT);
    robot.keyRelease(KeyEvent.VK_META);
    robot.delay(1000);
    robot.keyPress(KeyEvent.VK_META);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_META);
    robot.keyRelease(KeyEvent.VK_V);
    robot.delay(1000);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    robot.delay(2000);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
    waitBySeconds(2);
  }

  final String[] aa = {""};

  Thread thread2 = new Thread() {
    public void run(){
      if (aa[0].equals("a")) {
        try {

          System.out.println("Thread2 Running");
          System.out.println(345436476);
          Robot robot = new Robot();
          robot.delay(3000);
          robot.keyPress(KeyEvent.VK_DOWN);
          robot.keyRelease(KeyEvent.VK_DOWN);
          robot.delay(1000);
          Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
          robot.delay(3000);
          robot.mouseMove(dimension.width / 2, dimension.height / 3);
          robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
          robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

          robot.delay(2000);
          robot.keyPress(KeyEvent.VK_ESCAPE);
          robot.delay(300);
          robot.keyRelease(KeyEvent.VK_ESCAPE);

        } catch (Exception e) {

        }
        currentThread().interrupt();
      }

    }

  };

  @Step("BAŞLAT")
  public void sendFile(){
    thread2.start();

    aa[0] = "a";
    driver.navigate().to("http://pp.koton.com");
    System.out.println("Thread Running");
    System.out.println(driver.getPageSource());
  }


}
