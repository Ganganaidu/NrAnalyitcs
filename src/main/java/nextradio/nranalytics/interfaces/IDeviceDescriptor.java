package nextradio.nranalytics.interfaces;


import nextradio.nranalytics.objects.DeviceState;

public interface IDeviceDescriptor {

    DeviceState getDeviceDescription(String adId, String radioSourceName);

    void setDeviceID(String deviceID);

    String getDeviceVersionCode();
}
