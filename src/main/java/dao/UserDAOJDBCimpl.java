package dao;

import models.User;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDAOJDBCimpl implements UserDAO {
    private Connection conn;


    public UserDAOJDBCimpl(Connection conn) {
        this.conn = conn;

    }

    public User findById(long id)  {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE id = "+id);
            if (rs.next()){
                return new User(
                        Long.valueOf(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(User user)  {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO users (name, login, password, role) values ('" + user.getName() + "', '" + user.getLogin() + "', '" + user.getPassword() + "', 'user')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(User user)  {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE users SET name = '"+user.getName()+"', login = '"+user.getLogin()+"' , password = '"+user.getPassword()+"', role = '"+user.getRole()+"' WHERE id = '"+user.getId()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(User user)  {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM users WHERE id = '" + user.getId()+ "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List <User> getUsers(){
        ArrayList <User> clients = new ArrayList<User>();
        ResultSet rs;
        User user;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users;");
            while (rs.next()) {
                user = new User(Long.valueOf(rs.getString("id")),rs.getString("name"),rs.getString("login"),rs.getString("password"),rs.getString("role"));
                clients.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public User authorize (String login) {
        ResultSet rs;
        User user = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE login = '"+ login +"' ;");
            if (rs.next()){
                user = new User(
                        Long.valueOf(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            assert user != null;
            if (user.getLogin().equals(login)){
                return user;
            } else {
                return null;
            }
        }
    }

    @Override
    public boolean findByLogin (String login) {
        ResultSet rs;
        User user = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE login = '"+ login +"' ;");
            if (rs.next()){
                user = new User(
                        Long.valueOf(rs.getString("id")),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return user != null;
        }
    }


}