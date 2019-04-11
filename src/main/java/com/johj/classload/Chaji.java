package com.johj.classload;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author:wenwei
 * @date:2019/03/29
 * @description:
 */
public class Chaji {

    private void  main(){
        Map<String,String> map = Maps.newHashMap();
        Map map1 = Maps.newHashMap();
        Map x = map.entrySet().stream().filter(ents->"test".equals(ents.getValue())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));

    }

    private void test(){

    }
}
