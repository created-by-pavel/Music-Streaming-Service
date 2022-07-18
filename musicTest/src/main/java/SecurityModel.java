
public class SecurityModel {
    private static String name;
    private static String password;
    private static String accessToken;
    private static String refreshedToken;

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getRefreshedToken() {
        return refreshedToken;
    }

    public static void setName(String name) {
        SecurityModel.name = name;
    }

    public static void setPassword(String password) {
        SecurityModel.password = password;
    }

    public static void setAccessToken(String accessToken) {
        SecurityModel.accessToken = accessToken;
    }

    public static void setRefreshedToken(String refreshedToken) {
        SecurityModel.refreshedToken = refreshedToken;
    }

    public static String toStr() {
        return "SecurityToken{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshedToken='" + refreshedToken + '\'' +
                '}';
    }
}
