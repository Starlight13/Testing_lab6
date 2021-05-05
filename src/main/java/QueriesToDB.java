public class QueriesToDB {

    public static final String addColumnToSongsTable = "ALTER TABLE songs\n" +
            "ADD COLUMN album varchar(255) not null default 'Testing Album';";
    public static String deleteColumnInSongsTable = "ALTER TABLE songs\n" +
            "drop COLUMN album;";

    public static final String addColumnToUsersTable = "ALTER TABLE users\n" +
            "ADD COLUMN username varchar(255) not null default 'testing';";
    public static String deleteColumnInUsersTable = "ALTER TABLE users\n" +
            "drop COLUMN username;";


    public static String selectSongsGenreIDAndDuration = "select genre_id, duration_sec from songs";
    public static String selectSongsGenreIDAndDurationAndAlbum = "select genre_id, duration_sec, album from songs";


    public static final String SELECTQuerySumDurationGroupBy1ForSongs = "select genre_id, SUM(duration_sec) as duration_sec from songs GROUP BY genre_id";
    public static final String SELECTQuerySumDurationGroupBy2ForSongs = "select genre_id, album, SUM(duration_sec) as duration_sec from songs GROUP BY genre_id, album";

    public static String getSELECTQueryOrderById (String tableName) {
        return "select * from " + tableName + " order by id";
    }

    public static String getSELECTQuery (String tableName) {
        return "select * from " + tableName;
    }
}
