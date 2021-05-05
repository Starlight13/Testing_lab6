public class Main {

    public static void main(String[] args) throws Exception{

        DataExporter.exportDataFromDb(PathToFiles.userExpected,
                QueriesToDB.getSELECTQueryOrderById("users"),
                DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("users")));

        DataExporter.exportDataFromDb(PathToFiles.songExpected,
                QueriesToDB.getSELECTQueryOrderById("songs"),
                DataExporter.getTableColumnsNames(QueriesToDB.getSELECTQuery("songs")));

        DataExporter.exportDataFromDb(PathToFiles.songExpectedWithGroupBy,
                QueriesToDB.SELECTQuerySumDurationGroupBy1ForSongs,
                DataExporter.getTableColumnsNames(QueriesToDB.selectSongsGenreIDAndDuration));
    }
}
