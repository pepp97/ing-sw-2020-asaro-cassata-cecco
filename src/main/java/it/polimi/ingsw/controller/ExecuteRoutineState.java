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
        System.out.println("round: " + i);
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
            i++;
            controller.setGoOn(false);

        } else {

            if (controller.getGame().getCurrentPlayer().isInQue())
                return;

            if (i < controller.getGame().getCurrentPlayer().getGod().getRoutine().size() && controller.getGame().getWinner() == null && !controller.getGame().getCurrentPlayer().isDefeat()) {
                if ((!controller.isCanSkip()) || (!controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).isSkippable())) {
                    if (!controller.isGoOn()) {
                        controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().isUsable(controller.getGame());
                        i--;
                    } else {
                        controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().use(controller.getGame());
                    }
                }
            } else if (controller.getGame().getWinner() != null) {
                TurnState state = new NotifyVictoryState();
                controller.setState(state);
                state.executeState(controller);
            } else if (controller.getGame().getCurrentPlayer().isDefeat() || !controller.getGame().getCurrentPlayer().isHasBuilt()) {
                TurnState state = new DefeatState();
                controller.setState(state);
                state.executeState(controller);
            } else {
                TurnState state = new StartTurnState();
                i=-1;
                controller.setState(state);
                state.executeState(controller);
            }
            if (controller.getGame().getCurrentPlayer().getGod().getRoutine().size() != i)
                i++;
        }


        Worker w = (Worker) controller.getGame().getTargetInUse();
        if (controller.getGame().getCurrentPlayer().getGod().getRoutine().size() == i)
            executeState(controller);
        else if ((!controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().isInterationNeeded()) || (controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().isInterationNeeded() && w.getMandatorySquare() != null))
            executeState(controller);
        else //if (i > 0)
            // if ((!controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i - 1).getEffect().isInterationNeeded()))
            //   executeState(controller);
            if (controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().isInterationNeeded())
                executeState(controller);
        /*Worker w=new Worker();
            if(controller.getGame().getTargetInUse()!=null)
                 w=(Worker) controller.getGame().getTargetInUse();

            for(;i<controller.getGame().getCurrentPlayer().getGod().getRoutine().size() || controller.getGame().getWinner()!=null || !controller.getGame().getCurrentPlayer().isDefeat();i++){
                if ((!controller.isCanSkip()) || (!controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).isSkippable()))
                    if(!controller.isGoOn()){
                        controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().isUsable(controller.getGame());
                        i--;
                        if((controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i+1).getEffect().isInterationNeeded() && w.getMandatorySquare()==null ))
                            return;
                    }
                    else
                        controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().use(controller.getGame());
            }

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



        }*/
    }
}