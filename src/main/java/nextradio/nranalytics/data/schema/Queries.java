package nextradio.nranalytics.data.schema;

// @formatter:off
public class Queries {

	public final static String STATIONSWITHIMAGES =
			"SELECT *" +
			" FROM " + Tables.Stations.tableName;

	public final static String activityEvents_STATIONS =
			"select " + Tables.activityEvents.tableName + ".*," +
					Tables.Stations.frequency + ", " +
					Tables.Stations.frequencySubChannel + ", " +
					Tables.Stations.callLetters + ", " +
					Tables.Stations.genre + ", " +
					Tables.Stations.market + ", " +
					Tables.Stations.endpoint + ", " +
					Tables.Stations.slogan + ", " +
					Tables.Stations.tableName + "." + Tables.Stations.IsFavorite + ", " +
					Tables.Stations.artistList +
			" from " + Tables.activityEvents.tableName +
			" left outer join " + Tables.Stations.tableName +
			" on " + Tables.Stations.tableName + "." + Tables.Stations._id + "=" + Tables.activityEvents.stationID;

	public final static String LISTENINGHISTORY_STATIONS =
		 "select 	listeningHistory._id as _id,"
        +"              listeningHistory.isFavorite,"
		+"				stations.frequency,"
		+"				stations.frequencySubChannel,"
		+"				stations.actualFrequencySubChannel,"
		+"				stations.callLetters,"
		+"				stations.genre,"
		+"				stations.market,"
		+"				stations.headline,"
		+"				stations.publicStationID,"
		+"				stations.slogan,"
		+"				stations.IsFavorite,"
		+"				stations.artistList, "
		+"				stations.endpoint, "
        +"              stations.lastListened, "
        +"              stations.stringProviderName, "
        +"              stations.streamType, "
        +"              stations.streamUrl, "
        +"              stations.streamOffset, "
		+"				activityEvents.UFIDIdentifier,"
		+"				activityEvents.itemType,"
		+"				activityEvents.timestamp,"
		+"				activityEvents.artist,"
		+"				activityEvents.album,"
		+"				activityEvents.title,"
		+"				activityEvents.description,"
		+"				activityEvents.imageURL,"
		+"				activityEvents.imageURLHiRes,"
		+"				activityEvents.stationID,"
        +"				activityEvents.trackingID,"
		+"				listeningHistory.lastheard,"
		+"				listeningHistory.isDisliked,"
		+"				listeningHistory._id as listeningHistoryID,"
		+"				activityEvents._id as activityID"
		+"		from listeningHistory "
		+"		inner join activityEvents  "
		+"		on listeningHistory.eventID = activityEvents._id "
		+"		inner join stations  "
		+"		on stations._id = activityEvents.stationID ";

}
// @formatter:on