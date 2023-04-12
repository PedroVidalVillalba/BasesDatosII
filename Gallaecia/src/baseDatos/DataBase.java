package baseDatos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBase {
    private static DataBase currentDB;

    private Connection connection;

    private UserType userType;

    public DataBase(UserType userType) {
        Properties configuration = new Properties();
        FileInputStream configurationFile;

        try {
            switch (userType) {
                case Admin:
                    configurationFile = new FileInputStream("DataBaseGuest.properties");
                    break;
                case Guest:
                default:
                    configurationFile = new FileInputStream("DataBaseAdmin.properties");
                    break;
            }

            configuration.load(configurationFile);
            configurationFile.close();

            Properties user = new Properties();

            String manager = configuration.getProperty("manager");

            user.setProperty("user", configuration.getProperty("user"));
            user.setProperty("password", configuration.getProperty("password"));

            this.connection = DriverManager.getConnection("jdbc:" + manager + "://" +
                    configuration.getProperty("server") + ":" +
                    configuration.getProperty("port") + "/" +
                    configuration.getProperty("dataBase"),
                    user);

            DataBase.currentDB = this;

        } catch(IOException | SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public static DataBase getCurrentDB() {
        return DataBase.currentDB;
    }

    public static void closeCurrentDB() {
        try {
            currentDB.connection.close();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
    }

    public UserType getUserType() {
        return this.userType;
    }


}
