package it.polimi.ingsw.model;

/**
        * It is the micro-effect that have the role to check if the player have win if he is go down by levelToWin level
        *  @author  Salvatore Cassata
        */


public class CheckVictory implements  SubAction{

    private int levelToWin;

    public int getLevelToWin() {
        return levelToWin;
    }

    public void setLevelToWin(int levelToWin) {
        this.levelToWin = levelToWin;
    }

    /**
            * @param game
     */
    @Override
    public void use(Game game) {

        //se è salito da livello 3 a livello 4
        for(Player p: game.getPlayerList()){
            if (p.getWorkers().contains((Worker) game.getTargetInUse()));
            game.setWinner(p);}

    }

    /**
     * @param game
     * @return
     */
    @Override
    public Boolean isUsable(Game game) {
        Worker worker = (Worker) game.getTargetInUse();
        // vedere come fare per Pan
        if (worker.getHistoryPos().get(worker.getHistoryPos().size() - 1).getLevel() - worker.getHistoryPos().get(worker.getHistoryPos().size() - 2).getLevel() >= levelToWin) {
            return true;
            // player have win
        }

        return false;
    }
}