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

package org.mac.sample.spring.boot.data.mybatis.xml;

import org.mac.sample.spring.boot.data.model.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Employee mapper
 *
 * @auther mac
 * @date 2018-12-04
 */
@Component//for IDEA Could not autowire. No beans of '*Mapper' type found.
public interface EmployeeMapper {

    Employee getEmployeeBy(Integer id);

    void insert(Employee employee);

    List<Employee> getAll();

    int update(Employee employee);

    int deleteEmployeeBy(Integer id);
}
