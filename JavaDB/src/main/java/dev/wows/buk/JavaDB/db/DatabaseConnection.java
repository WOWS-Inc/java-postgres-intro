package dev.wows.buk.JavaDB.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseConnection {

    private static final String CONFIG_FILE = "src/main/resources/config.properties";
    private static final String DEFAULT_URL = "localhost";
    private static final String DEFAULT_PORT = "5432";

    private final DataSource dataSource;

    private String dbUser;
    private String dbUrl;
    private String dbPort;
    private String dbPws;
    private String dbDatabase;

    public DatabaseConnection() {

        getDatabaseCredentials();
        dataSource = createDataSource();
    }

    public Connection getConnection() throws SQLException {

        return dataSource.getConnection();
    }

    private void getDatabaseCredentials() {

        try (InputStream input = new FileInputStream(CONFIG_FILE)) {

            Properties props = new Properties();
            props.load(input);

            dbUser = props.getProperty("db.user");
            dbUrl = props.getProperty("db.url", DEFAULT_URL);
            dbPort = props.getProperty("db.port", DEFAULT_PORT);
            dbPws = props.getProperty("db.password");
            dbDatabase = props.getProperty("db.database");
        }
        catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
    private DataSource createDataSource() {

        final String url = "jdbc:postgresql://" + dbUrl + ":" + dbPort + "/" + dbDatabase;
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setUrl(url);
        dataSource.setUser(dbUser);

        if (dbPws != null && !dbPws.isBlank())
            dataSource.setPassword(dbPws);

        return dataSource;
    }
} 