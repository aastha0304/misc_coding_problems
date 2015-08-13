package hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class BinPacking {
	void display(Map<String, Integer> h){
		for (Map.Entry<String, Integer> entry : h.entrySet()) {
		    String key = entry.getKey();
		    int value = (int)entry.getValue();
		    System.out.println(key+" "+value);
		}
	}
	int getMins(String word){
		return Integer.parseInt(word.substring(0,word.indexOf("min")));
	}
	int firstFit(Map<String, Integer> h, int binSz, String tm) throws ParseException{
		int csz = binSz;
		int fintasks = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mma");
		Date start = sdf.parse(tm);
		Calendar cal = Calendar.getInstance();
		for(Iterator<Map.Entry<String, Integer>> it = h.entrySet().iterator(); it.hasNext(); ) {
		      Map.Entry<String, Integer> entry = it.next();
			  int dur = h.get(entry.getKey());
			  cal.setTime(start);
			  if(dur<csz){
				  csz-=dur;
				  fintasks++;
				  System.out.println(sdf.format(start)+" "+entry.getKey());
				  it.remove();
				  cal.add(Calendar.MINUTE, dur);
				  start = cal.getTime();
				  if(csz>=binSz)
					  break;
			  }
		}
		if(binSz==240){
			if(start.after(sdf.parse("04:00PM")))
				System.out.println(sdf.format(start)+" "+"Networking Event");
			else
				System.out.println("04:00PM Networking Event");
		}
		return fintasks;
	}
	public static void main(String[] args) throws IOException, ParseException{
		BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		String line;
		Map<String, Integer> h = new LinkedHashMap<String, Integer>();
		BinPacking ob = new BinPacking();
		int tasks = 0;
		while((line = systemIn.readLine()) != null) {
		    String tmp = line.trim();
		    if(!tmp.isEmpty()){
			    int lw = tmp.lastIndexOf(' ');
			    String key = tmp.substring(0, lw);
			    String val = tmp.substring(lw+1);
			    if(val.equals("lightning")){
			    	val = "5min";
			    }
			    int mins = ob.getMins(val);
			    h.put(key, mins);
			    tasks++;
		    }
		}
		int fin = 0;
		boolean morning = true;
		int tracks = 0;
		while(fin<tasks){
			if(morning){
				tracks++;
				System.out.println("Track "+tracks);
				fin += ob.firstFit(h, 180, "09:00AM");
				System.out.println("12:00PM Lunch");
				morning = !morning;
				
			}else{
				fin += ob.firstFit(h, 240, "01:00PM");
				morning = !morning;
			}
		}
	}
}
