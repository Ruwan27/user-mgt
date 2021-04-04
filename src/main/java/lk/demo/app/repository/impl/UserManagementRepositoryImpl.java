package lk.demo.app.repository.impl;

import lk.demo.app.repository.UserManagementRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserManagementRepositoryImpl implements UserManagementRepository {

    @Override
    public int addUser(Map<String, Object> params) {
        return 0;
    }
}
