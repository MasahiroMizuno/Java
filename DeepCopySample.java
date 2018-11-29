import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DeepCopySample{

     public static void main(String []args){
        // List<Map<String, Object> testList = new ArrayList<>();
        // testList.addAll(�����炠�������X�g);
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
        
        System.out.println("�R�s�[��");
        for(Map mp : testList){
          System.out.println(mp.get("1key1"));
         }
         System.out.println();
         
         //�_���ȃp�^�[�� �f�B�[�v�R�s�[���s
         toList.addAll(testList);
         
         //��낵���p�^�[���@�f�B�[�v�R�s�[����
         /*
         for(Map mp : testList){
             toList.add(new HashMap(mp));
         }
         */
        System.out.println("�R�s�[��");
         for(Map mp : toList){
          System.out.println(mp.get("1key1"));
         }    
         System.out.println();
         
         
         testList.get(0).put("1key1","updated");
         
        System.out.println("�R�s�[���@�ύX��");
        for(Map mp : testList){
          System.out.println(mp.get("1key1"));
         }        
         
         System.out.println();
         System.out.println("�R�s�[��ύX��");
         for(Map mp : toList){
          System.out.println(mp.get("1key1"));
         }     

     }
}
