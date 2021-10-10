package main.controller;
/*import main.model.ToDoList;
import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController
{
    @Autowired
    ToDoListRepository toDoListRepository;
    @RequestMapping("/")
    public String index(Model model) {
        Iterable<ToDoList> iterable = toDoListRepository.findAll ();
        List<ToDoList> lists = new ArrayList<> ();
        for (ToDoList list : iterable) {
            lists.add(list);
        }
        model.addAttribute ("lists", lists);
        model.addAttribute ("listsCount", lists.size ());
        return "index";
    }

}*/
