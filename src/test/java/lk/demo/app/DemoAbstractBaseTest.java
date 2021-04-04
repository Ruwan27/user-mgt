package lk.demo.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Properties;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DemoConfigTestContextInitializer.class})
@SpringBootTest
@ActiveProfiles("dev")
public class DemoAbstractBaseTest {

    @BeforeTestClass
    public static void setSystemProperty() {
        Properties properties = System.getProperties();
        properties.setProperty("spring.profiles.active", "dev");
    }
}
