package lk.demo.app.repository.impl.mapper;

import lk.demo.app.domain.NewUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static NewUser listUsers(ResultSet rs) throws SQLException {
        NewUser newUser = new NewUser();
        newUser.setName(rs.getString("ROLE"));
        newUser.setEmail(rs.getString("CUSTOMER_CATEGORY"));
        newUser.setAddress(rs.getString("DESCRIPTION"));
        return newUser;
    }

    public static String getRole(ResultSet rs) throws SQLException {
        return rs.getString("role");
    }
}
