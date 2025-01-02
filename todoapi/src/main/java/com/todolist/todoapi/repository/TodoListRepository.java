package com.todolist.todoapi.repository;

import com.todolist.todoapi.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    List<TodoList> findByPriority(int priority);

    List<TodoList> findByNameTodoIgnoreCase(String nameTodo);

    boolean existsByPriority(Integer priority);

    boolean existsByNameTodo(String name);



}
