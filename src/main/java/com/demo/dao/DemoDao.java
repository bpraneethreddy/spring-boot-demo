package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Map<String, Object>> getUsers() throws Exception{
		List<Map<String, Object>> userList=jdbcTemplate.queryForList("SELECT * FROM app_user");
		return userList;
	}

	public Integer saveUser(String name, String contact, String email) throws Exception {
		String query="INSERT INTO `app_user` (first_name,contact_no,email,status,role,user_type) VALUES (?,?,?,?,?,?)";
		return getGeneratedKey(jdbcTemplate, query, new Object[] {name,contact,email,"Active","Admin","administrator"});
	}

	public static int getGeneratedKey(JdbcTemplate jdbcTemplate,final String query,final Object[] values)throws Exception {
	     KeyHolder keyHolder = new GeneratedKeyHolder();
	    	jdbcTemplate.update(new PreparedStatementCreator() {
		    	  public PreparedStatement createPreparedStatement(Connection connection)throws SQLException {
		    	    PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    	    if(values!=null){
		    	    	int i=0;
		    	    	for(Object obj:values)
		    	    		ps.setObject(++i, obj);
		    	    }
		    	    return ps;}}, keyHolder);
	    	if(keyHolder==null || keyHolder.getKey()==null )
	    		return 0;
	    	Long generatedId = new Long(keyHolder.getKey().longValue());
	    	return generatedId.intValue();
	} 
	
}
