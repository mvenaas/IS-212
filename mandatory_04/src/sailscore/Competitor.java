package sailscore;

import java.util.LinkedList;


/**
 * Created by Erlend on 14.03.2017.
 */
public class Competitor {
    String name;
    int sailid;
    int finalScore;
    LinkedList<Integer> score = new LinkedList();

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }

    public void addScore(int points){
        score.addLast(points);
    }

    public LinkedList getScore(){
        return score;
    }



    public Competitor(String name, int sailid) {
        this.name = name;
        this.sailid = sailid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSailid() {
        return sailid;
    }

    public void setSailid(int sailid) {
        this.sailid = sailid;
    }

}
