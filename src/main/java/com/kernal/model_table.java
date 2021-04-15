package com.kernal;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
public class model_table {
    public model_table(){
        System.out.println("建立模型");
    }
    public void model_main(){
       //0 離開
       //1 新增
       //2 排序
       //3 顯示當前的表單
        Scanner input=new Scanner(System.in);
        String command=input.nextLine();
        if(isVaildinput(command)) {
            switch (command) {
                default:
                    System.out.println("accept");
                    break;
            }
        }
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
