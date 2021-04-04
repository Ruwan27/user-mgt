package lk.demo.app;

import lk.demo.app.repository.UserManagementRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class DemoApplicationTests extends DemoAbstractBaseTest {

    public DemoApplicationTests() {
    }

    @Autowired
    public UserManagementRepository userManagementRepository;

    @Test
    public void saveOrder() {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", "111");
        params.put("status", "ss");
        params.put("comment", "test");
        params.put("teratoryCode", "ss");
        params.put("intiatedBy", 1);
        int val = userManagementRepository.addUser(params);
        assertNotNull(val);
    }

}
