package com.kernal;
import org.apache.commons.lang3.StringUtils;

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
    }

    Scanner input=new Scanner(System.in);
    ArrayList<Object> selections=new ArrayList<>();


    public void model_main(){
       //0 離開
       //1 新增
       //2 排序
       //3 顯示當前的表單
        // 4清空整個表單
        // 5清空單一選項

//        Scanner input=new Scanner(System.in);
        System.out.println("0 離開\n1 新增\n2 排序\n3 顯示當前的表單\n4 清除整個表單");
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
                    selections.clear();
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
    private void show_mod(ArrayList<Object> item_List){
        if(item_List.size()==0){
            System.out.println("清單項目為空");
            return;
        }
        for(Object element:item_List){
            selection temp_element=(selection)element;
            System.out.println((temp_element).string_index+","+(temp_element).value);
        }
    }
    public int get_index(){//取得物件修改編號
        return index;
    }
    public boolean isVaildinput(String input){
        if((input).length()>2){
            System.out.println("不認得的指令");
            return false;
        }else if(!StringUtils.isNumeric((input))){
            System.out.println("不是數字");
            return false;
        }
        return true;
    }
}
