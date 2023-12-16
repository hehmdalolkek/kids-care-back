package com.hehmdalolkek.spring.kidscareback.exceptions;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(int id){
        super("Could not found the group with id "+ id);
    }
}
