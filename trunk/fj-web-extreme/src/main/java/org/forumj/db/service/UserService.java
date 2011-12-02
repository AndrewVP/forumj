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
package org.forumj.db.service;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.configuration.ConfigurationException;
import org.forumj.common.db.entity.IUser;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class UserService extends FJCommonService {

   public static IUser readUser(Long userId) throws ConfigurationException, SQLException, IOException{
      return getUserDao().read(userId);
   }
   
   public static void updateUser(IUser user) throws IOException, ConfigurationException, SQLException {
      getUserDao().update(user);
   }

   public static IUser readUserByMail(String mail) throws ConfigurationException, SQLException, IOException{
      return getUserDao().readByMail(mail);
   }

   public static void createUser(IUser user) throws SQLException, ConfigurationException, IOException{
      getUserDao().create(user);
   }
}