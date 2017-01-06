package cn.lawliex.ask.data;

/**
 * Created by Terence on 2017/1/5.
 */
public enum  EventType {
    LIKE(0),
    COMMENT(1),
    FOLLOW(2),
    QUESTION(3),
    ANSWER(4);

    private int value;
    private EventType(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
