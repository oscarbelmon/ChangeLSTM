package es.uji.giant.statistics;

import cern.jet.random.engine.RandomEngine;

public class VonMisesLinearWithDensity extends VonMisesWithDensity {
    private double speed;
    private double myMu;

    public VonMisesLinearWithDensity(double mu, double kappa, double speed, RandomEngine randomEngine) {
        super(mu, kappa, randomEngine);
        this.speed = speed;
        this.myMu = mu;
    }

    @Override
    public double nextDouble() {
        mu += speed;
        return super.nextDouble();
    }

    @Override
    public int nextInt() {
        mu += speed;
        return super.nextInt();
    }

    @Override
    public void reset() {
        mu = myMu;
    }
}
