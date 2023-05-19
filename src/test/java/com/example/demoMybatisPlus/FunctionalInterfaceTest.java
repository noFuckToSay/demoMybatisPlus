package com.example.demoMybatisPlus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author:22024002
 * @date:2023/5/8 15:09
 * @description:
 */
@SpringBootTest
@Slf4j
public class FunctionalInterfaceTest {

    /**
     * 消费者接口-Consumer-accept()
     */
    @Test
    void testConsumer() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        //函数式接口-消费者接口
        Consumer<Integer> printOperation = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("打印 " + integer);
            }
        };
        nums.forEach(printOperation);
    }

    @Test
    void testConsumerLamda() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        //消费者接口-lamda表达式调用

        nums.forEach(integer -> {
            System.out.println("打印 " + integer);
        });
    }

    /**
     * 断言接口-Predicate-test、and、or、isEqual、negate
     */
    @Test
    void testPredicate() {
        Integer target = 3;
        Predicate<Integer> pre = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 0;
            }
        };

        //断言接口调用
        System.out.println(pre.test(target));
    }

    /**
     * 断言接口-Predicate-test、and、or、isEqual、negate
     */
    @Test
    void testPredicateLamda() {
        Integer num = 0;
//        Predicate<Integer> pre=(n)->{return n>0;};
        Predicate<Integer> gtZero = n -> n > 0;  //第一个断言:n>0
        Predicate<Integer> gtTen = n -> n > 10; //第二个断言:n>10
        Predicate<Integer> eqZero = Predicate.isEqual(0);    //第三个断言:n==0

        //断言接口调用
        System.out.println(gtZero.test(num));
        System.out.println(gtZero.and(gtTen).test(num));
        System.out.println(gtZero.or(gtTen).test(num));
        System.out.println(gtTen.negate().test(num));
        System.out.println(eqZero.test(num));
    }

    /**
     * 生产者接口-Supplier-get()
     */
    @Test
    void testSupplier() {
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };

        System.out.println(supplier.get());
    }

    @Test
    void testSupplierLamda() {
        Supplier<Integer> supplier = () -> {
            return 1024;
        };

        System.out.println(supplier.get());
    }

    /**
     * 函数接口-Function-apply()、compose()、andThen()、identity()
     */
    @Test
    void testFunction() {
        Integer num = 3;
        Function<Integer, String> addThree = new Function<Integer, String>() {     //把整数加三,然后转为字符串
            @Override
            public String apply(Integer integer) {
                integer += 3;
                return String.format("%d", integer);
            }
        };

        System.out.println(addThree.apply(num));
    }

    @Test
    void testFunctionOthers() {
        Integer num = 3;
        Function<Integer, Integer> addThree = (integer) -> {
            System.out.println("执行+3...");
            integer += 3;
            return integer;
        };     //数字加3功能
//        Function<Integer,Integer> addThree=new Function<Integer, Integer>() {     //数字加3功能
//            @Override
//            public Integer apply(Integer integer) {
//                System.out.println("执行+3...");
//                integer+=3;
//                return integer;
//            }
//        };

        Function<Integer, Integer> addFive = (integer) -> {
            System.out.println("执行+5...");
            integer += 5;
            return integer;
        };    //数字加5功能
//        Function<Integer,Integer> addFive=new Function<Integer, Integer>() {    //数字加5功能
//            @Override
//            public Integer apply(Integer integer) {
//                System.out.println("执行+5...");
//                integer+=5;
//                return integer;
//            }
//        };

        Function<Integer, Integer> identity = Function.identity();                 //返回数字本身功能

        System.out.println(addThree.compose(addFive).apply(num));   //先执行自增5的功能,再执行自增3的功能
        System.out.println(addThree.andThen(addFive).apply(num));   //先执行自增3的功能,再执行自增5的功能
        System.out.println(identity.apply(num));                    //返回要操作的元素本身
    }

    @Test
    void testHashMap(){
        Map<String,Object> map=new HashMap<>();
        List<String> list=Arrays.asList("111","111");
        int result=list.stream().filter(item->{
            return null==map.putIfAbsent(item,item);
        }).mapToInt(a->Integer.parseInt(a)).sum();
        System.out.println(result);
        System.out.println(list.size());
    }

    @Test
    void testStreamMax(){
        Map<String,Object> map=new HashMap<>();
        List<String> list=Arrays.asList("111","222","333");
        Long maxValue=list.stream().mapToLong(Long::parseLong).max().getAsLong();
        Long minValue=list.stream().mapToLong(Long::parseLong).min().getAsLong();
        System.out.println(maxValue);
        System.out.println(minValue);
    }
}
