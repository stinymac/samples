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

package org.mac.sample.spring.annotation.tx.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * model
 *
 * @auther mac
 * @date 2018-10-18
 */
@Repository
public class SimpleRepository {
    @Autowired
    public JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "INSERT INTO `test`.`t_user`(`name`, `age`) VALUES ( ?, ?)";
        String name = UUID.randomUUID().toString().substring(0,5).toUpperCase();
        jdbcTemplate.update(sql,name,18);
    }
}
