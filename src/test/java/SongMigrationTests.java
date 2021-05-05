import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class SongMigrationTests {

    @Test
    public void testMigratedSongs() {
        try {
            Migration.executeMigration(QueriesToDB.addColumnToSongsTable);
            DataExporter.exportDataFromDb(PathToFiles.songResult,
                    QueriesToDB.getSELECTQueryOrderById("songs"),
                    DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("songs")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.songExpected, PathToFiles.songResult, PathToFiles.songLogFile);
        boolean equality = csvComparer.compareCSV();
        Migration.executeMigration(QueriesToDB.deleteColumnInSongsTable);
        Assert.assertTrue(equality);
    }

    @Test
    public void testNonMigratedSongs() {
        try {
            DataExporter.exportDataFromDb(PathToFiles.songResult,
                    QueriesToDB.getSELECTQueryOrderById("songs"),
                    DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("songs")));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.songExpected, PathToFiles.songResult, PathToFiles.songLogFile);
        boolean equality = csvComparer.compareCSV();
        Assert.assertTrue(equality);
    }

    @Test
    public void testMigratedSongsWithGroupBy() {
        try {
            Migration.executeMigration(QueriesToDB.addColumnToSongsTable);
            DataExporter.exportDataFromDb(PathToFiles.songResultWithGroupBy,
                    QueriesToDB.SELECTQuerySumDurationGroupBy2ForSongs,
                    DataExporter.getTableColumnsNames(QueriesToDB.selectSongsGenreIDAndDurationAndAlbum));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CSVComparer csvComparer = new CSVComparer(PathToFiles.songExpectedWithGroupBy, PathToFiles.songResultWithGroupBy, PathToFiles.songLogFile);
        boolean equality = csvComparer.compareCSV();
        Migration.executeMigration(QueriesToDB.deleteColumnInSongsTable);
        Assert.assertTrue(equality);
    }
}
