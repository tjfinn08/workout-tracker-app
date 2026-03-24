package model;

public class SetEntry {
    private int numSets;
    private int numReps;
    private double weight;

    public SetEntry(int aNumSets, int aNumReps, double aWeight){
        numSets = aNumSets;
        numReps = aNumReps;
        weight = aWeight;
    }

    public int getNumSets() {
        return numSets;
    }

    public int getNumReps() {
        return numReps;
    }

    public double getWeight() {
        return weight;
    }
}
