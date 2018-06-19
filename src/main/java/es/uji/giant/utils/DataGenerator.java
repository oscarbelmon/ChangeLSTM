package es.uji.giant.utils;


import es.uji.giant.statistics.AbstractContinuousDistributionWithDensity;

public abstract class DataGenerator {
    protected AbstractContinuousDistributionWithDensity before;
    protected AbstractContinuousDistributionWithDensity after;
    protected int samplesBefore;
    protected int samplesAfter;

    public DataGenerator withDistributionBefore(final AbstractContinuousDistributionWithDensity before) {
        this.before = before;
        return this;
    }

    public DataGenerator withDistributionAfter(final AbstractContinuousDistributionWithDensity after) {
        this.after = after;
        return this;
    }

    public DataGenerator withSamplesBefore(int samplesBefore) {
        this.samplesBefore = samplesBefore;
        return this;
    }

    public DataGenerator withSamplesAfter(int samplesAfter) {
        this.samplesAfter = samplesAfter;
        return this;
    }

    public int getSamplesTotal() {
        return samplesBefore + samplesAfter;
    }

    public int getSamplesBefore() {
        return samplesBefore;
    }

    abstract public double[] generateData();
    abstract public void reset();
}
