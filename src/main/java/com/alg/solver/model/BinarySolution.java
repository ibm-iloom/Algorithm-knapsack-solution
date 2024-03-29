package com.alg.solver.model;

import java.util.Arrays;
import java.util.Objects;

public final class BinarySolution {

    private final byte[] chromosome;
    private double profit;
    private long weight;

    public BinarySolution(int size) {
        chromosome = new byte[size];
        profit = Double.MAX_VALUE;
        weight = Long.MAX_VALUE;
    }

    public BinarySolution(BinarySolution other) {
        this.chromosome = other.chromosome.clone();
        profit = other.getProfit();
        weight = other.getWeight();
    }

    public byte getBit(int position) {
        return chromosome[position];
    }

    public double getProfit() {
        return profit;
    }

    private void setProfit(double profit) {
        this.profit = profit;
    }

    private long getWeight() {
        return weight;
    }

    private void setWeight(long weight) {
        this.weight = weight;
    }

    public void flip(int position) {
        chromosome[position] = (byte) (chromosome[position] ^ 1);
    }

    public void shuffle() {
        for (int i = 0; i < chromosome.length; i++) {
            if (Math.random() > 0.3) {
                chromosome[i] = 0;
            } else {
                chromosome[i] = 1;
            }
        }
    }

    public int getSize() {
        return chromosome.length;
    }

    public void updateFitness(KnapsackData data, double alpha) {
        long sumVal = 0, sumWeight = 0;
        for (int i = 0; i < data.getSize(); i++) {
            Item item = data.getData(i);
            if (getBit(i) == 1) {
                sumWeight += item.getWeight();
            } else {
                sumVal += item.getProfit();
            }
        }
        double violation = Math.max((double) sumWeight / data.getMaximumWeight() - 1, 0);
        setWeight(sumWeight);
        setProfit(sumVal + alpha * violation);
    }

    @Override
    public String toString() {
        return Arrays.toString(chromosome);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(profit, weight);
        result = 31 * result + Arrays.hashCode(chromosome);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BinarySolution other = (BinarySolution) obj;
        return Arrays.equals(this.chromosome, other.chromosome);
    }

}
