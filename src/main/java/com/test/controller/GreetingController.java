package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by peterhsu on 2017/7/12.
 */
@Controller
public class GreetingController {
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "peter") String name, @RequestParam(value = "date", required = false) String date, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("date", new Date().toString());
        return "greeting";
    }
}

