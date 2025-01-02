package com.todolist.todoapi.controller;

import com.todolist.todoapi.entities.TodoList;
import com.todolist.todoapi.exceptions.InvalidPriorityException;
import com.todolist.todoapi.exceptions.NullCompletionException;
import com.todolist.todoapi.exceptions.TodoNotFoundException;
import com.todolist.todoapi.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão das tarefas na aplicação To-Do List.
 * Fornece endpoints para listar, adicionar, editar e excluir tarefas.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoListController {

    /**
     * Serviço responsável pelas operações de negócios relacionadas às tarefas.
     */
    @Autowired
    private TodoListService todoListService;

    /**
     * Endpoint para listar todas as tarefas.
     *
     * @return Uma lista de todas as tarefas.
     */
    @GetMapping
    public List<TodoList> listAll() {
        return todoListService.findAll();
    }

    /**
     * Endpoint para listar as tarefas filtradas por prioridade.
     *
     * @param priority A prioridade das tarefas a serem retornadas.
     * @return Uma lista de tarefas com a prioridade especificada.
     */
    @GetMapping("/priority/{priority}")
    public List<TodoList> listByPriority(@PathVariable int priority) {
        return todoListService.findByPriority(priority);
    }

    /**
     * Endpoint para listar as tarefas filtradas pelo nome.
     *
     * @param nameTodo O nome da tarefa a ser filtrada.
     * @return Uma lista de tarefas com o nome especificado.
     */
    @GetMapping("/name/{nameTodo}")
    public List<TodoList> listByNameTodo(@PathVariable String nameTodo) {
        return todoListService.findByNameTodo(nameTodo);
    }

    /**
     * Endpoint para listar uma tarefa específica pelo seu ID.
     *
     * @param id O ID da tarefa a ser recuperada.
     * @return A tarefa correspondente ao ID fornecido.
     * @throws TodoNotFoundException Se a tarefa com o ID fornecido não for encontrada.
     */
    @GetMapping("/{id}")
    public TodoList listById(@PathVariable Long id) {
        return todoListService.findById(id);
    }

    /**
     * Endpoint para adicionar uma nova tarefa à lista de afazeres.
     *
     * @param todoList A tarefa a ser adicionada.
     * @return A tarefa adicionada, com todos os dados persistidos.
     * @throws InvalidPriorityException Se a prioridade fornecida for inválida.
     * @throws NullCompletionException Se o status de conclusão for nulo.
     */
    @PostMapping
    public TodoList addTodo(@RequestBody TodoList todoList) {
        return todoListService.createTodo(todoList);
    }

    /**
     * Endpoint para editar uma tarefa existente, identificada pelo seu ID.
     *
     * @param id O ID da tarefa a ser editada.
     * @param todoList O objeto com as atualizações a serem aplicadas.
     * @return A tarefa atualizada.
     * @throws TodoNotFoundException Se a tarefa com o ID fornecido não for encontrada.
     */
    @PutMapping("/{id}")
    public TodoList editTodo(@PathVariable Long id, @RequestBody TodoList todoList) {
        return todoListService.updateTodo(id, todoList);
    }

    /**
     * Endpoint para excluir uma tarefa da lista de afazeres.
     *
     * @param id O ID da tarefa a ser excluída.
     * @throws TodoNotFoundException Se a tarefa com o ID fornecido não for encontrada.
     */
    @DeleteMapping("/{id}")
    public void deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodo(id);
    }

}
