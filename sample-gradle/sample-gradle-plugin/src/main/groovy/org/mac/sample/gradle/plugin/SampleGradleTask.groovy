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

package org.mac.sample.gradle.plugin

import groovy.xml.MarkupBuilder
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SampleGradleTask extends DefaultTask{

    SampleGradleTask() {
        group = 'org.mac'
        description = 'sample plugin task'
    }

    @TaskAction
    void doAction() {
        writeReleaseNote()
    }

    private void writeReleaseNote() {
        String version = project.extensions.releaseNote.version
        String code = project.extensions.releaseNote.code
        String description = project.extensions.releaseNote.description
        String fileName = project.extensions.releaseNote.fileName

        def file = project.file(fileName)

        if (file != null && !file.exists()) {
            file.createNewFile()
        }

        def sw = new StringWriter()
        def xmlBuilder = new MarkupBuilder(sw)

        if (file.text == null || file.text.size() <= 0) {
            xmlBuilder.releases {
                release buildReleaseNoteXmlBodyClosure(version,code,description)
            }
            file.withWriter {Writer writer -> writer.append(sw.toString())}
        }
        else { //append
            xmlBuilder.release buildReleaseNoteXmlBodyClosure(version,code,description)

            def lines = file.readLines()
            def length = lines.size() - 1
            file.withWriter {writer ->
                lines.eachWithIndex{ String line, int index ->
                    if (index == length) {
                        writer.append(sw.toString() + '\r\n')
                    }
                    writer.append(line+'\r\n')
                }
            }
        }

        println "[ $project.name ]execute write release note sucess!"
    }

    def buildReleaseNoteXmlBodyClosure (String version,String code,String description) {
        return {
            relaseVersion(version)
            relaseCode(code)
            relasedescription(description)
        }
    }
}
