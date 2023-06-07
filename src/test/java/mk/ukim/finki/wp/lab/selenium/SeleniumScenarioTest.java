package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.model.*;
import mk.ukim.finki.wp.lab.service.BalloonService;
import mk.ukim.finki.wp.lab.service.ManufacturerService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;

    @Autowired
    BalloonService balloonService;

    @Autowired
    ManufacturerService manufacturerService;

    private HtmlUnitDriver driver;

    private static Balloon b2;

    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";
    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(false);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() {
        if (!dataInitialized) {

            //m1 = manufacturerService.save("m1", "m1",  "m2");

            m2 = manufacturerService.save("m2", "m2",  "m2");

            //b2 = balloonService.save("b2", "b2", m2.getId(), Type.kocka).get();

            regularUser = userService.register(user, user, user, user, user, LocalDate.now(), Role.ROLE_USER);
            adminUser = userService.register(admin, admin, admin, admin, admin, LocalDate.now(), Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonPage balloonPage = BalloonPage.to(this.driver);
        balloonPage.assertElemts(0, 0, 0,  0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        balloonPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        balloonPage.assertElemts(0, 0, 0,  1);

        balloonPage = AddOrEditBalloon.addBalloon(this.driver, "test", "description", m2.getName());
        balloonPage.assertElemts(1, 1, 1,  1);

        balloonPage = AddOrEditBalloon.addBalloon(this.driver, "test1", "description", m2.getName());
        balloonPage.assertElemts(2, 2, 2,  1);

        balloonPage.getDeleteButtons().get(1).click();
        balloonPage.assertElemts(1, 1, 1,  1);

        balloonPage = AddOrEditBalloon.editBalloon(this.driver, balloonPage.getEditButtons().get(0), "test1", "description", m2.getName());
        balloonPage.assertElemts(1, 1, 1,  1);

        loginPage = LoginPage.logout(this.driver);
        balloonPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
        //balloonPage.assertElemts(1, 0, 0,  0);
        balloonPage.assertElemts(1, 0, 0,  0);

    }

}
