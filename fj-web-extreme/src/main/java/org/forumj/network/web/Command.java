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
package org.forumj.network.web;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public enum Command {
   
   NONE(""),
   SET_LOCALE("setLocale"),
   SET_EMAIL("setEmail"),
   PREVIEW_NEW_POST("view_new"),
   PREVIEW_NEW_THREAD("view"),
   PREVIEW_EDITED_POST("view_edit"),
   CREATE_POST("write_new"),
   UPDATE_POST("write_edit"),
   ;
   
   private String command;

   /**
    * @param command
    */
   private Command(String command) {
      this.command = command;
   }

   public String getCommand() {
      return command;
   }
   
   public static Command valueOfString(String command){
      if (command != null && !command.trim().isEmpty()){
         for(Command result : values()){
            if (result.getCommand().equalsIgnoreCase(command)) return result;
         }
      }else{
         return NONE;
      }
      throw new IllegalArgumentException("Illegal command parameter: " + command);
   }

}
