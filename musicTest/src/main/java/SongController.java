import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class SongController {
    RestTemplate restTemplate = new RestTemplate();
    String AUTHENTICATION_URL = "http://localhost:9194/user/get-all-songs";

    public void getSongs() {
        HttpHeaders authenticationHeaders = getHeaders();
        HttpEntity<String> entity = new HttpEntity<>(authenticationHeaders);
        ResponseEntity<Song[]> responseEntity = restTemplate.exchange(AUTHENTICATION_URL,
                HttpMethod.GET, entity, Song[].class);
        Song[] songs = responseEntity.getBody();
        System.out.println(songs[0].toString());
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
       // headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "*/*");
        headers.setBearerAuth(SecurityModel.getAccessToken());
        headers.setConnection("keep-alive");
        return headers;
    }
}
