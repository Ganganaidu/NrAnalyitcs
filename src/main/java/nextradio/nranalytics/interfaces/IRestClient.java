package nextradio.nranalytics.interfaces;


import nextradio.nranalytics.web.RequestMethod;
import nextradio.nranalytics.web.Response;

/**
 * <p>
 * Interface for making REST-based calls for TagStation web services and images.
 * </p>
 * <p>
 * <p>
 * This class was copied from an example posted at: http://lukencode
 * .com/2010/04/27/calling-web-services-in-android-using-httpclient/
 * </p>
 * <p>
 * It has been heavily adapted for this application
 */
public interface IRestClient {

    void AddHeader(String name, String value);

    void ClearHeaders();

    void AddParam(String name, Object value);

    void AddParam(String name, String value);

    void ClearParams();

    void setBody(String body);

    String ExecuteForString(RequestMethod method) throws InvalidHTTPStatusException;

    String ExecuteForString(RequestMethod post, int expectedStatusCode) throws InvalidHTTPStatusException;

    Response Execute(RequestMethod method) throws InvalidHTTPStatusException;

    Response Execute(RequestMethod post, int expectedStatusCode) throws InvalidHTTPStatusException;

    void setURL(String URL);

    void ClearBody();

    String getUrl();

    class InvalidHTTPStatusException extends Exception {
        public InvalidHTTPStatusException(String errorMessage) {
            super(errorMessage);
        }
    }
}