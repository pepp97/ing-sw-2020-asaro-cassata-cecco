package it.polimi.ingsw.model;

/**
 * @author cecco
 */
public enum Color {
    WHITE("WHITE", "ffffff"),
    BLACK("BLACK", "000000"),
    BROWN("BROWN", "a52a2a");

    private String color;
    private String hexadecimal;

    /**
     * This class describes the colors present in the game, the colors are used to be assign to the players
     *
     * @param color       describes the name of the color
     * @param hexadecimal describes the hexadecimal value of the color
     */
    Color(String color, String hexadecimal) {
        this.color = color;
        this.hexadecimal = hexadecimal;
    }

    public String getColor() {
        return this.color;
    }

}
