package it.polimi.ingsw.controller;

public class ExecuteRoutineState implements TurnState {
    int i=0;


    @Override
    public void executeState(Controller controller) {

        for(;i<controller.getGame().getCurrentPlayer().getGod().getRoutine().size() || controller.getGame().getWinner()!=null || !controller.getGame().getCurrentPlayer().isDefeat();i++)
            if((!controller.isCanSkip())||(!controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).isSkippable()))
                if (controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().isUsable(controller.getGame()))
                    controller.getGame().getCurrentPlayer().getGod().getRoutine().get(i).getEffect().use(controller.getGame());


        if(controller.getGame().getWinner()!=null) {
            TurnState state=new NotifyVictoryState();
            controller.setState(state);
            state.executeState(controller);
        }
        else if(controller.getGame().getCurrentPlayer().isDefeat()||!controller.getGame().getCurrentPlayer().isHasBuilt()){
            TurnState state=new DefeatState();
            controller.setState(state);
            state.executeState(controller);
        }
        else {
            TurnState state=new StartTurnState();
            controller.setState(state);
            state.executeState(controller);
        }



    }





}
