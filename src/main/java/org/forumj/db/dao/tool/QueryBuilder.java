/*
 * Copyright (c) 2011
 * Andrew V. Pogrebnyak
 * All rights reserved.
 *
 * This software is distributed under GNU General Public License Version 2.0
 * You shall use it and distribute only in accordance with the terms of the 
 * License Agreement.
  */
package org.forumj.db.dao.tool;

import java.io.*;

import org.forumj.web.servlet.tool.FJServletTools;

/**
 *
 * @author <a href="mailto:an.pogrebnyak@gmail.com">Andrew V. Pogrebnyak</a>
 */
public class QueryBuilder {
   
   private static String loadConfigQuery = null;

   private static String createThreadQuery = null;

   private static String updateThreadQuery = null;
   
   private static String updatePostQuery = null;

   private static String updatePostBodyQuery = null;

   private static String updatePostHeadQuery = null;

   private static String readThreadQuery = null;
   
   private static String firstPostIdInThreadQuery = null;

   private static String createPostQuery = null;
   
   private static String readPostQuery = null;

   private static String readPostHeadQuery = null;

   private static String readPostBodyQuery = null;

   private static String addedPostsAmountQuery = null;

   private static String addedPostsInThreadAmountQuery = null;
   
   private static String addedThreadsAmountQuery = null;
   
   private static String createPostBodyQuery = null;
   
   private static String createPostHeadQuery = null;

   private static String loadAnswersQuery = null;

   private static String loadIgnorQuery = null;

   private static String updateIgnorQuery = null;

   private static String createAnswerQuery = null;
   
   private static String receiveMailQuery = null;

   private static String markMailAsReadQuery = null;
   
   private static String loadInboxQuery = null;
   
   private static String loadOutNotReceivedBoxQuery = null;

   private static String loadOutReceivedBoxQuery = null;

   private static String loadDraftBoxQuery = null;
   
   private static String loadMailQuery = null;

   private static String deleteFromOutboxQuery = null;
   
   private static String deleteFromInboxQuery = null;
   
   private static String loadInterfacesQuery = null;
   
   private static String loadInterfaceQuery = null;

   private static String loadFoldersInQuery = null;
   
   private static String loadFoldersNotInQuery = null;
   
   private static String loadFoldersQuery = null;
   
   private static String loadSubscribesQuery = null;

   private static String deleteSubscribeByTitleIdQuery = null;
   
   private static String deleteSubscribeByIdQuery = null;
   
   private static String deleteSubscribeByKeyQuery = null;
   
   private static String isSubscribeKeyPresentQuery = null;

   private static String createSubscribeQuery = null;

   private static String updateUserQuery = null;
   
   private static String deleteFolderTranzitQuery = null;
   
   private static String deleteFolderFromViewQuery = null;
   
   private static String deleteAllFoldersFromViewQuery = null;
   
   private static String deleteViewQuery = null;
   
   private static String deleteThreadTranzitQuery = null;
   
   private static String deleteVTranzitQuery = null;
   
   private static String appendThreadInFolderQuery = null;
   
   private static String deleteFolderQuery = null;
   
   private static String isInterfaceContainsFolderQuery = null;
   
   private static String addFolderQuery = null;
   
   public static String getLoadConfigQuery() throws IOException{
      if (loadConfigQuery == null){
         loadConfigQuery = loadQuery("/sql/load_config.sql");
      }
      return loadConfigQuery;
   }
   
   public static String getUpdateUserQuery() throws IOException{
      if (updateUserQuery == null){
         updateUserQuery = loadQuery("/sql/update_user.sql");
      }
      return updateUserQuery;
   }
   
   public static String getCreateThreadQuery() throws IOException{
      if (createThreadQuery == null){
         createThreadQuery = loadQuery("/sql/create_thread.sql");
      }
      return createThreadQuery;
   }
   
   public static String getUpdateThreadQuery() throws IOException{
      if (updateThreadQuery == null){
         updateThreadQuery = loadQuery("/sql/update_thread.sql");
      }
      return updateThreadQuery;
   }
   
   public static String getUpdatePostQuery() throws IOException{
      if (updatePostQuery == null){
         updatePostQuery = loadQuery("/sql/update_post.sql");
      }
      return updatePostQuery;
   }
   
