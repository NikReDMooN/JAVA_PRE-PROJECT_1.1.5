package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.DriverManager;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {


    private static SessionFactory sessionFactory;


    private static String URL = "jdbc:mysql://localhost:3306/java_pre-project_1.1.4";
    private static String user = "root";
    private static String password = "Weskerandrik1!";

    public static Connection Connection;





    public static Connection buildConnection(){ //JDBC
        if (Connection == null) {
            try {
                Connection = DriverManager.getConnection(URL
                        , user
                        , password);
            } catch (SQLException e) {
                System.out.println("Во время работы возникла ошибка " + e);
            }
        }
        return Connection;
    }

    public static void createFactory() { //HIBERNATE
        try {
           Properties properties = new Properties();
           properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/java_pre-project_1.1.4");
           properties.setProperty("hibernate.connection.username", "ReDMooN");
           properties.setProperty("hibernate.connection.password", "Weskerandrik1!");
           properties.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
           properties.setProperty("show_sql", "true");
           properties.setProperty("hibernate.format_sql","true");
           Configuration configuration = new Configuration();
           configuration.setProperties(properties);
           configuration.addAnnotatedClass(User.class);
           StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
           sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (HibernateException e) {
           System.out.println( "Что-то пошло не так " + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) createFactory();
        return sessionFactory;
    }


}
