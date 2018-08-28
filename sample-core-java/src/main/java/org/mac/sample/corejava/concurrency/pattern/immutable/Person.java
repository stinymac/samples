 /*
  *      (             |"|           !!!       #   ___                             o
  *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
  *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  *
  * 虽不能至,心向往之。(Although I can't, my heart is longing for it.)
  *
  *
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  */
package org.mac.sample.corejava.concurrency.pattern.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mac
 * @create 2018-05-25 17:37
 *
 */
public final class Person {

    private final String name;
    private final List<String> addresses;


    public Person(String name, List<String> addressList) {
        synchronized (Person.class) {
            this.name = name;
            List<String> copy = new ArrayList<>();
            Collections.copy(copy, addressList);
            this.addresses = copy;
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
