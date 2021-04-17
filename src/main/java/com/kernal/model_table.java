package com.kernal;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
public class model_table {

    public model_table(){
        System.out.println("建立模型");
    }

    private model_table get_model_table_Instance(){
        return new model_table();
    }

    class selection {
        int  index;
        String string_index;
        String value;
        public selection(int index,String value){
            this.string_index= String.format("%02d",index);
            this.value= value;
        }
        public selection(int  index){
            this.index= index;
        }

        public void update_index(int index){
            this.string_index=String.format("%02d",index);
            this.index = index;
        }
        public String get_value(){
            return this.value;
        }
        @Override
        public int hashCode(){
            this.index=Integer.valueOf(string_index);
            return this.index;
        }
    }

    Scanner input=new Scanner(System.in);
    ArrayList<Object> selections=new ArrayList<>();


    public void model_main()throws  Exception{
       //0 離開
       //1 新增
       //2 排序
       //3 顯示當前的表單
        // 4清空整個表單
        // 5清空單一選項

//        Scanner input=new Scanner(System.in);

        System.out.println("0 離開\n1 新增\n2 排序\n3 顯示當前的表單\n4 清除整個表單\n5 刪除單一選項 \n6 抽出三項");

        String command=input.nextLine();
        if(isVaildinput(command)) {
            switch (command) {
                case 0+"":
                    System.out.println("離開");
                    System.exit(1);
                    break;
                case 1+"":
                    System.out.println("新增模式");
                    add_mod();
                    break;
                case 3+"":
                    System.out.println("顯示模式");
                    show_mod(selections);
                    break;
                case 4+"":
                    System.out.println("清空整個表單");
                    selections.clear();
                    break;
                case 5+"":
                    System.out.println("消除單一選項");
                    remove_one_selection(selections);
                    break;
                case 6+"":
                    System.out.println("抽出三項");
//                    go_To_select_result(selections);
                    File f=new File("E:\\"+get_c_Day()+".txt");
                    if(!f.exists())
                        show_result(go_To_select_result(selections));
                    else
                        System.out.println("\t今日目標已經生成，好好去做吧");
                    sign_this_day();
                    break;
                default:
                    System.out.println("accept");
                    break;
            }
        }
    }

    private int index=0;

    private ArrayList<Object> add_mod(){
        index++;
        String context=input.nextLine();
        selection item=new selection(this.get_index(),context);
        selections.add(item);
        return selections;
    }
    private void remove_one_selection(ArrayList<Object> selections){
            Scanner deleted_input=new Scanner(System.in);
            int deleted_index=deleted_input.nextInt();
            boolean result=selections.removeIf(t->t.hashCode()==deleted_index);
            if(result){
                System.out.println("\t已經刪除");
                RE_tag_index(selections,deleted_index);
            }else {
                System.out.println("\t尚未發現項目");
            }
    }
    private void RE_tag_index(ArrayList<Object> selections,int deleted_index){
        //中間打斷的問題
        //1 2 3 4 5 -- remove 3 --> 1 2 4 5
        // 1 2
        for(int i=0;i<selections.size();i++){
           if(deleted_index>selections.get(i).hashCode())//前面全部略過
               continue;
            int update_code=selections.get(i).hashCode();
            ((selection)selections.get(i)).update_index(update_code-1);
        }

    }
    private final int max_select_times=3;
    private  ArrayList<String> go_To_select_result (ArrayList<Object> selections){
        ArrayList<String> result=new ArrayList<>();
        if(selections.size()<=3){//不足三項
            for(int select_times=0;select_times<selections.size();select_times++){
                selection sele= (selection) selections.get(select_times);
                result.add(sele.get_value());

            }
            return result;
        }
        Random rd=new Random();
        int[] hash_table=new int[selections.size()];
        for(int select_times=0;select_times<max_select_times;select_times++){
            int random_num=rd.nextInt(selections.size());
            while(hash_table[random_num]==1){
                random_num=rd.nextInt(selections.size());
            }
            String selection_value= ((selection)selections.get(random_num)).get_value();

            result.add(selection_value);
            hash_table[random_num]=1;
        }
        return result;
    }
    public String get_c_Day(){
        DateTime dt1 = new DateTime();

        String c_Day=(dt1+"").substring(0,10);
        c_Day.replace("-","");
        return  c_Day;
    }
    private void sign_this_day()throws IOException{
        try{
            String c_Day= get_c_Day();
            FileWriter fw=new FileWriter("E:\\"+c_Day+".TXT");
            fw.write("");
            fw.flush();
            fw.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private void show_result(ArrayList<String> result_list){
        System.out.println(result_list);
    }
    private void show_mod(ArrayList<Object> item_List){

        if(item_List.size()==0){
            System.out.println("\t清單項目為空");
            return;
        }

        for(Object element:item_List){
            selection temp_element=(selection)element;
            System.out.println("\t"+(temp_element).string_index+","+(temp_element).value);
        }
    }

    public int get_index(){//取得物件修改編號
        return index;
    }

    public boolean isVaildinput(String input){
        if((input).length()>2){
            System.out.println("\t不認得的指令");
            return false;
        }else if(!StringUtils.isNumeric((input))){
            System.out.println("\t不是數字");
            return false;
        }
        return true;
    }
}
