package tw.agudoe2.highwayfee;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.app.Activity;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.BadTokenException;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.math.*;
import org.apache.http.util.ByteArrayBuffer;

public class MainActivity extends Activity {

	static int [] highwayAcc={0,1,77,82,89,166,170,176,184,191,197,204};
	static String [] highwayName={"無","國1","國1汐五高架","國2","國3","國3甲","國4","國5","國6","國8","國10"};
	static String [] detName={
"0無", //無
"0基隆端", //國道1
"1基隆",
"2八堵",
"6五堵",
"9汐止收費站",
"10汐止",
"11汐止系統",
"12汐止端",
"15東湖",
"16內湖",
"23圓山",
"25台北",
"27三重",
"33五股",
"35泰山收費站",
"41林口",
"49桃園",
"52機場系統",
"57內壢",
"62中壢",
"65平鎮系統",
"67幼獅",
"69楊梅",
"71楊梅收費站",
"83湖口",
"91竹北",
"95新竹",
"99新竹系統",
"110頭份",
"117造橋收費站",
"132苗栗",
"150三義",
"160后里",
"162后里收費站",
"165台中系統",
"168豐原",
"174大雅",
"178台中",
"181南屯",
"189王田",
"192彰化系統",
"198彰化",
"207埔鹽系統",
"211員林",
"218員林收費站",
"220北斗",
"230西螺",
"236虎尾",
"240斗南",
"243雲林系統",
"246斗南收費站",
"250大林",
"257民雄",
"264嘉義",
"270水上",
"272嘉義系統",
"280新營收費站",
"288新營",
"303麻豆",
"311安定",
"313新市收費站",
"315台南系統",
"319永康",
"327台南",
"330仁德系統",
"338路竹",
"342高科",
"346岡山收費站",
"349岡山",
"356楠梓",
"362鼎金系統",
"367高雄",
"369瑞隆路",
"370五甲系統",
"371五甲",
"372高雄端",
"12汐止端", //國道1汐五高架
"18堤頂",
"20下塔悠",
"26環北",
"32五股",
"0機場端", //國道2
"1大園",
"5大竹",
"8機場系統",
"11南桃園",
"18大湳",
"20鶯歌系統",
"0基金", //國道3
"2瑪東系統",
"4七堵收費站",
"10汐止系統",
"12新台五路",
"15南港",
"16南港系統",
"17南深路",
"20木柵",
"26新店",
"31安坑",
"35中和",
"43土城",
"46樹林收費站",
"50三鶯",
"54鶯歌系統",
"62大溪",
"68龍潭",
"72龍潭收費站",
"79關西",
"90竹林",
"98寶山",
"100新竹系統",
"103茄苳",
"109香山",
"115西濱",
"119竹南",
"122後龍收費站",
"124大山",
"130後龍",
"144通霄",
"156苑裡",
"158大甲收費站",
"164大甲",
"169中港系統",
"176沙鹿",
"182龍井",
"191和美",
"196彰化系統",
"202快官",
"207烏日",
"209中投",
"211霧峰",
"214霧峰系統",
"217草屯",
"222中興系統",
"224中興",
"234名間收費站",
"236名間",
"243竹山",
"260斗六",
"269古坑系統",
"273古坑收費站",
"279梅山",
"290竹崎",
"297中埔",
"300水上系統",
"311白河",
"313白河收費站",
"329烏山頭",
"334官田",
"340善化",
"342善化收費站",
"346新化系統",
"357關廟",
"369田寮",
"373田寮收費站",
"383燕巢系統",
"391九如",
"400長治",
"407麟洛",
"411竹田收費站",
"415竹田系統",
"421崁頂",
"424南州",
"430林邊",
"431大鵬灣端",
"0台北端", //國道3甲
"3萬芳",
"5木柵",
"6深坑端",
"0清水端", //國道4
"2中港系統",
"9神岡",
"11台中系統",
"14后豐",
"17豐原端",
"0南港系統", //國道5
"4石碇",
"15坪林",
"30頭城收費站",
"30頭城",
"38宜蘭",
"46羅東",
"54蘇澳",
"0霧峰系統", //國道6
"3舊正",
"5東草屯",
"17國姓",
"29愛蘭",
"34埔里",
"37埔里端",
"0台南端", //國道8
"2新吉",
"6台南系統",
"9新市",
"14新化系統",
"15新化端",
"0左營端", //國道10
"1鼎金系統",
"6仁武",
"13燕巢",
"19燕巢系統",
"22嶺口",
"33旗山端"};
	static String [] mulName={"x1","x2","x3","x4","x5","x6","x7","x8","x9"};

