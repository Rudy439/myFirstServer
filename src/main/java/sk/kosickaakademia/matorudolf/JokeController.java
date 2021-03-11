package sk.kosickaakademia.matorudolf;

import jdk.internal.access.JavaSecurityAccess;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JokeController {

    String joke1 = "How does Moses make tea? He brews.";
    String joke2 = "What kind of exercise do lazy people do? Diddly-squats.";
    String joke3 = "What do you call a magic dog? A labracadabrador.";
    List<String> list  = new ArrayList<>();

    public JokeController(){
        list.add(joke1);
        list.add(joke2);
        list.add(joke2);

    }


    @GetMapping ("/jokes")
    public ResponseEntity<String> getJokes(){

        JSONObject obj = new JSONObject();
        JSONArray jArray = new JSONArray();
       for (String s: list)
           jArray.add(s);
        JavaSecurityAccess.ProtectionDomainCache object;
        obj.put("jokes",jArray);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
    }
    @GetMapping ("/joke/{id}")
    public ResponseEntity<String> getJokeById(@PathVariable Integer id){
        JSONObject obj = new JSONObject();
        int status;
        if(id<1  || id > list.size()) {
            obj.put("error", "Invalid id");
            status = 404;
        }else {


            obj.put("joke", list.get(id - 1));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
    }
    }

