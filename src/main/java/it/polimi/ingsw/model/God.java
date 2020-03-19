package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
/**
 * It is the micro-effect that have the role to check if the player have win
 *  @author  Salvatore Cassata
 */

public class God {
    public List<Integer> getCantDo() {
        return cantDo;
    }

    public void setCantDo(List<Integer> cantDo) {
        this.cantDo = cantDo;
    }

    private List<Integer> cantDo = new ArrayList<>();

}
