package main.controller;
import main.model.ToDoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.model.ToDoList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * /list/ (get-запрос/задача)                               GET
 * json-file:
 * {
 *     "id: id-number,
 *     "name": "описание задачи", *
 *     "description": 'информация'
 *     "date": 'дата',
 * }
 *
 * /list/{id} (get-(уточнённая задача-по) id)                 GET_ID
 * json-file:
 * {
 *     "id: id-number,
 *     "name": "конкретная(уточненная задача)",
 *     "description": 'информация'
 *     "date": 'дата',
 * }
 *
 * /list/ (post-добавление)                                    POST
 * json-file:
 * {
 *     "name": "задача",
 *     "description": 'описание задачи',
 *     "date": 'дата',
 * }
 *
 * /list/{id} (put-сохранение данных)                           PUT
 * json-file;
 * {
 *      "name": "задача",
 *      "description": 'описание задачи',
 *      "date": 'дата',
 * }
 *
 * /list/{id} (delete-удаление определенной задачи по id)        DELETE
 *
 * /list/{id} (patch - сохранение определенных изменений данных) PATCH
 * не вполне понимаю эту функцию
 */
//@Controller
@RestController
public class ToDoController {
    @Autowired//автоматическое создание Репозитория
    private ToDoListRepository toDoListRepository;

    public ToDoController(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }



    @GetMapping("/lists")//ПОЛУЧЕНИЕ СПИСКА
    public List<ToDoList> list() {
        Iterable<ToDoList> iterable = toDoListRepository.findAll();
        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        for (ToDoList toDoList : iterable) {
            toDoLists.add(toDoList);
        }

        return toDoLists;
    }

    @PostMapping("/lists")//ДОБАВЛЕНИЕ ЗАПИСИ isRepository
    public ResponseEntity<?> addTodo(ToDoList record) {
        toDoListRepository.save(record);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/lists")//ОБНОВЛЕНИЕ ДЕЛА isRepository
    public void editTodo(ToDoList record) {
        toDoListRepository.save(record);
    }

    @GetMapping("/lists/{id}")//ПОЛУЧЕНИЕ ДЕЛА ПО ID isRepository
    public ResponseEntity<?> getTodo(@PathVariable int id) {
        Optional<ToDoList> optionalToDoList = toDoListRepository.findById(id);
        return optionalToDoList.map(toDoList ->
                new ResponseEntity<>(toDoList, HttpStatus.OK)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/lists/{id}")//УДАЛЕНИЕ ДЕЛА ПО ID isRepository
    public void deleteTodo(@PathVariable int id) {
        toDoListRepository.deleteById(id);
    }

    @DeleteMapping("/lists")//УДАЛЕНИЕ СПИСКА ДЕЛ isRepository
    public void deleteAll() {
        toDoListRepository.deleteAll();
    }
}
