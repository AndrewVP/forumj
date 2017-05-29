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
package org.forumj.network.web.controller.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.forumj.common.db.entity.IUser;
import org.forumj.common.db.service.*;
import org.forumj.network.web.FJServletTools;
import org.forumj.network.web.FJUrl;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class DelView{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception{
      HttpSession session = request.getSession();
      String actionParameter = request.getParameter("ACT");
      IUser user = (IUser) session.getAttribute("user");
      InterfaceService interfaceService = FJServiceHolder.getInterfaceService();
      if (user != null && !user.isBanned() && user.isLogined()){
         if (actionParameter != null && !"".equals(actionParameter)){
            String nrwParameter = request.getParameter("NRW");
            Integer nrw = Integer.valueOf(nrwParameter);
            if ("del".equalsIgnoreCase(actionParameter)){
               for (int nrwIndex = 0; nrwIndex < nrw; nrwIndex++) {
                  String folderIdParameter = request.getParameter(String.valueOf(nrwIndex));
                  if (folderIdParameter != null){
                     Long folderId = Long.valueOf(folderIdParameter);
                     interfaceService.deleteInterface(folderId, user);
                  }
               }
            }
         }
         //TODO Magic integer!
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=6");
         response.sendRedirect(url.toString());
      }else{
         // Session expired
         StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
         response.sendRedirect(exit.toString());
      }
   }
}