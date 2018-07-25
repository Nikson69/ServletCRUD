package services;

import dao.UserDAO;
import dao.UserDAOJDBCimpl;
import dao.UserDaoHibernateImpl;
import utils.DBHelper;

import java.io.IOException;
import java.util.Properties;


public class UserDaoFactory {

    public UserDaoFactory()  {

    }

    public static UserDAO getUserDAO(){
        Properties properties = new Properties();
        String dbValue = null;
        try  {
            properties.load(UserDaoFactory.class.getResourceAsStream("/config.properties"));
            dbValue = properties.getProperty("db.value");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        assert dbValue != null;
        switch (dbValue){
            case "Hibernate":
                return new UserDaoHibernateImpl(DBHelper.getSessionFactory());
            case "JDBC":
                return new UserDAOJDBCimpl(DBHelper.getMysqlConnection());
        }
        return null;
    }
}
