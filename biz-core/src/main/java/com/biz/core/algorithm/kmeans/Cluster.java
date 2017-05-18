package com.biz.core.algorithm.kmeans;

import java.util.List;

/**
 * KMeans 计算
 *
 * @author 江南
 * @date 2017年01月11日
 * @reviewer
 * @see
 */
public class Cluster implements Comparable<Cluster> {

    private int id;

    private double center;

    private List<Double> members;

    public Cluster(int id, double center, List<Double> members) {
        this.id = id;
        this.center = center;
        this.members = members;
    }

    public void addPoint(Double point) {
        members.add(point);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCenter() {
        return center;
    }

    public void setCenter(double center) {
        this.center = center;
    }

    public List<Double> getMembers() {
        return members;
    }

    public void setMembers(List<Double> members) {
        this.members = members;
    }

    public void resetClusterCenter() {
        if (members.size() != 0) {
            Double all = 0.0;
            for (Double member : members) {
                all += member;
            }
            this.center = all / members.size();
        }
    }

    public int compareTo(Cluster o) {
        if (this.getCenter() > o.getCenter()) {
            return 1;
        } else if (this.getCenter() < o.getCenter()) {
            return -1;
        } else {
            return 0;
        }
    }
}
