package eu.plasmo.api;

public class Update {

    private String name;
    private Rank old, rank;

    public Update(String name, Rank old, Rank rank) {

        this.name = name;
        this.old = old;
        this.rank = rank;

    }

    public String getName() {
        return name;
    }

    public Rank getOld() {
        return old;
    }

    public Rank getRank() {
        return rank;
    }

}
