package io.github.timtim17.makeamountlimitedcoins.coin;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CoinCounter {
    private Map<Coin, Integer> counts;

    public CoinCounter(List<Coin> coins) {
        counts = new TreeMap<>();

        for (Coin c : coins) {
            counts.put(c, 0);
        }
    }

    public void addCoin(Coin type) throws IllegalArgumentException {
        addCoin(type, 1);
    }

    public void addCoin(Coin type, int num) throws IllegalArgumentException {
        checkValidType(type);
        checkNumCoinsValid(num);

        counts.put(type, counts.get(type) + num);
    }

    public void removeCoin(Coin type) throws IllegalArgumentException,
            IllegalStateException {
        removeCoin(type, 1);
    }

    public void removeCoin(Coin type, int num) throws IllegalArgumentException,
            IllegalStateException {
        checkValidType(type);
        checkNumCoinsValid(num);

        int newCount = counts.get(type) - num;
        if (newCount < 0) {
            throw new IllegalStateException();
        }
        counts.put(type, newCount);
    }

    public int getCountOfCoin(Coin type) throws IllegalArgumentException {
        checkValidType(type);

        return counts.get(type);
    }

    public int size() {
        int size = 0;
        for (Coin c : counts.keySet()) {
            size += counts.get(c);
        }
        return size;
    }

    public int sum() {
        int sum = 0;
        for (Coin c : counts.keySet()) {
            sum += counts.get(c) * c.getValue();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean hasAtLeastOne = false;
        for (Coin c : counts.keySet()) {
            int count = counts.get(c);
            if (count > 0) {
                if (hasAtLeastOne) {
                    sb.append(", ").append(pluralHelper(c, count));
                } else {
                    sb.append(pluralHelper(c, count));
                    hasAtLeastOne = true;
                }
            }
        }
        return sb.toString();
    }

    private void checkValidType(Coin type) throws IllegalArgumentException {
        if (!counts.containsKey(type)) {
            throw new IllegalArgumentException();
        }
    }

    private void checkNumCoinsValid(int num) throws IllegalArgumentException {
        if (num < 0) {
            throw new IllegalArgumentException();
        }
    }

    private String pluralHelper(Coin type, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException();
        }

        if (count == 1) {
            return "1 " + type.getName();
        } else {
            return count + " " + type.getNamePlural();
        }
    }
}
