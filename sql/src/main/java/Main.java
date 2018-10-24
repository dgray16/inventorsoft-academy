import lombok.AccessLevel;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;

/**
 * Links:
 * <ul>
 *     <li> https://docs.google.com/presentation/d/1VfQuVLYfFfyTd8AfXSsvOGAeMuqOfKYOU3zcUfxTTCw </li>
 *     <li> https://docs.google.com/presentation/d/1KcNmklZlR7dukU_B41vUT_ZRUys1u5TJBeEf4Av_9QQ </li>
 *     <li> https://docs.google.com/presentation/d/1EQ8Hmg2qpQqkEi1ysjqHkMX2cZLvWfCMchlvie4cvX0 </li>
 *     <li> https://docs.google.com/presentation/d/1ON5K22OOD5EKYvOSy6hRH-jC0JfbivVASpSQ6NeptnI </li>
 * </ul>
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
class Main {

    static final String DB_URL = "jdbc:hsqldb:mem:training";
    static final String USER = "SA";
    static final String PREPARED_SQL = "SELECT BOOKS.TITLE FROM BOOKS WHERE BOOKS.ID = ?";
    static final String PRINT_INFO = "%s : %s";

    @SneakyThrows
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, StringUtils.EMPTY)) {
            regularStatement(connection);
            preparedStatement(connection);
            transactions(connection);
        }
    }

    @SneakyThrows
    private static void regularStatement(Connection connection) {
        String initSql = getSqlCode("init.sql");
        String insertSql = getSqlCode("insert.sql");

        /* Regular statement */
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(initSql);
            statement.executeQuery(insertSql);

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKS")) {
                printInfo(resultSet);
            }
        }
    }

    @SneakyThrows
    private static void preparedStatement(Connection connection) {
        /* Prepared statement */
        try (PreparedStatement preparedStatement = connection.prepareStatement(PREPARED_SQL)) {
            preparedStatement.setInt(INTEGER_ONE, 2);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                printInfo(resultSet);
            }
        }

        System.out.println("\n---------------\n");

        /* Regular statement */
        try (Statement statement = connection.createStatement()) {
            String query = PREPARED_SQL.replace("?", "1 OR TRUE = TRUE UNION SELECT NATIONALITY.NAME FROM NATIONALITY");

            try (ResultSet resultSet = statement.executeQuery(query)) {
                printInfo(resultSet);
            }
        }
    }

    @SneakyThrows
    private static void transactions(Connection connection) {
        System.out.println("\n --------------- \n");
        connection.setAutoCommit(false);

        try (Statement statement = connection.createStatement()) {
            try {
                Integer rowsAffected = statement.executeUpdate("UPDATE BOOKS SET BOOKS.STOCK = 500 WHERE BOOKS.ID = 2 OR BOOKS.ID = 1");

                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKS")) {
                    printInfo(resultSet);
                }

                /* Rollback */
                /*if (true) {
                    throw new SQLException("Commit shall not pass!");
                }*/

                /* Commit */
                connection.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            } finally {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKS")) {
                    printInfo(resultSet);
                }
            }
        }
    }

    @SneakyThrows
    private static String getSqlCode(@NonNull String filename) {
        URL sqlResource = Main.class.getClassLoader().getResource(filename);
        File file = new File(sqlResource.getPath());
        return new String(Files.readAllBytes(Paths.get(file.getPath())));
    }

    @SneakyThrows
    private static void printInfo(ResultSet resultSet) {
        if (BooleanUtils.isFalse(resultSet.isBeforeFirst())) {
            System.out.println("Query returned no rows");
        } else {
            int columnsCount = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                for (int i = INTEGER_ONE; i <= columnsCount; i++) {
                    System.out.println(String.format(
                            PRINT_INFO, resultSet.getMetaData().getColumnName(i), resultSet.getObject(i))
                    );
                }
                System.out.println("\n");
            }
        }
    }

}