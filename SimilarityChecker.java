package application;

import java.util.HashSet;

public class SimilarityChecker {

    // Word-level similarity ignoring order and punctuation
    public static int calculateApproximate(String a, String b) {
        a = a.toLowerCase().replaceAll("[^a-z ]", " ").trim();
        b = b.toLowerCase().replaceAll("[^a-z ]", " ").trim();

        String[] wordsA = a.split("\\s+");
        String[] wordsB = b.split("\\s+");

        HashSet<String> setA = new HashSet<>();
        HashSet<String> setB = new HashSet<>();

        for (String w : wordsA) setA.add(w);
        for (String w : wordsB) setB.add(w);

        int common = 0;
        for (String w : setA) if (setB.contains(w)) common++;

        double similarity = ((2.0 * common) / (setA.size() + setB.size())) * 100;
        return (int) Math.round(similarity);
    }
}
