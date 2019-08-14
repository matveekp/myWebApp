package com.example.myWebApp.controller;

import com.example.myWebApp.entity.Message;
import com.example.myWebApp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String startForm(@ModelAttribute Message message, Model model) {
        model.addAttribute("allMessages", messageRepository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Message message, Model model) {
        messageRepository.save(message);
        model.addAttribute("addInfo", message.getText());
        model.addAttribute("allMessages", messageRepository.findAll());
        return "index";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(name = "tag", required = true, defaultValue = "null") String  tag, Model model) {
        model.addAttribute("messageInfo", messageRepository.findByTag(tag));
        return "index";
    }

}
