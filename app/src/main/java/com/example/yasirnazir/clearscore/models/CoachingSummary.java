package com.example.yasirnazir.clearscore.models;

/**
 * Created by yasirnazir on 3/14/18.
 */

public class CoachingSummary {
    private String selected;

    private String numberOfTodoItems;

    private String activeChat;

    private String activeTodo;

    private String numberOfCompletedTodoItems;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getNumberOfTodoItems() {
        return numberOfTodoItems;
    }

    public void setNumberOfTodoItems(String numberOfTodoItems) {
        this.numberOfTodoItems = numberOfTodoItems;
    }

    public String getActiveChat() {
        return activeChat;
    }

    public void setActiveChat(String activeChat) {
        this.activeChat = activeChat;
    }

    public String getActiveTodo() {
        return activeTodo;
    }

    public void setActiveTodo(String activeTodo) {
        this.activeTodo = activeTodo;
    }

    public String getNumberOfCompletedTodoItems() {
        return numberOfCompletedTodoItems;
    }

    public void setNumberOfCompletedTodoItems(String numberOfCompletedTodoItems) {
        this.numberOfCompletedTodoItems = numberOfCompletedTodoItems;
    }

    @Override
    public String toString() {
        return "ClassPojo [selected = " + selected + ", numberOfTodoItems = " + numberOfTodoItems + ", activeChat = " + activeChat + ", activeTodo = " + activeTodo + ", numberOfCompletedTodoItems = " + numberOfCompletedTodoItems + "]";
    }
}
