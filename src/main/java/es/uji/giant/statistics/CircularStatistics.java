package es.uji.giant.statistics;

import org.apache.commons.math3.analysis.function.Atan2;

import java.util.Arrays;

public class CircularStatistics {
    private final Lazy meanX2Lazy = new MeanX2Lazy();
    private final Lazy meanY2Lazy = new MeanY2Lazy();
    private final Lazy meanX2NotLazy = new MeanX2();
    private final Lazy meanY2NotLazy = new MeanY2();
    private Lazy meanX2 = meanX2NotLazy;
    private Lazy meanY2 = meanY2NotLazy;
    private double[] data;

    public CircularStatistics() {
        super();
    }

    public void setData(final double[] data) {
        this.data = data;
    }

    public double lengthMeanVector() {
        return Math.sqrt(meanX2() + meanY2());
    }

    private double meanX2() {
        return meanX2.reDo();
    }

    private double meanY2() {
        return meanY2.reDo();
    }

    public double circularMean() {
        double cosinus = Math.sqrt(meanX2()) / lengthMeanVector();
        double sinus = Math.sqrt(meanY2()) / lengthMeanVector();
        double atan2 = new Atan2().value(cosinus, sinus);
        if(cosinus < 0 ) return Math.PI + atan2;
        else return atan2;
    }

    public double circularVariance() {
        return 1 - lengthMeanVector();
    }

    // https://en.wikipedia.org/wiki/Directional_statistics
    public double circularStandardDeviation() {
        return Math.sqrt(-2 * Math.log(lengthMeanVector()));
    }

    private interface Lazy {
        double reDo();
        default void setMean(double mean) { }
    }

    private class MeanX2 implements Lazy {
        @Override
        public double reDo() {
            double meanX = Arrays.stream(data)
                    .map(d -> Math.cos(d + Math.PI/2))
                    .average()
                    .getAsDouble();

            meanX2Lazy.setMean(meanX * meanX);
            meanX2 = meanX2Lazy;

            return meanX * meanX;
        }
    }

    private class MeanY2 implements Lazy {
        @Override
        public double reDo() {
            double meanY = Arrays.stream(data)
                    .map(d -> Math.sin(d + Math.PI/2))
                    .average()
                    .getAsDouble();

            meanY2Lazy.setMean(meanY * meanY);
            meanY2 = meanY2Lazy;

            return meanY * meanY;
        }
    }

    private class MeanX2Lazy implements Lazy {
        double meanX2;

        @Override
        public double reDo() {
            return meanX2;
        }

        @Override
        public void setMean(double mean) {
            meanX2 = mean;
        }
    }

    private class MeanY2Lazy implements Lazy {
        double meanY2;

        @Override
        public double reDo() {
            return meanY2;
        }

        @Override
        public void setMean(double mean) {
            meanY2 = mean;
        }
    }
}
