package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;

/**
 * @RestController 와 @Controller의 차이
 * @Controller : Model 객체를 만들어 데이터를 담고 View를 반환해준다.
 *  -> 주로 View를 반환할때 사용
 * @RestController : 단순히 객체만을 반환하고, 객체 데이터는 JSON or XML형식으로 HTTP응답에 담아서 전송한다.
 *  -> @Controller 와 @ResponseBody가 합쳐진 형태
 */

/**
 * @RequestMapping : 사용자로부터 URI값을 지정해놓는 것
 */

@RestController
@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request") String header){
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/check")
    public String check(){
        return "Hi, there, This is a message from First Services";
    }
}
