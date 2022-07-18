package ru.crbpavel.musicapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.crbpavel.musicapp.model.ResponseToken;
import ru.crbpavel.musicapp.model.SecurityModel;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField login;

    @FXML
    PasswordField password;

    private String AUTHENTICATION_URL = "http://localhost:9194/login";
    private String GET_USER_URL = "http://localhost:9194/user/find-user-by-security-model";
    private RestTemplate restTemplate = new RestTemplate();

    public void login(ActionEvent event) throws IOException {
        if (getResponse()) {
            switchToSampleScene(event);
        } else {
            System.out.println("access denied");
        }
    }

    private void switchToSampleScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private Boolean getResponse() throws JsonProcessingException {
        HttpHeaders authenticationHeaders = getHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", login.getText());
        map.add("password", password.getText());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, authenticationHeaders);

        ResponseEntity<String> authenticationResponse = restTemplate.exchange(AUTHENTICATION_URL,
                HttpMethod.POST, entity, String.class);

        if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
            SecurityModel.setLogin(login.getText());
            SecurityModel.setPassword(password.getText());
            ResponseToken responseToken = new ObjectMapper().readValue(authenticationResponse.getBody(), ResponseToken.class);
            SecurityModel.setAccessToken(responseToken.getAccessToken());
            SecurityModel.setRefreshedToken(responseToken.getRefreshedToken());
            SecurityModel.setUsername(getUserName());
            return true;
        } else {
            return false;
        }
    }

    private String getUserName() {
        HttpHeaders authenticationHeaders = getHeaders2();
        HttpEntity<String> entity = new HttpEntity<>(authenticationHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(GET_USER_URL,
                HttpMethod.GET, entity, String.class);
        String username = responseEntity.getBody();
        return username;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Accept", "*/*");
        headers.setConnection("keep-alive");
        return headers;
    }

    public HttpHeaders getHeaders2() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.setBearerAuth(SecurityModel.getAccessToken());
        headers.setConnection("keep-alive");
        return headers;
    }
}
