package repository;

import entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoginRepository extends DataBase {

    public List<User> findAll(){
        List<User> userList = new ArrayList<>();

        try {
            connect();


            String sql = "select * from User;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = User.builder()
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .nickname(rs.getString("nickname"))
                        .birthday(rs.getTimestamp("birthday"))
                        .build();

                userList.add(user);
            }

            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

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
}
