package main.repository;

import main.models.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository  extends CrudRepository<ToDo, Integer> {
}
