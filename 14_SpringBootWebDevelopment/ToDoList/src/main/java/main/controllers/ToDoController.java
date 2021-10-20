package main.controllers;

import main.models.ToDo;
import main.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping()
    public String list(Model model ) {
        ArrayList<ToDo> toDos = new ArrayList<>();
        toDoRepository.findAll().forEach(toDos::add);
        model.addAttribute("list", toDos);
        return "index";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable int id, Model model) {
        Optional<ToDo> toDoOptional = toDoRepository.findById(id);
        if (toDoOptional.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("todo", toDoOptional);
        return "operations/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {

        if (toDoRepository.existsById(id)) {
            toDoRepository.deleteById(id);

        }
        return "redirect:/";
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteAll() {
        toDoRepository.deleteAll();
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/new")
    public String newToDo(Model model) {
        ToDo toDo = new ToDo();
        model.addAttribute("todo", toDo);
        return "operations/new";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("todo") ToDo toDo) {

        ToDo toDoNew = toDoRepository.save(toDo);
        return "redirect:/";
    }


    @PostMapping("/edit")
    public String put( @ModelAttribute("todo") ToDo toDo ) {
        if (toDoRepository.existsById(toDo.getId())) {
            toDoRepository.save(toDo);
        }
        return "redirect:/";
    }

}
