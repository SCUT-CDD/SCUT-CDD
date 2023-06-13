package Model.Entity;

public enum Value {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);//表示扑克牌从A到K
    public final int number;

    Value(int value) {
        this.number = value;
    }
    public int getValue() {
        return  number;
    }


}
