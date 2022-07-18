import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginController {
    RestTemplate restTemplate = new RestTemplate();
    String AUTHENTICATION_URL = "http://localhost:9194/login";
    public void getResponse() throws JsonProcessingException, UnsupportedEncodingException {
        HttpHeaders authenticationHeaders = getHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name","yoda133@mail.ru");
        map.add("password","1234");
        SecurityModel.setName("yoda133@mail.ru");
        SecurityModel.setPassword("1234");
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, authenticationHeaders);

        ResponseEntity<String> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
                HttpMethod.POST, entity, String.class);
        if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
            String token = authenticationResponse.getBody();
            ResponseToken responseToken = new ObjectMapper().readValue(token, ResponseToken.class);
            SecurityModel.setAccessToken(responseToken.getAccessToken());
            SecurityModel.setRefreshedToken(responseToken.getRefreshedToken());
            System.out.println(SecurityModel.toStr());
        }
        if(authenticationResponse.getStatusCode().equals(HttpStatus.UNAUTHORIZED)){
            System.out.println("access denied");
        }
    }
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "*/*");
        headers.setConnection("keep-alive");
        return headers;
    }
    private String getBody(final SecurityModel user) throws JsonProcessingException, UnsupportedEncodingException {
        return URLEncoder.encode(new ObjectMapper().writeValueAsString(user), StandardCharsets.UTF_8.toString());
    }
}
