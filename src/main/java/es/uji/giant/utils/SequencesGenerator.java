package es.uji.giant.utils;

import cern.jet.random.engine.RandomEngine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import es.uji.giant.data.Parameters;
import es.uji.giant.main.Main;
import es.uji.giant.statistics.AbstractContinuousDistributionWithDensity;
import es.uji.giant.statistics.VonMisesLinearWithDensity;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SequencesGenerator {
    private String input;
    private String output;

    public SequencesGenerator(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public void go() {
        try {
            Reader reader = new FileReader(Paths.get(input).toFile());
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            Type type = new TypeToken<List<Parameters>>() {
            }.getType();

            List<Parameters> parameters = gson.fromJson(reader, type);

            parameters.stream()
                    .forEach(this::process);

        } catch (FileNotFoundException e) {
            System.out.println("File " + input + " does not exists");
        }
    }

    private void process(Parameters parameters) {
        AbstractContinuousDistributionWithDensity before = new VonMisesLinearWithDensity(parameters.before.getMu(), parameters.before.getKappa(), parameters.before.getBeta(), RandomEngine.makeDefault());
        AbstractContinuousDistributionWithDensity after = new VonMisesLinearWithDensity(parameters.after.getMu(), parameters.after.getKappa(), parameters.after.getBeta(), RandomEngine.makeDefault());

        DataGenerator generator = new VonMisestDataGenerator()
                .withDistributionBefore(before)
                .withSamplesBefore(parameters.before.getSamples())
                .withDistributionAfter(after)
                .withSamplesAfter(parameters.after.getSamples());

        for(int i = 0; i < parameters.getIterations(); i++) {
            generator.reset();

            double[] data = generator.generateData();

            window(data, parameters.before.getSamples());
        }

    }

    private void window(double[] data, int samples) {
        List<Double> doubles = Arrays.stream(data)
                .boxed()
                .collect(Collectors.toList());

        List<String> list = new ArrayList<>();

        list.add(doubles.subList(0 , samples).toString() + ", 0");
        for(int i = 1; i < samples; i++) {
            list.add(doubles.subList(i , i+samples).toString() + ", " + (samples-i));
        }

        try {
            Files.write(Paths.get(output), list, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
