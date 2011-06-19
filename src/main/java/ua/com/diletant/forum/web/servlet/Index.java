/*
 * Copyright Andrew V. Pogrebnyak
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law || agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES || CONDITIONS OF ANY KIND, either express || implied.
 * See the License for the specific language governing permissions &&
 * limitations under the License.
 */
package ua.com.diletant.forum.web.servlet;

import static ua.com.diletant.forum.tool.PHP.*;
import static ua.com.diletant.forum.tool.Diletant.*;
import static ua.com.diletant.forum.tool.FJServletTools.*;
import static ua.com.diletant.forum.web.servlet.tool.FJServletTools.*;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ua.com.diletant.forum.db.dao.IndexDao;
import ua.com.diletant.forum.db.entity.*;
import ua.com.diletant.forum.exception.InvalidKeyException;
import ua.com.diletant.forum.tool.LocaleString;

/**
 * 
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
@WebServlet("/index.php")
public class Index extends HttpServlet {

   private static final long serialVersionUID = 1828936989822948738L;

   /**
    * {@inheritDoc}
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      StringBuffer buffer = new StringBuffer();
      List<Map<String, Object>> $views = null;
      try {
         HttpSession session = request.getSession();
         String $defLang = (String) session.getAttribute("lang");
         if ($defLang == null){
            $defLang = "ua"; 
         }
         //Предотвращаем кеширование
         cache(response);
         // Функции   
         // номер страницы
         Integer $pg = request.getParameter("page") == null ? 1 : Integer.valueOf(request.getParameter("page"));
         // Загружаем локализацию
         LocaleString $locale = new LocaleString($defLang, null, "ua");
         String where="index.php?page=" + $pg + "&lang=" + $defLang;
         // Авторизован?
         // Соединяемся с БД
         // Подхватываем куку
         String $location="index.php";
         if (cokie(request, response, $location)){
            // нажато "выйти"
            exit(request);
            User user = (User) session.getAttribute("user");
            Long $_idu=user.getId();
            IndexDao $dao = new IndexDao(user);  
            // Собираем статистику
            buffer.append("<!doctype html public \"-//W3C//DTD HTML 4.01 Transitional//EN\">\"");
            buffer.append("<html>");
            buffer.append("<head>");
            buffer.append("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
            buffer.append("<style type='text/css'>");
            // Стили
            buffer.append(loadResource("/css/style.css"));
            buffer.append("</style>");
            // Скрипты (флажки)
            buffer.append("<script type='text/javascript'>");
            buffer.append("// <!--");
            buffer.append(loadResource("/js/jsmain_chek.js"));
            buffer.append("// -.");
            buffer.append("</script>");
            Long $m_xb = $dao.getMaxPostId();
            Long $m_xt = $dao.getMaxThreadId();
            buffer.append("<script language='javascript' type='text/javascript'>");
            buffer.append("// <!-- \n");
            buffer.append("var m_xb=" + $m_xb + ";");
            buffer.append("var m_xt=" + $m_xt + ";");
            buffer.append("// -.");
            buffer.append("</script>");
            buffer.append("<script type='text/javascript'>");
            buffer.append("// <!--");
            buffer.append(loadResource("/js/indicator.js"));
            buffer.append("// -.");
            buffer.append("</script>");
            // Скрипты (submit формы интерфейсов)
            buffer.append("<script type='text/javascript'>");
            buffer.append("// <!--");
            buffer.append(loadResource("/js/jsview_ok.js"));
            buffer.append("// -.");
            buffer.append("</script>");
            buffer.append("<link rel=\"icon\" href=\"/favicon.ico\" type=\"image/x-icon\">");
            buffer.append("<link rel=\"shortcut icon\" href=\"/favicon.ico\" type=\"image/x-icon\">");
            buffer.append("<title>");
            buffer.append($locale.getString("MSG_MAIN_TITLE"));
            buffer.append("</title>");
            buffer.append("</head>");
            // Цвет фона страницы
            buffer.append("<body class='mainBodyBG'>");
            // Снег
            //      include("js/snow.php");
            // Главная таблица
            buffer.append("<table border='0' id=t1 style='border-collapse: collapse' width='100%'>");
            buffer.append("<tr><td>");
            buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
            // Таблица с лого и верхним баннером
            buffer.append(loadResource("/html/logo.html"));
            // соединяемся и определяем кол-во страниц
            long $nfirstpost=($pg-1)*user.getPp();
            int $limit=$pg*user.getPp();
            // Интерфейс по умолчанию
            if (session.getAttribute("view") == null){
               session.setAttribute("view", user.getView());
            }
            //      if (!isset($_SESSION['idu'])) $_SESSION['view']=$_SESSION['def_view'];
            List<FJThread> $threads = $dao.getThreads(Long.valueOf((Integer) session.getAttribute("view")), user.getPp(), $nfirstpost, $locale, user.isLogined(), $pg, user.getPt());
            int $count = $dao.getThreadCount();
            // кол-во страниц с заголовками
            int $cou_p = ceil($count/user.getPp())+1;
            // Проверяем наличие почты
            String $str_nmail = "";
            if (user.isLogined()) {
               int $mailCount = $dao.getNewMailCount(user.getId());
               if ($mailCount > 0) {
                  $str_nmail="<a class=hdforum href='control.php?id=2' rel='nofollow'><font color=red>" + $locale.getString("mess66") + " " + $mailCount +" " + $locale.getString("mess67") + "</font></a>";
               }
            }
            // Таблица главных ссылок
            buffer.append("<tr>");
            buffer.append("<td width='100%'>");
            buffer.append("<table id='t2' width='100%'>");
            /*Главное меню*/
            buffer.append(menu(request, user, $defLang, $locale));
            // Интерфейс
            // Имя текущего
            if (session.getAttribute("vname") == null){
               session.setAttribute("vname", $dao.getCurrentViewName(Long.valueOf((Integer)session.getAttribute("view"))));
            }
            $views = $dao.getViewsArray($_idu);
            buffer.append("<tr><td>");

         buffer.append("<table class=control>");
         buffer.append("<tr>");
         buffer.append("<td class=leftTop></td>");
         buffer.append("<td class=top colspan=3></td>");
         buffer.append("<td class=rightTop></td>");
         buffer.append("</tr>");
         buffer.append("<tr class=heads>");
         buffer.append("<td class=left></td>");
         buffer.append("<td class=bg2 align=left>");
         buffer.append("<span class=mnuforum>");
         buffer.append($locale.getString("mess81"));
         buffer.append("</span>");
         buffer.append("<span class=nik>");
         buffer.append("<?echo($_SESSION['vname']);?>");
         buffer.append("</span>");
         buffer.append("</td>");
         buffer.append("<td class=bg2 align=right>");
         buffer.append("<form method='post' name='view_form' action='slctview.php' class=frmsmall>");
         /*Выводим интерфейсы*/
         buffer.append("<span class=mnuforum>");
         buffer.append($locale.getString("mess80"));
         buffer.append("</span>");
         buffer.append("<select class='mnuforumSm'  size='1' name='VIEW'>");
         buffer.append("<option selected class=mnuprof value='" + $views.get(0).get("id") + "'>");
         buffer.append($views.get(0).get("name"));
         buffer.append("</option>");
         for (int $vw1=1; $vw1< $views.size() -1; $vw1++)
         {
            buffer.append("<option class=mnuprof value='" + $views.get($vw1).get("id") + "'>");
            buffer.append($views.get($vw1).get("name"));
            buffer.append("</option>");
         }
         buffer.append("</select>");
         buffer.append("</form>");
         buffer.append("</td>");
         buffer.append("<td class=bg2 align=right>");
         buffer.append(fd_button("OK","document.view_form.submit();","view_ok", "1"));
         buffer.append("</td>");
         buffer.append("<td class=right></td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td class=leftBtm></td>");
         buffer.append("<td class=btm colspan=3></td>");
         buffer.append("<td class=rightBtm></td>");
         buffer.append("</tr>");
         buffer.append("</table>");

         buffer.append("</td>");        
         buffer.append("</tr>");
         // Стройка!!!
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table width='100%'>");
         String $mess = "";
         if (user.isLogined()){
            $mess=$locale.getString("mess133");
         }
         else {
            $mess=$locale.getString("mess134");
         }
            buffer.append("<tr><td colspan='3'><p><font face='Arial' color='red' size='3'><span style='text-decoration: none'><b>");
            buffer.append($mess);
            buffer.append("</b></span></font></p></td>");
         buffer.append("<td style='text-align: right;'>");    
         // Сторінка сформована :)   
         buffer.append("<span class=posthead>" + $locale.getString("mess91") + "</span>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Ссылки на другие страницы (здесь collspan!)
         buffer.append("<tr><td style='padding:2px'>");
         buffer.append("<font class='page'><b>" + $locale.getString("mess22") + "&nbsp;</b></font>");
         int $i_3=1;
         if ($pg>5) $i_3=$pg-5;
         int $i_4=$pg+5;
         if ($cou_p-$pg<5) $i_4=$cou_p;
         int $i_2=0;
         for (int $i_1=$i_3; $i_1<$i_4; $i_1++){
            $i_2=$i_2+1;
            if (($i_1>($pg-5) && $i_1<($pg+5)) || $i_2==10 || $i_1==1 || $i_1==($cou_p-1)){
               if ($i_2==10) $i_2=0;
               if ($i_1==$pg){
                  buffer.append("<font class='pagecurrent'><b>" + $i_1 + "</b></font>");
               }
               else {
                  buffer.append("<a class='pageLink' href='index.php?page=" + $i_1 + "'>" + $i_1 + "</a>");
               }
            }
         }
         buffer.append("<font class='page' style='margin-left:5px;'><b>" + $locale.getString("mess136") + "&nbsp;" + ($cou_p-1) + "</b></font>");
         buffer.append("</td>");
         buffer.append("<td align='right'>");
         buffer.append("<form name='str' method='get' class=frmsmall action='index.php'>");
         buffer.append("<font class=page style='margin-right:4px;'>");
               buffer.append("<b>");
                     buffer.append($locale.getString("mess137"));
                           buffer.append("</b>");
                                 buffer.append("</font>");
                                       buffer.append("<input class='mnuforumSm' style='padding:2px' type=\"text\" size='5' name='page'>");
                                             buffer.append("</form>");
                                                   buffer.append("</td>");
                                                         buffer.append("<td>");
                                                               buffer.append(fd_button("OK","document.str.submit();","page_ok", "1"));
                                                               buffer.append("</td>"); 
         buffer.append("<td style='text-align: right;'>");    
      // Индикатор   
         buffer.append("<span class=posthead>" + $locale.getString("mess164") + ":&nbsp;</span>");
         buffer.append("<span class=posthead id='indicatort' style='color:red'>&nbsp;</span><br />");
         buffer.append("<span class=posthead >" + $locale.getString("mess165") + ":&nbsp;</span>");
         buffer.append("<span class=posthead id='indicatorb' style='color:red'>&nbsp;</span>");
         buffer.append("</td>");
      buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
      // Закончили таблицу главных ссылок
      // Таблица Заголовков тем
         if (user.isLogined()){
            // Форма выводится только для зарегистрированых
            buffer.append("</table>");        
            buffer.append("<form method='post' name='del_form' action='movetitle.php?page=" + $pg + "' class=frmsmall>");
            buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         }
         buffer.append("<tr>");
         buffer.append("<td height='400' valign='top'>");
         buffer.append("<table class='content'>");
      // Заголовки таблицы
         buffer.append("<tr><td class=internal align='left' colspan='3'><span class=hdforum2>Тема:  </span>" + $str_nmail + " </td>");
      // Ответы
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + $locale.getString("MSG_ANSW") + "</span>");
         buffer.append("</td>");
      // Просмотры
         buffer.append("<td class=internal align='center'><span class=hdforum2>");
         buffer.append($locale.getString("MSG_VIEWS") + "</span></td>");
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + $locale.getString("MSG_AUTH") + "</span></td>");
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + $locale.getString("MSG_LAST") + "</span></td>");
      // Папка
         buffer.append("<td class=internal align='center'><span class=hdforum2>" + $locale.getString("mess82") + "</span></td>");
         // Флажок (только для авторизованых)
         if (user.isLogined()) {
            buffer.append("<td class=internal align='center'>");
            buffer.append("<input type='checkbox' id='main_ch' onclick='m_chek()'>");
            buffer.append("</td>");
            buffer.append("<td class=internal></td>");
         }
            buffer.append("</tr>");
      // Определяем кол-во строк таблицы
         int $i2=$pg*user.getPp();
         if ($i2>$count) {
            $i2=$count-($pg-1)*user.getPp();
         }else{
            $i2=user.getPp();
         }
      // Выводим строки
         for (FJThread $thread : $threads) {
            buffer.append( $thread.toString());
         }
      // Главные ссылки внизу страницы
         buffer.append("</table>");
         buffer.append("<script language='javascript' type='text/javascript'>");
         buffer.append("// <!-- \n");
         buffer.append("if (request){");
         buffer.append("var idss = '" + substr($dao.getIndctrIds(), 1) + "';");
         buffer.append("getIndicatorInfo();");
         buffer.append("}");
         buffer.append("// -->");
         buffer.append("</script>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("<tr>");
         buffer.append("<td width='100%'>");
         buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         buffer.append("<tr><td colspan='4' style='padding:2px'>");
         buffer.append("<font class=page><b>" + $locale.getString("mess22") + "&nbsp;</b></font>");
         $i_3=1;
         if ($pg>5) $i_3=$pg-5;
         $i_4=$pg+5;
         if ($cou_p-$pg<5) $i_4=$cou_p;
         $i_2=0;
         for (int $i_1=$i_3; $i_1<$i_4; $i_1++){
            $i_2=$i_2+1;
            if (($i_1>($pg-5) && $i_1<($pg+5)) || $i_2==10 || $i_1==1 || $i_1==($cou_p-1)){
               if ($i_2==10) $i_2=0;
               if ($i_1==$pg){
                  buffer.append("<font class='pagecurrent'><b>" + $i_1 + "</b></font>");
               }
               else {
                  buffer.append("<a class='pageLink' href='index.php?page=" + $i_1 + "'>" + $i_1 + "</a>");
               }
            }
         }
         buffer.append("<font class='page' style='margin-left:5px;'><b>" + $locale.getString("mess136") + "&nbsp;" + ($cou_p-1) + "</b></font>");
         buffer.append("</td>");
         buffer.append("</tr>");
         // Сервис интерфейса
         if (user.isLogined()) {
            // Выбираем доступные папки
            List<Map<String, Object>> $arrFolders = $dao.getFoldersArray($_idu);
            buffer.append("<tr>");
            buffer.append("<table class=control>");        
            buffer.append("<tr>");
            buffer.append("<td class=leftTop></td>");
            buffer.append("<td class=top colspan=3></td>");
            buffer.append("<td class=rightTop></td>");
            buffer.append("</tr>");
            buffer.append("<tr class=heads>");
            buffer.append("<td class=left></td>");
            buffer.append("<td class=bg2 align=left>");
            buffer.append("<span class=mnuforum>" + $locale.getString("mess81") + "</span><span class=nik>" + session.getAttribute("vname") + "</span>");
            buffer.append("</td>");
            buffer.append("<td class=bg2 align=right>");
            // Выводим папки
            buffer.append("<span class=mnuforum>" + $locale.getString("mess83") + "</span>");
            buffer.append("<select class='mnuforumSm' size='1' name='VIEW'>");
            buffer.append("<option selected value='" + $arrFolders.get(0).get("id") + "'><span class=mnuprof>" + $arrFolders.get(0).get("flname") + "</span></option>");
            for (int $fl1=1; $fl1< $arrFolders.size()-1; $fl1++){
               buffer.append("<option value='" + $arrFolders.get($fl1).get("id") + "'><span class=mnuprof>" + $arrFolders.get($fl1).get("flname") + "</span></option>");
            }        
            buffer.append("</select>");
         // Прередаем нужные пераметры...
            buffer.append(fd_form_add(user));
            buffer.append("<input type=hidden name=\"NRW\" id='nrw' value=\"" + $i2 + "\">");
         // Кнопка
            buffer.append("</td>");        
            buffer.append("<td class=bg2 align=right>");
            buffer.append(fd_button("OK","document.del_form.submit();","del_ok", "1"));
            buffer.append("</td>");        
            buffer.append("<td class=right></td>");
                  buffer.append("</tr>");
                        buffer.append("<tr>");
                              buffer.append("<td class=leftBtm></td>");
                                    buffer.append("<td class=btm colspan=3></td>");
                                          buffer.append("<td class=rightBtm></td>");
                                                buffer.append("</tr>");
            buffer.append("</table>");        
            buffer.append("</tr>");
            buffer.append("</table>");        
            buffer.append("</form>");
            buffer.append("<table border='0' style='border-collapse: collapse' width='100%'>");
         }
         /*Главное меню*/
         menu(request, user, $defLang, $locale);
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
      // Таблица активных пользователей
      // Выбираем Активных юзеров
         List<Map<String, Object>> $arrUsers = $dao.getUsersArray();
         buffer.append("<tr>");
         buffer.append("<td width=\"100%\">");
         buffer.append("<table width='100%'><tr><td>");
         buffer.append("<font class=mnuforum>");
         buffer.append($locale.getString("MSG_READERS") + "<br>");
         buffer.append("</font>");
         buffer.append("<font class=nick>");
         for (int $tu1=0 ; $tu1<$arrUsers.size()-1; $tu1++){
            buffer.append(str_replace(" ", "&nbsp;", (String)$arrUsers.get($tu1).get("nick")));
            if ($tu1!=$arrUsers.size()-2) buffer.append("; ");
         }
         buffer.append("</font>");
         buffer.append("<font class=mnuforum>");
         buffer.append("<br>" + $locale.getString("MSG_GUESTS") + ": ");
         buffer.append("</font>");
         buffer.append("<font class=nick>");
         // Выводим количество гостей
         buffer.append($dao.getGuestCount());
         // Закрываем соединение с БД
         buffer.append("</font>");
         buffer.append("</td>");
         buffer.append("</tr>");
         buffer.append("</table>");
         buffer.append("</td>");
         buffer.append("</tr>");
      // Баннер внизу, счетчики и копирайт.
         buffer.append(loadResource("/html/end.html"));
         buffer.append("</body>");
         buffer.append("</html>");
         }
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter writer = response.getWriter();
      writer.write(buffer.toString());
   }   
}

