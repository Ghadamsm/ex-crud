package com.example.trackersystem.Controller;


import com.example.trackersystem.API.ApiResponse;
import com.example.trackersystem.Model.Tracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")

public class TrackerController {


    ArrayList<Tracker> trackers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Tracker> getTask(){
        return trackers ;

    }


    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Tracker tracker){
        trackers.add(tracker);
        return new ApiResponse("Task added");
    }


    @PutMapping("/update/{index}")
    public ApiResponse upDateTask(@PathVariable int index , @RequestBody Tracker tracker){

        trackers.set(index , tracker);
        return new ApiResponse("UpDated");
    }




    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){

        try {

            trackers.remove(index);
            return new ApiResponse("deleted");

        } catch (Exception e){
            return new ApiResponse("Invalid");
        }
    }


    @PutMapping("/change/{index}")
    public ApiResponse changeStatus(@PathVariable int index ){

         if (trackers.get(index).getStatus().equals("not done")){
              trackers.get(index).setStatus("done");
              return new ApiResponse("Good!");
         } else {
             return new ApiResponse("Invalid");

         }
    }



    @GetMapping("/search/{search}")
    public ArrayList<Tracker> searchTitle(@PathVariable String search ){
        ArrayList<Tracker> searchArray = new ArrayList<>();

        for (Tracker tracker : trackers){
           if (tracker.getTitle().contains(search)){
               searchArray.add(tracker);
           }
        }

        return searchArray ;
    }


}
