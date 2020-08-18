package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DataController {
    @RequestMapping("/")
    public String date() {
        return (new Date()).toString();
    }
}
