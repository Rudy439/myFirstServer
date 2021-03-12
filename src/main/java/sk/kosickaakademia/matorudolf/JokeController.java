package sk.kosickaakademia.matorudolf;





import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class JokeController {

    String joke1 = "How does Moses make tea? Hebrews it.";
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
    @GetMapping ("/joke")
    public ResponseEntity<String> getRandomJoke(@PathVariable Integer id){

        JSONObject obj = new JSONObject();
        int status;
        if(list.size()==0) {
            obj.put("error", "Invalid id, no joke");
            status = 404;
        }else {
            Random rand = new Random();
            int index = rand.nextInt(list.size());
            obj.put("id",index);
            obj.put("joke", list.get(index));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());


    }
    @PostMapping ("/joke/new")
    public ResponseEntity<String> newJoke (@RequestBody String data) {
        try{
            JSONObject obj = (JSONObject) new JSONParser().parse(data);
            System.out.println(obj.toString());
             String newJoke = String.valueOf(obj.get("joke"));
            if(newJoke==null)
                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{}");
            System.out.println(newJoke);
            list.add(newJoke);
            return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body("{}");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("{}");
    }
}

