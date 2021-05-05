import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class UserMigrationTests {

    @Test
    public void testNonMigratedUsersReturn() {
        try {
            dbWorker.getDBData(filePaths.userResult,
                    QueriesToDB.selectAllUsersOrderById,
                    dbWorker.getTableColumnsNames(QueriesToDB.selectAllUsers));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Comparer csvComparer = new Comparer(filePaths.userExpected, filePaths.userResult, filePaths.userLogFile);
        boolean equality = csvComparer.compareCSV();
        Assert.assertTrue(equality);
    }

    @Test
    public void testMigratedUsers() {
        try {
            Migration.migrate(QueriesToDB.addColumnToUsersTable);
            dbWorker.getDBData(filePaths.userResult,
                    QueriesToDB.selectAllUsersOrderById,
                    dbWorker.getTableColumnsNames(QueriesToDB.selectAllUsers));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        Comparer csvComparer = new Comparer(filePaths.userExpected, filePaths.userResult, filePaths.userLogFile);
        boolean equality = csvComparer.compareCSV();
        Migration.migrate(QueriesToDB.deleteColumnInUsersTable);
        Assert.assertTrue(equality);
    }
}
