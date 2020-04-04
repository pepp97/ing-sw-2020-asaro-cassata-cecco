package it.polimi.ingsw.ParserServer;

import it.polimi.ingsw.model.Color;

public class SquareToJson {
    private int cordinataX, cordinataY;
    private int levels;
    private String color;

    public SquareToJson( int levels, String color, int cordinataX, int cordinataY) {
        this.levels = levels;
        this.color = color;
        this.cordinataX = cordinataX;
        this.cordinataY = cordinataY;
    }

    public int getCordinataX() {
        return cordinataX;
    }

    public int getCordinataY() {
        return cordinataY;
    }

    public int getLevels() {
        return levels;
    }

    public String getColor() {
        return color;
    }

    public void setCordinataX(int cordinataX) {
        this.cordinataX = cordinataX;
    }

    public void setCordinataY(int cordinataY) {
        this.cordinataY = cordinataY;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Creating toString
    @Override
    public String toString()
    {
        return  " {\n" +
                "  \"cordinataX\":"+this.cordinataX+" ,\n" +
                "  \"cordinataY\":"+this.cordinataY+" ,\n" +
                "  \"levels\":"+this.levels+" ,\n" +
                "  \"color\":"+this.color+"\n" +
                "}" ;
    }
}
