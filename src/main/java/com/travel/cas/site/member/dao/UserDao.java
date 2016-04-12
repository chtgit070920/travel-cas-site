package com.travel.cas.site.member.dao;

import com.travel.cas.site.member.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao extends JdbcDaoSupport{
	
	private String sql;
	private List<String> columns;
	
	public Integer save(User user) {
		
//		Assert.notNull(user);
//		Assert.notNull(sql);
//		Assert.notNull(columns);
		
		return this.getJdbcTemplate().update(
				"insert into t_member(id,mobile,email,password,name,sex,birthday,lastlogintime,lastloginip,curlogintime,curloginip,cjtime,xgtime,state) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",   
						new Object[] { user.getId(), user.getMobile(),
								user.getEmail(), user.getPassword(),
								user.getName(),user.getSex(), user.getBirthday(),
								user.getLastlogintime(), user.getLastloginip(),
								user.getCurlogintime(), user.getCurloginip(),
								user.getCjtime(), user.getXgtime(),
								user.getState() }
		);  
	}
	
	public User getByLoginname(String loginname){
		return this.getJdbcTemplate().queryForObject("select * from t_member where mobile=? or email =? ",new Object[] {loginname,loginname},
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setId(rs.getString("id"));
						user.setMobile(rs.getString("mobile"));
						user.setEmail(rs.getString("email"));
						user.setCjtime(rs.getDate("cjtime"));
						user.setXgtime(rs.getDate("xgtime"));
						user.setState(rs.getString("state"));
						return user;
					}
				});
	}
	
	public Integer modifyPwd(String userid,String newpwd){
		return this.getJdbcTemplate().update(
				"update  t_member set password=? where id=?",   
            new Object[]{newpwd,userid }
		);  
	}
	
	

	public void setSql(String sql) {
		this.sql = sql;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	
	
	
}
