<script language="PHP">
function fd_smiles($tmptxt) {
   $tmptxt=str_replace(":)","<img border='0' src='forum/smiles/smile_.gif'>",$tmptxt);
   $tmptxt=str_replace(":(","<img border='0' src='forum/smiles/sad.gif'>",$tmptxt);
   $tmptxt=str_replace(":D","<img border='0' src='forum/smiles/biggrin.gif'>",$tmptxt);
   $tmptxt=str_replace(":[russian]","<img border='0' src='forum/smiles/russian.gif'>",$tmptxt);
   $tmptxt=str_replace(":[pioners]","<img border='0' src='forum/smiles/take_example.gif'>",$tmptxt);
   $tmptxt=str_replace(":[beer]","<img border='0' src='forum/smiles/drinks.gif'>",$tmptxt);
   $tmptxt=str_replace(":[no-no]","<img border='0' src='forum/smiles/acute.gif'>",$tmptxt);
   $tmptxt=str_replace(":[nea]","<img border='0' src='forum/smiles/nea.gif'>",$tmptxt);
   $tmptxt=str_replace(":[babruysk]","<img border='0' src='forum/smiles/to_babruysk.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ohi]","<img border='0' src='forum/smiles/girl_sigh.gif'>",$tmptxt);
   $tmptxt=str_replace(":[klizma]","<img border='0' src='forum/smiles/girl_hospital.gif'>",$tmptxt);
   $tmptxt=str_replace(":[king]","<img border='0' src='forum/smiles/king2.gif'>",$tmptxt);
   $tmptxt=str_replace(":g)","<img border='0' src='forum/smiles/girl_smile.gif'>",$tmptxt);
   $tmptxt=str_replace(":g(","<img border='0' src='forum/smiles/girl_sad.gif'>",$tmptxt);
   $tmptxt=str_replace(":[blum]","<img border='0' src='forum/smiles/girl_blum.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ghaha]","<img border='0' src='forum/smiles/girl_haha.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gwacko]","<img border='0' src='forum/smiles/girl_wacko.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gmad]","<img border='0' src='forum/smiles/girl_mad.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ghide]","<img border='0' src='forum/smiles/girl_hide.gif'>",$tmptxt);
   $tmptxt=str_replace(":[glove]","<img border='0' src='forum/smiles/girl_in_love.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gfish]","<img border='0' src='forum/smiles/girl_prepare_fish.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gcrazy]","<img border='0' src='forum/smiles/girl_crazy.gif'>",$tmptxt);
   $tmptxt=str_replace(":[mblum]","<img border='0' src='forum/smiles/blum3.gif'>",$tmptxt);
   $tmptxt=str_replace(":[toclue]","<img border='0' src='forum/smiles/to_clue.gif'>",$tmptxt);
   $tmptxt=str_replace(":[snooks]","<img border='0' src='forum/smiles/snooks.gif'>",$tmptxt);
   $tmptxt=str_replace(":[scare]","<img border='0' src='forum/smiles/scare.gif'>",$tmptxt);
   $tmptxt=str_replace(":[scare2]","<img border='0' src='forum/smiles/scare2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gwerewolf]","<img border='0' src='forum/smiles/girl_werewolf.gif'>",$tmptxt);
   $tmptxt=str_replace(":[gdevil]","<img border='0' src='forum/smiles/girl_devil.gif'>",$tmptxt);
   $tmptxt=str_replace(":[friends]","<img border='0' src='forum/smiles/friends.gif'>",$tmptxt);
   $tmptxt=str_replace(":[taunt]","<img border='0' src='forum/smiles/taunt.gif'>",$tmptxt);
   $tmptxt=str_replace(":[offtopic]","<img border='0' src='forum/smiles/offtopic.gif'>",$tmptxt);
   $tmptxt=str_replace(":[queen]","<img border='0' src='forum/smiles/queen.gif'>",$tmptxt);
   $tmptxt=str_replace(":[butcher]","<img border='0' src='forum/smiles/butcher.gif'>",$tmptxt);
   $tmptxt=str_replace(":[rtfm]","<img border='0' src='forum/smiles/rtfm.gif'>",$tmptxt);
   $tmptxt=str_replace(":[shok]","<img border='0' src='forum/smiles/shok.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr2]","<img border='0' src='forum/smiles/KidRock_02.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr5]","<img border='0' src='forum/smiles/KidRock_05.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr7]","<img border='0' src='forum/smiles/KidRock_07.gif'>",$tmptxt);
   $tmptxt=str_replace(":[kr4]","<img border='0' src='forum/smiles/KidRock_04.gif'>",$tmptxt);
   $tmptxt=str_replace(":[whistle]","<img border='0' src='forum/smiles/whistle.gif'>",$tmptxt);
   $tmptxt=str_replace(":[whatever]","<img border='0' src='forum/smiles/whatever_01.gif'>",$tmptxt);
   $tmptxt=str_replace(":[vinsent]","<img border='0' src='forum/smiles/vinsent.gif'>",$tmptxt);
   $tmptxt=str_replace(":[victory]","<img border='0' src='forum/smiles/victory.gif'>",$tmptxt);
   $tmptxt=str_replace(":[triniti]","<img border='0' src='forum/smiles/triniti.gif'>",$tmptxt);
   $tmptxt=str_replace(":[tommy]","<img border='0' src='forum/smiles/tommy.gif'>",$tmptxt);
   $tmptxt=str_replace(":[to_keep_order]","<img border='0' src='forum/smiles/to_keep_order.gif'>",$tmptxt);
   $tmptxt=str_replace(":[tease]","<img border='0' src='forum/smiles/tease.gif'>",$tmptxt);
   $tmptxt=str_replace(":[suicide]","<img border='0' src='forum/smiles/suicide.gif'>",$tmptxt);
   $tmptxt=str_replace(":[spruce_up]","<img border='0' src='forum/smiles/spruce_up.gif'>",$tmptxt);
   $tmptxt=str_replace(":[slow]","<img border='0' src='forum/smiles/slow.gif'>",$tmptxt);
   $tmptxt=str_replace(":[skull]","<img border='0' src='forum/smiles/skull.gif'>",$tmptxt);
   $tmptxt=str_replace(":[rofl]","<img border='0' src='forum/smiles/rofl.gif'>",$tmptxt);
   $tmptxt=str_replace(":[read]","<img border='0' src='forum/smiles/read.gif'>",$tmptxt);
   $tmptxt=str_replace(":[rabbi]","<img border='0' src='forum/smiles/rabbi.gif'>",$tmptxt);
   $tmptxt=str_replace(":[punish]","<img border='0' src='forum/smiles/punish.gif'>",$tmptxt);
   $tmptxt=str_replace(":[pooh_door]","<img border='0' src='forum/smiles/pooh_door.gif'>",$tmptxt);
   $tmptxt=str_replace(":[pioneer]","<img border='0' src='forum/smiles/pioneer.gif'>",$tmptxt);
   $tmptxt=str_replace(":[ok]","<img border='0' src='forum/smiles/ok.gif'>",$tmptxt);
   $tmptxt=str_replace(":[new_russian]","<img border='0' src='forum/smiles/new_russian.gif'>",$tmptxt);
   $tmptxt=str_replace(":[moil]","<img border='0' src='forum/smiles/moil.gif'>",$tmptxt);
   $tmptxt=str_replace(":[lazy2]","<img border='0' src='forum/smiles/lazy2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[jc]","<img border='0' src='forum/smiles/Just_Cuz_11.gif'>",$tmptxt);
   $tmptxt=str_replace(":[hi]","<img border='0' src='forum/smiles/hi.gif'>",$tmptxt);
   $tmptxt=str_replace(":[help]","<img border='0' src='forum/smiles/help.gif'>",$tmptxt);
   $tmptxt=str_replace(":[heat]","<img border='0' src='forum/smiles/heat.gif'>",$tmptxt);
   $tmptxt=str_replace(":[good]","<img border='0' src='forum/smiles/good.gif'>",$tmptxt);
   $tmptxt=str_replace(":[fuck]","<img border='0' src='forum/smiles/fuck.gif'>",$tmptxt);
   $tmptxt=str_replace(":[fool]","<img border='0' src='forum/smiles/fool.gif'>",$tmptxt);
   $tmptxt=str_replace(":[flirt]","<img border='0' src='forum/smiles/flirt.gif'>",$tmptxt);
   $tmptxt=str_replace(":[dntknw]","<img border='0' src='forum/smiles/dntknw.gif'>",$tmptxt);
   $tmptxt=str_replace(":[dance2]","<img border='0' src='forum/smiles/dance2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[brunette]","<img border='0' src='forum/smiles/brunette.gif'>",$tmptxt);
   $tmptxt=str_replace(":[angel]","<img border='0' src='forum/smiles/angel.gif'>",$tmptxt);
   $tmptxt=str_replace(":[aleksey_01]","<img border='0' src='forum/smiles/aleksey_01.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_cray2]","<img border='0' src='forum/smiles/girl_cray2.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_cray3]","<img border='0' src='forum/smiles/girl_cray3.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_impossible]","<img border='0' src='forum/smiles/girl_impossible.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_wink]","<img border='0' src='forum/smiles/girl_wink.gif'>",$tmptxt);
   $tmptxt=str_replace(":[girl_dance]","<img border='0' src='forum/smiles/girl_dance.gif'>",$tmptxt);
   $tmptxt=str_replace(":[snoozer_18]","<img border='0' src='forum/smiles/snoozer_18.gif'>",$tmptxt);
   $tmptxt=str_replace(":[drag_10]","<img border='0' src='forum/smiles/drag_10.gif'>",$tmptxt);
   $tmptxt=str_replace(":[Koshechka_09]","<img border='0' src='forum/smiles/Koshechka_09.gif'>",$tmptxt);
   $tmptxt=str_replace(":[Koshechka_11]","<img border='0' src='forum/smiles/Koshechka_11.gif'>",$tmptxt);
   $tmptxt=str_replace(":[libelle_1]","<img border='0' src='forum/smiles/libelle_1.gif'>",$tmptxt);
   $tmptxt=str_replace(":[connie_6]","<img border='0' src='forum/smiles/connie_6.gif'>",$tmptxt);
   $tmptxt=str_replace(":[connie_1]","<img border='0' src='forum/smiles/connie_1.gif'>",$tmptxt);
   $tmptxt=str_replace(":[aftar]","<img border='0' src='forum/smiles/aftar.gif'>",$tmptxt);
   $tmptxt=str_replace(":[party]","<img border='0' src='forum/smiles/party.gif'>",$tmptxt);
   $tmptxt=str_replace(":[smoke]","<img border='0' src='forum/smiles/smoke.gif'>",$tmptxt);
   $tmptxt=str_replace(":[feminist]","<img border='0' src='forum/smiles/feminist.gif'>",$tmptxt);
   $tmptxt=str_replace(":[spam_light]","<img border='0' src='forum/smiles/spam_light.gif'>",$tmptxt);
   $tmptxt=str_replace(":[laie_32]","<img border='0' src='forum/smiles/laie_32.gif'>",$tmptxt);
   $tmptxt=str_replace(":[laie_44]","<img border='0' src='forum/smiles/laie_44.gif'>",$tmptxt);
   $tmptxt=str_replace(":[laie_48]","<img border='0' src='forum/smiles/laie_48.gif'>",$tmptxt);
   $tmptxt=str_replace(";)","<img border='0' src='forum/smiles/wink3.gif'>",$tmptxt);
   
   $_result=$tmptxt;
   return $_result;
}
</script>