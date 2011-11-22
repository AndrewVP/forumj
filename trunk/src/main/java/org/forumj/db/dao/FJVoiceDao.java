/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
 */
package org.forumj.db.dao;

import static org.forumj.db.dao.tool.QueryBuilder.*;

import java.io.IOException;
import java.sql.*;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.db.entity.*;

/**
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class FJVoiceDao extends FJDao {

   public FJVoice read(Long threadId, IUser user) throws IOException, ConfigurationException, SQLException{
      FJVoice result = new FJVoice();
      String query = getReadVoiceQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, threadId);
         st.setLong(2, user.getId());
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result.setId(rs.getLong("id"));
            result.setThreadId(threadId);
            result.setUserId(rs.getLong("user"));
            result.setNodeId(rs.getLong("node"));
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   public void delete(FJVoice voice, Connection connection) throws ConfigurationException, IOException, SQLException{
      String query = getDeleteVoiceQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = connection == null ? getConnection() : connection;
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query);
         st.setLong(1, voice.getNodeId());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(connection == null ? conn : null, st, error);
      }
   }
   
   public boolean isUserVoted(long threadId, IUser user) throws SQLException, ConfigurationException, IOException{
      boolean result = false;
      String query = getIsUserVotedQuery();
      Connection conn = null;
      PreparedStatement st = null;
      try {
         conn = getConnection();
         st = conn.prepareStatement(query) ;
         st.setLong(1, threadId);
         st.setLong(2, user.getId());
         ResultSet rs = st.executeQuery();
         if (rs.next()){
            result = true;
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }
   
   public void create(FJVoice voice, Connection connection) throws IOException, ConfigurationException, SQLException{
      String query = getCreateVoiceQuery();
      Connection conn = null;
      PreparedStatement st = null;
      boolean error = true;
      try {
         conn = connection == null ? getConnection() : connection;
         conn.setAutoCommit(false);
         st = conn.prepareStatement(query);
         st.setLong(1, voice.getThreadId());
         st.setLong(1, voice.getNodeId());
         st.setLong(1, voice.getUserId());
         st.executeUpdate();
         error = false;
      }finally{
         writeFinally(connection == null ? conn : null, st, error);
      }
   }

   /**
    * Возвращает количество проголосовавших
    * в опросе
    * @throws SQLException 
    * @throws ConfigurationException 
    */
   public int getVoicesAmount(Long threadId) throws ConfigurationException, SQLException{
      String query = "SELECT COUNT(id) AS nvcs FROM voice WHERE head=" + threadId;
      Connection conn = null;
      Statement st = null;
      int result = 0;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            result = rs.getInt("nvcs");
         }
      }finally{
         readFinally(conn, st);
      }
      return result;
   }

   /**
    * Возвращает, есть ли уже голос текущего юзера
    * в опросе
    * @throws SQLException 
    * @throws ConfigurationException 
    */
   public boolean isUserVote(Long threadId, IUser user) throws ConfigurationException, SQLException{
      String query="SELECT user FROM voice WHERE head=" + threadId + " AND user=" + user.getId().toString();
      Connection conn = null;
      Statement st = null;
      try {
         conn = getConnection();
         st = conn.createStatement();
         ResultSet rs = st.executeQuery(query);
         if (rs.next()){
            return true;
         }
      }finally{
         readFinally(conn, st);
      }
      return false;
   }
}