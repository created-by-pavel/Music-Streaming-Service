import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;

public class Application {
    public static void main(String args[]) throws JsonProcessingException, UnsupportedEncodingException {
        LoginController loginController = new LoginController();
        loginController.getResponse();
        SongController songController = new SongController();
        songController.getSongs();
    }
}
