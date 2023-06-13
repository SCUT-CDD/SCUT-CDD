package ControllerImpl;

import java.util.Vector;

import Model.Service.GameTurnService;
import Model.ServiceImpl.GameTurnServiceImpl;

public class GameTurnConImpl {
    private GameTurnService gameTurnService=new GameTurnServiceImpl();

    public void outputCurrentShowingPlayer(){
        System.out.println("Turn to: "+gameTurnService.getCurrentShowingPlayerInString());
    }
    public void outputUpperShownCards(){
        System.out.println("Shown Cards: "+gameTurnService.getUpperShownCardGroup().toString());
    }

    /**
     * 描述:进入回合
     * @author 叶达杭
     * @return void
    */
    public void comeIntoRound(int op){
        switch (op){
            case 1://出牌
                break;
            case 2://跳过
                passRound();
                break;
        }
    }

    /**
     * 描述:跳过回合
     * @author 叶达杭
     * @return void
    */
    public void passRound(){
        gameTurnService.upPassCount();
        gameTurnService.turnToNextPlayer();
    }
    public void showCards(Vector<Integer> positions){
        if(gameTurnService.playerShowCards(positions)){
            //出牌有效
            gameTurnService.turnToNextPlayer();
            outputUpperShownCards();
            gameTurnService.clearPassCount();
        }
    }

}