	static int queryMax=6;
	Spinner Sp_highway [];
	Spinner Sp_from [];
	Spinner Sp_to [];
	Spinner Sp_mul [];
	ArrayAdapter<String> Ad_highway;
	ArrayAdapter<String> Ad_interchange [];
	ArrayAdapter<String> Ad_mul;
	String feeStation="";
	int defaultInterchangeFrom [];
	int defaultInterchangeTo [];

	Button Bt_sta1;
	Button Bt_sta2;
	Button Bt_sta3;
	Button Bt_sta4;
	Button Bt_stations;

	int stationFee = 40;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle(getResources().getString(R.string.app_name)+" (highwayfee)");

		Bt_sta1 = (Button) findViewById(R.id.Bt_sta1);
		Bt_sta2 = (Button) findViewById(R.id.Bt_sta2);
		Bt_sta3 = (Button) findViewById(R.id.Bt_sta3);
		Bt_sta4 = (Button) findViewById(R.id.Bt_sta4);
		Bt_stations = (Button) findViewById(R.id.Bt_stations);

		Sp_highway = new Spinner [queryMax];
		Sp_from = new Spinner [queryMax];
		Sp_to = new Spinner [queryMax];
		Sp_mul = new Spinner [queryMax];
		Sp_highway[0] = (Spinner) findViewById(R.id.Sp_highway0);
		Sp_from[0] = (Spinner) findViewById(R.id.Sp_from0);
		Sp_to[0] = (Spinner) findViewById(R.id.Sp_to0);
		Sp_mul[0] = (Spinner) findViewById(R.id.Sp_mul0);
		Sp_highway[1] = (Spinner) findViewById(R.id.Sp_highway1);
		Sp_from[1] = (Spinner) findViewById(R.id.Sp_from1);
		Sp_to[1] = (Spinner) findViewById(R.id.Sp_to1);
		Sp_mul[1] = (Spinner) findViewById(R.id.Sp_mul1);
		Sp_highway[2] = (Spinner) findViewById(R.id.Sp_highway2);
		Sp_from[2] = (Spinner) findViewById(R.id.Sp_from2);
		Sp_to[2] = (Spinner) findViewById(R.id.Sp_to2);
		Sp_mul[2] = (Spinner) findViewById(R.id.Sp_mul2);
		Sp_highway[3] = (Spinner) findViewById(R.id.Sp_highway3);
		Sp_from[3] = (Spinner) findViewById(R.id.Sp_from3);
		Sp_to[3] = (Spinner) findViewById(R.id.Sp_to3);
		Sp_mul[3] = (Spinner) findViewById(R.id.Sp_mul3);
		Sp_highway[4] = (Spinner) findViewById(R.id.Sp_highway4);
		Sp_from[4] = (Spinner) findViewById(R.id.Sp_from4);
		Sp_to[4] = (Spinner) findViewById(R.id.Sp_to4);
		Sp_mul[4] = (Spinner) findViewById(R.id.Sp_mul4);
		Sp_highway[5] = (Spinner) findViewById(R.id.Sp_highway5);
		Sp_from[5] = (Spinner) findViewById(R.id.Sp_from5);
		Sp_to[5] = (Spinner) findViewById(R.id.Sp_to5);
		Sp_mul[5] = (Spinner) findViewById(R.id.Sp_mul5);

