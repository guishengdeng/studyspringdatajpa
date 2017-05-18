package com.biz.core.algorithm.kmeans;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KMeans implements Serializable {

    private static final long serialVersionUID = 4788381142024867112L;

    private Logger logger = LoggerFactory.getLogger(KMeans.class);

    private static final Integer DEFAULT_CLUSTER_NUM = 5;

    private static final Integer MAX_ITERATE_NUM = 20;


    private List<Double> pointList;

    private List<Double> oldClusterCenter;

    private List<Double> newClusterCenter;

    private List<Cluster> clusterList;

    private int numCluster;

    private int iterateNum = 0;

    /**
     * 差分值,一个cluster至少需要拥有的点数
     */
    private int maxDiscrete = 3;

    /**
     * 误差范围  用于设置中心点偏离的最大误差
     * 优化此选项可以有效减少迭代次数
     */
    private double errorRange = 1000.0;

    /**
     * 初始化中心点
     */
    private void randomClusterCenter() {
        Double max = Collections.max(pointList);
        Double min = Collections.min(pointList);
        double differ = Math.abs(max - min);
        double step = differ / (numCluster + 1);
        for (int i = 1; i <= numCluster; i++) {
            newClusterCenter.add(min + step * i);
        }
        //        logger.debug("等间隔初始化中心点[{}]",newClusterCenter.toString());
    }


    public KMeans(List<Double> pointList) {
        this(pointList, DEFAULT_CLUSTER_NUM);
    }

    /**
     * 初始化数据
     */
    public KMeans(List<Double> pointList, int numCluster) {
        if (pointList.size() - 20 > 500) {
            Collections.sort(pointList);
            this.pointList = pointList.subList(0, pointList.size() - 20);
        } else {
            this.pointList = pointList;
        }
        this.numCluster = numCluster;
        this.newClusterCenter = Lists.newArrayList();
        this.oldClusterCenter = Lists.newArrayList();
        //        logger.debug("START K MEANS");
        //        logger.debug("待聚类点为[{}]", pointList.toString());
        //        logger.debug("聚类值为[{}]",numCluster);
        //初始化随机点
        this.randomClusterCenter();
        //初始化Cluster
        initCluster();
        //迭代计算
        cluster();
    }

    /**
     * 初始化Cluster
     */
    private void initCluster() {
        clusterList = Lists.newArrayList();
        int i = 0;
        for (Double center : newClusterCenter) {
            clusterList.add(new Cluster(i, center, Lists.<Double>newArrayList()));
        }
    }

    private void cluster() {
        iterateNum++;
        //遍历priceList 找出与Cluster center距离最小的点,加入到对应cluster中
        for (Double point : pointList) {
            List<Double> diffList = Lists.newArrayList();
            for (Double center : newClusterCenter) {
                double diff = Math.abs(point - center);
                diffList.add(diff);
            }
            Double min = Collections.min(diffList);
            int clusterCenterIndex = diffList.indexOf(min);
            Double clusterCenter = newClusterCenter.get(clusterCenterIndex);
            addPoint2Cluster(point, clusterCenter);
        }
        //计算新的ClusterCenter
        computeClusterCenter();
        //判断是否需要继续迭代
        if (!finish()) {
            //清空cluster中的mermber
            resetClusterMember();
            cluster();
        }
    }


    private boolean finish() {

        if (iterateNum > MAX_ITERATE_NUM) {
            logger.debug("迭代次数大于[{}],终止迭代", iterateNum);
            return true;
        }

        for (int i = 0; i < numCluster; i++) {
            double abs = Math.abs(newClusterCenter.get(i) - oldClusterCenter.get(i));
            if (abs > errorRange) {
                return false;
            }
        }
        logger.debug("中心点误差小于[{}],终止迭代", errorRange);
        return true;
    }


    /**
     * 清空Cluster中的members记录
     */
    private void resetClusterMember() {
        for (Cluster cluster : clusterList) {
            cluster.getMembers().clear();
        }
    }

    /**
     * 计算新的ClusterCenter
     */
    private void computeClusterCenter() {
        oldClusterCenter.clear();
        oldClusterCenter.addAll(newClusterCenter);
        newClusterCenter.clear();
        List<Double> fixCenters = Lists.newArrayList();
        List<Integer> memberSize = Lists.newArrayList();
        for (Cluster cluster : clusterList) {
            memberSize.add(cluster.getMembers().size());
            if (cluster.getMembers().size() <= maxDiscrete) {
                fixCenters.add(cluster.getCenter());
            } else {
                cluster.resetClusterCenter();
            }
            newClusterCenter.add(cluster.getCenter());
        }

        Integer maxSize = Collections.max(memberSize);
        Cluster maxSizeCluster = clusterList.get(memberSize.indexOf(maxSize));
        List<Double> maxSizeMembers = maxSizeCluster.getMembers();
        Double maxPoint = Collections.max(maxSizeMembers);
        Double minPoint = Collections.min(maxSizeMembers);
        double maxSizeCenter = maxSizeCluster.getCenter();


        Double tempCenter;
        if (fixCenters.size() != 0) {
            for (Cluster cluster : clusterList) {
                if (cluster.getCenter() == fixCenters.get(0)) {
                    double midPoint = (maxPoint - minPoint) / 2;
                    cluster.setCenter(midPoint);
                    newClusterCenter.remove(fixCenters.get(0));
                    newClusterCenter.add(midPoint);
                    break;
                }
            }
        }

        //todo:待优化
        Collections.sort(clusterList);
    }

    public void logShowClusterDistribute() {
        for (Cluster cluster : clusterList) {
            List<Double> member = cluster.getMembers();
            double center = cluster.getCenter();
            int size = member.size();
            if (size > 2) {
                Double max = Collections.max(member);
                Double min = Collections.min(member);
                Double average = (max - min) / size;
                double standardDiviation = MathUtil.getStandardDiviation(member);
                logger.debug("[cluster center:{},size:{},{}~{},average:{},标准差:{}]", center, size, min, max, average, standardDiviation);
            } else {
                logger.debug("[cluster center:{},size:{}]", cluster.getCenter(), size);
            }
        }
    }

    public int getIterateNum() {
        return iterateNum;
    }

    public List<Cluster> getClusterList() {
        return clusterList;
    }

    public List<KMeansRangeVo> getRanges(String titleTemplate) {
        List<Integer> processPrice = Lists.newArrayList();
        processPrice.add(0);
        List<Double> clusterInterval = Lists.newArrayList();

        for (Cluster cluster : clusterList) {
            List<Double> members = cluster.getMembers();
            if (members.size() == 0) {
                continue;
            }
            Double max = Collections.max(members);
            Double min = Collections.min(members);
            clusterInterval.add(min / 100);
            clusterInterval.add(max / 100);
        }
        for (int i = 1; i + 1 < clusterInterval.size(); i = i + 2) {
            //边界平均值
            double v = (clusterInterval.get(i) + clusterInterval.get(i + 1)) / 2;
            //边界的数量级
            int magnitude = String.valueOf((int) v).length() - 1;
            if (magnitude > 2) {
                magnitude = 2;
            }
            //10的数量级次方
            int pow = (int) Math.pow(10, magnitude);
            //边界平均值对数量级四舍五入取整
            int v2 = (int) (Math.round(v / pow) * pow);
            processPrice.add(v2);
        }
        processPrice.add(Integer.MAX_VALUE);
        List<KMeansRangeVo> priceRangeVos = Lists.newArrayList();
        for (int i = 0; i < processPrice.size() - 1; i++) {
            if (processPrice.get(i) >= processPrice.get(i + 1) - 1) {
                continue;
            }
            KMeansRangeVo priceRangeVo = new KMeansRangeVo(processPrice.get(i), processPrice.get(i + 1) - 1, titleTemplate);
            priceRangeVos.add(priceRangeVo);
        }
        return priceRangeVos;
    }

    private void addPoint2Cluster(Double point, Double clusterCenter) {
        for (Cluster cluster : clusterList) {
            if (cluster.getCenter() == clusterCenter) {
                cluster.addPoint(point);
                break;
            }
        }
    }
}