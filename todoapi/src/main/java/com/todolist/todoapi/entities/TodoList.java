package com.todolist.todoapi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Representa uma tarefa de uma lista de afazeres (Todo List).
 * Contém informações sobre o nome da tarefa, descrição, prioridade e status de conclusão.
 */
@Entity
@Table(name = "todos")
public class TodoList {

    /**
     * O ID único da tarefa.
     * A anotação @Id define este campo como chave primária.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O nome da tarefa.
     * Não pode ser nulo ou em branco, garantido pela anotação @NotBlank.
     */
    @NotBlank
    private String nameTodo;

    /**
     * Descrição detalhada da tarefa.
     */
    private String description;

    /**
     * A prioridade da tarefa (valor inteiro).
     * Um valor mais baixo pode indicar maior prioridade.
     */
    private Integer priority;

    /**
     * Indica se a tarefa foi concluída.
     * Pode ser verdadeiro ou falso.
     */
    private Boolean isComplete;

    /**
     * Construtor para criar uma nova tarefa.
     *
     * @param nameTodo    O nome da tarefa.
     * @param description A descrição da tarefa.
     * @param priority    A prioridade da tarefa.
     * @param isComplete  O status de conclusão da tarefa.
     */
    public TodoList(String nameTodo, String description, Integer priority, Boolean isComplete) {
        this.nameTodo = nameTodo;
        this.description = description;
        this.priority = priority;
        this.isComplete = isComplete;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public TodoList() {
    }

    /**
     * Retorna o ID único da tarefa.
     *
     * @return O ID da tarefa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da tarefa.
     *
     * @param id O ID da tarefa.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna o nome da tarefa.
     *
     * @return O nome da tarefa.
     */
    public String getNameTodo() {
        return nameTodo;
    }

    /**
     * Define o nome da tarefa.
     *
     * @param nameTodo O nome da tarefa.
     */
    public void setNameTodo(String nameTodo) {
        this.nameTodo = nameTodo;
    }

    /**
     * Retorna a descrição da tarefa.
     *
     * @return A descrição da tarefa.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define a descrição da tarefa.
     *
     * @param description A descrição da tarefa.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retorna o status de conclusão da tarefa.
     *
     * @return true se a tarefa estiver concluída, caso contrário, false.
     */
    public Boolean getIsComplete() {
        return isComplete;
    }

    /**
     * Define o status de conclusão da tarefa.
     *
     * @param isComplete O status de conclusão da tarefa.
     */
    public void setComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    /**
     * Retorna a prioridade da tarefa.
     *
     * @return A prioridade da tarefa.
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Define a prioridade da tarefa.
     *
     * @param priority A prioridade da tarefa.
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}