package web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SimpleController {

    @RequestMapping(value = "/requestMapping",method = RequestMethod.GET, produces = "application/json")
    public List<String> test_json_binding(){
        return Arrays.asList("I", "Master", "Spring");
    }

    @GetMapping("/home")
    public String createFile(){
        System.out.println("Creating File");
        return "home";
    }

}