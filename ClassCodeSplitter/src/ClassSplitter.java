import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;


class ClassSplitter{
    
    private static final String output_dir_str = "../output";
    private static final String input_dir_str = "../input" ;
    private static final String pattern_format = "^\\s*class\\s+\\w+\\s*\\{?\\s*$";
    private static final Pattern pattern = Pattern.compile(pattern_format);
    
   
    public static void main(String[] args){
        File[] input_file = getInputFiles();
        dirGenerator();
        formatOutputFiles(input_file);
    }
    
    private static File[] getInputFiles(){
        File[] files = (new File("../input")).listFiles();
        return files;
    }
    
    private static void dirGenerator(){
        File output_dir = new File(output_dir_str);
        
        if(!output_dir.exists()){
            (new File(output_dir_str)).mkdir();
        }
    }
 
    private static void formatOutputFiles(File[] input_files){
        for(File input_file : input_files){
            BufferedReader br = null;
            ArrayList<String> text_contents = new ArrayList<String>();
            try{
                br = new BufferedReader(new FileReader(input_file));
                String line;
                int head_line = -1;
                int last_line = -1;
                int counter = 0;
                
                while((line = br.readLine()) != null){
                    text_contents.add(line);
                    
                    if(pattern.matcher(line).find() && head_line != -1 ){
                      last_line = counter - 1;
                      generateJavaFile(input_file,text_contents,head_line,last_line);
                      
                      //Reset the head_line and last_line to enter the next loop.
                      head_line = -1;
                      last_line = -1;
                    } 
                    if(pattern.matcher(line).find() && head_line == -1 ){
                      head_line = counter;
                    }
                    counter++;
                }
                
                generateJavaFile(input_file,text_contents,head_line,-1);
                
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                try{
                    br.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }    
    
    private static void generateJavaFile(File input_file,ArrayList<String> text,int head_line, int last_line){
        
        if(head_line == -1 && last_line == -1){
            System.out.println("No class statement found. "+input_file);
        }else{
            int begin_index = text.get(head_line).indexOf("class") + 6;
            int end_index = text.get(head_line).indexOf("{");
            
            // When the class statement is at the end of the file pattern.
            if(end_index < 0 ) end_index = text.get(head_line).length();
            
            String output_file_name = text.get(head_line).substring(begin_index,end_index);
            
            File output_file = new File(output_dir_str + "/" + output_file_name +".java");
            
            PrintWriter print_writer = null;
            try{
                if(!output_file.createNewFile()){
                    System.out.println("Could NOT create file. The file might already exists.: "+output_file.getName());
                }
            
                else if(last_line == -1){
                    print_writer = new PrintWriter(new BufferedWriter(new FileWriter(output_file)));
                
                for(int i = head_line; i < text.size(); i++){
                    print_writer.println(text.get(i));
                }
             
                }else{
                    print_writer = new PrintWriter(new BufferedWriter(new FileWriter(output_file)));
                    for(int i = head_line; i <= last_line; i++){
                        print_writer.println(text.get(i));
                    }
                }
            }catch(IOException e){
                e.printStackTrace();
            }finally{
                print_writer.close();
            }
        }
    }
}
