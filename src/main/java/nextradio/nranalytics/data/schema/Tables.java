package nextradio.nranalytics.data.schema;


/**
 * All of the sqlite tables used in this application are defined here as static
 * classes. The columns of each table are defined as fields of each class,
 * however, the field "tableName" is not a column, but the name of the table
 * itself.  DataService and {@link CustomSQLiteOpenHelper} are the main
 * users of these definitions. Other classes use these definitions to index
 * cursors for UI purposes (see StationBrowseCursorAdapter).
 *
 * @author mminer
 */
public class Tables {

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    /**
     * Table that stores all events. Basically, a record in this table
     * represents a RadioEventInfo object. Generally, these are actually
     * received by the chip. That means, the NextRadioService has to be
     * running in order to create new records in this table. The only exception
     * to this rule is if a "Recently Played" event was favorited. <h4>related
     * tables:</h4>
     * <ul>
     * <li>{@link Tables.listeningActivityAds} - related ads for the event</li>
     * <li>{@link Stations} - station that the event played on</li>
     * <li>{@link Tables} - the main image SD card path is stored
     * here against imageURL</li>
     * </ul>
     *
     * @author mminer
     */
    public static class activityEvents {

        public static final String tableName = "activityEvents";
        public static final String _id = "_id";
        public static final String itemType = "itemType";
        public static final String UFIDIdentifier = "UFIDIdentifier";
        public static final String timestamp = "timestamp";
        public static final String artist = "artist";
        public static final String album = "album";
        public static final String title = "title";
        public static final String description = "description";
        public static final String imageURL = "imageURL";
        public static final String stationID = "stationID";
        public static final String imageURLHiRes = "imageURLHiRes";
        public static final String primaryAction = "primaryAction";
        public static final String trackingID = "trackingID";
        public static final String teID = "teID";
    }

    /**
     * <ul>
     * <li>{@link Tables.listeningActivityAds} - related ads for the event</li>
     * <li>{@link Stations} - station that the event played on</li>
     * <li>{@link Tables} - the main image SD card path is stored
     * here against imageURL</li>
     * </ul>
     *
     * @author mminer
     */
    public static class listeningHistory {

        public static final String tableName = "listeningHistory";
        public static final String _id = "_id";
        public static final String eventID = "eventID";
        public static final String lastheard = "lastheard";
        public static final String savedDate = "savedDate";
        public static final String isFavorite = "isFavorite";
        public static final String isFromNowPlaying = "isFromNowPlaying";
        public static final String isDisLiked = "isDisliked";
    }

    /**
     * Table that stores the ads associated to events. Basically, a record in
     * this table represents an Enhancement object. At this point, the records in
     * this table come from TagStation exclusively. In the future, ads may come
     * from the HD chip itself, but it should be considered to add such fields
     * to {@link activityEvents} <h4>related tables:</h4>
     * <ul>
     * <li>{@link Tables.activityEvents} - parent record ("eventID" to
     * "_id")</li>
     * the fields may contain an imageURL
     * depending on type</li>
     * </ul>
     * * @author mminer
     */
    public static class listeningActivityAds {
        public static final String tableName = "listeningActivityAds";

        public static final String _id = "_id";
        public static final String eventID = "EventID";
        public static final String IsFavorite = "IsFavorite";
        public static final String adType = "adType";
        public static final String field0 = "field0";
        public static final String field1 = "field1";
        public static final String field2 = "field2";
        public static final String field3 = "field3";
        public static final String field4 = "field4";
        public static final String field5 = "field5";
    }

    /**
     * Table that stores all stations. Stations are downloaded from TagStation
     * when Location determines is necessary. <h4>related tables:
     * </h4>
     * <ul>
     * <li>{@link Tables.activityEvents}</li>
     * </ul>
     *
     * @author mminer
     */
    public static class Stations {
        public static final int DEFAULT_OFFSET = 0;

        public static final String tableName = "stations";
        public static final String _id = "_id";
        public static final String publicStationID = "publicStationID";
        public static final String frequency = "frequency";
        public static final String frequencySubChannel = "frequencySubChannel";
        public static final String actualFrequencySubChannel = "actualFrequencySubChannel";
        public static final String callLetters = "callLetters";
        public static final String genre = "genre";
        public static final String formatGroup = "formatGroup";
        public static final String slogan = "slogan";
        public static final String imageURL = "imageURL";
        public static final String imageURLHiRes = "imageURLHiRes";
        public static final String headline = "headline";
        public static final String headlineText = "headlineText";
        public static final String IsValid = "IsValid";
        public static final String IsFavorite = "IsFavorite";
        public static final String artistList = "artistList";
        public static final String lastListened = "lastListened";
        public static final String piCode = "piCode";
        public static final String countryCode = "countryCode";
        public static final String endpoint = "endpoint";
        public static final String market = "market";
        public static final String fromNoData = "fromNoData";
        public static final String trackingID = "trackingID";
        public static final String streamProviderName = "stringProviderName";
        public static final String streamUrl = "streamUrl";
        public static final String streamType = "streamType";
        public static final String streamMetaKey = "streamMetaKey";
        public static final String streamOffset = "streamOffset";
        public static final String phoneNumber = "phoneNumber";
        public static final String websiteUrl = "websiteUrl";
        public static final String hasRecentEvents = "hasRecentlyPlayedList";
        public static final String additionalContent = "additionalContent";
        public static final String isLocal = "isLocal";
        public static final String IsFavoritedFromSearch = "IsFavoritedFromSearch";
        public static final String city = "city";
        public static final String hasStreamingMetaData = "hasStreamingMetaData";
    }