   public static String getDeleteFolderTranzitQuery() throws IOException{
      if (deleteFolderTranzitQuery == null){
         deleteFolderTranzitQuery = loadQuery("/sql/delete_tranzit.sql");
      }
      return deleteFolderTranzitQuery;
   }
   
   public static String getDeleteFolderFromViewQuery() throws IOException{
      if (deleteFolderFromViewQuery == null){
         deleteFolderFromViewQuery = loadQuery("/sql/delete_tranzit_from_view.sql");
      }
      return deleteFolderFromViewQuery;
   }
   
   public static String getDeleteAllFoldersFromViewQuery() throws IOException{
      if (deleteAllFoldersFromViewQuery == null){
         deleteAllFoldersFromViewQuery = loadQuery("/sql/delete_all_tranzits_from_view.sql");
      }
      return deleteAllFoldersFromViewQuery;
   }
   
   public static String getDeleteViewQuery() throws IOException{
      if (deleteViewQuery == null){
         deleteViewQuery = loadQuery("/sql/delete_view.sql");
      }
      return deleteViewQuery;
   }
   
   public static String getDeleteThreadTranzitQuery() throws IOException{
      if (deleteThreadTranzitQuery == null){
         deleteThreadTranzitQuery = loadQuery("/sql/delete_thread_tranzit.sql");
      }
      return deleteThreadTranzitQuery;
   }
   
   public static String getDeleteVTranzitQuery() throws IOException{
      if (deleteVTranzitQuery == null){
         deleteVTranzitQuery = loadQuery("/sql/delete_vtranzit.sql");
      }
      return deleteVTranzitQuery;
   }
   
   public static String getAppendThreadInFolderQuery() throws IOException{
      if (appendThreadInFolderQuery == null){
         appendThreadInFolderQuery = loadQuery("/sql/append_thread_in_folder.sql");
      }
      return appendThreadInFolderQuery;
   }
   
   public static String getDeleteFolderQuery() throws IOException{
      if (deleteFolderQuery == null){
         deleteFolderQuery = loadQuery("/sql/delete_folder.sql");
      }
      return deleteFolderQuery;
   }

   public static String getIsInterfaceContainsFolderQuery() throws IOException{
      if (isInterfaceContainsFolderQuery == null){
         isInterfaceContainsFolderQuery = loadQuery("/sql/is_interface_contains_folder.sql");
      }
      return isInterfaceContainsFolderQuery;
   }
   
   public static String getAddFolderQuery() throws IOException{
      if (addFolderQuery == null){
         addFolderQuery = loadQuery("/sql/add_folder.sql");
      }
      return addFolderQuery;
   }
   
   public static String getUpdatePostBodyQuery(String bodyTable) throws IOException{
      if (updatePostBodyQuery == null){
         updatePostBodyQuery = loadQuery("/sql/update_post_body.sql");
      }
      return updatePostBodyQuery.replace("@@TABLE@@", bodyTable);
   }
   
   public static String getUpdatePostHeadQuery(String headTable) throws IOException{
      if (updatePostHeadQuery == null){
         updatePostHeadQuery = loadQuery("/sql/update_post_head.sql");
      }
      return updatePostHeadQuery.replace("@@TABLE@@", headTable);
   }
   
   public static String getReadThreadQuery() throws IOException{
      if (readThreadQuery == null){
         readThreadQuery = loadQuery("/sql/read_thread.sql");
      }
      return readThreadQuery;
   }
   
   public static String getFirstPostIdInThreadQuery() throws IOException{
      if (firstPostIdInThreadQuery == null){
         firstPostIdInThreadQuery = loadQuery("/sql/first_post_id_in_thread.sql");
      }
      return firstPostIdInThreadQuery;
   }
   
   public static String getCreatePostQuery() throws IOException{
      if (createPostQuery == null){
         createPostQuery = loadQuery("/sql/create_post.sql");
      }
      return createPostQuery;
   }
   
   public static String getReadPostQuery() throws IOException{
      if (readPostQuery == null){
         readPostQuery = loadQuery("/sql/read_post.sql");
      }
      return readPostQuery;
   }
   
