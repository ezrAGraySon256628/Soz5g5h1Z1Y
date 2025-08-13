// 代码生成时间: 2025-08-13 20:21:25
package com.example

// 导入必要的Groovy和Grails类
import grails.transaction.Transactional

// 定义一个服务类，用于实现排序算法
@Transactional
class SortingService {

    // 排序算法 - 冒泡排序
    public List sortWithBubbleSort(List list) {
        // 检查列表是否为空
        if (list == null) {
            throw new IllegalArgumentException('The list cannot be null.')
        }

        // 遍历每个元素，与后面的元素比较并交换位置
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    // 交换元素位置
                    Collections.swap(list, j, j + 1)
                }
            }
        }

        // 返回排序后的列表
        return list
    }

    // 排序算法 - 快速排序
    public List sortWithQuickSort(List list) {
        // 检查列表是否为空
        if (list == null) {
            throw new IllegalArgumentException('The list cannot be null.')
        }

        if (list.size() < 2) {
            return list
        }

        // 选择一个基准元素
        def pivot = list.first()

        // 将列表分为两部分：小于基准的和大于基准的
        def lessThanPivot = list.findAll { it <= pivot }
        def greaterThanPivot = list.findAll { it > pivot }

        // 递归排序两部分并合并
        return sortWithQuickSort(lessThanPivot) + [pivot] + sortWithQuickSort(greaterThanPivot)
    }

    // 排序算法 - 插入排序
    public List sortWithInsertionSort(List list) {
        // 检查列表是否为空
        if (list == null) {
            throw new IllegalArgumentException('The list cannot be null.')
        }

        // 遍历每个元素，插入到已排序部分的正确位置
        for (int i = 1; i < list.size(); i++) {
            def key = list[i]
            int j = i - 1

            // 将比key大的元素向右移动一个位置
            while (j >= 0 && list[j] > key) {
                list[j + 1] = list[j]
                j--
            }

            // 插入key到正确位置
            list[j + 1] = key
        }

        // 返回排序后的列表
        return list
    }

    // 排序算法 - 归并排序
    public List sortWithMergeSort(List list) {
        // 检查列表是否为空
        if (list == null) {
            throw new IllegalArgumentException('The list cannot be null.')
        }

        if (list.size() < 2) {
            return list
        }

        // 分割列表
        def mid = list.size() / 2
        def left = list.subList(0, mid)
        def right = list.subList(mid, list.size())

        // 递归排序两部分并合并
        return merge(sortWithMergeSort(left), sortWithMergeSort(right))
    }

    // 合并两个已排序的列表
    private List merge(List left, List right) {
        List result = []

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.first() <= right.first()) {
                result.add(left.remove(0))
            } else {
                result.add(right.remove(0))
            }
        }

        // 添加剩余的元素
        result.addAll(left)
        result.addAll(right)

        return result
    }
}
