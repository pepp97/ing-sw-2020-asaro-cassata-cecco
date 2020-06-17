package it.polimi.ingsw.controller;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.ChooseYourWorker;
import it.polimi.ingsw.events.ChooseTarget;
import it.polimi.ingsw.events.ChooseWorker;
import it.polimi.ingsw.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the State called when a Player is executing his turn
 */

public class ExecuteRoutineState implements TurnState {
    int i = -1;
    boolean result;
    private Controller controller;

    /**
     * this method is used to execute the state
     * @param controller is the main controller
     */
    @Override
    public void executeState(Controller controller) {
        this.controller = controller;
        Player currentPlayer = controller.getGame().getCurrentPlayer();
        God currentGod = currentPlayer.getGod();
        System.out.println("round: " + i);
        if (controller.getGame().getWinner() != null)
            return;

        if (i == -1) {
            chooseWorker();
            return;

        } else {

            if (currentPlayer.isInQue())
                return;

            if (!currentPlayer.isDefeat() && i < currentGod.getRoutine().size()) {
                if (((!controller.isCanSkip()) || (!currentGod.getRoutine().get(i).isSkippable()))) {
                    if (!controller.isGoOn()) {

                        result = currentGod.getRoutine().get(i).getEffect().isUsable(controller.getGame());
                        i--;

                    } else if (result) {
                        currentGod.getRoutine().get(i).getEffect().use(controller.getGame());
                        result = true;
                    } else controller.setGoOn(false);
                }
            } else if ((!currentPlayer.isHasBuilt() && i <= currentGod.getRoutine().size()) || currentPlayer.isDefeat()) {
                currentPlayer.setDefeat(true);
                TurnState state = new DefeatState();
                controller.setState(state);
                state.executeState(controller);
                state = new StartTurnState();
                i = -1;
                controller.setState(state);
                state.executeState(controller);
                ExecuteRoutineState state1 = new ExecuteRoutineState();
                controller.setState(state1);
                state1.executeState(controller);
            } else {
                controller.getGame().notifyCurrent(new ChooseTarget("", new ArrayList<>(), controller.getGame().squareToJsonArrayGenerator()));
                controller.startTimer();
                return;
            }
            if (controller.getGame().getCurrentPlayer().getGod().getRoutine().size() != i)
                i++;
        }


        Worker w = (Worker) controller.getGame().getTargetInUse();
        executeState(controller);

    }

    /**
     * this private method is called to ask to the current player what worker he wants use
     */

    private void chooseWorker() {
        List<Square> pos = new ArrayList<>();
        for (Worker s : controller.getGame().getCurrentPlayer().getWorkers())
            pos.add(s.getSquare());
        List<SquareToJson> positions = new ArrayList<>();
        for (Square s : pos)
            positions.add(new SquareToJson(s.getLevel(), s.getWorker().getC().toString(), s.getCoordinateX(), s.getCoordinateY()));
        SquareToJson[][] map = new SquareToJson[5][5];
        Square[][] mappa = controller.getGame().getField().getSquares();

        for (int x = 0; x < 5; x++)
            for (int y = 0; y < 5; y++)
                if (mappa[x][y].getWorker() != null)
                    map[x][y] = new SquareToJson(mappa[x][y].getLevel(), mappa[x][y].getWorker().getC().toString(), x, y);
                else map[x][y] = new SquareToJson(mappa[x][y].getLevel(), "", x, y);
        ChooseWorker chooseWorker = new ChooseWorker(positions, map);
        controller.getGame().getCurrentPlayer().setInQue(true);
        controller.getGame().notifyCurrent(chooseWorker);
        i++;
        controller.setGoOn(false);

    }
    /**
     * this method is used to go back if a player do a lose don't expected
     */

    @Override
    public void goBack() {
        i--;
    }
    /**
     * this method is used to check some lose condition
     * @return true if there are some condition to lose, false otherwise
     */
    @Override
    public Boolean tryToEscape() {
        boolean result = controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).isSkippable();
        if (result) i++;
        return result;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public boolean isResult() {
        return result;
    }
}