   public static String getReadPostHeadQuery(String headTable) throws IOException{
      if (readPostHeadQuery == null){
         readPostHeadQuery = loadQuery("/sql/read_post_head.sql");
      }
      return readPostHeadQuery.replace("@@TABLE@@", headTable);
   }
   
   public static String getReadPostBodyQuery(String bodyTable) throws IOException{
      if (readPostBodyQuery == null){
         readPostBodyQuery = loadQuery("/sql/read_post_body.sql");
      }
      return readPostBodyQuery.replace("@@TABLE@@", bodyTable);
   }
   
   public static String getAddedPostsAmountQuery() throws IOException{
      if (addedPostsAmountQuery == null){
         addedPostsAmountQuery = loadQuery("/sql/added_posts_amount.sql");
      }
      return addedPostsAmountQuery;
   }
   
   public static String getAddedPostsInThreadAmountQuery() throws IOException{
      if (addedPostsInThreadAmountQuery == null){
         addedPostsInThreadAmountQuery = loadQuery("/sql/added_posts_in_thread_amount.sql");
      }
      return addedPostsInThreadAmountQuery;
   }
   
   public static String getAddedThreadsAmountQuery() throws IOException{
      if (addedThreadsAmountQuery == null){
         addedThreadsAmountQuery = loadQuery("/sql/added_threads_amount.sql");
      }
      return addedThreadsAmountQuery;
   }
   
   public static String getCreatePostBodyQuery(String bodyTableName) throws IOException{
      if (createPostBodyQuery == null){
         createPostBodyQuery = loadQuery("/sql/create_post_body.sql");
      }
      return createPostBodyQuery.replace("@@currentBodyTable@@", bodyTableName);
   }
   
   public static String getCreatePostHeadQuery(String headTableName) throws IOException{
      if (createPostHeadQuery == null){
         createPostHeadQuery = loadQuery("/sql/create_post_head.sql");
      }
      return createPostHeadQuery.replace("@@currentHeadTable@@", headTableName);
   }
   
   public static String getLoadAnswersQuery() throws IOException{
      if (loadAnswersQuery == null){
         loadAnswersQuery = loadQuery("/sql/load_answers.sql");
      }
      return loadAnswersQuery;
   }
   
   public static String getLoadIgnorQuery() throws IOException{
      if (loadIgnorQuery == null){
         loadIgnorQuery = loadQuery("/sql/load_ignor.sql");
      }
      return loadIgnorQuery;
   }
   
   public static String getUpdateIgnorQuery() throws IOException{
      if (updateIgnorQuery == null){
         updateIgnorQuery = loadQuery("/sql/update_ignor.sql");
      }
      return updateIgnorQuery;
   }
   
   public static String getCreateAnswerQuery() throws IOException{
      if (createAnswerQuery == null){
         createAnswerQuery = loadQuery("/sql/create_answer.sql");
      }
      return createAnswerQuery;
   }
   
   public static String getReceiveMailQuery() throws IOException{
      if (receiveMailQuery  == null){
         receiveMailQuery  = loadQuery("/sql/receive_mail.sql");
      }
      return receiveMailQuery ;
   }
   
   public static String getMarkMailAsReadQuery() throws IOException{
      if (markMailAsReadQuery  == null){
         markMailAsReadQuery  = loadQuery("/sql/mark_mail_as_read.sql");
      }
      return markMailAsReadQuery ;
   }
   
   public static String getLoadInboxQuery() throws IOException{
      if (loadInboxQuery  == null){
         loadInboxQuery  = loadQuery("/sql/load_inbox.sql");
      }
      return loadInboxQuery ;
   }
   
   public static String getLoadOutNotReceivedBoxQuery() throws IOException{
      if (loadOutNotReceivedBoxQuery  == null){
         loadOutNotReceivedBoxQuery  = loadQuery("/sql/load_out_not_received_box.sql");
      }
      return loadOutNotReceivedBoxQuery ;
   }
   
   public static String getLoadOutReceivedBoxQuery() throws IOException{
      if (loadOutReceivedBoxQuery  == null){
         loadOutReceivedBoxQuery  = loadQuery("/sql/load_out_received_box.sql");
      }
      return loadOutReceivedBoxQuery;
   }
   
