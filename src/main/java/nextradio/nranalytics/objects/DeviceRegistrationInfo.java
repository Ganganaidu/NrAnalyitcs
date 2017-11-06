package nextradio.nranalytics.objects;


public class DeviceRegistrationInfo {

    private String TSD;
    private String cachingGroup;
    private String adGroup;
    private String feedUser;
    private boolean isRegisteringFirstTime;

    public String getTSD() {
        return TSD;
    }

    public void setTSD(String TSD) {
        this.TSD = TSD;
    }

    public String getCachingGroup() {
        return cachingGroup;
    }

    public void setCachingGroup(String cachingGroup) {
        this.cachingGroup = cachingGroup;
    }

    public String getAdGroup() {
        return adGroup;
    }

    public void setAdGroup(String adGroup) {
        this.adGroup = adGroup;
    }

    public String getFeedUser() {
        return feedUser;
    }

    public void setFeedUser(String feedUser) {
        this.feedUser = feedUser;
    }

    public boolean isRegisteringFirstTime() {
        return isRegisteringFirstTime;
    }

    public void setRegisteringFirstTime(boolean registeringFirstTime) {
        isRegisteringFirstTime = registeringFirstTime;
    }

    @Override
    public String toString() {
        return "DeviceRegistrationInfo{" +
                "TSD='" + TSD + '\'' +
                ", cachingGroup='" + cachingGroup + '\'' +
                ", adGroup='" + adGroup + '\'' +
                ", feedUser='" + feedUser + '\'' +
                '}';
    }
}
