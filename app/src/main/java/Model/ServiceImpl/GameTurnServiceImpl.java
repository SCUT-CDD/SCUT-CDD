package Model.ServiceImpl;

import static Model.Entity.Suit.DIAMONDS;
import static Model.Entity.Value.THREE;

import java.security.SecureRandom;
import java.util.Vector;

import Model.Dao.GameTurnDao;
import Model.DaoImpl.GameTurnDaoImpl;
import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.Entity.DeckOfCards;
import Model.Entity.Player;
import Model.Entity.SelectedCardGroup;
import Model.Service.GameTurnService;
import Model.Service.PlayerService;

/*
GameTurn类
包含每个玩家的牌组，牌分和本局游戏的得分
*/
public class GameTurnServiceImpl implements GameTurnService
{
    GameTurnDao gameTurnDao = new GameTurnDaoImpl();
    private Vector<Card> player1_handCards = new Vector<>(13);
    private Vector<Card> player2_handCards = new Vector<>(13);
    private Vector<Card> player3_handCards = new Vector<>(13);
    private Vector<Card> player4_handCards = new Vector<>(13);

    private int player1_cardPoint = 0;
    private int player2_cardPoint = 0;
    private int player3_cardPoint = 0;
    private int player4_cardPoint = 0;

    private int player1_score = 0;
    private int player2_score = 0;
    private int player3_score = 0;
    private int player4_score = 0;


    /**
     * 描述: 设置牌桌中玩家的手牌
     * @author 姚
     * @param cardGroup 玩家的手牌
     */
    public void setPlayer1_handCards(CardGroup cardGroup)
    {
        for(int i = 0;i < cardGroup.getCards().size(); i++)
            player1_handCards.add(cardGroup.getCards().elementAt(i));
    }
    public void setPlayer2_handCards(CardGroup cardGroup)
    {
        for(int i = 0;i < cardGroup.getCards().size(); i++)
            player2_handCards.add(cardGroup.getCards().elementAt(i));
    }
    public void setPlayer3_handCards(CardGroup cardGroup)
    {
        for(int i = 0;i < cardGroup.getCards().size(); i++)
            player3_handCards.add(cardGroup.getCards().elementAt(i));
    }
    public void setPlayer4_handCards(CardGroup cardGroup)
    {
        for(int i = 0;i < cardGroup.getCards().size(); i++)
            player4_handCards.add(cardGroup.getCards().elementAt(i));
    }

    /**
     * 描述: 获取玩家的手牌
     * @author 姚
     */
    public Vector<Card> getPlayer1_handCards(){return player1_handCards;}
    public Vector<Card> getPlayer2_handCards(){return player2_handCards;}
    public Vector<Card> getPlayer3_handCards(){return player3_handCards;}
    public Vector<Card> getPlayer4_handCards(){return player4_handCards;}

    /**
     * 描述: 设置玩家的牌分
     * @author 姚
     * @param point 整数，表示通过手牌数量计算出来的牌分
     */
    public void setPlayer1_cardPoint(int point){player1_cardPoint = point;}
    public void setPlayer2_cardPoint(int point){player2_cardPoint = point;}
    public void setPlayer3_cardPoint(int point){player3_cardPoint = point;}
    public void setPlayer4_cardPoint(int point){player4_cardPoint = point;}

    /**
     * 描述: 获取玩家的牌分
     * @author 姚
     */
    public int getPlayer1_cardPoint(){return player1_cardPoint;}
    public int getPlayer2_cardPoint(){return player2_cardPoint;}
    public int getPlayer3_cardPoint(){return player3_cardPoint;}
    public int getPlayer4_cardPoint(){return player4_cardPoint;}

    /**
     * 描述: 设置该局游戏中玩家的得分
     * @author 姚
     * @param point 整数，表示通过每个玩家牌分计算得到的某一玩家的该局游戏得分
     */
    public void setPlayer1_score(int point){player1_score = point;}
    public void setPlayer2_score(int point){player2_score = point;}
    public void setPlayer3_score(int point){player3_score = point;}
    public void setPlayer4_score(int point){player4_score = point;}

    /**
     * 描述: 获取玩家该局游戏的得分
     * @author 姚
     */
    public int getPlayer1_score(){return player1_score;}
    public int getPlayer2_score(){return player2_score;}
    public int getPlayer3_score(){return player3_score;}
    public int getPlayer4_score(){return player4_score;}

    public GameTurnServiceImpl() {}

    public GameTurnServiceImpl(Player player1, Player player2, Player player3, Player player4)
    {
        distributeCard(player1, player2, player3, player4);
    }


