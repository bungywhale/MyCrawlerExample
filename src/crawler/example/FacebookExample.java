package crawler.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


/**
 * 資料探索練習 Facebook Graph Api Search 
 * 
 * 重點
 * 1. 利用Graph Api調整出需要的資料
 * 2. 取得一組Access Token (試著使用 long term token)
 * 3. 試著用『excel』或任何最簡易的方式，對資料進行探索
 * 
 * @author Abola Lee
 *
 */
public class FacebookExample {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
		// [query sample]
		// search?fields=name,id,likes,talking_about_count&limit=1000&q=靠北&type=page
//		String uri =
//				"https://graph.facebook.com/v2.5"
//				+ "/search?q=%E9%9D%A0%E5%8C%97&type=page&limit=1000&fields=name,id,likes,talking_about_count"
//				+ "&access_token=EAACEdEose0cBAMzLyMlMxZAY73J1TBiZAHJhrF8du8fLhMmGYrUA3W9ezJDQk9oZAa07o4F2drZAL2k1cCtgdpl5LZClZCo6733A3xddNbjSAoTxEZBWY4rKnzLWTU3I0KMgT0qsw6eIRfdzg9RZCIkuTZCtGZCyzct657fywQpsFCNAZDZD";
		String uri =
				"https://graph.facebook.com/v2.5"
				+ "/search?q=chicago&type=page&limit=10&fields=name,id,likes,talking_about_count"
				+ "&access_token=EAACEdEose0cBAB8oS5s0yfIoZBaeBbwtzVzKEHZClpKKaW18nqZAEzpSwWfMm4HTQaDMKRL1qOLyHnKbu5S4kT4MJUghKGpaMFfrTkZAQHcFvYwyz8ss5JRcBvMJHbMuMFweYnp4701rb2cAbsMg5uGHgowIvZAHUknH6zNcTvgZDZD";


		// Jsoup select 後回傳的是  Elements 物件
//		[data sample]
//		----
//		{
//			"data": [
//			{
//				"name": "Chicago Cubs",
//					"id": "30530605658",
//					"likes": 3465540,
//					"talking_about_count": 191383
//			}
//		}
		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,名稱,按讚數,討論人數\n";
		
		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String name = data.select("name").text();
			String likes = data.select("likes").text();
			String talking_about_count = data.select("talking_about_count").text();
			
			output += id+",\""+name+"\","+likes+","+talking_about_count+"\n";
		}
		
		System.out.println( output );

//		Output:
//		id,名稱,按讚數,討論人數
//		30530605658,"Chicago Cubs",3465542,191383
//		37152881613,"Chicago Bulls",18876323,273894
//		108659242498155,"Chicago, Illinois",1725963,0
//		29919479257,"Chicago Blackhawks",2993699,85158
//		162573130535985,"Chicago Fire",2264690,191826
//		115897014451,"Chicago Bears",4135140,111889
//		5953023255,"Chicago Tribune",484423,76325
//		49711380812,"Chicago Cares",6762,52
//		239104226231678,"Chicago P.D.",1322278,30546
//		1479040865653054,"Chicago Med",470453,22725
	} 
}
