package search.planetime.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(){
        log.info("-------log-------");
        return "index";
    }

    @RequestMapping("/home1")
    public String test1(){
        return "home/home1";
    }

    @RequestMapping("/home2")
    public String test2(){
        return "home/home2";
    }

    @RequestMapping("/home3")
    public String home3(){
        return "home/home3";
    }


    @RequestMapping("/login")
    public String login(){
        return "login/login";
    }

    @RequestMapping("/join")
    public String join(){
        return "login/join";
    }

}
