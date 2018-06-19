package es.uji.giant.statistics;

import cern.jet.math.Bessel;
import cern.jet.random.VonMises;
import cern.jet.random.engine.RandomEngine;

public class VonMisesWithDensity extends AbstractContinuousDistributionWithDensity {
    protected double mu;
    protected final double kappa;

    public VonMisesWithDensity(double mu, double kappa, RandomEngine randomEngine) {
        super(new VonMises(kappa, randomEngine));
        this.mu = mu;
        this.kappa = kappa;
    }

    @Override
    public int nextInt() {
        return super.nextInt() + (int)mu;
    }

    @Override
    public double nextDouble() {
        return super.nextDouble() + mu;
//        return mu;
    }

    public double density(final double x) {
        double num, den;

        num = Math.exp(kappa * Math.cos(x-mu));
        den = 2 * Math.PI * Bessel.i0(kappa);

        return num/den;
    }
}
