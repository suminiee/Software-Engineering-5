package com.vocabulary.web.login.social;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Service
public class NaverLoginService {

    @Value("${naver.client-id}")
    private String CLIENT_ID;

    @Value("${naver.cli-secret}")
    private String CLI_SECRET;

    /**
     * 네이버로부터 AccessToken값을 받아옴
     */
    public Map<String, Object> getAccessToken(HttpSession session, String code, String state) {
        String redirectURI = URLEncoder.encode("http://localhost:8080/login/naver/callback", StandardCharsets.UTF_8);

        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&" + // UrlBuilder 적용 필요
                "client_id=" + CLIENT_ID +
                "&client_secret=" + CLI_SECRET +
                "&redirect_uri=" + redirectURI +
                "&code=" + code +
                "&state=" + state;

        String res = requestToServer(apiURL);
        try {
            return new JSONParser(res).parseObject();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 네이버로부터 ApiResult 값을 받아옴
     */
    public String getApiResultFromNaver(HttpSession session) {
        String accessToken = session.getAttribute("currentAT").toString();
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        String headerStr = "Bearer " + accessToken;
        return requestToServer(apiURL, headerStr);
    }

    /**
     * 네이버 서버에 요청 (headerStr이 없는 경우)
     */
    private String requestToServer(String apiURL) {
        return requestToServer(apiURL, "");
    }

    /**
     * 네이버 서버에 요청
     */
    private String requestToServer(String apiURL, String headerStr) {
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (!headerStr.equals("")) {
                con.setRequestProperty("Authorization", headerStr);
            }

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                log.info("error");
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            return (responseCode == 200) ? res.toString() : null;
        }
        catch (IOException e) {
            log.info("error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 토큰 삭제 - 회원탈퇴
     */
    private void deleteToken(HttpServletRequest request) {
        String accessToken = request.getSession().getAttribute("currentAT").toString();
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&" +
                "client_id=" + CLIENT_ID +
                "&client_secret=" + CLI_SECRET +
                "&access_token=" + accessToken +
                "&service_provider=NAVER";
        requestToServer(apiURL);
    }

}
