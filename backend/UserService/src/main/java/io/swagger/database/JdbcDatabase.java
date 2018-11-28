package io.swagger.database;

import io.swagger.model.LoginRequest;
import io.swagger.model.LoginToken;
import io.swagger.model.NewUserRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.io.IOException;

public interface JdbcDatabase {

    public void createUser(NewUserRequest user) throws IOException;
    public LoginToken loginUser(LoginRequest user) throws IOException;
    public boolean validateAdmin(LoginToken token) throws IOException;
    public boolean validateToken(LoginToken token) throws IOException;
    public boolean invalidateToken(LoginToken token) throws IOException;
    public JdbcTemplate getJdbcTemplate();
    public DataSource getDataSource();

}
