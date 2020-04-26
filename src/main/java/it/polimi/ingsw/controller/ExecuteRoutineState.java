package it.polimi.ingsw.controller;

import it.polimi.ingsw.ParserServer.SquareToJson;
import it.polimi.ingsw.commands.ChooseYourWorker;
import it.polimi.ingsw.events.ChooseWorker;
import it.polimi.ingsw.model.Square;
import it.polimi.ingsw.model.Worker;

import java.util.ArrayList;
import java.util.List;

public class ExecuteRoutineState implements TurnState {
    int i = -1;


    @Override
    public void executeState(Controller controller) {
        System.out.println(i);
        if (i == -1) {
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

        } else {
            //       for(;i<controller.getGame().getCurrentPlayer().getGod().getRoutine().size() || controller.getGame().getWinner()!=null || !controller.getGame().getCurrentPlayer().isDefeat();i++) {
            //     while (controller.getGame().getCurrentPlayer().isInQue());
            if(controller.getGame().getCurrentPlayer().isInQue())
                return;
            if(i<controller.getGame().getCurrentPlayer().getGod().getRoutine().size() || controller.getGame().getWinner()!=null || !controller.getGame().getCurrentPlayer().isDefeat())
                if ((!controller.isCanSkip()) || (!controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).isSkippable()))
                    if (controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().isUsable(controller.getGame()))
                        controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().use(controller.getGame());

            else
                if (controller.getGame().getWinner() != null) {
                TurnState state = new NotifyVictoryState();
                controller.setState(state);
                state.executeState(controller);
            } else if (controller.getGame().getCurrentPlayer().isDefeat() || !controller.getGame().getCurrentPlayer().isHasBuilt()) {
                TurnState state = new DefeatState();
                controller.setState(state);
                state.executeState(controller);
            } else {
                TurnState state = new StartTurnState();
                controller.setState(state);
                state.executeState(controller);
            }
        }

        i++;
    }
}