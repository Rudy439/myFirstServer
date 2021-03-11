package sk.kosickaakademia.matorudolf;

import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController

public class Controller {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getRoot () {
        return "<h1>Ahoj.</h1>";
    }

    @RequestMapping(path = "/hello/sk")
    public String getHelloSk() {
        return "<h1>Ahoj.Ako sa máš?</h1>";
    }

    @RequestMapping(path = "/hello")
    public String getHello() {
        return "<h1>Hello.How are you?</h1>";
    }

    @RequestMapping("/time")
    public String currentTime(){
        return new Date().toString();
    }


    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String getHi() {
        return "<h2>Hello.What are you doing?</h2>";
    }

    @RequestMapping(path = "/hi/test")
    public String getHiTest(@RequestBody String text) {
        return "<h2>This is page for test.Your name is: " + text;
    }


    @RequestMapping(path = "/hi/{username}")
    public String getHiWithName(@PathVariable String username){
        return "<h2>Hi "+username+ ". What are you doing?</h2>";
    }




}