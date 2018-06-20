package es.uji.giant.utils;

public class VonMisestDataGenerator extends DataGenerator {
    @Override
    public double[] generateData() {
        double[] data = new double[samplesBefore + samplesAfter];
        for(int i = 0; i < samplesBefore; i++)
            data[i] = before.nextDouble();
        for(int i = samplesBefore; i < samplesBefore + samplesAfter; i++)
            data[i] = after.nextDouble();

        return data;
    }

    @Override
    public void reset() {
        before.reset();
        after.reset();
    }

}
