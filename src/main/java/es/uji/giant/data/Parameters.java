package es.uji.giant.data;

public class Parameters {
    public final Parameter before;
    public final Parameter after;

    public Parameters(Parameter before, Parameter after) {
        this.before = before;
        this.after = after;
    }

    public static class Parameter {
        private final double mu;
        private final double kappa;
        private final double beta;
        private final int samples;

        public Parameter(double mu, double kappa, double beta, int samples) {
            this.mu = mu;
            this.kappa = kappa;
            this.beta = beta;
            this.samples = samples;
        }

        public double getMu() {
            return mu;
        }

        public double getKappa() {
            return kappa;
        }

        public double getBeta() {
            return beta;
        }

        public int getSamples() {
            return samples;
        }
    }
}