		Ad_highway = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, highwayName);
		Ad_highway.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
		Ad_mul = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mulName);
		Ad_mul.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
		Ad_interchange = new ArrayAdapter [queryMax];
		for(int i=0; i<queryMax; i++) {
			Ad_interchange[i] = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
			Ad_interchange[i].setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
			Sp_highway[i].setAdapter(Ad_highway);
			Sp_from[i].setAdapter(Ad_interchange[i]);
			Sp_to[i].setAdapter(Ad_interchange[i]);
			Sp_mul[i].setAdapter(Ad_mul);

			initAdapter(i, 0);
		}

		for(int i=0; i<queryMax; i++) {
			final int qi=i;
			Sp_highway[qi].setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
				public void onItemSelected(AdapterView adapterView, View view, int position, long id){
					initAdapter(qi, position);
					TextView TV_a=null;
					TextView TV_c=null;
					switch(qi) {
					case 0: TV_a=(TextView)findViewById(R.id.TV_a0); TV_c=(TextView)findViewById(R.id.TV_c0); break;
					case 1: TV_a=(TextView)findViewById(R.id.TV_a1); TV_c=(TextView)findViewById(R.id.TV_c1); break;
					case 2: TV_a=(TextView)findViewById(R.id.TV_a2); TV_c=(TextView)findViewById(R.id.TV_c2); break;
					case 3: TV_a=(TextView)findViewById(R.id.TV_a3); TV_c=(TextView)findViewById(R.id.TV_c3); break;
					case 4: TV_a=(TextView)findViewById(R.id.TV_a4); TV_c=(TextView)findViewById(R.id.TV_c4); break;
					case 5: TV_a=(TextView)findViewById(R.id.TV_a5); TV_c=(TextView)findViewById(R.id.TV_c5); break;
					default: showAboutDialog("ERROR", "ERROR100:"+qi);
					}
					if(position==0) {
						Sp_from[qi].setVisibility(4);
						Sp_to[qi].setVisibility(4);
						Sp_mul[qi].setVisibility(4);
						TV_a.setVisibility(4);
						TV_c.setVisibility(4);
						Sp_mul[qi].setSelection(0);
					} else {
						Sp_from[qi].setVisibility(0);
						Sp_to[qi].setVisibility(0);
						Sp_mul[qi].setVisibility(0);
						TV_a.setVisibility(0);
						TV_c.setVisibility(0);
//					Log.v("text", " defaultInterchangeFrom["+qi+"] = "+defaultInterchangeFrom[position]);
						Sp_from[qi].setSelection(defaultInterchangeFrom[position]);
//					Log.v("text", " defaultInterchangeTo["+qi+"] = "+defaultInterchangeTo[position]);
						Sp_to[qi].setSelection(defaultInterchangeTo[position]);
					}
				}
				public void onNothingSelected(AdapterView arg0) {
				}
			});
			Sp_highway[qi].setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					Sp_highway[qi].setSelection(0);
					return true;
				}
			});

			Sp_from[qi].setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
				public void onItemSelected(AdapterView adapterView, View view, int position, long id){
					updateFee();
					defaultInterchangeFrom[Sp_highway[qi].getSelectedItemPosition()] = position;
//					Log.v("text", " defaultInterchangeFrom["+qi+"] = "+position);
				}
				public void onNothingSelected(AdapterView arg0) {
				}
			});

			Sp_to[qi].setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
				public void onItemSelected(AdapterView adapterView, View view, int position, long id){
					updateFee();
					defaultInterchangeTo[Sp_highway[qi].getSelectedItemPosition()] = position;
//					Log.v("text", " defaultInterchangeTo["+qi+"] = "+position);
				}
				public void onNothingSelected(AdapterView arg0) {
				}
			});

			Sp_mul[qi].setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
				public void onItemSelected(AdapterView adapterView, View view, int position, long id){
					updateFee();
				}
				public void onNothingSelected(AdapterView arg0) {
				}
			});
		}


		Bt_sta1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)  {
				switch(stationFee) {
				case 40:
				stationFee=38;
				break;
				case 38:
				stationFee=36;
				break;
				case 36:
				stationFee=40;
				break;
				}
				updateFee();
			}
		} );

		Bt_sta2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)  {
				showAboutDialog("無免費里程方案說明","無免費里程。每公里$0.82。\n\n請注意：本方案僅為試算。實際施行方案尚未決定，一切辦法依政府主管機關公佈為準。");
			}
		} );

		Bt_sta3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)  {
				showAboutDialog("每天免費10公里方案說明","每天免費10公里。超過後每公里$1。\n\n請注意：本方案僅為試算。實際施行方案尚未決定，一切辦法依政府主管機關公佈為準。");
			}
		} );

		Bt_sta4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)  {
				showAboutDialog("每天免費20公里方案說明","每天免費20公里。20~200公里時每公里$1.2、超過200公里後每公里$0.9。\n\n請注意：本方案僅為試算。實際施行方案尚未決定，一切辦法依政府主管機關公佈為準。");
			}
		} );

		Bt_stations.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)  {
				showAboutDialog("經過以下收費站", feeStation);
			}
		} );

		Sp_highway[0].setSelection(1);
		showWarning();
		//showAboutDialog("請注意！",warning);


		defaultInterchangeFrom =new int [highwayName.length];
		defaultInterchangeTo =new int [highwayName.length];
		for(int i=0; i<highwayName.length; i++) {
			defaultInterchangeFrom[i] = 0;
			defaultInterchangeTo[i] = 1;
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
	menu.add(0, 0, 0, "注意事項");
	menu.add(0, 1, 0, "請惠賜五星評價");
	menu.add(0, 2, 0, "線上討論區");
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Uri uri;
		Intent it;

		switch(item.getItemId()) {
		case 0:
			showWarning();
			//showAboutDialog("請注意！",warning);
			break;
		case 1:
			uri = Uri.parse("market://details?id=tw.agudoe2.highwayfee");
			it = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(it);
			break;
		case 2: uri=Uri.parse("http://facebook.com/highwayfee/");
			it=new Intent(Intent.ACTION_VIEW,uri);
			startActivity(it);
			break;
			default:
		}
		return super.onOptionsItemSelected(item);
	}

	void initAdapter(int which, int initTo) {
//		Log.v("text", "which="+which+" initTo="+initTo);
		Ad_interchange[which].clear();
		for(int i=highwayAcc[initTo]; i<highwayAcc[initTo+1]; i++) {
			Ad_interchange[which].add(detName[i]);
		}
	}

	void updateFee() {
//		Log.v("text", "updateFee()");
		int mileSum=0;
		feeStation="";
		int cntStation=0;
		for(int i=0; i<queryMax; i++) {
			if(Sp_highway[i].getSelectedItemPosition()==0) {continue;}
			int from=beginNum(Sp_from[i].getSelectedItem().toString());
//			Log.v("text", "from["+i+"]="+from);
			int to=beginNum(Sp_to[i].getSelectedItem().toString());
//			Log.v("text", "to["+i+"]="+to);

			int start;
			int end;
			if(to>from) {
				mileSum += (to-from)*(Sp_mul[i].getSelectedItemPosition()+1);
				start=Sp_from[i].getSelectedItemPosition();
				end=Sp_to[i].getSelectedItemPosition();
			} else {
				mileSum -= (to-from)*(Sp_mul[i].getSelectedItemPosition()+1);
				end=Sp_from[i].getSelectedItemPosition();
				start=Sp_to[i].getSelectedItemPosition();
			}

			for(int j=start; j<=end; j++) {
				if(detName[highwayAcc[Sp_highway[i].getSelectedItemPosition()]+j].contains("收費站")) {
					int x=Sp_mul[i].getSelectedItemPosition()+1;
					feeStation += Sp_highway[i].getSelectedItem().toString()+": "+Ad_interchange[i].getItem(j).toString()+(x>0?" x"+x:"")+"\n";
					cntStation+=x;
				}
			}
		}

		TextView TV_cnt = (TextView) findViewById(R.id.TV_cnt);
		TV_cnt.setText("共"+mileSum+"公里，經");
		Bt_stations.setText(""+cntStation+"收費站");
// ---------------
		int tmp1=cntStation*stationFee;
		Bt_sta1.setText("收費站$"+stationFee);
		TextView TV_sta1 = (TextView) findViewById(R.id.TV_sta1);
		TV_sta1.setText("$"+tmp1);

// ---------------
		int tmp2=(int) Math.round(mileSum*0.82);
//		if(mileSum<=10) {
//			tmp2 = 0;
//		} else {
//			tmp2 = (mileSum-10)*1;
//		}
		String compare2="";
		TextView TV_sta2 = (TextView) findViewById(R.id.TV_sta2);
		if(tmp2>tmp1) {
			compare2 = " (貴$"+(tmp2-tmp1)+")";
			TV_sta2.setTextColor(Color.RED);
		} else if(tmp2<tmp1) {
			compare2 = " (便宜$"+(tmp1-tmp2)+")";
			TV_sta2.setTextColor(Color.BLUE);
		} else {
			compare2 = " (同價)";
			TV_sta2.setTextColor(Color.BLACK);
		}
		TV_sta2.setText("$"+tmp2+compare2);

// ---------------
		int tmp3;
		if(mileSum<=10) {
			tmp3 = 0;
		} else {
			tmp3 = mileSum-10;
		}
		String compare3="";
		TextView TV_sta3 = (TextView) findViewById(R.id.TV_sta3);
		if(tmp3>tmp1) {
			compare3 = " (貴$"+(tmp3-tmp1)+")";
			TV_sta3.setTextColor(Color.RED);
		} else if(tmp3<tmp1) {
			compare3 = " (便宜$"+(tmp1-tmp3)+")";
			TV_sta3.setTextColor(Color.BLUE);
		} else {
			compare3 = " (同價)";
			TV_sta3.setTextColor(Color.BLACK);
		}
		TV_sta3.setText("$"+tmp3+compare3);

// ---------------
		int tmp4;
		if(mileSum<=20) {
			tmp4 = 0;
		} else {
			tmp4 = mileSum-20;
			if(tmp4<=180) {
				tmp4=(int) Math.round(tmp4*1.2);
			} else {
				tmp4=(int) Math.round((tmp4-180)*0.9+180*1.2);
			}
		}
		String compare4="";
		TextView TV_sta4 = (TextView) findViewById(R.id.TV_sta4);
		if(tmp4>tmp1) {
			compare4 = " (貴$"+(tmp4-tmp1)+")";
			TV_sta4.setTextColor(Color.RED);
		} else if(tmp4<tmp1) {
			compare4 = " (便宜$"+(tmp1-tmp4)+")";
			TV_sta4.setTextColor(Color.BLUE);
		} else {
			compare4 = " (同價)";
			TV_sta4.setTextColor(Color.BLACK);
		}
		TV_sta4.setText("$"+tmp4+compare4);

	}

	int beginNum(String src) {
//		Log.v("text", "beginNum->"+src);
		return Integer.valueOf(src.replaceAll("^([0-9]+).*", "$1")).intValue();
	}

