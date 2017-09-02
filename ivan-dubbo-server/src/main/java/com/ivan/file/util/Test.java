package com.ivan.file.util;

/**
 * Created by Administrator on 2017/9/2 0002.
 */
public class Test {
    public static void main(String[] args) {
        String regex = "^[A-Za-zd]+([-_.][A-Za-zd]+)*@([A-Za-zd]+[-.])+[A-Za-zd]{2,5}$";
        String[] str1 = {"_aaa@qq.com","aaa@","aa.b@qq.com","1123@163.com","113fe$@11.com","han. @sohu.com.cn","han.c@sohu.com.cn.cm.cm"};
        for (String str:str1){
            System.out.println(str+" \\\\. "+str.matches(regex));
        }
    }
}
