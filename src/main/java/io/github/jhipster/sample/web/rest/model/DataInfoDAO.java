package io.github.jhipster.sample.web.rest.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.jdo.Transaction;
import javax.persistence.EntityManagerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hubo on 2017/7/14.
 */
//public interface  DataInfoDAO extends CrudRepository<DataInfo,String> {
//
//    @Query(value="select * from dataset where weibo_content like ?3 and weibo_time>=?1 and weibo_time<=?2 order by weibo_time limit ?3,?4",nativeQuery =true)
//    List<DataInfo> fliterByTimeAndKey(String timestart,String timeend,String keysentence,int start,int num_per_search);
//
//    @Query(value="select * from dataset where weibo_content like ?1 order by weibo_time limit ?2,?3",nativeQuery = true)
//    List<DataInfo> findByKey(String keysentence,int start,int num_per_search);
//
//    @Query(value="select * from dataset where since_id=?1 limit 1",nativeQuery = true)
//    DataInfo findBySince_id(String id);
//
//}
@Repository
public class DataInfoDAO {
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<DataInfo> findByKey(String dbname,String keysentence,int start,int num_per_search)
    {

        return jdbcTemplate.query("select since_id,weibo_time,weibo_content from "+dbname+" where weibo_content like ? order by weibo_time limit ?,? "
           ,new Object[]{keysentence,start,num_per_search} , new DataInfoRowMapper());
    }

    public DataInfo findBySince_id(String dbname,String id)
    {
        DataInfo result=null;
        try {
            result= jdbcTemplate.queryForObject("select since_id,weibo_time,weibo_content from " + dbname + " where since_id=? limit 1", new Object[]{id}, new DataInfoRowMapper());
        }catch (EmptyResultDataAccessException e){
            result=null;
        }
        return result;
    }

    public List<DataInfo> fliterByTimeAndKey(String dbname,String timestart,String timeend,String keysentence,int start,int num_per_search)
    {
        return jdbcTemplate.query("select since_id,weibo_time,weibo_content from "+dbname+" where weibo_content like ? and weibo_time>=?" +
                " and weibo_time<=? order by weibo_time limit ?,? "
       ,new Object[]{keysentence,timestart,timeend,start,num_per_search} ,new DataInfoRowMapper());
    }


    public void save(List<DataInfo> data) {
    }

    public void save(DataInfo data){

    }
}


class DataInfoRowMapper implements RowMapper<DataInfo> {

    public DataInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        DataInfo dataInfo = new DataInfo();
        dataInfo.setSince_id(rs.getString("since_id"));
        dataInfo.setWeibo_content(rs.getString("weibo_content"));
        dataInfo.setWeibo_time(rs.getString("weibo_time"));

        return dataInfo;
    }
}
