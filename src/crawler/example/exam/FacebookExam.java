package crawler.example.exam;

import com.github.abola.crawler.CrawlerPack;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 練習：取得任一篇或多篇文章的 Reactions 總數
 *
 *
 * 重點
 * 1. 先利用Graph Api調整出需要的資料
 * 2. 修改程式，使用爬蟲包取得資料
 * 3. 上傳至GitHub
 * 
 * @author Abola Lee
 *
 */
public class FacebookExam {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑

		String uri = 
				"https://graph.facebook.com/v2.6"
				+ "/Cubs/feed?fields=id,link,message,created_time,likes.limit(0).summary(total_count),reactions.type(LOVE).limit(0).summary(total_count)&since=1478059200&until=1478188800"
				+ "&access_token=EAACEdEose0cBAB8oS5s0yfIoZBaeBbwtzVzKEHZClpKKaW18nqZAEzpSwWfMm4HTQaDMKRL1qOLyHnKbu5S4kT4MJUghKGpaMFfrTkZAQHcFvYwyz8ss5JRcBvMJHbMuMFweYnp4701rb2cAbsMg5uGHgowIvZAHUknH6zNcTvgZDZD";


		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,message,created_time,#_of_likes,#_of_reactions\n";

		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String msg = data.select("message").text();
			String ct = data.select("created_time").text();
			String likes = data.select("likes").text();
			String rxns = data.select("reactions").text();
			// FIXIT
			String reactions = "";


			output += id+",\""+msg+"\","+ct+","+likes+","+rxns+"\n";
		}

		System.out.println( output );
//		Output:
//		id,message,created_time,#_of_likes,#_of_reactions
//		30530605658_10154449339340659,"David Ross homered in the final game of his career. He goes out a champion. #FlyTheW",2016-11-03T14:30:38+0000,91543,19040
//		30530605658_10154449111655659,"World Series MVP, Ben Zobrist. #FlyTheW",2016-11-03T13:00:41+0000,75308,11102
//		30530605658_10154448813880659,"Your #Cubs are #WorldSeries CHAMPS. Now celebrate with your fellow fans on the MLB Fans app: http://atmlb.com/2eqYoOa",2016-11-03T11:00:05+0000,38174,5991
//		30530605658_10154448783280659,"Today is our day. #FlyTheW",2016-11-03T10:45:57+0000,176348,34731
//		30530605658_10154448517145659,"This would not have been possible without you. Thank you, #Cubs fans! #FlyTheW",2016-11-03T07:07:15+0000,139724,26274
//		30530605658_10154448428485659,"What a game. What a series. What a season. #FlyTheW",2016-11-03T06:11:47+0000,132027,25662
//		30530605658_10154448204755659,"The Cubs are #WorldSeries #Champs. They discuss their epic #Game7 win.",2016-11-03T05:45:22+0000,64955,31329
//		30530605658_10154448294710659,"History. The Chicago Cubs win the 2016 World Series. #FlyTheW",2016-11-03T05:05:22+0000,252232,55676
//		30530605658_10154448230810659,"World Series Champions. #FlyTheW",2016-11-03T04:47:51+0000,238272,49934
//		30530605658_10154447652160659,"Moment of truth. Play ball! http://atmlb.com/2ephVOV #FlyTheW",2016-11-03T00:02:15+0000,32861,3931
//		30530605658_10154447507685659,"A sincere thank you to our fans past, present and future who make their mark on this organization. Let’s #FlyTheW tonight.",2016-11-02T23:01:43+0000,40742,7582
//		30530605658_10154447507685659,"A sincere thank you to our fans past, present and future who make their mark on this organization. Let’s #FlyTheW tonight.",2016-11-02T23:01:43+0000,40742,7582
//		30530605658_10154447316845659,"What is Cubs manager Joe Maddon thinking ahead of #WorldSeries #Game7?",2016-11-02T21:48:45+0000,23035,10216
//		30530605658_10154446904050659,"One game. It's all on the line. Let's #FlyTheW.",2016-11-02T19:10:14+0000,38621,4832
//		30530605658_10154446646985659,"#WorldSeries Game 6. What a game! #FlyTheW",2016-11-02T17:34:27+0000,32030,4608
//		30530605658_10154446240210659,"A swing of beauty. #FlyTheW",2016-11-02T15:00:53+0000,36533,4903
//		30530605658_10154446109380659,"In two road #WorldSeries starts, Jake Arrieta rose to the occasion. http://atmlb.com/2ezMuzc #FlyTheW",2016-11-02T14:00:16+0000,30480,3144
//		30530605658_10154445999570659,"Addison Russell's historic night. #FlyTheW",2016-11-02T13:00:40+0000,51562,8155
//		30530605658_10154445167940659,"Jake. Addi. Rizz. KB. Lots of heroes in #WorldSeries Game 6! #FlyTheW",2016-11-02T04:15:44+0000,61573,9813

	} 
}
