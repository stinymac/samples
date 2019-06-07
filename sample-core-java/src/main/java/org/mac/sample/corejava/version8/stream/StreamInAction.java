/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.version8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 *
 * @auther mac
 * @date 2019-05-26
 */
public class StreamInAction {

    public static void main(String[] args) {

        org.mac.sample.corejava.version8.stream.Trader raoul = new org.mac.sample.corejava.version8.stream.Trader("Raoul","Cambridge");
        org.mac.sample.corejava.version8.stream.Trader mario = new org.mac.sample.corejava.version8.stream.Trader("Mario","Milan");
        org.mac.sample.corejava.version8.stream.Trader alan  = new org.mac.sample.corejava.version8.stream.Trader("Alan", "Cambridge");
        org.mac.sample.corejava.version8.stream.Trader brian = new org.mac.sample.corejava.version8.stream.Trader("Brian","Cambridge");

        List<org.mac.sample.corejava.version8.stream.Transaction> transactions = Arrays.asList(
                new org.mac.sample.corejava.version8.stream.Transaction(brian, 2011, 300),
                new org.mac.sample.corejava.version8.stream.Transaction(raoul, 2012, 1000),
                new org.mac.sample.corejava.version8.stream.Transaction(raoul, 2011, 400),
                new org.mac.sample.corejava.version8.stream.Transaction(mario, 2012, 710),
                new org.mac.sample.corejava.version8.stream.Transaction(mario, 2012, 700),
                new org.mac.sample.corejava.version8.stream.Transaction(alan,  2012, 950)
        );
        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        System.out.println("1--------------------------");
        List<org.mac.sample.corejava.version8.stream.Transaction> t2011 = transactions.stream()
                    .filter(t -> 2011 == t.getYear())
                    .sorted(Comparator.comparing(org.mac.sample.corejava.version8.stream.Transaction::getValue))
                    .collect(toList());
        System.out.println(t2011);

        //2.What are all the unique cities where the traders work?
        System.out.println("2--------------------------");
        transactions.stream().map(t -> t.getTrader().getCity()).distinct()
                    .forEach(System.out::println);

        //3.Find all traders from Cambridge and sort them by name.
        System.out.println("3--------------------------");
        transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                    .map(org.mac.sample.corejava.version8.stream.Transaction::getTrader)
                    .distinct()
                    .sorted(Comparator.comparing(org.mac.sample.corejava.version8.stream.Trader::getName))
                    .forEach(System.out::println);

        //4.Return a string of all traders’ names sorted alphabetically
        System.out.println("4--------------------------");
        String names = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted()
                    .reduce("",(o,n) -> o + ","+n);
        System.out.println(names);

        //5. Are any traders based in Milan?
        System.out.println("5--------------------------");
        boolean tradersBasedInMilan = transactions.stream().anyMatch(t -> "Milan".equals(t.getTrader().getCity()));
        System.out.println("Are any traders based in Milan? "+tradersBasedInMilan);

        //6.Print all transactions’ values from the traders living in Cambridge.
        System.out.println("6--------------------------");
        transactions.stream().filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                    .map(org.mac.sample.corejava.version8.stream.Transaction::getValue).forEach(System.out::println);

        //7.What’s the highest value of all the transactions?
        System.out.println("7--------------------------");
        Optional<Integer> maxOptional = transactions.stream().map(org.mac.sample.corejava.version8.stream.Transaction::getValue).reduce((i, j) -> i > j ? i : j);
        System.out.println(maxOptional.get());

        //8.Find the transaction with the smallest value.
        System.out.println("8--------------------------");
        Optional<Integer> minOptional = transactions.stream().map(org.mac.sample.corejava.version8.stream.Transaction::getValue).reduce(Integer::min);
        System.out.println(minOptional.get());
    }
}
