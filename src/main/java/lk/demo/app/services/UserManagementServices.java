package lk.demo.app.services;

import lk.demo.app.domain.NewUser;
import lk.demo.app.domain.NewUserResponse;

public interface UserManagementServices {

    NewUserResponse addUser(NewUser newUser);
}
