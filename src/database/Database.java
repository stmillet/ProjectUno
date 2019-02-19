package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Database {

    //provide persistent storage for the server's data
    static String databaseName = "jdbc:sqlite:identifier.sqlite";

    /**
     * Initializes a table in the SQLite Database from the commands stored in the
     * corresponding schema.sql file in database.schemas
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public void initializeTable(File schema,
                                Connection connection)
            throws FileNotFoundException, SQLException {
        //read in from schema
        Scanner scanner =
                new Scanner(
                        new BufferedReader(
                                new FileReader(schema))); //throws File Not Found Exception
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            String next = scanner.next();

            if (!next.contains(";")) {
                stringBuilder.append(next);
                stringBuilder.append(" ");
            }
            else {
                stringBuilder.append(";");
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement(
                stringBuilder.toString());
        preparedStatement.executeUpdate();
    }
}
