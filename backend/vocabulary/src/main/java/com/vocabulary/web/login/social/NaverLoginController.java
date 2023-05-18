package com.vocabulary.web.login.social;

import com.vocabulary.domain.login.LoginService;
import com.vocabulary.domain.member.domain.Member;
import com.vocabulary.web.login.LoginForm;
import com.vocabulary.web.login.session.MemberSessionDto;
import com.vocabulary.web.login.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NaverLoginController {

    private final LoginService loginService;
    private final NaverLoginService naverLoginService;

    @Value("${naver.client-id}")
    private String CLIENT_ID;

    @GetMapping("/login/naver")
    public String naverLoginForm(HttpSession session) {
        SecureRandom random = new SecureRandom(); // 랜덤 state 값 생성
        String state = new BigInteger(130, random).toString();

        session.setAttribute("state",state);

        String apiURL = makeAuthorizeURL(state);
        return "redirect:" + apiURL;
    }

    @RequestMapping("/login/naver/callback")
    private String naverCallback(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String state = request.getParameter("state");
        String code = request.getParameter("code");

        Map<String, Object> accessToken = naverLoginService.getAccessToken(session, code, state);

        if (accessToken != null && !accessToken.equals("")) {
            session.setAttribute("currentUser", accessToken);
            session.setAttribute("currentAT", accessToken.get("access_token"));
            session.setAttribute("currentRT", accessToken.get("refresh_token"));
        }
        if (accessToken.get("access_token") == null) {
            log.info("access token 발급 실패");
            session.invalidate();
            return "redirect:/main";
        }

        String apiResult = naverLoginService.getApiResultFromNaver(session);
        LoginForm loginForm = resultParse(apiResult);

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (loginMember == null) {
            return "login/SignInForm";
        }

        MemberSessionDto sessionDto = new MemberSessionDto();
        sessionDto.setId(loginMember.getId());
        sessionDto.setRole(loginMember.getRole());

        session.setAttribute(SessionConst.LOGIN_MEMBER, sessionDto);
        return "redirect:/main";
    }

    private String makeAuthorizeURL(String state) {
        String redirectURI= URLEncoder.encode("http://localhost:8080/login/naver/callback", StandardCharsets.UTF_8);

        // 인증 URL 제작 (URL + redirectURI + state)
        String apiURL="https://nid.naver.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + CLIENT_ID +
                "&redirect_uri=" + redirectURI +
                "&state=" + state;

        return apiURL;
    }

    private LoginForm resultParse(String apiResult) {
        LoginForm loginForm = new LoginForm();

        // JSON 파싱
        JSONParser parser = new JSONParser();
        Object obj = null;
        try {
            obj = parser.parse(apiResult);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObj = (JSONObject) obj;
        JSONObject response_obj = (JSONObject)jsonObj.get("response");
        loginForm.setLoginId((String)response_obj.get("email"));
        loginForm.setPassword((String)response_obj.get("id"));

        return loginForm;
    }

}
