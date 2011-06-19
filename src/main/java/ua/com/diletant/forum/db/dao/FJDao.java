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
package ua.com.diletant.forum.db.dao;

import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.configuration.*;
import org.apache.commons.dbcp.*;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

import ua.com.diletant.forum.db.sql.result.QueryResult;
import ua.com.diletant.forum.email.EMail;
import ua.com.diletant.forum.exception.DBException;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJDao {

   /**
    * E-mail класс для отправки сообщений об ошибах
    *
    * @var Email
    */
   private EMail mail = new EMail();

   /**
    * Максимальное время выполнения запроса
    *
    * @var maxQueryTime
    */
   private int maxQueryTime = 10;

   public static DataSource dataSource = null;

   protected static Configuration config = null;
   
   public static Connection getConnection() throws SQLException, ConfigurationException{
      if (dataSource == null){
         synchronized (FJDao.class) {
            if (config == null){
               config = new PropertiesConfiguration("fj.properties");
            }
            if (dataSource == null){
               ObjectPool connectionPool = new GenericObjectPool(null);
               String connectURI = config.getString("jdbc.url");
               String userName = config.getString("jdbc.user.name");
               String userPassword = config.getString("jdbc.user.password");
               ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectURI, userName, userPassword);
               PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);
               dataSource = new PoolingDataSource(connectionPool); 
            }
         }
      }
      return dataSource.getConnection();
   }
   
   /**
    * Вызывается при возникновении ошибки БД
    *
    * @param DataBaseException $exception
    */
   protected void onDatabaseError(DBException exception){
      mail.sendInvalidQueryMail(999, 999, exception.getMessage(), "tema");
   }

   /**
    * Событие, генерируемое после выполнения запроса к БД
    *
    * @param QueryResult $result
    */
   protected void onQuery(QueryResult result){
      if (result.getQueryTime() > this.maxQueryTime){
         mail.sendInvalidQueryMail(0, result.getQueryTime(), result.getQuery(), "tema");
      }
   }
}