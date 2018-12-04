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

package org.mac.sample.spring.boot.web.service;

import org.mac.sample.spring.boot.data.mybatis.annotation.DepartmentMapper;
import org.mac.sample.spring.boot.web.model.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Department service
 *
 * @auther mac
 * @date 2018-12-04
 */
@Service
public class DepartmentService {

    private DepartmentMapper departmentMapper;

    /**
     * 构造器注入的方式,能够保证注入的组件不可变,并且确保需要的依赖不为空。
     * 此外,构造器注入的依赖总是能够在返回客户端（组件）代码的时候保证完全初始化的状态。
     *
     * 组件不可变:
     * final 关键字
     *
     * 确保需要的依赖不为空:
     * 由于实现了有参数的构造函数,所以不会调用默认构造函数,那么就需要Spring容器传入所需要的参数,
     * 所以就两种情况:
     * 1.有该类型的参数->传入,OK 。
     * 2.无该类型的参数->报错,所以保证不会为空。
     *
     * 保证完全初始化的状态:
     * Java类加载实例化的过程中，构造方法是最后一步。
     * @param departmentMapper
     */
    @Autowired
    public DepartmentService(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public Department save(Department department) {
        departmentMapper.insert(department);
        return department;
    }

    public Department get(Integer departmentId){
       return departmentMapper.getDepartmentBy(departmentId);
    }
}
