package nextradio.nranalytics.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nextradio.nranalytics.interfaces.IRestClient;


/**
 * <p>
 * This class was copied from an example posted at: http://lukencode
 * .com/2010/04/27/calling-web-services-in-android-using-httpclient/
 * </p>
 * <p>
 * It has been heavily adapted for this application
 */
public class RestClient2 implements IRestClient {
    public static final String HEADER_LONGITUDE = "longitude";
    public static final String HEADER_LATITUDE = "latitude";
    public static final String INCLUDE_DEMO = "demo";

    Map<String, String> params = new HashMap<>();
    Map<String, String> headers = new HashMap<>();
    private String mURL;
    private String requestBody;
    private boolean isTestRunning;

    public RestClient2(String mHostURL, boolean withTestCase) {
        mURL = mHostURL;
        isTestRunning = withTestCase;
    }

    @Override
    public void ClearBody() {
        this.requestBody = null;
    }

    @Override
    public String getUrl() {
        return mURL;
    }

    @Override
    public void AddHeader(String name, String value) {
        headers.put(name, value);
    }

    @Override
    public void ClearHeaders() {
        headers.clear();
    }

    @Override
    public void AddParam(String name, Object value) {
        params.put(name, value.toString());
    }

    @Override
    public void AddParam(String name, String value) {
        params.put(name, value);
    }

    @Override
    public void ClearParams() {
        params.clear();
    }

    @Override
    public void setBody(String requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public String ExecuteForString(RequestMethod method) throws InvalidHTTPStatusException {
        return ExecuteForString(method, 200);
    }

    @Override
    public String ExecuteForString(RequestMethod method, int expectedStatusCode) throws InvalidHTTPStatusException {
        return Execute(method, expectedStatusCode).body;
    }

    @Override
    public Response Execute(RequestMethod method) throws InvalidHTTPStatusException {
        return Execute(method, 200);
    }

    @Override
    public Response Execute(RequestMethod method, int expectedStatusCode) throws InvalidHTTPStatusException {
        if (method == RequestMethod.POST) {

            HttpRequest request = HttpRequest.post(mURL);
            setUserAgent(request);
            request = request.connectTimeout(10000).readTimeout(10000).ignoreCloseExceptions(true).headers(headers);

            return buildRequest(request, expectedStatusCode);

        } else if (method == RequestMethod.PUT) {

            HttpRequest request = HttpRequest.put(mURL);
            setUserAgent(request);
            request = request.connectTimeout(10000).readTimeout(10000).ignoreCloseExceptions(true).headers(headers);

            return buildRequest(request, expectedStatusCode);

        } else {
            HttpRequest request = HttpRequest.get(mURL).connectTimeout(10000).readTimeout(10000).ignoreCloseExceptions(true).headers(headers);
            setUserAgent(request);
            int receivedStatusCode = request.code();
            String body = request.body();
            Map<String, List<String>> headers = request.headers();
            if (receivedStatusCode == expectedStatusCode) {
                return new Response(body, headers);
            }
            throw new InvalidHTTPStatusException("didn't get expected status: " + expectedStatusCode + " actually got " + receivedStatusCode + "\n\nMessage: " + body);
        }
    }

    private void setUserAgent(HttpRequest request) {
        if (isTestRunning) {
            return;
        }
        String userAgent = System.getProperty("http.agent");
        //request.userAgent(userAgent + " NextRadio/" + PreferenceStorage.getInstance().getVersionName());
    }

    private Response buildRequest(HttpRequest request, int expectedStatusCode) throws InvalidHTTPStatusException {
        if (params != null && params.size() > 0) {
            request = request.form(params);
        } else if (requestBody != null) {
            request.contentType(HttpRequest.CONTENT_TYPE_JSON, HttpRequest.CHARSET_UTF8);
            request = request.send(requestBody);
        }

        int receivedStatusCode = request.code();
        if (receivedStatusCode == expectedStatusCode) {
            String body = request.body();
            Map<String, List<String>> headers = request.headers();
            return new Response(body, headers);
        }
        String errorMessage = "didn't get expected status: " + expectedStatusCode + " actually got " + receivedStatusCode;
        String responseBody = request.body();
        if (responseBody != null) {
            errorMessage = errorMessage + " body:" + responseBody;
        }
        throw new InvalidHTTPStatusException(errorMessage);
    }

    @Override
    public void setURL(String URL) {
        mURL = URL;
    }
}