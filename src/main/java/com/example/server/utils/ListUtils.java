package com.example.server.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author
 * @Description 数组相关工具类
 * @date 2020/10/16 12:53
 */
public class ListUtils {
    private ListUtils() {
    }

    /**
     * 将一个大数组分割成多个数组
     *
     * @param list     原数组
     * @param splitNum 每个子数组的长度
     * @return list<list>
     */
    public static <T> List<List<T>> getSplitList(List<T> list, int splitNum) {
        List<List<T>> splitList = new LinkedList<>();
        int groupFlag = list.size() % splitNum == 0 ? (list.size() / splitNum) : (list.size() / splitNum + 1);
        for (int j = 1; j <= groupFlag; j++) {
            if ((j * splitNum) <= list.size()) {
                splitList.add(list.subList(j * splitNum - splitNum, j * splitNum));
            } else if ((j * splitNum) > list.size()) {
                splitList.add(list.subList(j * splitNum - splitNum, list.size()));
            }
        }
        splitList.remove(null);
        return splitList;
    }
}
