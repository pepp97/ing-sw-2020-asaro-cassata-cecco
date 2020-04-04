package it.polimi.ingsw.model;

public enum Color {
    WHITE ("White","ffffff"),
    BLACK("Black","000000"),
    BROWN("Brown","a52a2a");

    private String color;
    private String hexadecimal;

    Color(String color,String hexadecimal){
        this.color=color;
        this.hexadecimal=hexadecimal;
    }

    public String getColor(){
        return this.color;
    }

}
