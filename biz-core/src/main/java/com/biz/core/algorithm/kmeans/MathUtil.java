package com.biz.core.algorithm.kmeans;

import java.util.List;

/**
 * 计算工具类
 *
 * @author 江南
 * @date 2017年01月11日
 * @reviewer
 * @see
 */
public class MathUtil {
    /**
     * 求给定双精度数组中值的最大值
     *
     * @param inputData 输入数据数组
     * @return 运算结果, 如果输入值不合法，返回为-1
     */
    public static Double getMax(Double[] inputData) {
        if (inputData == null || inputData.length == 0)
            return (double) -1;
        Double max = inputData[0];
        for (Double anInputData : inputData) {
            if (max < anInputData)
                max = anInputData;
        }
        return max;
    }

    /**
     * 求求给定双精度数组中值的最小值
     *
     * @param inputData 输入数据数组
     * @return 运算结果, 如果输入值不合法，返回为-1
     */
    public static Double getMin(Double[] inputData) {
        if (inputData == null || inputData.length == 0)
            return (double) -1;
        Double min = inputData[0];
        for (Double anInputData : inputData) {
            if (min > anInputData)
                min = anInputData;
        }
        return min;
    }

    /**
     * 求给定双精度数组中值的和
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public static Double getSum(Double[] inputData) {
        if (inputData == null || inputData.length == 0)
            return (double) -1;
        Double sum = 0d;
        for (Double anInputData : inputData) {
            sum = sum + anInputData;
        }
        return sum;

    }

    /**
     * 求给定双精度数组中值的数目
     *
     * @return 运算结果
     */
    public static int getCount(Double[] inputData) {
        return inputData == null ? -1 : inputData.length;
    }

    /**
     * 求给定双精度数组中值的平均值
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public static Double getAverage(Double[] inputData) {
        return (inputData == null || inputData.length == 0) ? (double) -1 : getSum(inputData) / inputData.length;
    }

    /**
     * 求给定双精度数组中值的平方和
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public static Double getSquareSum(Double[] inputData) {
        if (inputData == null || inputData.length == 0)
            return (double) -1;
        Double sqrNum = 0.0;
        for (Double anInputData : inputData) {
            sqrNum = sqrNum + anInputData * anInputData;
        }
        return sqrNum;
    }

    /**
     * 求给定双精度数组中值的方差
     *
     * @param inputData 输入数据数组
     * @return 运算结果
     */
    public static Double getVariance(Double[] inputData) {
        return (getSquareSum(inputData) - getCount(inputData) * getAverage(inputData) * getAverage(inputData)) / getCount(inputData);
    }

    /**
     * 求给定双精度数组中值的标准差
     *
     * @return 运算结果
     */
    public static Double getStandardDiviation(List<Double> inputList) {
        Double[] inputData = new Double[inputList.size()];
        for (int i = 0; i < inputList.size(); i++) {
            inputData[i] = inputList.get(i);
        }
        //绝对值化很重要
        return Math.sqrt(Math.abs(getVariance(inputData)));
    }
}
