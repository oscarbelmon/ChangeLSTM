package es.uji.giant.main;

import es.uji.giant.utils.SequencesGenerator;

public class Main {
    public static void main(String[] args) {
        SequencesGenerator main = new SequencesGenerator(args[0], args[1]);
        main.go();
    }
}
