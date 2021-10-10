package main.controller;
import main.model.ToDoList;
import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class WebController {
    @Autowired//автоматическое создание Репозитория
    ToDoListRepository toDoListRepository;


    @GetMapping("/")
    public String index(Model model) {
        Iterable<ToDoList> toDoLists = toDoListRepository.findAll();
        ArrayList<ToDoList> lists = new ArrayList<>();
        for (ToDoList toDo : toDoLists) {
            lists.add(toDo);
        }

        model.addAttribute("counts", lists.size());
        model.addAttribute("lists", lists);
        return "index";
    }


}
