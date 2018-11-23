/*
 *      (             |"|           !!!       #   ___                             o
 *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
 *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 *
 *                    !!!         |                             |"|            _             o          _     _
 *    __MMM__      `  _ _  '      |.===.         ,,,,,         _|_|_         _|_|_        ` /_\ '     o' \,=./ `o
 *     (o o)      -  (OXO)  -     {}o o{}       /(o o)\        (o o)         (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 * 虽不能至,心向往之。(Although it is not possible, my heart is longing for it.)
 *
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 */

package org.mac.sample.spring.boot.web.controller;

import org.mac.sample.spring.boot.web.model.entity.Department;
import org.mac.sample.spring.boot.web.model.entity.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 *
 * @auther mac
 * @date 2018-11-22
 */
@Controller
public class EmployeeController {

    private static Map<Integer, Employee> employees = new HashMap<>();
    static {
        employees.put(1001, new Employee(1001, "Jerry", "Jerry@163.com", 1, new Department(101, "D-AA")));
        employees.put(1002, new Employee(1002, "Tom", "Tom@163.com", 1, new Department(102, "D-BB")));
        employees.put(1003, new Employee(1003, "Alex", "Alex@163.com", 0, new Department(103, "D-CC")));
        employees.put(1004, new Employee(1004, "Jack", "Jack@163.com", 0, new Department(104, "D-DD")));
        employees.put(1005, new Employee(1005, "Spike", "Spike@163.com", 1, new Department(105, "D-EE")));
    }

    @GetMapping("employees")
    public String list(Model model) {
        model.addAttribute("employees",employees.values());
        return "employee/list";
    }
}
