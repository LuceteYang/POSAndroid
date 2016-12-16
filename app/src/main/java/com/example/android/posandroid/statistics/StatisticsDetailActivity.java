package com.example.android.posandroid.statistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.posandroid.R;
import com.example.android.posandroid.dao.MenuDao;
import com.example.android.posandroid.dao.OrderDao;
import com.example.android.posandroid.dao.OrderMenuDao;
import com.example.android.posandroid.model.Menu;
import com.example.android.posandroid.model.Order;
import com.example.android.posandroid.model.OrderMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StatisticsDetailActivity extends AppCompatActivity {
    TextView tv_best_statistics, tv_best_content,tv_worst_statistics,tv_worst_content;
    Button btn_statistics_ok;
    int type;
    OrderDao od;
    MenuDao md;
    OrderMenuDao omd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_detail);
        tv_best_statistics = (TextView)findViewById(R.id.tv_best_statistics);
        tv_best_content = (TextView)findViewById(R.id.tv_best_content);
        tv_worst_statistics = (TextView)findViewById(R.id.tv_worst_statistics);
        tv_worst_content = (TextView)findViewById(R.id.tv_worst_content);
        btn_statistics_ok= (Button)findViewById(R.id.btn_statistics_ok);
        type  = getIntent().getExtras().getInt("type");
        od = new OrderDao();
        md = new MenuDao();
        omd = new OrderMenuDao();
        btn_statistics_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(type==1){
            //메뉴
            tv_best_statistics.setText("Best 메뉴");
            tv_worst_statistics.setText("Worst 메뉴");
            List<Menu> menulist = md.menuList();
            Map<String,Integer> hm = new HashMap<String, Integer>();
            for(int i=0;i<menulist.size();i++){
                String menuNmae = menulist.get(i).getName();
                List<OrderMenu> omList = omd.orderMenuListByMenu(menuNmae);
                int count=0;
                for(int j = 0;j<omList.size();j++){
                    count+= omList.get(j).getCount();
                }
                hm.put(menuNmae,count);
            }
            List<String> strList = new ArrayList<>();
            hm=sortByValue(hm);
            for (Map.Entry<String,Integer> entry : hm.entrySet()) {
                //정렬한 리스트에서 순번을 배열번호로 변경하여 원본 리스트에서 추출
                strList.add(entry.getKey());
            }
            int j=1;
            String str="";
            for(int i=0;i<strList.size();i++){
                if(j<4){
                    str+=String.valueOf(j)+". "+strList.get(i)+"\n";
                }
                j++;
            }
            tv_worst_content.setText(str);
            String str1="";
            int t=1;
            for(int i=strList.size();i>0;i--){
                if(t<4){
                    str1+=String.valueOf(t)+". "+strList.get(i-1)+"\n";
                }
                t++;
            }
            tv_best_content.setText(str1);

        }else if(type==2){
            //테이블
            tv_best_statistics.setText("Best 테이블");
            tv_worst_statistics.setText("Worst 테이블");
            Map<String,Integer> hm = new HashMap<String, Integer>();
            List<Order> omList = od.allOrderInfo();
            for(int i=0;i<omList.size();i++){
                if(hm.get(String.valueOf(omList.get(i).getTable()))==null){
                    hm.put(String.valueOf(omList.get(i).getTable()),1);
                }else{
                    hm.put(String.valueOf(omList.get(i).getTable()),hm.get(String.valueOf(omList.get(i).getTable()))+1);
                }
            }
            List<String> tableList = new ArrayList<>();
            hm= sortByValue(hm);
            for (Map.Entry<String,Integer> entry : hm.entrySet()) {
                //정렬한 리스트에서 순번을 배열번호로 변경하여 원본 리스트에서 추출
                Log.i(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
                tableList.add(entry.getKey());
            }
            String str="";
            int j=1;
            for(int i=0;i<tableList.size();i++){
                if(j<4){
                    str+=String.valueOf(j)+". "+tableList.get(i)+"번 테이블"+"\n";
                }
                j++;
            }
            tv_worst_content.setText(str);
            String str1="";
            int t=1;
            for(int i=tableList.size();i>0;i--){
                if(t<4){
                    str1+=String.valueOf(t)+". "+tableList.get(i-1)+"번 테이블"+"\n";
                }
                t++;
            }
            tv_best_content.setText(str1);


        }else if(type==3){
            //요일
            tv_best_statistics.setText("Best 요일");
            tv_worst_statistics.setText("Worst 요일");

        }else if(type==4){
            //시간
            tv_best_statistics.setText("Best 시간");
            tv_worst_statistics.setText("Worst 시간");

            Map<String,Integer> hm = new HashMap<String, Integer>();
            List<Order> omList = od.allOrderInfo();
            for(int i=0;i<omList.size();i++){
                if(hm.get(String.valueOf(omList.get(i).getTable()))==null){
                    hm.put(String.valueOf(omList.get(i).getTable()),1);
                }else{
                    hm.put(String.valueOf(omList.get(i).getTable()),hm.get(String.valueOf(omList.get(i).getTable()))+1);
                }
            }
            List<String> tableList = new ArrayList<>();
            hm= sortByValue(hm);
            for (Map.Entry<String,Integer> entry : hm.entrySet()) {
                //정렬한 리스트에서 순번을 배열번호로 변경하여 원본 리스트에서 추출
                Log.i(String.valueOf(entry.getKey()),String.valueOf(entry.getValue()));
                tableList.add(entry.getKey());
            }
            String str="";
            int j=1;
            for(int i=0;i<tableList.size();i++){
                if(j<4){
                    str+=String.valueOf(j)+". "+tableList.get(i)+"번 테이블"+"\n";
                }
                j++;
            }
            tv_worst_content.setText(str);
            String str1="";
            int t=1;
            for(int i=tableList.size();i>0;i--){
                if(t<4){
                    str1+=String.valueOf(t)+". "+tableList.get(i-1)+"번 테이블"+"\n";
                }
                t++;
            }
            tv_best_content.setText(str1);

        }
    }
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value : " + entry.getValue());
        }
    }
}
