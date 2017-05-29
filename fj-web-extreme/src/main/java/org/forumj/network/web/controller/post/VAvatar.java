/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
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
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class VAvatar{

   public void doPost(HttpServletRequest request, HttpServletResponse response, String webapp, String userURI) throws Exception, IOException {
      boolean vAvatar = request.getParameter("v_avatar") != null;
      boolean sAvatar = vAvatar && request.getParameter("s_avatar") != null;
      HttpSession session = request.getSession();
      IUser user = (IUser) session.getAttribute("user");
      if (user != null && !user.isBanned() && user.isLogined()){
         user.setWantSeeAvatars(vAvatar);
         user.setShowAvatar(sAvatar);
         UserService userService = FJServiceHolder.getUserService();
         userService.update(user);
         //TODO Magic integer!
         StringBuilder url = new StringBuilder("/").append(userURI).append("/").append(FJUrl.SETTINGS).append("?id=9");
         response.sendRedirect(url.toString());
      }else{
         // Session expired
         StringBuilder exit = new StringBuilder("/").append(userURI).append("/").append(FJUrl.INDEX);
         response.sendRedirect(exit.toString());
      }
   }

}