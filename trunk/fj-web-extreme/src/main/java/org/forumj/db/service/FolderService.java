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
public class FolderService extends FJCommonService {

   public static void moveToRecyclebin(long threadId, IUser user) throws IOException, ConfigurationException, SQLException{
      getFolderDao().moveToRecyclebin(threadId, user);
   }
   
   public static void deleteFolder(Long folderId, IUser user) throws ConfigurationException, SQLException, IOException{
      getFolderDao().delete(folderId, user);
   }

   public static void deleteFolderFromView(Long folderId, Long viewId, IUser user) throws ConfigurationException, SQLException, IOException{
      getFolderDao().deleteFolderFromView(folderId, viewId, user);
   }

}