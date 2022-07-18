import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;

public class ResponseToken {
    private String accessToken;
    private String refreshedToken;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshedToken() {
        return refreshedToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshedToken(String refreshedToken) {
        this.refreshedToken = refreshedToken;
    }
}
