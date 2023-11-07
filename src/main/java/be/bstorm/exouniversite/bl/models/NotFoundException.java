package be.bstorm.exouniversite.bl.models;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
