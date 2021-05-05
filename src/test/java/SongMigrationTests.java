import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class SongMigrationTests {

    @Test
    public void testMigratedSongs() {
        try {
            Migration.migrate(QueriesToDB.addColumnToSongsTable);
            dbWorker.getDBData(filePaths.songResult,
                    QueriesToDB.selectAllSongsOrderById,
                    dbWorker.getTableColumnsNames(QueriesToDB.selectAllSongs));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        Comparer comparer = new Comparer(filePaths.songExpected, filePaths.songResult, filePaths.songLogFile);
        boolean isEqual = comparer.compareCSV();
        Migration.migrate(QueriesToDB.deleteColumnInSongsTable);
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testNonMigratedSongs() {
        try {
            dbWorker.getDBData(filePaths.songResult,
                    QueriesToDB.selectAllSongsOrderById,
                    dbWorker.getTableColumnsNames(QueriesToDB.selectAllSongs));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        Comparer comparer = new Comparer(filePaths.songExpected, filePaths.songResult, filePaths.songLogFile);
        boolean isEqual = comparer.compareCSV();
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testMigratedSongsWithGroupBy() {
        try {
            Migration.migrate(QueriesToDB.addColumnToSongsTable);
            dbWorker.getDBData(filePaths.songResultWithGroupBy,
                    QueriesToDB.SELECTQuerySumDurationGroupBy2ForSongs,
                    dbWorker.getTableColumnsNames(QueriesToDB.selectSongsGenreIDAndDurationAndAlbum));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        Comparer comparer = new Comparer(filePaths.songExpectedWithGroupBy, filePaths.songResultWithGroupBy, filePaths.songLogFile);
        boolean isEqual = comparer.compareCSV();
        Migration.migrate(QueriesToDB.deleteColumnInSongsTable);
        Assert.assertTrue(isEqual);
    }
}
