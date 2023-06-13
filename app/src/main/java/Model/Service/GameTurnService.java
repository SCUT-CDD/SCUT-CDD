package Model.Service;

import java.util.Vector;

import Model.Entity.SelectedCardGroup;

public interface GameTurnService {
     public String getCurrentShowingPlayerInString();

     public void distributeCard();

     public void decideShowingOrder();
     public boolean playerShowCards(Vector<Integer> index);

     public void turnToNextPlayer();

     public SelectedCardGroup getUpperShownCardGroup();

     public void upPassCount();
     public void clearPassCount();

}
