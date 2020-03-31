package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwapTargetTest {
    private SwapTarget swapTarget=new SwapTarget();
    private Game game=new Game();

    @Test
     void swapTargetTest(){
        Target a= new Worker();
        Target b=new Square(0, 0);
        game.setTargetInUse(a);
        game.setTargetSelected(b);
        assertEquals(a, game.getTargetInUse());
        assertEquals(b, game.getTargetSelected());
        swapTarget.use(game);
        assertEquals(b, game.getTargetInUse());
        assertEquals(a, game.getTargetSelected());
    }
}
