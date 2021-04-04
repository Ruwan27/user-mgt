package lk.demo.app.services;

import lk.demo.app.domain.NewUser;
import lk.demo.app.domain.NewUserResponse;

import java.util.List;

public interface UserManagementServices {

    NewUserResponse addUser(NewUser newUser);

    NewUserResponse updateUser(NewUser newUser);

    List<NewUser> listAllUser();
}
