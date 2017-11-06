package nextradio.nranalytics.web;

import java.util.List;
import java.util.Map;

public class Response {
    public String body;
    public Exception exception;
    public Map<String, List<String>> headers;

    public Response(Exception exception) {
        this.exception = exception;
    }

    public Response(String body, Map<String, List<String>> headers) {
        this.body = body;
        this.headers = headers;
    }

    public String getHeader(String key) {
        if (headers == null) {
            return null;
        }

        List<String> headerList = headers.get(key);
        if (headerList.size() == 0) {
            return null;
        }

        return headerList.get(0);
    }

    public boolean wasSuccessful() {
        return exception == null;
    }

    public boolean failed() {
        return exception != null;
    }
}
