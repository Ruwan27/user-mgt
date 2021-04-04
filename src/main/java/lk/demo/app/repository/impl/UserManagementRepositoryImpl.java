package lk.demo.app.repository.impl;

import lk.demo.app.domain.NewUser;
import lk.demo.app.repository.UserManagementRepository;
import lk.demo.app.repository.impl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserManagementRepositoryImpl implements UserManagementRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserManagementRepositoryImpl(@Qualifier("demo-NamedParameter") NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int addUser(Map<String, Object> params) {
        String sql = "INSERT INTO users (`userName`, `empId`, `address`, `email`, `initiated_by`, `initiated_date`) " +
                "VALUES ( :userName, :empId, :address, email, :intiatedBy, NOW() )";
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int updateUser(Map<String, Object> params) {
        return 0;
    }

    @Override
    public List<String> getUserRole(Map<String, Object> params) {
        String sql = "SELECT role FROM roles WHERE roleType = :roleType";
        return namedParameterJdbcTemplate.query(sql, (rs, i) -> UserMapper.getRole(rs));
    }

    @Override
    public List<NewUser> listAllUser() {
        String sql = "SELECT userName, empId, address, email FROM users";
        return namedParameterJdbcTemplate.query(sql, (rs, i) -> UserMapper.listUsers(rs));
    }
}
