package lk.demo.app.services.impl;

import lk.demo.app.domain.NewUser;
import lk.demo.app.domain.NewUserResponse;
import lk.demo.app.services.UserManagementServices;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServicesImpl implements UserManagementServices {

    @Override
    public NewUserResponse addUser(NewUser newUser) {
        return null;
    }
}
