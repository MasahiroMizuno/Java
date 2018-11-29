import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DeepCopySample{

     public static void main(String []args){
        // List<Map<String, Object> testList = new ArrayList<>();
        // testList.addAll(元からあったリスト);
        int i;
        int j;
        
        Map<String, Object> map1 = new HashMap<String,Object>();
        Map<String, Object> map2 = new HashMap<String,Object>();
        
        map1.put("1key1","1value1");
        map1.put("1key2","1value2");
        
        map2.put("2key1","2value1");
        
        
        List<Map> testList = new ArrayList<>();
        List<Map> toList = new ArrayList<>();
        
        testList.add(map1);
        testList.add(map2);
        
        System.out.println("コピー元");
        for(Map mp : testList){
          System.out.println(mp.get("1key1"));
         }
         System.out.println();
         
         //ダメなパターン ディープコピー失敗
         toList.addAll(testList);
         
         //よろしいパターン　ディープコピー成功
         /*
         for(Map mp : testList){
             toList.add(new HashMap(mp));
         }
         */
        System.out.println("コピー先");
         for(Map mp : toList){
          System.out.println(mp.get("1key1"));
         }    
         System.out.println();
         
         
         testList.get(0).put("1key1","updated");
         
        System.out.println("コピー元　変更後");
        for(Map mp : testList){
          System.out.println(mp.get("1key1"));
         }        
         
         System.out.println();
         System.out.println("コピー先変更後");
         for(Map mp : toList){
          System.out.println(mp.get("1key1"));
         }     

     }
}
