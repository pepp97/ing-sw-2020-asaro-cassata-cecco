package it.polimi.ingsw.model;

public enum Color {
    WHITE ("WHITE","ffffff"),
    BLACK("BLACK","000000"),
    BROWN("BROWN","a52a2a");

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
