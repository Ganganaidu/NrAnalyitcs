package nextradio.nranalytics.interfaces;


import io.reactivex.Observable;
import nextradio.nranalytics.objects.DeviceRegistrationInfo;
import nextradio.nranalytics.objects.DeviceState;

public interface ITagStationAPIClient {

    Observable<DeviceRegistrationInfo> registerDevice(DeviceState deviceState);

    boolean report(String key, String serializedData, String batchName, String batchId, DeviceState deviceState);
}
