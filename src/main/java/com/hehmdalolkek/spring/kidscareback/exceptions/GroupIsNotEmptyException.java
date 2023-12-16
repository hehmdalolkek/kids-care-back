package com.hehmdalolkek.spring.kidscareback.exceptions;

public class GroupIsNotEmptyException extends RuntimeException {

    public GroupIsNotEmptyException(int id) {
        super("The group with id = " + id + " contains children and cannot be deleted.");
    }
}
