/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.forumj.dbextreme.db.dao;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.forumj.common.config.FJConfiguration;
import org.forumj.common.db.entity.Entity;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;


/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class BaseDao {

   public static DataSource dataSource = null;

   public void create(Entity entity) throws Exception {
      String query = getCreateQuery();
      PreparedStatement st = null;
      Connection conn = null;
      boolean error = true;
      try{
         conn = getConnection();
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
         prepareStatementForUpdate(entity, st);
         int insertedRows = st.executeUpdate();
         if (insertedRows == 1){
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()){
               entity.setId(rs.getLong(1));
               error = false;
            }
         }
      }finally{
         writeFinally(conn, st, error);
      }
   }

   protected String getCreateQuery() throws IOException {
      throw new NotImplementedException();
   }

   protected int prepareStatementForUpdate(Entity entity, PreparedStatement st) throws SQLException {
      return 0;
   }

   protected Object readValue(String query, Object...values) throws IOException, SQLException, ConfigurationException {
      Object result = null;
      try (
              Connection conn = getConnection();
              PreparedStatement st = conn.prepareStatement(query)
      ){
         setParametersToStatement(values, st);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = rs.getObject(1);
         }
      }
      return result;
   }

   protected Object read(String query, Object...values) throws IOException, SQLException, ConfigurationException {
      Object result = null;
      try (
              Connection conn = getConnection();
              PreparedStatement st = conn.prepareStatement(query)
      ){
         setParametersToStatement(values, st);
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = readObject(rs);
         }
      }
      return result;
   }

   private void setParametersToStatement(Object[] values, PreparedStatement st) throws SQLException {
      int parameterIndex = 1;
      for(Object value : values){
         if (value instanceof String){
            st.setString(parameterIndex++, (String) value);
         }else if (value instanceof Integer){
            st.setInt(parameterIndex++, (Integer) value);
         }else if (value instanceof Long){
            st.setLong(parameterIndex++, (Long) value);
         }
      }

   }

   protected Object readObject(ResultSet rs) throws SQLException {
      throw new RuntimeException("Not implemented method");
   }

   public static Connection getConnection() throws SQLException, ConfigurationException{
      if (dataSource == null){
         synchronized (BaseDao.class) {
            Configuration config = FJConfiguration.getConfig();
            if (dataSource == null){
               Integer maxActive = config.getInteger("dbcp.maxActive", 10);
               ObjectPool connectionPool = new GenericObjectPool(null, maxActive);
               String connectURI = config.getString("jdbc.url");
               String userName = config.getString("jdbc.user.name");
               String userPassword = config.getString("jdbc.user.password");
               String driver = config.getString("jdbc.driver");
               String validationQuery = config.getString("dbcp.validationQuery");
               if (driver != null){
                  try {
                     Class.forName(driver);
                  } catch (ClassNotFoundException e) {
                     e.printStackTrace();
                  }
               }
               ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, userName, userPassword);
               new PoolableConnectionFactory(connectionFactory,connectionPool,null,validationQuery,false,true);
               dataSource = new PoolingDataSource(connectionPool); 
            }
         }
      }
      return dataSource.getConnection();
   }
   
   protected void readFinally(Connection conn, Statement st){
      try {
         if (conn != null){
            conn.close();
         }
         if (st != null){
            st.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   protected void writeFinally(Connection conn, Statement st, boolean error){
      try {
         if (conn != null){
            if (!error){
               conn.commit();
            }else{
               conn.rollback();
            }
            conn.setAutoCommit(true);
         }
         if (st != null){
            st.close();
         }
         if (conn != null){
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}