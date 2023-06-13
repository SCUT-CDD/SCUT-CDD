package ControllerImpl;

import Model.Service.CardboardService;
import Model.ServiceImpl.CardboardServiceImpl;

public class CardBoardConImpl {
    private CardboardService cardboardService = new CardboardServiceImpl();
    public void operationEvent(int opcode){
        switch (opcode){
            case 1://开始对局
                startGame();
                break;
        }
    }

    public void startGame() {
        System.out.println("CardBoardConImpl开始对局");
        cardboardService.startGameInit();//初始化玩家数据对局 进入GameTurn
    }

    public void disAllPlayerHandCards(){
        cardboardService.displayAllPlayerCard();
    }

}
