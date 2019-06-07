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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 创建Stream
 *
 * @auther mac
 * @date 2019-05-20
 */
public class CreateStreamSample {
    public static void main(String[] args) {
        createStreamFromCollection().forEach(System.out::println);
        createStreamFromValues().forEach(System.out::println);
        createStreamFromArrays().forEach(System.out::println);
        /*try {
            createStreamFromFile().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        createStreamFromIterator().forEach(System.out::println);
        createStreamFromGenerate().forEach(System.out::println);
        createEntryStreamFromGenerate().forEach(System.out::println);
    }

    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("Tom","Jerry","Spike");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues() {
        return Stream.of("Tom","Jerry","Spike");
    }

    private static Stream<String> createStreamFromArrays() {
        return Arrays.stream(new String[]{"Tom","Jerry","Spike"});
    }

    private static Stream<String> createStreamFromFile() throws IOException {
        Path path = Paths.get("E:\\IDEAWorkspace\\stinymac\\samples\\sample-core-java\\src\\main\\java\\org\\mac\\sample\\corejava\\version8\\stream\\Dish.java");
        Stream<String> lines = Files.lines(path);
        return lines;
    }

    private static Stream<Integer> createStreamFromIterator()  {
        return Stream.iterate(0,n -> n+2).limit(10);
    }

    private static Stream<Double> createStreamFromGenerate()  {
        return Stream.generate(Math::random).limit(10);
    }

    private static Stream<Entry> createEntryStreamFromGenerate()  {
        return Stream.generate(new SimpleSupplier()).limit(10);
    }

    static class SimpleSupplier implements Supplier<Entry> {

        private int index =0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Entry get() {
            index = random.nextInt(100);
            return new Entry(index,"::"+ index);
        }
    }

    static class Entry {

        private int id;
        private String name;

        public Entry(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Entry {" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
