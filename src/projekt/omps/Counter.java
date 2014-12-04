package projekt.omps;

/**
 * Created by student18 on 2014-11-13.
 */
public class Counter {
    private int count;
    public Counter(){
        count=0;
    }
    public void increment(){
        count++;
    }
    public void clear(){
        count=0;
    }
    public int getCount(){
        return count;
    }
}