/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mac.examples.concurrency.pattern.balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Entry class description
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
