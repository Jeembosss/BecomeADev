package com.popov.portalOne.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindNumbers {
    public static List<Integer> DATA;

    public static int arithmeticMean() {
        int arithmeticMean = 0;
        for (Integer num : DATA) {
            arithmeticMean += num;
        }
        arithmeticMean /= DATA.size();
        return arithmeticMean;
    }

    public static List <ArrayList<Long>> increasingAndDecreasingSequenceOfNum() {
        int maxIncreasingLength = 1;
        int currentIncreasingLength = 1;
        int maxDecreasingLength = 1;
        int currentDecreasingLength = 1;
        int endIncreasing = 0;
        int endDecreasing = 0;
        for (int i = 1; i < DATA.size(); i++) {
            if (DATA.get(i) > DATA.get(i - 1)) {
                currentIncreasingLength++;
            } else {
                if (currentIncreasingLength > maxIncreasingLength) {
                    endIncreasing = i - 1;
                    maxIncreasingLength = currentIncreasingLength;
                }
                currentIncreasingLength = 1;
            }

            if (DATA.get(i) < DATA.get(i - 1)) {
                currentDecreasingLength++;
            } else {
                if (currentDecreasingLength > maxDecreasingLength) {
                    endDecreasing = i - 1;
                    maxDecreasingLength = currentDecreasingLength;
                }
                currentDecreasingLength = 1;
            }
        }
        if (currentIncreasingLength > maxIncreasingLength) {
            endIncreasing = DATA.size() - 1;
            maxIncreasingLength = currentIncreasingLength;
        }
        if (currentDecreasingLength > maxDecreasingLength) {
            endDecreasing = DATA.size() - 1;
            maxDecreasingLength = currentDecreasingLength;
        }
        ArrayList<Long> IncreasingResult = new ArrayList<>();
        ArrayList<Long> DecreasingResult = new ArrayList<>();
        for (int i = 1; i < maxIncreasingLength; i++) {
            IncreasingResult.add(Long.valueOf(DATA.get(endIncreasing - maxIncreasingLength + i)));
        }

        for (int i = 1; i < maxDecreasingLength; i++) {
            DecreasingResult.add(Long.valueOf(DATA.get(endDecreasing - maxDecreasingLength +  i)));
        }

        List <ArrayList<Long>> results = new ArrayList<>();
        results.add(IncreasingResult);
        results.add(DecreasingResult);
        return results;
    }

    public static int[] MaxMinMedian() {
        Collections.sort(DATA);
        int median;
        int max = DATA.get(DATA.size() - 1);
        int min = DATA.get(0);

        if (DATA.size() % 2 == 0) {
            int mid1 = DATA.size() / 2;
            int mid2 = mid1 - 1;
            median = (DATA.get(mid1) + DATA.get(mid2)) / 2;
        } else {
            int mid = DATA.size() / 2;
            median = DATA.get(mid);
        }

        return new int[]{max, min, median};
    }


}
