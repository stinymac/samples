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

package org.mac.sample.spring;

import org.springframework.cglib.beans.BeanCopier;

import java.time.LocalDateTime;

/**
 * CglibBeanCopierPropertiesCopier             100 times copy cost:[3]  ms
 * StaticCglibBeanCopierPropertiesCopier       100 times copy cost:[0]  ms
 * SpringBeanUtilsPropertiesCopier             100 times copy cost:[6]  ms
 * ApacheCommonsBeanUtilsPropertiesCopier      100 times copy cost:[16] ms
 * ApacheCommonsPropertyUtilsPropertiesCopier  100 times copy cost:[5]  ms
 * --------------------------------------------------------------------------------
 * CglibBeanCopierPropertiesCopier             1000 times copy cost:[8]  ms
 * StaticCglibBeanCopierPropertiesCopier       1000 times copy cost:[0]  ms
 * SpringBeanUtilsPropertiesCopier             1000 times copy cost:[10] ms
 * ApacheCommonsBeanUtilsPropertiesCopier      1000 times copy cost:[40] ms
 * ApacheCommonsPropertyUtilsPropertiesCopier  1000 times copy cost:[10] ms
 * --------------------------------------------------------------------------------
 * CglibBeanCopierPropertiesCopier             10000 times copy cost:[25]  ms
 * StaticCglibBeanCopierPropertiesCopier       10000 times copy cost:[0]   ms
 * SpringBeanUtilsPropertiesCopier             10000 times copy cost:[30]  ms
 * ApacheCommonsBeanUtilsPropertiesCopier      10000 times copy cost:[134] ms
 * ApacheCommonsPropertyUtilsPropertiesCopier  10000 times copy cost:[41]  ms
 * --------------------------------------------------------------------------------
 * CglibBeanCopierPropertiesCopier             100000 times copy cost:[52]  ms
 * StaticCglibBeanCopierPropertiesCopier       100000 times copy cost:[1]   ms
 * SpringBeanUtilsPropertiesCopier             100000 times copy cost:[34]  ms
 * ApacheCommonsBeanUtilsPropertiesCopier      100000 times copy cost:[667] ms
 * ApacheCommonsPropertyUtilsPropertiesCopier  100000 times copy cost:[279] ms
 * --------------------------------------------------------------------------------
 * CglibBeanCopierPropertiesCopier             1000000 times copy cost:[58]   ms
 * StaticCglibBeanCopierPropertiesCopier       1000000 times copy cost:[4]    ms
 * SpringBeanUtilsPropertiesCopier             1000000 times copy cost:[324]  ms
 * ApacheCommonsBeanUtilsPropertiesCopier      1000000 times copy cost:[4411] ms
 * ApacheCommonsPropertyUtilsPropertiesCopier  1000000 times copy cost:[2804] ms
 * --------------------------------------------------------------------------------
 * CglibBeanCopierPropertiesCopier             10000000 times copy cost:[586]   ms
 * StaticCglibBeanCopierPropertiesCopier       10000000 times copy cost:[49]    ms
 * SpringBeanUtilsPropertiesCopier             10000000 times copy cost:[2092]  ms
 * ApacheCommonsBeanUtilsPropertiesCopier      10000000 times copy cost:[43904] ms
 * ApacheCommonsPropertyUtilsPropertiesCopier  10000000 times copy cost:[28159] ms
 * --------------------------------------------------------------------------------
 *
 * @auther mac
 * @date 2019-06-11
 */
public class PropertyCopyContrastTest {

    public static void main(String[] args) throws Exception {
        PropertiesCopier cglibBeanCopier = new CglibBeanCopierPropertiesCopier();
        PropertiesCopier staticCglibBeanCopier = new StaticCglibBeanCopierPropertiesCopier();
        PropertiesCopier springBeanUtilsPropertiesCopier = new SpringBeanUtilsPropertiesCopier();
        PropertiesCopier apacheCommonsBeanUtilsPropertiesCopier = new ApacheCommonsBeanUtilsPropertiesCopier();
        PropertiesCopier apacheCommonsPropertyUtilsPropertiesCopier = new ApacheCommonsPropertyUtilsPropertiesCopier();

        int[] timesArray = new int[]{100,1000,10000,100000,1000000,10000000};
        for (int i = 0,length = timesArray.length; i < length ; i++) {
            int times = timesArray[i];
            doPropertiesCopy(cglibBeanCopier, times);
            doPropertiesCopy(staticCglibBeanCopier, times);
            doPropertiesCopy(springBeanUtilsPropertiesCopier, times);
            doPropertiesCopy(apacheCommonsBeanUtilsPropertiesCopier, times);
            doPropertiesCopy(apacheCommonsPropertyUtilsPropertiesCopier, times);
            System.out.println("--------------------------------------------------------------------------------");
        }

    }

    public static void doPropertiesCopy(PropertiesCopier copier,int times) throws Exception {
        Account source = new Account("621109089121237901", 99999999.99, 1, true, LocalDateTime.of(2099,12,31,23,59,59));
        Account target = new Account();

        copier.copyProperties(source,target);
        long startTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            copier.copyProperties(source,target);
        }
        long endTime = System.nanoTime();
        System.out.println(copier.getClass().getName() + "-> " + times + " times copy cost:["+(endTime-startTime)+"] ns");
    }

    public interface PropertiesCopier {
        void copyProperties(Object source, Object target) throws Exception;
    }

    public static class CglibBeanCopierPropertiesCopier implements PropertiesCopier{
        @Override
        public void copyProperties(Object source, Object target) throws Exception {
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            copier.copy(source, target, null);
        }
    }

    /**全局静态 BeanCopier，避免每次都生成新的对象*/
    public static class StaticCglibBeanCopierPropertiesCopier implements PropertiesCopier {
        private  BeanCopier copier = BeanCopier.create(Account.class, Account.class, false);

        @Override
        public void copyProperties(Object source, Object target) throws Exception {
            copier.copy(source, target, null);
        }
    }

    public static class SpringBeanUtilsPropertiesCopier implements PropertiesCopier {
        @Override
        public void copyProperties(Object source, Object target) throws Exception {
            org.springframework.beans.BeanUtils.copyProperties(source, target);
        }
    }

    public static class ApacheCommonsBeanUtilsPropertiesCopier implements PropertiesCopier {
        @Override
        public void copyProperties(Object source, Object target) throws Exception {
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
        }
    }

    public static class ApacheCommonsPropertyUtilsPropertiesCopier implements PropertiesCopier {
        @Override
        public void copyProperties(Object source, Object target) throws Exception {
            org.apache.commons.beanutils.PropertyUtils.copyProperties(target, source);
        }
    }

    public static class Account {

        private String sn;
        private double balance;
        private Integer type;
        private boolean active;
        private LocalDateTime expireDateTime;

        public Account() {
        }

        public Account(String sn, double balance, Integer type, boolean active, LocalDateTime expireDateTime) {
            this.sn = sn;
            this.balance = balance;
            this.type = type;
            this.active = active;
            this.expireDateTime = expireDateTime;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public LocalDateTime getExpireDateTime() {
            return expireDateTime;
        }

        public void setExpireDateTime(LocalDateTime expireDateTime) {
            this.expireDateTime = expireDateTime;
        }
    }
}
