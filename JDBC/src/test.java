/**
 * Created by SG0226566 on 2017-01-08.
 */
public class test {
    public static void main(String[] args) {
        CinemaDatabaseAPI cinemaDatabase = new CinemaDatabaseAPI();
        cinemaDatabase.openDatabaseConnection();
        cinemaDatabase.closeDatabaseConnection();
    }
}
