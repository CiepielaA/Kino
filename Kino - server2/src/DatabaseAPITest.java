/**
 * Created by Adrian on 14.01.2017.
 */
public class DatabaseAPITest {
    public static void main(String[] args) {
        CinemaDatabaseAPI cinemaDatabase = new CinemaDatabaseAPI();
        cinemaDatabase.openDatabaseConnection();

        cinemaDatabase.closeDatabaseConnection();
    }
}
