package es.uji.giant.statistics;

import cern.jet.random.AbstractContinousDistribution;
import cern.jet.random.engine.RandomEngine;

public abstract class AbstractContinuousDistributionWithDensity extends AbstractContinousDistribution implements WithDensity {
    protected AbstractContinousDistribution distribution;

    public AbstractContinuousDistributionWithDensity(AbstractContinousDistribution distribution) {
        this.distribution = distribution;
    }

    public void reset() {
        // To be implemented by children
    }

    @Override
    public double apply(double v) {
        return distribution.apply(v);
    }

    @Override
    public int apply(int i) {
        return distribution.apply(i);
    }

    @Override
    public Object clone() {
        return distribution.clone();
    }

    @Override
    protected RandomEngine getRandomGenerator() {
        return super.getRandomGenerator();
    }

    @Override
    public int nextInt() {
        return distribution.nextInt();
    }

    @Override
    protected void setRandomGenerator(RandomEngine randomEngine) {
        super.setRandomGenerator(randomEngine);
    }

    @Override
    public double nextDouble() {
        return distribution.nextDouble();
    }
}
