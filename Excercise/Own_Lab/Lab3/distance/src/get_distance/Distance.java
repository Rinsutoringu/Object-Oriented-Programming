package get_distance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A class to check distance from every two city
 */
public class Distance{
    HashMap<String, Integer> compare_dic;
    ArrayList<String> city_list;

    // create a dictionary to save each two city's distance.
    public Distance(){
        this.compare_dic = new HashMap<String, Integer>();
        this.city_list = new ArrayList<String>();
    };


    /**
     * get user input city name, since get "###" will stop.
     */
    public void get_cityname(){
        String a = null;
        Scanner get_city = new Scanner(System.in);
        try
        {
            // 直到检查到关键词或啥都没读着的时候跳出死循环
            // 把读取到的正确数据加入city_list里面
            while (true) 
            {
                a = get_city.nextLine();
                if (a.equals("###")|| a == null) break;
                this.city_list.add(a);
            }
        }
        catch(Exception e){System.err.println("something error\n");}
        finally{get_city = null;}
    };

    /**
     * put together two cityname and distance
     */
    public void join_citylist(){

        Scanner get_distance = new Scanner(System.in);
        int a = city_list.size();
        
        // 有x个城市，就会有x^2条路径
        // 创建循环 循环次数为获取城市的数量（
        
        for (int n = 0; n < a; n++) {
            for (int m = 0; m <a; m++) {
                this.compare_dic.put((city_list.get(n)).concat(city_list.get(m)), get_distance.nextInt());
                }
            }
    };

    /**
     * 
     * @return distance from input city name.
     */
    public int seek_distance(){
        Scanner get_seek = new Scanner(System.in);
        int get_distance = -1;
        try {
            get_distance = compare_dic.get(get_seek.nextLine().concat(get_seek.nextLine())); 
        } catch (Exception e) {
            System.out.println("Cannot find this path.\n");
        }
        return get_distance;
    };

    public static void main(String[] args) {
        // 实例化
        Distance distance = new Distance();
        distance.get_cityname();
        distance.join_citylist();
        int a = distance.seek_distance();
        if (a == -1) {
            System.out.println("Path cannot find!\n");
            return;
        }
        System.out.printf("find out a path %d.", a);

    }

}