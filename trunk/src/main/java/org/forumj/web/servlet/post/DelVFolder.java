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
package org.forumj.web.servlet.post;

import static org.forumj.tool.Diletant.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.forumj.common.*;
import org.forumj.db.dao.*;
import org.forumj.db.entity.User;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet(urlPatterns = {FJUrl.DELETE_FOLDER_FROM_VIEW}, name = FJServletName.DELETE_FOLDER_FROM_VIEW)
public class DelVFolder extends HttpServlet {

   private static final long serialVersionUID = 9093427147196842541L;

   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         StringBuffer buffer = new StringBuffer();
         HttpSession session = request.getSession();
         String viewIdParameter = request.getParameter("IDVW");
         String actionParameter = request.getParameter("ACT");
         User user = (User) session.getAttribute("user");
         if (user != null && !user.isBanned() && user.isLogined()){
            Long viewId = Long.valueOf(viewIdParameter);
            if (actionParameter != null && !"".equals(actionParameter)){
               FJFolderDao dao = new FJFolderDao();
               String nrwParameter = request.getParameter("NRW");
               Integer nrw = Integer.valueOf(nrwParameter);
               if ("del".equalsIgnoreCase(actionParameter)){
                  for (int nrwIndex = 0; nrwIndex < nrw; nrwIndex++) {
                     String folderIdParameter = request.getParameter(String.valueOf(nrwIndex));
                     Long folderId = Long.valueOf(folderIdParameter);
                     dao.deleteFolderFromView(folderId, viewId, user);
                  }
               }
            }
            //TODO Magic integer!
            buffer.append(successPostOut("0", "control.php?id=6&view=" + viewIdParameter));
         }else{
            // Вошли незарегистрировавшись
            buffer.append(unRegisteredPostOut());
         }
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
}