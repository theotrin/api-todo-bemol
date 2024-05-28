package br.com.bemol.api_todo_bemol.repository;


import br.com.bemol.api_todo_bemol.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
