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

package org.mac.sample.corejava.concurrency.pattern.balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Mac
 * @create 2018-06-06 14:33
 **/

public class BalkingData {
    
    private String fileName;
    private String content;
    private boolean changed =  false;

    public BalkingData(String fileName, String content, boolean changed) {
        this.fileName = fileName;
        this.content = content;
        this.changed = changed;
    }
    
    public synchronized void change(String newContent){
        this.content = newContent;
        this.changed = true;
    }
    
    public synchronized void save () throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls do save,content=" + content);
        try (Writer writer = new FileWriter(fileName, true)) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }
    }
}
