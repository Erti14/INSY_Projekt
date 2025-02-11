import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //Parameters for the connection
        String username = "postgres.pmmyurrjdfdumbhmbaiw";
        String password = "m54yi9PuVNHOpUrs";
        String hostname = "aws-0-eu-central-1.pooler.supabase.com";
        String database = "postgres";
        String port = "5432";
        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + database;


        // Register the PostgreSQL driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        // Connect to the database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection made successfully");
            Statement statement = connection.createStatement();
            System.out.println("Here are all books sorted by their title:");
            ResultSet resultSet = statement.executeQuery("SELECT title FROM books order by title");

            while (resultSet.next()) {
                String columnValue = resultSet.getString("title");
                System.out.println("Title: " + columnValue);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Close the connection
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
