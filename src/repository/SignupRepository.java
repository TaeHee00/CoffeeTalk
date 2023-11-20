package repository;

import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SignupRepository extends DataBase {
    public Optional<User> findByEmail(String email) {
        User user = null;

        try {
            connect();

            String sql = "select * from User where email = '" + email + "';";
            ResultSet rs = stmt.executeQuery(sql);

            boolean isFind = rs.next();
            if (isFind) {
                user = User.builder()
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .nickname(rs.getString("nickname"))
                        .birthday(rs.getTimestamp("birthday"))
                        .build();
            }

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }

    public void save(User user) {
        try {
            connect();

            PreparedStatement prepStmt =
                    con.prepareStatement("insert into User (email, password, name, nickname, birthday)" +
                            " values (?, ?, ?, ?, ?)");

            prepStmt.setString(1, user.email());
            prepStmt.setString(2, user.password());
            prepStmt.setString(3, user.name());
            prepStmt.setString(4, user.nickname());
            prepStmt.setTimestamp(5, user.birthday());

            prepStmt.executeUpdate();

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
