package com.example.myWebApp.controller;

import com.example.myWebApp.entity.Message;
import com.example.myWebApp.repositories.MessageRepository;
import com.example.myWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String startForm(@ModelAttribute Message message, Model model) {
        model.addAttribute("allMessages", messageRepository.findAll());
        return "index";
    }
//==========
//    OLD
//==========
//    @PostMapping("/add")
//    public String add(@ModelAttribute Message message, Model model) {
//        messageRepository.save(message);
//        model.addAttribute("addInfo", message.getText());
//        model.addAttribute("allMessages", messageRepository.findAll());
//        return "index";
//    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView registration(@Valid Message message, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        messageRepository.save(message);
        modelAndView.addObject("addInfo", "Message has been added successfully");
        modelAndView.addObject("allMessages",  messageRepository.findAll());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("filter")
    public String filter(@RequestParam(name = "tag", required = true, defaultValue = "null") String  tag, @ModelAttribute Message message, Model model) {
        model.addAttribute("foundMessages", messageRepository.findByTag(tag));
        return "index";
    }

}