    public static class NewsItems {
        public static final String tableName = "newsItems";
        public static final String _id = "_id";
        public static final String id = "id";
        public static final String body = "body";
        public static final String callToActionData = "callToActionData";
        public static final String callToActionImageUrl = "callToActionImageUrl";
        public static final String callToActionText = "callToActionText";
        public static final String callToActionType = "callToActionType";
        public static final String cardType = "cardType";
        public static final String createdAt = "createdAt";
        public static final String expiresAt = "expiresAt";
        public static final String hasBeenDeleted = "hasBeenDeleted";
        public static final String hasBeenRead = "hasBeenRead";
        public static final String hasBeenNotified = "hasBeenNotified";
        public static final String imageUrl = "imageUrl";
        public static final String iconUrl = "iconUrl";
        public static final String newsType = "newsType";
        public static final String newsTypeText = "newsTypeText";
        public static final String subBody = "subBody";
        public static final String subtitle = "subtitle";
        public static final String title = "title";
        public static final String trackId = "trackID";

        public static final String[] COLUMNS = {
                id,
                body,
                callToActionData,
                callToActionImageUrl,
                callToActionText,
                callToActionType,
                cardType,
                createdAt,
                expiresAt,
                hasBeenDeleted,
                hasBeenRead,
                hasBeenNotified,
                imageUrl,
                iconUrl,
                newsType,
                newsTypeText,
                subBody,
                subtitle,
                title,
                trackId
        };

        public static final String SORT_ORDER = createdAt + " DESC, " + id;
        public static final int MAX_CACHE_SIZE = 100;
    }

    public static class NewsFeedReporting {
        public static final String tableName = "NewsFeedReporting";
        public static final String _id = "_id";
        public static final String utcDateTime = "reportDateTime";
        public static final String trackID = "trackID";
        public static final String batchID = "batchID";
        public static final String foreignId = "foreignID";
        public static final String action = "actionID";
    }

    public static class StationReporting {
        public static final String tableName = "stationReporting";
        public static final String _id = "_id";
        public static final String publicStationID = "publicStationID";
        public static final String startTime = "startTime";
        public static final String endTime = "endTime";
        public static final String lastUFID = "lastUFID";
        public static final String trackID = "trackID";
        public static final String isClosed = "isClosed";
        public static final String source_type = "source";
        public static final String previousPublicStationID = "previousPublicStationID";
        public static final String batchID = "batchID";
        public static final String type = "type";
        public static final String market = "market";
        public static final String output = "output";
    }

    public static class ImpressionReporting {
        public static final String tableName = "impressionReporting";
        public static final String _id = "_id";
        @Deprecated
        public static final String ufid = "ufid";
        public static final String createTime = "createTime";
        public static final String action = "action";
        public static final String source = "source";
        public static final String trackingID = "trackingID";
        public static final String stationID = "stationID";
        public static final String cardTrackingID = "cardTrackingID";
        public static final String teID = "teID";
        public static final String type = "type";
    }

    public static class ImpressionVisualReporting {
        public static final String tableName = "impressionVisualReporting";
        public static final String _id = "_id";
        public static final String trackingID = "trackingID";
        public static final String createTime = "createTime";
        public static final String source = "source";
        public static final String stationID = "stationID";
        public static final String cardTrackingID = "cardTrackingID";
        public static final String teID = "teID";
        public static final String batchID = "batchID";
        public static final String type = "type";
    }

    public static class LocationTracking {
        public static final String tableName = "LocationTracking";
        public static final String _id = "_id";
        public static final String createTime = "createTime";
        public static final String source = "source";
        public static final String action = "action";
        public static final String latitude = "latitude";
        public static final String longitude = "longitude";
        public static final String hAccuracy = "horizontalAccuracy";
        public static final String ipAddress = "ipAddress";
        public static final String locSpeed = "locSpeed";
    }

    /**
     * We need this table to save the session using start and end time based on the application usage
     * <p> Check implementation here
     * {@link com.nextradioapp.sdk.androidSDK.ext.DatabaseAdapter}
     * <p>
     * We update App session if end time is 2 min lesser than the current time
     */
    public static class AppSession {
        public static final String tableName = "AppSession";
        public static final String _id = "_id";
        public static final String startTime = "start_time";
        public static final String endTime = "end_time";
        public static final String uniqueId = "uniqueId";
        public static final String batchID = "batchID";
    }

    /**
     * We use this table to save device utc time and server offset,
     * Server offset is difference between device UTC time and Server UTC time.
     * We need this to determine device time for our reporting purpose
     *
     * @author gkondati
     */
    public static class UTCOffset {
        public static final String tableName = "utcOffset";
        public static final String _id = "_id";
        public static final String deviceUTCTime = "device_utc_time";
        public static final String serverOffset = "server_offset";
        public static final String batchID = "batchID";
    }

    public static class StreamFailure {
        public static final String tableName = "StreamFailure";
        public static final String _id = "_id";
        public static final String createTime = "create_time";
        public static final String streamingSource = "streaming_source";
        public static final String failureType = "failure_type";
        public static final String source = "source";
        public static final String batchID = "batchID";
    }

    public static class StreamOffset {
        public static final String tableName = "StreamOffset";
        public static final String _id = "_id";
        public static final String createTime = "create_time";
        public static final String streamingSource = "streaming_source";
        public static final String trackingID = "trackingID";
        public static final String batchID = "batchID";
    }

    public static class SearchQuery {
        public static final String tableName = "SearchQuery";
        public static final String _id = "_id";
        public static final String createTime = "create_time";
        public static final String searchString = "search_string";
        public static final String searchResults = "search_results";
        public static final String stationID = "stationID";
        public static final String batchID = "batchID";
    }

}