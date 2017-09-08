package io.github.timtim17.makeamountlimitedcoins.coin;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

/**
 * @author Austin Jenchi (timtim17)
 */
public class CoinCounterTest {
    private static final Coin[] ALL_COINS = {Coin.PENNY, Coin.NICKEL,
            Coin.DIME, Coin.QUARTER, Coin.HALF_DOLLAR, Coin.DOLLAR};
    public static final List<Coin> EXCLUDE_PENNY = Collections.singletonList(Coin.PENNY);

    @Test
    public void testAddCoin() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        c.addCoin(Coin.PENNY);
        assertEquals(1, c.getCountOfCoin(Coin.PENNY));

        c.addCoin(Coin.QUARTER, 10);
        assertEquals(1, c.getCountOfCoin(Coin.PENNY));
        assertEquals(10, c.getCountOfCoin(Coin.QUARTER));

        c.addCoin(Coin.PENNY, 0);
        assertEquals(11, c.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCoinExceptionInvalidType() throws Exception {
        CoinCounter c = constructSomeCoinCounter(EXCLUDE_PENNY);
        c.addCoin(Coin.PENNY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCoinExceptionInvalidNum() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        c.addCoin(Coin.PENNY, -5);
    }

    @Test
    public void testRemoveCoin() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        c.addCoin(Coin.PENNY, 10);

        c.removeCoin(Coin.PENNY);
        assertEquals(9, c.getCountOfCoin(Coin.PENNY));

        c.removeCoin(Coin.PENNY, 8);
        assertEquals(1, c.getCountOfCoin(Coin.PENNY));

        c.removeCoin(Coin.PENNY, 0);
        assertEquals(1, c.getCountOfCoin(Coin.PENNY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCoinIllegalArgumentExceptionInvalidType() throws
            Exception {
        CoinCounter c = constructSomeCoinCounter(EXCLUDE_PENNY);
        c.removeCoin(Coin.PENNY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCoinIllegalArgumentExceptionInvalidNum() throws
            Exception {
        CoinCounter c = constructSomeCoinCounter(EXCLUDE_PENNY);
        c.removeCoin(Coin.PENNY, -5);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveCoinIllegalStateException() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        // pennies = 0
        c.removeCoin(Coin.PENNY);
    }

    @Test
    public void testGetCoinCount() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        assertEquals(0, c.getCountOfCoin(Coin.PENNY));

        c.addCoin(Coin.PENNY);
        assertEquals(1, c.getCountOfCoin(Coin.PENNY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCoinCountException() throws Exception {
        CoinCounter c = constructSomeCoinCounter(EXCLUDE_PENNY);
        c.getCountOfCoin(Coin.PENNY);
    }

    @Test
    public void testSize() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        assertEquals(0, c.size());

        c.addCoin(Coin.PENNY);
        assertEquals(1, c.size());

        c.addCoin(Coin.QUARTER);
        assertEquals(2, c.size());

        c.removeCoin(Coin.PENNY);
        assertEquals(1, c.size());
    }

    @Test
    public void testSum() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        assertEquals(0, c.sum());

        c.addCoin(Coin.PENNY);
        assertEquals(1, c.sum());

        c.addCoin(Coin.QUARTER);
        assertEquals(26, c.sum());

        c.removeCoin(Coin.PENNY);
        assertEquals(25, c.sum());
    }

    @Test
    public void testToString() throws Exception {
        CoinCounter c = constructAllCoinCounter();
        assertEquals("", c.toString());

        c.addCoin(Coin.PENNY);
        assertEquals("1 Penny", c.toString());

        c.addCoin(Coin.DOLLAR, 5);
        assertEquals("1 Penny, 5 Dollar Coins", c.toString());
    }


    private CoinCounter constructAllCoinCounter() {
        return constructSomeCoinCounter(Collections.emptyList());
    }

    private CoinCounter constructSomeCoinCounter(List<Coin> exclude) {
        return new CoinCounter(
                Arrays.stream(ALL_COINS)
                        .filter(t -> !exclude.contains(t))
                        .collect(toList())
        );
    }
}