   public static String getLoadDraftBoxQuery() throws IOException{
      if (loadDraftBoxQuery  == null){
         loadDraftBoxQuery  = loadQuery("/sql/load_draft_box.sql");
      }
      return loadDraftBoxQuery;
   }
   
   public static String getLoadMailQuery() throws IOException{
      if (loadMailQuery  == null){
         loadMailQuery  = loadQuery("/sql/load_mail.sql");
      }
      return loadMailQuery;
   }
   
   public static String getDeleteFromOutboxQuery() throws IOException{
      if (deleteFromOutboxQuery  == null){
         deleteFromOutboxQuery  = loadQuery("/sql/delete_from_outbox.sql");
      }
      return deleteFromOutboxQuery;
   }
   
   public static String getDeleteFromInboxQuery() throws IOException{
      if (deleteFromInboxQuery  == null){
         deleteFromInboxQuery  = loadQuery("/sql/delete_from_inbox.sql");
      }
      return deleteFromInboxQuery;
   }
   
   public static String getLoadInterfacesQuery() throws IOException{
      if (loadInterfacesQuery == null){
         loadInterfacesQuery = loadQuery("/sql/load_interfaces.sql");
      }
      return loadInterfacesQuery;
   }
   
   public static String getLoadInterfaceQuery() throws IOException{
      if (loadInterfaceQuery == null){
         loadInterfaceQuery = loadQuery("/sql/load_interface.sql");
      }
      return loadInterfaceQuery;
   }
   
   public static String getLoadFoldersInQuery() throws IOException{
      if (loadFoldersInQuery == null){
         loadFoldersInQuery = loadQuery("/sql/load_folders_in.sql");
      }
      return loadFoldersInQuery;
   }
   
   public static String getLoadFoldersNotInQuery() throws IOException{
      if (loadFoldersNotInQuery == null){
         loadFoldersNotInQuery = loadQuery("/sql/load_folders_not_in.sql");
      }
      return loadFoldersNotInQuery;
   }
   
   public static String getLoadFoldersQuery() throws IOException{
      if (loadFoldersQuery == null){
         loadFoldersQuery = loadQuery("/sql/load_folders.sql");
      }
      return loadFoldersQuery;
   }
   
   public static String getLoadSubscribesQuery() throws IOException{
      if (loadSubscribesQuery == null){
         loadSubscribesQuery = loadQuery("/sql/load_subscribes.sql");
      }
      return loadSubscribesQuery;
   }
   
   public static String getDeleteSubscribeByTitleIdQuery() throws IOException{
      if (deleteSubscribeByTitleIdQuery == null){
         deleteSubscribeByTitleIdQuery = loadQuery("/sql/delete_subscribe_by_title_id.sql");
      }
      return deleteSubscribeByTitleIdQuery;
   }
   
   public static String getDeleteSubscribeByIdQuery() throws IOException{
      if (deleteSubscribeByIdQuery == null){
         deleteSubscribeByIdQuery = loadQuery("/sql/delete_subscribe_by_id.sql");
      }
      return deleteSubscribeByIdQuery;
   }
   
   public static String getDeleteSubscribeByKeyQuery() throws IOException{
      if (deleteSubscribeByKeyQuery == null){
         deleteSubscribeByKeyQuery = loadQuery("/sql/delete_subscribe_by_key.sql");
      }
      return deleteSubscribeByKeyQuery;
   }
   
   public static String getIsSubscribeKeyPresentQuery() throws IOException{
      if (isSubscribeKeyPresentQuery == null){
         isSubscribeKeyPresentQuery = loadQuery("/sql/is_subscribe_key_present.sql");
      }
      return isSubscribeKeyPresentQuery;
   }
   
   public static String getCreateSubscribeQuery() throws IOException{
      if (createSubscribeQuery == null){
         createSubscribeQuery = loadQuery("/sql/create_subscribe.sql");
      }
      return createSubscribeQuery;
   }
   
   private static String loadQuery(String path) throws IOException{
      ClassLoader classLoader = FJServletTools.class.getClassLoader();
      InputStream stream = classLoader.getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      StringBuffer result = new StringBuffer();
      while(br.ready()){
         result.append(br.readLine() + "\n");
      }
      return result.toString();
   }
}