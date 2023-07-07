package Model.Entity;

import android.graphics.Bitmap;

import java.util.Vector;

import Model.ServiceImpl.CardboardServiceImpl;

public class Player {
    private String nickName;
    private int order;
    private int score;
    private CardGroup playerCardGroup = new CardGroup();//玩家剩余的手牌
    private SelectedCardGroup selCards = new SelectedCardGroup();//玩家选中即将要出的牌
    private final Vector<Prop> props;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank=0;

    public Title getTitles() {
        return titles;
    }

    private final Title titles =new Title(0,"平平无奇","未获得任何成就");

    public String getTitle() {
        return title;
    }

    private String title;
    private final Vector<Player> playmate;

    public int getWinTimes() {
        return winTimes;
    }

    public void addWinTimes() {
        this.winTimes+=1;
    }

    private int winTimes=0;

    public int getLoseTimes() {
        return loseTimes;
    }

    public void addLoseTimes() {
        this.loseTimes +=1;
    }

    private int loseTimes=0;

    public Bitmap getHeadBitmap() {
        return headBitmap;
    }

    public void setHeadBitmap(Bitmap headBitmap) {
        this.headBitmap = headBitmap;
    }
    Bitmap headBitmap = null;



    public int winRate(){
        if(winTimes+loseTimes==0)
            return  0;
        else return winTimes/(winTimes+loseTimes);
    }



    public Player(int order,String name,int score) {
        this.nickName=name;
        this.order=order;
        this.score = score;
        this.props = new Vector<>(4);
        props.add(new Prop(1, "偷龙转凤", 300));
        props.add(new Prop(2, "透视眼", 400));
        props.add(new Prop(3, "移花接木", 500));
        props.add(new Prop(4, "偷天换日", 600));
        playmate =new Vector<>(3);
        title=this.titles.Id_Name(winTimes);
    }
//    public Player(int order, String name, int score, int Title) {
//        this.nickName=name;
//        this.order=order;
//        this.score = score;
//        this.props = new Vector<>(4);
//        this.titles=new Vector<>(8);
//        props.add(new Prop(1, "偷龙转凤", 300));
//        props.add(new Prop(2, "透视眼", 400));
//        props.add(new Prop(3, "移花接木", 500));
//        props.add(new Prop(4, "偷天换日", 600));
//        playmate =new Vector<>(3);
//        title= this.titles.get(Title).getName(this);
//    }

    public void propInitial(CardboardServiceImpl board) {
        for (Prop prop : props) {
            if(prop.getId()==1||prop.getId()==2)
            {
                if (prop.canUse(score)&&board.getGameState()==-1) {
                prop.setCanUse(true);}

              }
            else if(prop.getId()==3||prop.getId()==4)
            {
                if (prop.canUse(score)&&board.getGameState()==1) {
                prop.setCanUse(true);
                }

            }
        }
    }

    public void useProp(int id){
        for (Prop prop:props) {
            if(prop.getId()==id) {
                switch(id) {
                    case 1:
                        prop.Tou_Long_Zhuan_Feng(this);
                        break;
                    case 2:
                        prop.Tou_Shi_Yan(this);
                        break;
                    case 3:
                        prop.Yin_Hua_Jie_Mu(this);
                        break;
                    case 4:
                        prop.Tou_Tian_Huan_Ri(this);
                        break;
                    default:
                        System.out.println("无效的道具ID");
                }
                return;
            }
        }
    }

    public Prop getPropById(int id) {
        for (Prop prop : props) {
            if (prop.getId() == id) {
                return prop;
            }
        }
        return null; // 如果未找到指定id的道具，则返回null
    }



    //可从playerCardGroup中选择有意向要出的牌，参数是单张card,返回值是已经选择的selCards牌组
    private void selectCardGroup(Card card){
        //如果CardGroup中事先没有这张牌，则将这张牌加入选择区
        if(!selCards.getCards().contains(card)) {
            selCards.getCards().add(card);
            //显示选择的牌的函数
            //
            //
            //
        }
        //如果选择区已经存在这张牌
        else{
            selCards.getCards().remove(card);
            //显示选择的牌的函数
            //
            //
            //
        }
    }
    private Card getSingleCard()
    {
        if(selCards.getCards().size()==1)
           return selCards.getCards().get(0);
        else if (selCards.getCards().size()!=1){
            System.out.println("该道具只能交换一张牌");
        }
      return null;
    }
    //将已经选择的牌出掉
    private void showCardsGroup(){
        //调用前端的显示函数
        //
        //
        //
    }
    //跳过当前自己的回合
   private void passTurn(){
        //
       //
       //
   }


    public String getNickName(){
        return nickName;
    }

    public void setNickName(String name){
          this.nickName=name;
    }

    public int getScore(){
        return score;
    }

    public CardGroup getPlayerCardGroup(){
        return playerCardGroup;
    }//获得玩家手牌的接口函数
    public void displayHandCards(){
        System.out.println("玩家"+order+"的手牌为：");
        for(int i=0; i<playerCardGroup.getCards().size(); i++)
        {
            Card card = playerCardGroup.getCards().elementAt(i);
            System.out.print("  (" + card.CardToString() + ")");
        }
        System.out.println();
    }

    public SelectedCardGroup getSelCards(){
        return selCards;
    }//获得玩家选择的牌的接口函数


    public Vector<Player> getPlaymate() {
        return playmate;
    }
    public void setPlaymate(Player player1,Player player2,Player player3){
        this.playmate.add(player1);
        this.playmate.add(player2);
        this.playmate.add(player3);
    }


}
