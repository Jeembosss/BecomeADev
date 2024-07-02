package com.popov.portalOne.core;

import java.util.Collections;
import java.util.List;

public class FindNumbers {
    public static List<Integer> DATA;

    public static long arithmeticMean() {
        long arithmeticMean = 0;
        for (Integer num : DATA) {
            arithmeticMean += num;
        }
        arithmeticMean /= DATA.size();
        return arithmeticMean;
    }

    public static long[] increasingAndDecreasingSequenceOfNum() {
        int maxIncreasingLength = 1;
        int currentIncreasingLength = 1;
        int maxDecreasingLength = 1;
        int currentDecreasingLength = 1;

        for (int i = 1; i < DATA.size(); i++) {
            if (DATA.get(i) > DATA.get(i - 1)) {
                currentIncreasingLength++;
            } else {
                if (currentIncreasingLength > maxIncreasingLength) {
                    maxIncreasingLength = currentIncreasingLength;
                }
                currentIncreasingLength = 1;
            }

            if (DATA.get(i) < DATA.get(i - 1)) {
                currentDecreasingLength++;
            } else {
                if (currentDecreasingLength > maxDecreasingLength) {
                    maxDecreasingLength = currentDecreasingLength;
                }
                currentDecreasingLength = 1;
            }

        }
        if (currentIncreasingLength > maxIncreasingLength) {
            maxIncreasingLength = currentIncreasingLength;
        }
        if (currentDecreasingLength > maxDecreasingLength) {
            maxDecreasingLength = currentDecreasingLength;
        }
        return new long[]{maxIncreasingLength, maxDecreasingLength};
    }

    public static long[] MaxMinMedian() {
        Collections.sort(DATA);
        long median;
        long max = DATA.get(DATA.size() - 1);
        long min = DATA.get(0);

        if (DATA.size() % 2 == 0) {
            int mid1 = DATA.size() / 2;
            int mid2 = mid1 - 1;
            median = (DATA.get(mid1) + DATA.get(mid2)) / 2;
        } else {
            int mid = DATA.size() / 2;
            median = DATA.get(mid);
        }

        return new long[]{max, min, median};
    }


}
