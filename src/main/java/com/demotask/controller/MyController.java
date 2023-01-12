package com.demotask.controller;

import com.demotask.solver.SolveGps;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
@RequestMapping("/api")
public class MyController {

    @GetMapping("/index")
    public String home(Model model){

        return "index";
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public String calculatePath(@RequestParam("from") String from,
                                @RequestParam("to") String to,
                                @RequestParam("file") MultipartFile multipartFile) {

        // Create a file to send it to the method parameter
        String fileName = "answer.txt";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }catch (IOException e){
            e.getStackTrace();
        }

        double answer = 0;
        try {
            answer = SolveGps.path(from, to, file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "Answer: " + answer;
    }
}
