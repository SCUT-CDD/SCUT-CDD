package Model.ServiceImpl;

import Model.Dao.CardboardDao;
import Model.Dao.GameRuleDao;
import Model.DaoImpl.CardboardDaoImpl;
import Model.DaoImpl.GameRuleDaoImpl;
import Model.Entity.Card;
import Model.Entity.Player;
import Model.Service.CardboardService;
import Model.Service.GameTurnService;


public class CardboardServiceImpl implements CardboardService
{
    CardboardDao cardboardDao =new CardboardDaoImpl();
    GameRuleDao gameRuleDao =new GameRuleDaoImpl();
    private static int  GameState=-1;

    @Override
    public void startGameInit() {
       Player player1 = new Player(1,"player1",1000);
       Player player2 = new Player(2,"player2",1000);
       Player player3 = new Player(3,"player3",1000);
       Player player4 = new Player(4,"player4",1000);
       cardboardDao.clearPlayerVec();
       cardboardDao.addPlayerToVec(player1);
       cardboardDao.addPlayerToVec(player2);
       cardboardDao.addPlayerToVec(player3);
       cardboardDao.addPlayerToVec(player4);
        GameTurnService gameTurnService = new GameTurnServiceImpl();
        gameTurnService.distributeCard();//发牌
        gameTurnService.decideShowingOrder();//决定出牌顺序
        gameRuleDao.setFirstRoundFlag(true);//设置flag
    }

//    public void startGame()
//    {
//        GameState=1;
//       //Player player1 = new Player();
//       //Player player2 = new Player();
//       //Player player3 = new Player();
//       //Player player4 = new Player();
////        GameTurn gameTurn = new GameTurn(player1, player2, player3, player4);
////        gameTurn.decideShowingOrder(player1, player2, player3, player4).showCardsGroup();
//    }
    public void playGame()
    {}
    public void endGame()
    {}

    /**
     * 描述:返回当前游戏牌局的状态
     * @author 殷飞
     *return int类型 GameState
     */

    public int  getGameState(){
        return this.GameState;
    }
    public  void setPlayerMates(Player player1, Player player2, Player player3, Player player4) {
//        cardboardDao.getPlayerQue().toArray();
        player1.setPlaymate(player2, player3, player4);
        player2.setPlaymate(player1, player3, player4);
        player3.setPlaymate(player1, player2, player4);
        player4.setPlaymate(player1, player2, player3);
    }

    @Override
    public void displayAllPlayerCard() {
        cardboardDao.getPlayerVec().get(0).displayHandCards();
        cardboardDao.getPlayerVec().get(1).displayHandCards();
        cardboardDao.getPlayerVec().get(2).displayHandCards();
        cardboardDao.getPlayerVec().get(3).displayHandCards();
    }

    public static void main(String []args)
    {

        //初始化玩家
        Player player1 = new Player(1,"Amy",10000);
        Player player2 = new Player(2,"Bob",10000);
        Player player3 = new Player(3,"Rebecca",10000);
        Player player4 = new Player(4,"Tommy",10000);
        //为玩家添加游戏同伴
//        setPlayerMates(player1,player2,player3,player4);
        //初始化游戏并发牌
        GameTurnServiceImpl game = new GameTurnServiceImpl(player1,player2,player3,player4);
        //展示所有玩家的手牌
//        displayAllPlayerCard(player1,player2,player3,player4);

        System.out.println("\n");


        for(int i=0;i<7;i++)
            game.getPlayer1_handCards().remove(0);  //player1 6   6??
        for(int i=0;i<4;i++)
            game.getPlayer2_handCards().remove(0);  //player2 18  9??

        game.getPlayer3_handCards().remove(0);      //player3 36  12??

//        player1.giveCard();
        player1.getSelCards().toString();
        player1.getPlayerCardGroup().toString();
        //player4 52  13??
        //
        System.out.println("player1:");
        for(int i=0; i<player1.getPlayerCardGroup().getCards().size(); i++)
        {
            Card card = player1.getPlayerCardGroup().getCards().elementAt(i);
            System.out.print("  (" + card.CardToString() + ")");
        }

        System.out.println("player2:");
        for(int i=0; i<player2.getPlayerCardGroup().getCards().size(); i++)
        {
            Card card = player2.getPlayerCardGroup().getCards().elementAt(i);
            System.out.print("  (" + card.CardToString() + ")");
        }
       /* ScoringRule scoring = new ScoringRule();
        scoring.calculateCardPointOfAllPlayers(game);
        scoring.calculateScoreOfAllPlayers(game);
        game.printCardPointAndScore();*/
    }


}