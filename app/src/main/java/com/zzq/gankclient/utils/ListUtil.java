package com.zzq.gankclient.utils;

import java.util.List;

public class ListUtil {

    private ListUtil(){

    }

    /**
     * 判断List集合是否为空
     * @return 返回true为null
     */
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }
}
