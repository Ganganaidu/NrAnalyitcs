package nextradio.nranalytics.objects;

/**
 * This class describes the user's device
 * <p/>
 * Many of the member variables are assigned using the properties from the
 * "Build" class of Android.
 */
public class DeviceState {
    /**
     * flag to indicate if the device display is active
     */
    private boolean isDisplayOn;
    /**
     * The consumer-visible brand with which the product/hardware will be
     * associated, if any."
     */
    private String brand;
    /**
     * The name of the industrial design.
     */
    private String device;
    /**
     * the creator of the handset
     */
    private String manufacturer;
    /**
     * The end user-visible name of the product
     */
    private String model;
    /**
     * the carrier to which the device is registered: Sprint, Verizon, etc
     */
    private String carrier;
    /**
     * miscellaneous information
     */
    private String otherInfo;
    /**
     * country code of the device
     */
    private String countryCode;
    /**
     * device selected language
     */
    private String language;
    /**
     * Fm api source name if available else send "unknown"
     */
    private String fmAPI;
    /**
     * advertisement id
     */
    private String adID;
    /**
     * we get this once we register with the device, and we use this to update device
     */
    private String tagStationID;
    /**
     * current application version
     */
    private String appVersion;
    /**
     * system version
     */
    private String systemVersion;

    public boolean isDisplayOn() {
        return isDisplayOn;
    }

    public void setDisplayOn(boolean displayOn) {
        isDisplayOn = displayOn;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getFmAPI() {
        return fmAPI;
    }

    public void setFmAPI(String fmAPI) {
        this.fmAPI = fmAPI;
    }

    public String getAdID() {
        return adID;
    }

    public void setAdID(String adID) {
        this.adID = adID;
    }

    public String getTagStationID() {
        return tagStationID;
    }

    public void setTagStationID(String tagStationID) {
        this.tagStationID = tagStationID;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    private boolean getIsDisplayOn() {
        return isDisplayOn;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getUpdateString() {
        return brand +
                device +
                manufacturer +
                model +
                otherInfo +
                fmAPI +
                adID +
                appVersion +
                systemVersion;
    }

    public boolean equals() {
        return !(brand == null || brand.length() == 0)
                && !(manufacturer == null || manufacturer.length() == 0)
                && !(countryCode == null || countryCode.length() == 0)
                && !(language == null || language.length() == 0)
                && !(appVersion == null || appVersion.length() == 0)
                && !(systemVersion == null || systemVersion.length() == 0);
    }

    @Override
    public String toString() {
        return "DeviceState{" +
                "isDisplayOn=" + isDisplayOn +
                ", brand='" + brand + '\'' +
                ", device='" + device + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", carrier='" + carrier + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", language='" + language + '\'' +
                ", fmAPI='" + fmAPI + '\'' +
                ", adID='" + adID + '\'' +
                ", tagStationID='" + tagStationID + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                '}';
    }
}
