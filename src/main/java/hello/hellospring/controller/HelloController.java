package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("Hello")
    public String Hello(Model model){
            model.addAttribute("data","hello!!");
            return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-templates";
    }
    
    @GetMapping("hello-string")
    // http통신 프로토콜 헤더,바디부
    // 바디부에 이 내용을 직접 넣어주겠다는 뜻
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    // API, 객체가 오면 JSON 방식으로 데이터를 만들어서 http응답에 반환하겠다 -> 이게 디폴트
    // @ResponseBody 어노테이션이 있다면, 객체를 HttpMessageConverter로 보내고
    // HttpMessageConverter에서 조건을 따진다
    // 1. 단순 문자열이라면 StringConverter가 동작
    // 2. 객체라면 JsonConverter가 동작 -> Json스타일(key, value)로 바꿔서 보내준다
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 문자열이 아닌 객체 넘겨줌
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
