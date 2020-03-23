package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SwapTargetTest {
    private SwapTarget swapTarget=new SwapTarget(false);
    private Game game=new Game();

    @Test
    public void swapTargetTest(){
        Target a= new Worker(Color.Black);
        Target b=new Square(0, 0);
        game.setTargetInUse(a);
        game.setTargetSelected(b);
        assertEquals(a, game.getTargetInUse());
        assertEquals(b, game.getTargetSelected());
        swapTarget.use((Worker) a, b, game);
        assertEquals(b, game.getTargetInUse());
        assertEquals(a, game.getTargetSelected());
    }
}
