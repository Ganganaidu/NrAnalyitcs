package nextradio.nranalytics.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import nextradio.nranalytics.data.schema.Tables;


public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "applicationdata";

    private static final int DATABASE_VERSION = 141;

    private static CustomSQLiteOpenHelper mInstance;

    public static CustomSQLiteOpenHelper getInstance(Context ctx) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mInstance == null) {
            mInstance = new CustomSQLiteOpenHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    private static String[] PatchesFrom119 = {
            "create table " + Tables.StationReporting.tableName + " (" +
                    Tables.StationReporting._id + " integer primary key autoincrement, " +
                    Tables.StationReporting.endTime + " datetime not null, " +
                    Tables.StationReporting.isClosed + " int not null default 0, " +
                    Tables.StationReporting.lastUFID + " text, " +
                    Tables.StationReporting.publicStationID + " int not null, " +
                    Tables.StationReporting.startTime + " datetime not null)"
    };
    private static String[] PatchesFrom122 = {
            "create table " + Tables.ImpressionReporting.tableName + " (" +
                    Tables.ImpressionReporting._id + " integer primary key autoincrement, " +
                    Tables.ImpressionReporting.source + " integer not null, " +
                    Tables.ImpressionReporting.ufid + " text, " +
                    Tables.ImpressionReporting.action + " integer not null, " +
                    Tables.ImpressionReporting.createTime + " datetime not null)"
    };
    private static String[] PatchesFrom123 = {"ALTER TABLE activityEvents ADD COLUMN primaryAction text DEFAULT \"\";"};

    private static String[] PatchesFrom124 = {
            "create table " + Tables.ImpressionVisualReporting.tableName + " (" +
                    Tables.ImpressionVisualReporting._id + " integer primary key autoincrement, " +
                    Tables.ImpressionVisualReporting.source + " integer not null, " +
                    Tables.ImpressionVisualReporting.trackingID + " text, " +
                    Tables.ImpressionVisualReporting.createTime + " datetime not null)"
    };

    private static String[] PatchesFrom125 = {
            "alter table " + Tables.activityEvents.tableName +
                    " ADD COLUMN '" + Tables.activityEvents.trackingID + "'"
    };
    private static String[] PatchesFrom126 = {
            "alter table " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.fromNoData + "' int default 0",
            "DROP INDEX IF EXISTS iStationLookup",
            "DROP INDEX IF EXISTS iPublicStationID"
    };
    private static String[] PatchesFrom127 = {
            "CREATE UNIQUE INDEX iStationLookup ON stations(frequency, frequencySubChannel, callLetters, publicStationID, fromNoData);"
    };
    private static String[] PatchesFrom128 = {
            // flip the records. the column was mis-labeled in V1
            "update listeningHistory set isFromNowPlaying = not isFromNowPlaying"
    };
    private static String[] PatchesFrom129 = {
            "alter table " + Tables.ImpressionVisualReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.stationID + "'"
    };
    private static String[] PatchesFrom130 = {
            "delete from " + Tables.ImpressionReporting.tableName,
            "alter table " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionReporting.trackingID + "'",
            "alter table " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionReporting.stationID + "'",

            "alter table " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.trackingID + "'",
    };

    private static String[] PatchesFrom131 = {
            "alter table " + Tables.activityEvents.tableName +
                    " ADD COLUMN '" + Tables.activityEvents.teID + "'",

            //impressionVisualReporting
            "ALTER TABLE " + Tables.ImpressionVisualReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.teID + "'",
            "ALTER TABLE " + Tables.ImpressionVisualReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.cardTrackingID + "'",

            //impressionReporting
            "ALTER TABLE " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionReporting.teID + "'",
            "ALTER TABLE " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionReporting.cardTrackingID + "'",

            "ALTER TABLE " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.LocationTracking.latitude + "'",
            "ALTER TABLE " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.LocationTracking.longitude + "'",

            //Creating table for location tracking
            "create table " + Tables.LocationTracking.tableName + " (" +
                    Tables.LocationTracking._id + " integer primary key autoincrement, " +
                    Tables.LocationTracking.source + " int not null default 0, " +
                    Tables.LocationTracking.action + " int not null default 0, " +
                    Tables.LocationTracking.longitude + " text, " +
                    Tables.LocationTracking.latitude + " text, " +
                    Tables.ImpressionReporting.createTime + " datetime not null)"

    };

    private static String[] PatchesFrom132 = {

            "ALTER TABLE " + Tables.ImpressionVisualReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.batchID + "'",

            "ALTER TABLE " + Tables.StationReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.batchID + "'",

            "ALTER TABLE " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.batchID + "'",

            "ALTER TABLE " + Tables.LocationTracking.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.batchID + "'",

            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.batchID + "'",

    };

    private static String[] PatchesFrom133 = {

            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.formatGroup + "'",

    };


    //changes after build number > 1559
    private static String[] PatchesFrom134 = {

            "ALTER TABLE " + Tables.LocationTracking.tableName +
                    " ADD COLUMN '" + Tables.LocationTracking.hAccuracy + "'",
            "ALTER TABLE " + Tables.LocationTracking.tableName +
                    " ADD COLUMN '" + Tables.LocationTracking.ipAddress + "'",
            "ALTER TABLE " + Tables.LocationTracking.tableName +
                    " ADD COLUMN '" + Tables.LocationTracking.locSpeed + "'",

            "ALTER TABLE " + Tables.StationReporting.tableName +
                    " ADD COLUMN '" + Tables.StationReporting.previousPublicStationID + "'",
            "ALTER TABLE " + Tables.StationReporting.tableName +
                    " ADD COLUMN '" + Tables.StationReporting.source_type + "'",
            "ALTER TABLE " + Tables.StationReporting.tableName +
                    " ADD COLUMN '" + Tables.StationReporting.trackID + "'",

            //table for location tracking
            "create table " + Tables.AppSession.tableName + " (" +
                    Tables.AppSession._id + " integer primary key autoincrement, " +
                    Tables.AppSession.startTime + " int not null default 0, " +
                    Tables.AppSession.endTime + " text, " +
                    Tables.AppSession.batchID + " text, " +
                    Tables.AppSession.uniqueId + " text not null)"
    };

    private static String[] PatchesFrom135 = {
            //////// stations
            "create table " + Tables.NewsItems.tableName + " (" +
                    Tables.NewsItems._id + " integer primary key autoincrement, " +
                    Tables.NewsItems.id + " text not null, " +
                    Tables.NewsItems.trackId + " text, " +
                    Tables.NewsItems.body + " text, " +
                    Tables.NewsItems.callToActionData + " text, " +
                    Tables.NewsItems.callToActionImageUrl + " text, " +
                    Tables.NewsItems.callToActionText + " text, " +
                    Tables.NewsItems.callToActionType + " text, " +
                    Tables.NewsItems.cardType + " text, " +
                    Tables.NewsItems.createdAt + " datetime, " +
                    Tables.NewsItems.expiresAt + " datetime, " +
                    Tables.NewsItems.hasBeenRead + " integer NOT NULL DEFAULT " + Tables.FALSE + ", " +
                    Tables.NewsItems.hasBeenNotified + " integer NOT NULL DEFAULT " + Tables.FALSE + ", " +
                    Tables.NewsItems.imageUrl + " text, " +
                    Tables.NewsItems.iconUrl + " text, " +
                    Tables.NewsItems.newsType + " text, " +
                    Tables.NewsItems.newsTypeText + " text, " +
                    Tables.NewsItems.subBody + " text, " +
                    Tables.NewsItems.subtitle + " text, " +
                    Tables.NewsItems.title + " text, " +
                    "UNIQUE (" + Tables.NewsItems.id + ") ON CONFLICT IGNORE);",

            "create table " + Tables.NewsFeedReporting.tableName + " (" +
                    Tables.NewsFeedReporting._id + " integer primary key autoincrement, " +
                    Tables.NewsFeedReporting.foreignId + " text not null, " +
                    Tables.NewsFeedReporting.action + " integer , " +
                    Tables.NewsFeedReporting.utcDateTime + " text, " +
                    Tables.NewsFeedReporting.trackID + " text, " +
                    Tables.NewsFeedReporting.batchID + " text );"
    };

    private static String[] PatchesFrom136 = {
            "ALTER TABLE " + Tables.NewsItems.tableName +
                    " ADD COLUMN '" + Tables.NewsItems.hasBeenDeleted + "' " +
                    " integer NOT NULL DEFAULT " + Tables.FALSE
    };

    private static String[] PatchesFrom137 = {
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.streamMetaKey + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.streamOffset + "'" +
                    " integer NOT NULL DEFAULT " + Tables.Stations.DEFAULT_OFFSET,
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.streamProviderName + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.streamType + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.streamUrl + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.phoneNumber + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.websiteUrl + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.additionalContent + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.city + "'" +
                    " text",
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.isLocal + "'" +
                    " integer NOT NULL DEFAULT " + Tables.FALSE,

            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.IsFavoritedFromSearch + "'" +
                    " integer NOT NULL DEFAULT " + Tables.FALSE,

            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.hasRecentEvents + "'" +
                    " integer NOT NULL DEFAULT " + Tables.FALSE,

            "ALTER TABLE " + Tables.StationReporting.tableName +
                    " ADD COLUMN '" + Tables.StationReporting.type + "'" +
                    " integer NOT NULL DEFAULT 0",

            "ALTER TABLE " + Tables.StationReporting.tableName +
                    " ADD COLUMN '" + Tables.StationReporting.market + "'" +
                    " integer NOT NULL DEFAULT 0",

            "ALTER TABLE " + Tables.StationReporting.tableName +
                    " ADD COLUMN '" + Tables.StationReporting.output + "'" +
                    " integer NOT NULL DEFAULT 0",

            "ALTER TABLE " + Tables.ImpressionReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionReporting.type + "'" +
                    " integer NOT NULL DEFAULT 0",

            "ALTER TABLE " + Tables.ImpressionVisualReporting.tableName +
                    " ADD COLUMN '" + Tables.ImpressionVisualReporting.type + "'" +
                    " integer NOT NULL DEFAULT 0",

            "create table " + Tables.UTCOffset.tableName + " (" +
                    Tables.UTCOffset._id + " integer primary key autoincrement, " +
                    Tables.UTCOffset.deviceUTCTime + " text, " +
                    Tables.UTCOffset.serverOffset + " text, " +
                    Tables.UTCOffset.batchID + " text );"
    };

    private static String[] PatchesFrom138 = {
            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.hasStreamingMetaData + "'" +
                    " integer NOT NULL DEFAULT " + Tables.FALSE,

            "ALTER TABLE " + Tables.Stations.tableName +
                    " ADD COLUMN '" + Tables.Stations.actualFrequencySubChannel + "'" +
                    " integer NOT NULL DEFAULT 0 "
    };

    private static String[] PatchesFrom139 = {
            "create table " + Tables.SearchQuery.tableName + " (" +
                    Tables.SearchQuery._id + " integer primary key autoincrement, " +
                    Tables.SearchQuery.searchString + " text, " +
                    Tables.StreamOffset.createTime + " datetime, " +
                    Tables.SearchQuery.searchResults + " integer, " +
                    Tables.SearchQuery.stationID + " integer, " +
                    Tables.SearchQuery.batchID + " text );",

            "create table " + Tables.StreamFailure.tableName + " (" +
                    Tables.StreamFailure._id + " integer primary key autoincrement, " +
                    Tables.StreamFailure.streamingSource + " text, " +
                    Tables.StreamFailure.failureType + " integer, " +
                    Tables.StreamFailure.source + " integer, " +
                    Tables.StreamFailure.createTime + " datetime, " +
                    Tables.SearchQuery.batchID + " text );",

            "create table " + Tables.StreamOffset.tableName + " (" +
                    Tables.StreamOffset._id + " integer primary key autoincrement, " +
                    Tables.StreamOffset.streamingSource + " text, " +
                    Tables.StreamOffset.trackingID + " text, " +
                    Tables.StreamOffset.createTime + " datetime, " +
                    Tables.StreamOffset.batchID + " text );"
    };

    private static String[] PatchesFrom140 = {
            "ALTER TABLE " + Tables.listeningHistory.tableName +
                    " ADD COLUMN '" + Tables.listeningHistory.isDisLiked + "'" +
                    " integer default 0",
    };

    private static String[] TableCreates = {
            //@formatter:off

            "create table chipLog (" +
                    "_id integer primary key autoincrement, " +
                    "logEntry text, " +
                    "timestamp datetime not null);",

            ///////// activityEvents
            "create table " + Tables.activityEvents.tableName + " (" +
                    Tables.activityEvents._id + " integer primary key autoincrement, " +
                    Tables.activityEvents.UFIDIdentifier + " string, " +
                    Tables.activityEvents.itemType + " integer, " +
                    Tables.activityEvents.timestamp + " datetime not null, " +
                    Tables.activityEvents.artist + " text, " +
                    Tables.activityEvents.album + "  text, " +
                    Tables.activityEvents.title + " text, " +
                    Tables.activityEvents.description + " text, " +
                    Tables.activityEvents.imageURL + " text, " +
                    Tables.activityEvents.imageURLHiRes + " text, " +
                    Tables.activityEvents.stationID + " integer not null); ",

            "create table " + Tables.listeningHistory.tableName + " (" +
                    Tables.activityEvents._id + " integer primary key autoincrement, " +
                    Tables.listeningHistory.lastheard + " datetime not null, " +
                    Tables.listeningHistory.savedDate + " datetime, " +
                    Tables.listeningHistory.eventID + " integer, " +
                    Tables.listeningHistory.isFromNowPlaying + " integer, " +
                    Tables.listeningHistory.isFavorite + " integer default 0);",

            "create table " + Tables.listeningActivityAds.tableName + " (" +
                    Tables.listeningActivityAds._id + " integer primary key autoincrement, " +
                    Tables.listeningActivityAds.IsFavorite + " integer default 0, " +
                    Tables.listeningActivityAds.eventID + " integer not null, " +
                    Tables.listeningActivityAds.adType + " text not null, " +
                    Tables.listeningActivityAds.field0 + " text, " +
                    Tables.listeningActivityAds.field1 + "  text, " +
                    Tables.listeningActivityAds.field2 + " text, " +
                    Tables.listeningActivityAds.field3 + "  text, " +
                    Tables.listeningActivityAds.field4 + " text, " +
                    Tables.listeningActivityAds.field5 + " text);",

            //////// stations
            "create table " + Tables.Stations.tableName + " (" +
                    Tables.Stations._id + " integer primary key autoincrement, " +
                    Tables.Stations.publicStationID + " int not null, " +
                    Tables.Stations.frequency + " int not null, " +
                    Tables.Stations.frequencySubChannel + " int, " +
                    Tables.Stations.callLetters + " text not null, " +
                    Tables.Stations.genre + " text, " +
                    Tables.Stations.market + " text, " +
                    Tables.Stations.endpoint + " text, " +
                    Tables.Stations.lastListened + " bigint not null default 0, " +
                    Tables.Stations.slogan + " text default '', " +
                    Tables.Stations.imageURL + " text, " +
                    Tables.Stations.imageURLHiRes + " text, " +
                    Tables.Stations.headline + " text, " +
                    Tables.Stations.headlineText + " text, " +
                    Tables.Stations.piCode + " text, " +
                    Tables.Stations.countryCode + " text, " +
                    Tables.Stations.IsValid + " int not null default 1, " +
                    Tables.Stations.IsFavorite + " int default 0, " +
                    Tables.Stations.artistList + " text);",
            "CREATE UNIQUE INDEX iStationLookup ON stations(frequency, frequencySubChannel, callLetters);",
            "CREATE INDEX iPublicStationID ON stations(publicStationID);",

            //@formatter:on
    };


    // @formatter:on
    private CustomSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            for (String sql : TableCreates) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom119) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom122) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom123) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom124) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom125) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom126) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom127) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom128) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom129) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom130) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom131) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom132) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom133) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom134) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom135) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom136) {
                database.execSQL(sql);
            }
            for (String sql : PatchesFrom137) {
                database.execSQL(sql);
            }

            for (String sql : PatchesFrom138) {
                database.execSQL(sql);
            }

            for (String sql : PatchesFrom139) {
                database.execSQL(sql);
            }

            for (String sql : PatchesFrom140) {
                database.execSQL(sql);
            }

        } catch (Exception ex) {
            Log.e("HD_RADIO", ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d("upgrade", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        if (oldVersion < 119) {
            database.execSQL("DROP TABLE IF EXISTS userSaved");
            database.execSQL("DROP TABLE IF EXISTS " + Tables.Stations.tableName);
            database.execSQL("DROP TABLE IF EXISTS " + Tables.listeningActivityAds.tableName);
            database.execSQL("DROP TABLE IF EXISTS " + Tables.listeningHistory.tableName);
            database.execSQL("DROP TABLE IF EXISTS chipLog");
            database.execSQL("DROP TABLE IF EXISTS " + Tables.Stations.tableName);
            database.execSQL("DROP TABLE IF EXISTS " + Tables.activityEvents.tableName);
            database.execSQL("DROP INDEX IF EXISTS iStationLookup");
            onCreate(database);
        } else {
            if (oldVersion <= 119) {
                for (String sql : PatchesFrom119) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 122) {
                for (String sql : PatchesFrom122) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 123) {
                for (String sql : PatchesFrom123) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 124) {
                for (String sql : PatchesFrom124) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 125) {
                for (String sql : PatchesFrom125) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 126) {
                for (String sql : PatchesFrom126) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 127) {
                for (String sql : PatchesFrom127) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 128) {
                for (String sql : PatchesFrom128) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 129) {
                for (String sql : PatchesFrom129) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 130) {
                for (String sql : PatchesFrom130) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 131) {
                for (String sql : PatchesFrom131) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 132) {
                for (String sql : PatchesFrom132) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 133) {
                for (String sql : PatchesFrom133) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 134) {
                for (String sql : PatchesFrom134) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 135) {
                for (String sql : PatchesFrom135) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 136) {
                for (String sql : PatchesFrom136) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 137) {
                for (String sql : PatchesFrom137) {
                    database.execSQL(sql);
                }
            }

            if (oldVersion <= 138) {
                for (String sql : PatchesFrom138) {
                    database.execSQL(sql);
                }
            }

            if (oldVersion <= 139) {
                for (String sql : PatchesFrom139) {
                    database.execSQL(sql);
                }
            }
            if (oldVersion <= 140) {
                for (String sql : PatchesFrom140) {
                    database.execSQL(sql);
                }
            }
        }
    }

    public static void clearInstance() {
        mInstance = null;
    }
}
