package com.todolist.todoapi.controller;

import com.todolist.todoapi.exceptions.InvalidPriorityException;
import com.todolist.todoapi.exceptions.NameNotFoundException;
import com.todolist.todoapi.exceptions.NullCompletionException;
import com.todolist.todoapi.exceptions.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controlador responsável pelo tratamento de exceções lançadas pelos endpoints da API.
 * Utiliza a anotação {@link ControllerAdvice} para capturar e tratar exceções globalmente,
 * retornando respostas adequadas para o cliente.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Manipulador de exceção para {@link InvalidPriorityException}.
     * Retorna uma resposta de erro 400 (Bad Request) com a mensagem de erro.
     *
     * @param invalidPriorityException A exceção lançada quando uma prioridade inválida é fornecida.
     * @return Uma resposta com status HTTP 400 (Bad Request) e a mensagem de erro.
     */
    @ExceptionHandler(InvalidPriorityException.class)
    public ResponseEntity<String> threatInvalidPriority(InvalidPriorityException invalidPriorityException) {
        String errorMessage = invalidPriorityException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    /**
     * Manipulador de exceção para {@link TodoNotFoundException}.
     * Retorna uma resposta de erro 404 (Not Found) com a mensagem de erro.
     *
     * @param todoNotFoundException A exceção lançada quando a tarefa não é encontrada.
     * @return Uma resposta com status HTTP 404 (Not Found) e a mensagem de erro.
     */
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<String> threatTodoNotFound(TodoNotFoundException todoNotFoundException) {
        String errorMessage = todoNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    /**
     * Manipulador de exceção para {@link NullCompletionException}.
     * Retorna uma resposta de erro 400 (Bad Request) com a mensagem de erro.
     *
     * @param nullCompletionException A exceção lançada quando o status de conclusão é nulo.
     * @return Uma resposta com status HTTP 400 (Bad Request) e a mensagem de erro.
     */
    @ExceptionHandler(NullCompletionException.class)
    public ResponseEntity<String> threatNullCompletion(NullCompletionException nullCompletionException) {
        String errorMessage = nullCompletionException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    /**
     * Manipulador de exceção para {@link NameNotFoundException}.
     * Retorna uma resposta de erro 404 (Not Found) com a mensagem de erro.
     *
     * @param nameNotFoundException A exceção lançada quando o nome não é encontrado.
     * @return Uma resposta com status HTTP 404 (Not Found) e a mensagem de erro.
     */
    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<String> threatNameNotFoundException(NameNotFoundException nameNotFoundException) {
        String errorMessage = nameNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
