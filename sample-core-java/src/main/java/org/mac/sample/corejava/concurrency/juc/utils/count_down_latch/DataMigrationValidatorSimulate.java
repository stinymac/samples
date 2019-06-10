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

package org.mac.sample.corejava.concurrency.juc.utils.count_down_latch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 背景：
 *
 * 数据迁移
 *
 * 将数据库中表中的数据迁移到其他平台
 *
 * 完成后事件通知校验
 *
 * @author Mac
 * @create 2018-06-14 12:43
 **/

public class DataMigrationValidatorSimulate {

    class Event {

        private int id;
        private List<SourceAndTargetDataFeature> sourceSourceAndTargetDataFeatures;

        public Event(int id) {
            this.id = id;
            this.sourceSourceAndTargetDataFeatures = new ArrayList<>();
            // 模拟一批迁移
            for (int i = 0; i < 10; i++) {
                sourceSourceAndTargetDataFeatures.add(new SourceAndTargetDataFeature("Event-"+id+"_t-"+i,1000000,"<table name='Event-"+id+"_t-"+i+"'><column name='c' type='varchar'></column></table>"));
            }

        }

        public int getId() {
            return id;
        }


        public List<SourceAndTargetDataFeature> getSourceSourceAndTargetDataFeatures() {
            return sourceSourceAndTargetDataFeatures;
        }

    }


    class SourceAndTargetDataFeature {

        private String tableName;
        private int     sourceRecordCount;
        private String  sourceTableSchema;

        private int     targetRecordCount;
        private String  targetTableSchema;
        //and ..........

        public SourceAndTargetDataFeature(String tableName, int recordCount, String tableSchema) {
            this.tableName = tableName;
            this.sourceRecordCount = recordCount;
            this.sourceTableSchema = tableSchema;
        }

        public String getTableName() {
            return tableName;
        }

        public int getSourceRecordCount() {
            return sourceRecordCount;
        }

        public void setSourceRecordCount(int sourceRecordCount) {
            this.sourceRecordCount = sourceRecordCount;
        }

        public String getSourceTableSchema() {
            return sourceTableSchema;
        }

        public void setSourceTableSchema(String sourceTableSchema) {
            this.sourceTableSchema = sourceTableSchema;
        }

        public int getTargetRecordCount() {
            return targetRecordCount;
        }

        public void setTargetRecordCount(int targetRecordCount) {
            this.targetRecordCount = targetRecordCount;
        }

        public String getTargetTableSchema() {
            return targetTableSchema;
        }

        public void setTargetTableSchema(String targetTableSchema) {
            this.targetTableSchema = targetTableSchema;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

    }

    interface Watcher {

        void  checkDone();
    }

    class DataFeatureWatcher implements Watcher {
        private final CountDownLatch latch;
        private final GroupWatcher groupWatcher;
        private final SourceAndTargetDataFeature dataFeature;

        public DataFeatureWatcher(int size, GroupWatcher groupWatcher, SourceAndTargetDataFeature dataFeature) {
            this.latch = new CountDownLatch(size);
            this.groupWatcher = groupWatcher;
            this.dataFeature = dataFeature;
        }

        @Override
        public void checkDone() {
            latch.countDown();
            if (latch.getCount() == 0) {
                System.out.println("Table ["+dataFeature.getTableName()+"] finish validate.");
                groupWatcher.checkDone();
            }
        }
    }

    class GroupWatcher implements Watcher {
        private final CountDownLatch latch;
        private final Event event;

        public GroupWatcher(int size, Event event) {
            this.latch = new CountDownLatch(size);
            this.event = event;
        }

        @Override
        public void checkDone() {
            latch.countDown();
            if (latch.getCount() == 0) {
                System.out.println("====Event ["+event.getId()+"] finish validate.====");
            }
        }
    }



    public void mockValidate() {
        Event[] events = new Event[]{new Event(1),new Event(2)};
        ExecutorService executor = Executors.newFixedThreadPool(5);


        for (int i = 0; i < events.length; i++) {

            List<SourceAndTargetDataFeature> sourceSourceAndTargetDataFeatures = events[i].getSourceSourceAndTargetDataFeatures();
            GroupWatcher groupWatcher = new GroupWatcher(sourceSourceAndTargetDataFeatures.size(),events[i]);

            for (SourceAndTargetDataFeature sourceAndTargetDataFeature : sourceSourceAndTargetDataFeatures) {

                Watcher watcher =new DataFeatureWatcher(2,groupWatcher, sourceAndTargetDataFeature);

                RecordCountValidator recordCountValidator = new RecordCountValidator(sourceAndTargetDataFeature, watcher);
                TableSchemaValidator tableSchemaValidator = new TableSchemaValidator(sourceAndTargetDataFeature, watcher);

                executor.submit(recordCountValidator);
                executor.submit(tableSchemaValidator);
            }
        }

        executor.shutdown();
    }


    private final static Random random = new Random(System.currentTimeMillis());
    private final static void mockTakeTime(int mills) {
        try {
            Thread.sleep(random.nextInt(mills));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class RecordCountValidator implements Runnable {

        private final SourceAndTargetDataFeature sourceAndTargetDataFeature;
        private final Watcher watcher;

        public RecordCountValidator(SourceAndTargetDataFeature sourceAndTargetDataFeature, Watcher watcher) {
            this.sourceAndTargetDataFeature = sourceAndTargetDataFeature;
            this.watcher = watcher;
        }

        @Override
        public void run() {
            mockTakeTime(1000);
            //mock
            sourceAndTargetDataFeature.setTargetRecordCount(sourceAndTargetDataFeature.getSourceRecordCount());
            watcher.checkDone();
        }
    }


    class TableSchemaValidator implements Runnable {

        private final SourceAndTargetDataFeature sourceAndTargetDataFeature;
        private final Watcher watcher;


        public TableSchemaValidator(SourceAndTargetDataFeature sourceAndTargetDataFeature, Watcher watcher) {
            this.sourceAndTargetDataFeature = sourceAndTargetDataFeature;
            this.watcher = watcher;
        }

        @Override
        public void run() {
            mockTakeTime(10000);
            //mock
            sourceAndTargetDataFeature.setTargetTableSchema(sourceAndTargetDataFeature.getSourceTableSchema());
            watcher.checkDone();
        }
    }
}