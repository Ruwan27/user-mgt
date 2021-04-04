package lk.demo.app.services.impl;

import lk.demo.app.domain.NewUser;
import lk.demo.app.domain.NewUserResponse;
import lk.demo.app.repository.UserManagementRepository;
import lk.demo.app.services.UserManagementServices;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserManagementServicesImpl implements UserManagementServices {

    private final UserManagementRepository userManagementRepository;

    public UserManagementServicesImpl(UserManagementRepository userManagementRepository) {
        this.userManagementRepository = userManagementRepository;
    }

    @Override
    public NewUserResponse addUser(NewUser newUser) {
        NewUserResponse newUserResponse = new NewUserResponse();
        Map<String, Object> params = new HashMap<>();
        params.put("userName", newUser.getUserName());
        int val = userManagementRepository.addUser(params);
        return newUserResponse;
    }

    @Override
    public NewUserResponse updateUser(NewUser newUser) {
        NewUserResponse newUserResponse = new NewUserResponse();
        Map<String, Object> params = new HashMap<>();
        params.put("userName", newUser.getUserName());
        int val = userManagementRepository.updateUser(params);
        return newUserResponse;
    }

    @Override
    public List<NewUser> listAllUser() {
        return userManagementRepository.listAllUser();
    }
}
