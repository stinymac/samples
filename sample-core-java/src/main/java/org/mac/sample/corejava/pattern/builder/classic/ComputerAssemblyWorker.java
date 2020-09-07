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

package org.mac.sample.corejava.pattern.builder.classic;

/**
 * Computer组装工人
 *
 * @author ma.chao
 * @since 2020-09-07 15:24
 */
public class AssemblyWorkers {

    private ComputerBuilder computerBuilder;

    public AssemblyWorkers() {

    }

    public AssemblyWorkers( ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public void build() {
        computerBuilder.setCpu();
        computerBuilder.setMemory();
        computerBuilder.setHardDisk();
        computerBuilder.setKeyboard();
        computerBuilder.setMouse();
    }

    public Computer assembleComputer(){
        return computerBuilder.getComputer();
    }
}
