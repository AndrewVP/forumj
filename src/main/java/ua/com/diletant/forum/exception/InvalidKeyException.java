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
package ua.com.diletant.forum.exception;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class InvalidKeyException extends FJException {

   private static final long serialVersionUID = 6127971393401310618L;

   public InvalidKeyException(String message, Throwable cause) {
      super(message, cause);
   }

   public InvalidKeyException(String message) {
      super(message);
   }

   public InvalidKeyException(Throwable cause) {
      super(cause);
   }
   
   

}