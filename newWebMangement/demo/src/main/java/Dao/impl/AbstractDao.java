package Dao.impl;

import Dao.GenericDao;
import Dao.JDBCUtil;
import map.RowMapper;
import org.w3c.dom.Text;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbstractDao<T> implements GenericDao<T>{


    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> result = new ArrayList<>();
        Connection con= JDBCUtil.getConnection();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            statement=con.prepareStatement(sql);
            setParameter(statement,parameters);
            resultSet= statement.executeQuery();
            while (resultSet.next()){
                result.add(rowMapper.mapRow(resultSet));
            }
            return result;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        } finally {
             finalForTryCatch(con, statement, resultSet);
        }

    }

    @Override
    public void update(String sql, Object... parameters) {
            Connection con=null;
            PreparedStatement statement=null;
            try{
                con=JDBCUtil.getConnection();
                con.setAutoCommit(false);
                statement=con.prepareStatement(sql);
                setParameter(statement,parameters);
                statement.executeUpdate();
                con.commit();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try {
                    if(con!=null){
                        con.close();
                    }
                    if(statement!=null){
                        statement.close();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
    }

    @Override
    public Integer insert(String sql, Object... parameters) {
        Connection con= null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            Integer id =null;
            con=JDBCUtil.getConnection();
            con.setAutoCommit(false);
            statement=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(statement,parameters);
            statement.executeUpdate();
            resultSet=statement.getGeneratedKeys();
            while(resultSet.next()){
                id=resultSet.getInt(1);
            }
            con.commit();
            return id;


        } catch (SQLException e){
//            try {
//                if (con != null) {
//                    con.rollback();
//                }
//            } catch (SQLException e1){
                e.printStackTrace();
            //}

        }finally {
            finalForTryCatch(con, statement, resultSet);
        }
        return null;
    }

    private static void finalForTryCatch(Connection con, PreparedStatement statement, ResultSet resultSet) {
        try{
            if(con!=null){
                con.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection con=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        int result=0;
        try{
            con=JDBCUtil.getConnection();
            statement=con.prepareStatement(sql);
            setParameter(statement,parameters);
            resultSet=statement.executeQuery();
            while(resultSet.next()){
                result=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            finalForTryCatch(con,statement,resultSet);
        }
        return result;
    }

    @Override
    public T selectById(T t) {
        return null;
    }

    @Override
    public int delete(T t) {
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<T> arrayList) {
        return 0;
    }

    private void setParameter(PreparedStatement statement, Object ... paremeters){
        try{
            for (int i = 0; i < paremeters.length ; i++) {
                Object parameter = paremeters[i];
                int index=i+1;
                if(parameter instanceof Integer){
                    statement.setInt(index,(Integer)parameter);
                }else if(parameter instanceof String){
                    statement.setString(index,(String)parameter);
                }else if(parameter instanceof Date){
                    statement.setDate(index,(java.sql.Date) parameter);
                }else if(parameter instanceof Text){
                    statement.setString(index,(String)parameter);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
