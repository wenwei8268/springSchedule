package com.johj;


import com.johj.java9.Wenwei;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * author:zhou_wenwei
 * mail:
 * date:2017/12/4
 * description:
 */
@RunWith(SpringRunner.class)
public class TestString {
    @Test
    public void testString(){
        Integer i1= new Integer(100);
        Integer i2 = new Integer(100);
        System.out.println(i1.equals(i2)+"########");

        String s = new String("abc中国");
        s.hashCode();
        System.out.println(s.hashCode());
        String t = new String("abc");

        ArrayList<Wenwei> arrayList = new ArrayList<>();
        ArrayList<Wenwei> arrayList1 = new ArrayList<>();
        Wenwei wenwei = new Wenwei();

        arrayList.add(wenwei);


        System.out.println(s.equals(t)+"***********");
    }
}
