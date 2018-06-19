package es.uji.giant.main;

import cern.jet.random.engine.RandomEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.uji.giant.data.Parameters;
import es.uji.giant.statistics.AbstractContinuousDistributionWithDensity;
import es.uji.giant.statistics.VonMisesLinearWithDensity;
import es.uji.giant.utils.DataGenerator;
import es.uji.giant.utils.IntervalRandomGenerator;
import es.uji.giant.utils.VonMisestDataGenerator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        new Main().go(args[0]);
//        new Main().go2();
//        new Main().go3();
//        new Main().go4();
    }

    private void go(final String fileName) {
        try {
            Reader reader = new FileReader(Paths.get(fileName).toFile());
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            Parameters parameters = gson.fromJson(reader, Parameters.class);

            AbstractContinuousDistributionWithDensity before = new VonMisesLinearWithDensity(parameters.before.getMu(), parameters.before.getKappa(), parameters.before.getBeta(), RandomEngine.makeDefault());
            AbstractContinuousDistributionWithDensity after = new VonMisesLinearWithDensity(parameters.after.getMu(), parameters.after.getKappa(), parameters.after.getBeta(), RandomEngine.makeDefault());

            DataGenerator generator = new VonMisestDataGenerator()
                    .withDistributionBefore(before)
                    .withSamplesBefore(parameters.before.getSamples())
                    .withDistributionAfter(after)
                    .withSamplesAfter(parameters.after.getSamples());

            generator.reset();

            double[] data = generator.generateData();
            System.out.println(data.length);
//            Arrays.stream(data)
//                    .forEach(System.out::println);

        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " does not exists");
        }
    }

    private void go3() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        Parameters parameters = new Parameters(new Parameters.Parameter(0,0,0, 50), new Parameters.Parameter(1,1,1, 50));

        System.out.println(gson.toJson(parameters));

        System.out.println(parameters.after.getKappa());
    }

    private void go4() {
        IntervalRandomGenerator random = new IntervalRandomGenerator(10);
        random.unsorted();
    }
}
