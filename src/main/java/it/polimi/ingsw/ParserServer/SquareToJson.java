package it.polimi.ingsw.ParserServer;

import it.polimi.ingsw.model.Color;

public class SquareToJson {
    private int cordinataX, cordinataY;
    private int levels;
    private Color color;

    public SquareToJson( int levels, String color, int cordinataX, int cordinataY) {
        this.levels = levels;
        this.cordinataX = cordinataX;
        this.cordinataY = cordinataY;
        if(color.equals("WHITE")){
            this.color=Color.WHITE;
        }else if(color.equals("BLACK")){
            this.color=Color.BLACK;
        }else if(color.equals("BROWN")){
            this.color=Color.BROWN;
        }else{
            this.color=null;
        }
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

    public Color getColor() {
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

    public void setColor(Color color) {
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
