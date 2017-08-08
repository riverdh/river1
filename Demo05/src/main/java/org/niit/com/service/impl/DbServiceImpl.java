package org.niit.com.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.niit.com.bean.Info;
import org.niit.com.mapper.InfoDao;
import org.niit.com.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;

public class DbServiceImpl implements DbService {

	@Autowired
	private InfoDao infoDao;
	
	
	
	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	private String msg;
	private DataSource ds;
	
	public void setMsg(String msg) {
		this.msg = msg;
		
	}
	
	public void setDs(DataSource ds) {
		this.ds = ds;
	}



	@Override
	public void showMsg() {
		System.out.println("This is "+msg);
	}

	@Override
	public int insert(Info info) {
		if(info == null){
			return 0;
		}
		if(info.getIntro() == null){
			info.setIntro("");
		}
		
		String sql = "insert into info (name,intro) values (?,?)";
		Connection con = null;
		
		try{
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, info.getName());
			ps.setString(2, info.getIntro());
			
			int result = ps.executeUpdate();
			return result;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int delete(int id) {
		String sql = "delete from info where id = ? ";
		Connection con = null;
		
		try{
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			int result = ps.executeUpdate();
			return result;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public int update(Info info) {
		if(info == null){
			return 0;
		}
		if(info.getIntro() == null){
			info.setIntro("");
		}
		
		String sql = "update info set name = ? , intro = ? where id = ?";
		Connection con = null;
		
		try{
			con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, info.getName());
			ps.setString(2, info.getIntro());
			ps.setInt(3, info.getId());
			
			int result = ps.executeUpdate();
			return result;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		return 0;
	}

	@Override
	public List<Info> getInfo(int id) {
		
		if(id == 0){
			return infoDao.queryInfos();
		}else{
			List<Info> infoList = new ArrayList<>();
			Info result = infoDao.queryInfo(id);
			if(result != null){
				infoList.add(result);
			}
			return infoList;
		}
		
		/*String sql = "select * from info ";
		Connection con = null;
		
		try{
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			if(id != 0){
				sql += " where id = "+id;
			}
			sql += " order by id";
			ResultSet rs = stmt.executeQuery(sql);
			
			List<Info> infoList = new ArrayList<>();
			while(rs.next()){
				Info inf = new Info();
				inf.setId(rs.getInt("id"));
				inf.setName(rs.getString("name"));
				inf.setIntro(rs.getString("intro"));
				infoList.add(inf);
			}
			
			return infoList;

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}*/
		
	}

}
