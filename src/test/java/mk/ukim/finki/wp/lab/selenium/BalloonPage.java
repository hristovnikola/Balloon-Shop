package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import mk.ukim.finki.wp.lab.selenium.AbstractPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonPage extends AbstractPage {

    @FindBy(css = ".balloon_selenium")
    private List<WebElement> balloonRows;

    @FindBy(css = ".delete-balloon-selenium")
    private List<WebElement> deleteButtons;

    @FindBy(className = "edit-balloon-selenium")
    private List<WebElement> editButtons;

    @FindBy(css = ".add-balloon-selenium-btn")
    private List<WebElement> addBalloonButton;

    public BalloonPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonPage.class);
    }

    public void assertElemts(int balloonRows, int deleteButtons, int editButtons, int addBalloonButton) {
        Assert.assertEquals("rows do not match", balloonRows, this.getBalloonRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("add is visible", addBalloonButton, this.getAddBalloonButton().size());
    }

}
