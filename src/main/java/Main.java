public class Main {

    public static void main(String[] args) throws Exception{

        dbWorker.getDBData(filePaths.userExpected,
                QueriesToDB.selectAllUsersOrderById,
                dbWorker.getTableColumnsNames(QueriesToDB.selectAllUsers));

        dbWorker.getDBData(filePaths.songExpected,
                QueriesToDB.selectAllSongsOrderById,
                dbWorker.getTableColumnsNames(QueriesToDB.selectAllSongs));

        dbWorker.getDBData(filePaths.songExpectedWithGroupBy,
                QueriesToDB.SELECTQuerySumDurationGroupBy1ForSongs,
                dbWorker.getTableColumnsNames(QueriesToDB.selectSongsGenreIDAndDuration));
    }
}
