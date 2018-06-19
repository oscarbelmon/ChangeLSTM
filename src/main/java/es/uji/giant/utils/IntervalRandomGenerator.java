package es.uji.giant.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntervalRandomGenerator {
    private final int samples;

    public IntervalRandomGenerator(int samples) {
        this.samples = samples;
    }

    public int[] unsorted() {
        int[] data = new int[samples-1];
        List<Integer> range = IntStream.rangeClosed(0, samples-1)
                .boxed().collect(Collectors.toList());

        Random random = new Random();

        for(int i = 0; i < samples-1; i++) {
            int index = random.nextInt(range.size());
            data[i] = range.get(index);
            range.remove(index);
        }

        return data;
    }
}
