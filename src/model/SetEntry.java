package model;

public class SetEntry {
    private int numReps;
    private double weight;

    public SetEntry(int aNumReps, double aWeight){
        numReps = aNumReps;
        weight = aWeight;
    }

    public int getNumReps() {
        return numReps;
    }

    public double getWeight() {
        return weight;
    }
}
