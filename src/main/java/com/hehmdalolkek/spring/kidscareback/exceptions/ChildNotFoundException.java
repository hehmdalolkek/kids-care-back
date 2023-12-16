package com.hehmdalolkek.spring.kidscareback.exceptions;

public class ChildNotFoundException extends RuntimeException{
    public ChildNotFoundException(int id){
        super("Could not found the child with id "+ id);
    }
}
