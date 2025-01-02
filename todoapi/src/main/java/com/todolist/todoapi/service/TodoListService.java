package com.todolist.todoapi.service;

import com.todolist.todoapi.entities.TodoList;
import com.todolist.todoapi.exceptions.InvalidPriorityException;
import com.todolist.todoapi.exceptions.NameNotFoundException;
import com.todolist.todoapi.exceptions.NullCompletionException;
import com.todolist.todoapi.exceptions.TodoNotFoundException;
import com.todolist.todoapi.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela lógica de negócios relacionada à lista de afazeres (Todo List).
 * Esta classe contém métodos para encontrar, criar, editar e excluir tarefas.
 */
@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    private static TodoList todoList;

    /**
     * Retorna todas as tarefas, ordenadas por prioridade em ordem decrescente
     * e, em seguida, por nome de tarefa em ordem crescente.
     *
     * @return Lista de todas as tarefas ordenadas.
     */
    public List<TodoList> findAll() {
        return todoListRepository.findAll(Sort.by(Sort.Order.desc("priority")
                , Sort.Order.asc("nameTodo")));
    }

    /**
     * Retorna as tarefas filtradas pela prioridade especificada.
     *
     * @param priority A prioridade das tarefas a serem buscadas.
     * @return Lista de tarefas com a prioridade fornecida.
     * @throws InvalidPriorityException Se a prioridade fornecida for inválida ou não existir.
     */
    public List<TodoList> findByPriority(Integer priority) throws InvalidPriorityException {
        if (priority == null || priority < 1) {
            throw new InvalidPriorityException("Error: this priority is invalid.");
        }

        if (!todoListRepository.existsByPriority(priority)) {
            throw new InvalidPriorityException("Error: Priority does not exist.");
        }

        return todoListRepository.findByPriority(priority);
    }

    /**
     * Retorna as tarefas filtradas pelo nome da tarefa fornecido.
     *
     * @param nameTodo O nome da tarefa.
     * @return Lista de tarefas com o nome fornecido.
     * @throws NameNotFoundException Se o nome fornecido não for encontrado.
     */
    public List<TodoList> findByNameTodo(String nameTodo) throws NameNotFoundException {
        if (!todoListRepository.existsByNameTodo(nameTodo)) {
            throw new NameNotFoundException("Error: Name not found.");
        }

        if (nameTodo.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: The name cannot be null or empty.");
        }
        return todoListRepository.findByNameTodoIgnoreCase(nameTodo);
    }

    /**
     * Retorna uma tarefa específica com base no ID fornecido.
     *
     * @param id O ID da tarefa.
     * @return A tarefa correspondente ao ID.
     * @throws TodoNotFoundException Se a tarefa com o ID fornecido não for encontrada.
     */
    public TodoList findById(Long id) throws TodoNotFoundException {
        return todoListRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Error: Todo not found with id " + id));
    }

    /**
     * Cria uma nova tarefa.
     *
     * @param todoList A tarefa a ser criada.
     * @return A tarefa criada.
     * @throws InvalidPriorityException Se a prioridade da tarefa for inválida.
     * @throws NullCompletionException Se o campo de conclusão for nulo.
     */
    public TodoList createTodo(TodoList todoList) throws InvalidPriorityException {
        if (todoList.getPriority() == null || todoList.getPriority() < 1) {
            throw new InvalidPriorityException("Error: This priority is invalid.");
        }

        if (todoList.getIsComplete() == null) {
            throw new NullCompletionException("Error: Your task cannot have a null completion status.");
        }

        return todoListRepository.save(todoList);
    }

    /**
     * Atualiza uma tarefa existente com base no ID fornecido.
     *
     * @param id O ID da tarefa a ser atualizada.
     * @param todoList A tarefa com os novos dados.
     * @return A tarefa atualizada.
     * @throws TodoNotFoundException Se a tarefa com o ID fornecido não for encontrada.
     */
    public TodoList updateTodo(Long id, TodoList todoList) throws TodoNotFoundException {
        ensureTodoExists(id);
        todoList.setId(id);
        return todoListRepository.save(todoList);
    }

    /**
     * Exclui uma tarefa com base no ID fornecido.
     *
     * @param id O ID da tarefa a ser excluída.
     * @throws TodoNotFoundException Se a tarefa com o ID fornecido não for encontrada.
     */
    public void deleteTodo(Long id) throws TodoNotFoundException {
        ensureTodoExists(id);
        todoListRepository.deleteById(id);
    }

    /**
     * Verifica se uma tarefa existe com o ID fornecido.
     *
     * @param id O ID da tarefa a ser verificada.
     * @throws TodoNotFoundException Se a tarefa com o ID fornecido não for encontrada.
     */
    private void ensureTodoExists(Long id) throws TodoNotFoundException {
        if (!todoListRepository.existsById(id)) {
            throw new TodoNotFoundException("Error: Todo not found with id " + id);
        }
    }
}