//	private boolean NetworkExisted() {
//		ConnectivityManager CM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo info = CM.getActiveNetworkInfo();
//		boolean ret=false;
//		if(info!=null) {
//			//return true;
//			ret= info.isConnected();
////		} else {
////			return false;
//		}
////		Log.v("text", "NetworkExisted="+ (ret?"true":"false") );
//		return ret;
//	}

	void showAboutDialog(final String title,String message){

//		final SpannableString s = new SpannableString(message);
//		Linkify.addLinks(s, Linkify.ALL);

		try {
			final AlertDialog builder = new AlertDialog.Builder(this)
			.setTitle(title)
			.setMessage(message)
			.setCancelable(false)
			.setNegativeButton("好", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			})
			.create();
			builder.show();
			((TextView)builder.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
		} catch (BadTokenException e) {
			e.printStackTrace();
			return;
		}
	}

	void showWarning() {
		String warning="交通部[計程通行費試算]網頁釋出！\n阿古多 2012/11/22\n\n\n高速公路預計將由計次轉為計程收費。為了提供大家更方便的比較結果，特別撰寫本APP免費提供大家使用。\n\n目前交通部於2012/10/08所提三種方案的試算，結果僅供參考且不具任何效力。最終實際行方案尚未決定，一切辦法依政府主管機關公佈為準。\n\n注意！目前APP僅計算同一天的過路費。如果要計算不同天過路費需要自行加總。\n\n例如：台北到高雄當天來回就選x2。\n如果是週六去，週日回來。倍率要選x1然後自行乘2才是正確的答案。\n\n如有任何建議歡迎隨時與我聯絡。謝謝。\n\n阿古多 2012/10/09";
		final AlertDialog builder = new AlertDialog.Builder(this)
		.setTitle("請注意！")
		.setMessage(warning)
		.setCancelable(false)
		.setPositiveButton("去[計程通行費試算]網頁看看", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					Uri uri=Uri.parse("http://www.freeway.gov.tw/Fare_Calculator/Fare_Calculator.aspx");
					Intent it=new Intent(Intent.ACTION_VIEW,uri);
					startActivity(it);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		})
		.setNegativeButton("好", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.create();
		builder.show();
	}
}
