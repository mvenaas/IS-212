package sailscore;

import java.util.*;

/**
 * Created by Erlend on 14.03.2017.
 */

public class Regatta {

    List<Competitor> list = new ArrayList<>();
    List finalResults = new ArrayList();


    public Regatta(int laps) {
        addCompetitor("Bulbasaur", 101);
        addCompetitor("Pidgeot", 41);
        addCompetitor("Charmander", 443);
        addCompetitor("Pikachu", 76);
        addCompetitor("Vulpix", 65);
        addCompetitor("Meowth", 52);
        addCompetitor("Graveler", 241);
        addCompetitor("Magnemite", 98);
        addCompetitor("Haunter", 24);
        addCompetitor("Voltorb", 82);
        listCompetitors();
        for (int i = 0; i < laps; i++) {
            newRace();
            registerFinish();
        }
        sortResults(laps);
        finalResult(laps);

    }

    public void addCompetitor(String name, int sailid) {
        list.add(new Competitor(name, sailid));
    }

    public void listCompetitors() {
        System.out.format("%20s\n", "List of competitors:");
        System.out.format("%20s%10s\n", "Competitor", "ID");
        System.out.println("");
        for (Competitor list : list) {

            String name = list.getName();
            int id = list.getSailid();
            System.out.format("%20s%10d\n", name, id);
        }
        System.out.println("");
    }

    public void newRace() {
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));
    }

    public void registerFinish() {
        for (int i = 0; i < list.size(); i++) {
            int position = i + 1;
            list.get(i).addScore(position);
        }
    }

    public void sortResults(int laps) {
        for (Competitor list : list) {
            String name = list.getName();
            int id = list.getSailid();
            int sum = 0;
            for (int i = 0; i < list.getScore().size(); i++) {
                sum = sum + list.getScore().get(i).hashCode();
                list.setFinalScore(sum);
            }
        }
    }

    public void finalResult(int laps) {
        list.sort(Comparator.comparing(Competitor::getFinalScore).reversed());
        System.out.println("Final score after " + laps + " stages regatta:");
        System.out.format("%15s%15s%25s%15s\n", "Name", "Sail ID", "Stage results:", "Total score:");
        for (Competitor list : list) {
            System.out.format("%15s%15d%25s%15d\n", list.getName(), list.getSailid(), list.getScore(), list.getFinalScore());

        }

    }
}
