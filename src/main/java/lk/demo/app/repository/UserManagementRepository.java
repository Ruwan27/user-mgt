package lk.demo.app.repository;

import lk.demo.app.domain.NewUser;

import java.util.List;
import java.util.Map;

public interface UserManagementRepository {

    int addUser(Map<String, Object> params);

    int updateUser(Map<String, Object> params);

    List<String> getUserRole(Map<String, Object> params);

    List<NewUser> listAllUser();

}