    /**
     * 描述: 游戏开始，向每个玩家分发13张牌，并设置GameTurn类的四个牌组属性
     * @author 姚
     * @param player1 玩家1
     * @param player2 玩家2
     * @param player3 玩家3
     * @param player4 玩家4
     */
    public void distributeCard(Player player1, Player player2, Player player3, Player player4)
    {
        // Create a deck of cards
        DeckOfCards deck = new DeckOfCards();

        // Create a Random object to generate random numbers
        SecureRandom random = new SecureRandom();

        // Distribute 13 cards to every player
        while(player1.getPlayerCardGroup().getCards().size() < 13)
        {
            int i = random.nextInt(deck.poke.size());
            player1.getPlayerCardGroup().getCards().add(deck.poke.elementAt(i));
            deck.poke.remove(i);
        }
        player1.getPlayerCardGroup().cardGroupSortAsRule(1);
        setPlayer1_handCards(player1.getPlayerCardGroup());

        while(player2.getPlayerCardGroup().getCards().size() < 13)
        {
            int i = random.nextInt(deck.poke.size());
            player2.getPlayerCardGroup().getCards().add(deck.poke.elementAt(i));
            deck.poke.remove(i);
        }
        player2.getPlayerCardGroup().cardGroupSortAsRule(1);
        setPlayer2_handCards(player2.getPlayerCardGroup());

        while(player3.getPlayerCardGroup().getCards().size() < 13)
        {
            int i = random.nextInt(deck.poke.size());
            player3.getPlayerCardGroup().getCards().add(deck.poke.elementAt(i));
            deck.poke.remove(i);
        }
        player3.getPlayerCardGroup().cardGroupSortAsRule(1);
        setPlayer3_handCards(player3.getPlayerCardGroup());

        while(player4.getPlayerCardGroup().getCards().size() < 13)
        {
            int i = 0;
            player4.getPlayerCardGroup().getCards().add(deck.poke.elementAt(i));
            deck.poke.remove(i);
        }
        player4.getPlayerCardGroup().cardGroupSortAsRule(1);
        setPlayer4_handCards(player4.getPlayerCardGroup());
    }


    public void printCardPointAndScore()
    {
        System.out.println("Card point:");
        System.out.println("player1: " + getPlayer1_cardPoint() + "\tplayer2: " + getPlayer2_cardPoint()
                + "\tplayer3: " + getPlayer3_cardPoint() + "\tplayer4: " + getPlayer4_cardPoint());

        System.out.println("Score:");
        System.out.println("player1: " + getPlayer1_score() + "\tplayer2: " + getPlayer2_score()
                + "\tplayer3: " + getPlayer3_score() + "\tplayer4: " + getPlayer4_score());
    }

    public boolean if_Have_Diamonds_three(Player player){
        for (int i = 0; i < 13; i++) {
            if (player.getPlayerCardGroup().getCards().elementAt(i).getValue() == THREE
                    && player.getPlayerCardGroup().getCards().elementAt(i).getSuit() == DIAMONDS)
                return true;
        }
        return false;
    }


    /**
     * 描述: 查找初始手牌中含有方块3的玩家，决定第一位出牌的玩家
     * @author 姚
     */
    @Override
    public void decideShowingOrder() {
        // Judge who has DIAMONDS_THREE
       if (if_Have_Diamonds_three(gameTurnDao.getPlayerVec().get(0))){
           gameTurnDao.setCurrentPlayer(0);}
       else if (if_Have_Diamonds_three(gameTurnDao.getPlayerVec().get(1))){gameTurnDao.setCurrentPlayer(1);}
       else if (if_Have_Diamonds_three(gameTurnDao.getPlayerVec().get(2))){gameTurnDao.setCurrentPlayer(2);}
       else if (if_Have_Diamonds_three(gameTurnDao.getPlayerVec().get(3))){gameTurnDao.setCurrentPlayer(3);}
    }

    /* Function: Judge the showing CardGroup is valid or not
     *  Input: A object of CardGroup
     *  Output: If validate, return true; else return false */
    public boolean validate(CardGroup card_group) {
        boolean validity = false;
        return validity;
    }


    @Override
    public String getCurrentShowingPlayerInString() {
        return gameTurnDao.getCurrentShowingPlayer().getNickName();
    }

    @Override
    public void distributeCard() {
        Player player1 =gameTurnDao.getPlayerVec().get(0);
        Player player2 =gameTurnDao.getPlayerVec().get(1);
        Player player3 =gameTurnDao.getPlayerVec().get(2);
        Player player4 =gameTurnDao.getPlayerVec().get(3);
        distributeCard(player1,player2,player3,player4);
    }

    @Override
    public boolean playerShowCards(Vector<Integer> index){
        PlayerService playerService =new PlayerServiceImpl(gameTurnDao.getCurrentShowingPlayer());
        if(playerService.setSelectedCards(index)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void turnToNextPlayer() {
        gameTurnDao.updateCurrentPlayer();
    }

    @Override
    public SelectedCardGroup getUpperShownCardGroup() {
        return  gameTurnDao.getUpperShownCardGroup();
    }

    @Override
    public void upPassCount(){
        int newPassCount =gameTurnDao.getPassCount()+1;
        gameTurnDao.setPassCount(newPassCount);
    }
    @Override
    public void clearPassCount(){
        gameTurnDao.setPassCount(0);
    }
}