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

package org.mac.sample.spring.boot.web.service;

import org.mac.sample.spring.boot.data.model.entity.Employee;
import org.mac.sample.spring.boot.data.mybatis.xml.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Employee service
 *
 * @auther mac
 * @date 2018-12-05
 */
@Service
public class EmployeeService {

    private EmployeeMapper employeeMapper;

    //@Autowired注解在构造函数上是可选的
    public EmployeeService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public Employee get(Integer id){
        return employeeMapper.getEmployeeBy(id);
    }

    public List<Employee> getEmployees(){
       return employeeMapper.getAll();
    }

    public Employee save(Employee employee){

        employeeMapper.insert(employee);

        return employee;
    }

    public int update(Employee employee) {
        return employeeMapper.update(employee);
    }

    public int remove(Integer id) {
        return employeeMapper.deleteEmployeeBy(id);
    }
}
