package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChessResults {

    private final List<String> names = new ArrayList<>();
    private final List<Competitor> competitorList = new ArrayList<>();

    public List<String> getCompetitorsNamesFromFile(String fileName){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();
            while(line != null) {
                competitorList.add(new Competitor(line));

                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }

        while (competitorList.size() != 0) {
            int index = -1;
            String currentName = "---";
            int currentScore = -1;

            for (int i = 0; i < competitorList.size(); i++) {
                Competitor competitor = competitorList.get(i);
                if (competitor.getScore() > currentScore) {
                    index = i;
                    currentName = competitor.getName();
                    currentScore = competitor.getScore();
                }
            }

            names.add(currentName);
            competitorList.remove(index);
        }

        return names;
    }

    private static class Competitor {

        private final String name;
        private int score = 0;

        public Competitor(String competitorString) {
            String[] parsedCompetitorString = competitorString.split(",");
            name = parsedCompetitorString[0];
            for (int i = 1; i < parsedCompetitorString.length; i++) {
                score += Integer.parseInt(parsedCompetitorString[i]);
            }
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}

