/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.ulsysdao;

/**
 *
 * @author hanht
 */
public class SystemBo {
    private Integer id;
    private Integer numberLDown;
    private Integer numberLUp;
    private Integer numberThread;
    private Integer numberCore;
    private String pathDown;
    private String pathUp;
    private Integer numberRetry;
    private Integer numberTimeout;
    private String pathTemp;
    private String videoQuality;
    private String videoType;
    
    public SystemBo() {
    }
    
    public Integer getID() {
        return id;
    }
    public void setID(Integer id) {
        this.id = id;
    }
    public Integer getNumberLDown() {
        return numberLDown;
    }
    public void setNumberLDown(Integer noLDown) {
        this.numberLDown = noLDown;
    }
    public Integer getNumberLUp()  {
        return numberLUp;
    }
    public void setNumberLUp(Integer noLUp) {
        this.numberLUp = noLUp;
    }
    public Integer getNumberThread() {
        return numberThread;
    }
    public void setNumberThread(Integer numberThread) {
        this.numberThread = numberThread;
    }
    public Integer getNumberCore() {
        return numberCore;
    }
    public void setNumberCore(Integer numberCore) {
        this.numberCore = numberCore;
    }
    public String getPathDown() {
        return pathDown;
    }
    public void setPathDown(String pathDown) {
        this.pathDown = pathDown;
    }
    public String getPathUp() {
        return pathUp;
    }
    public void setPathUp(String pathUp) {
        this.pathUp = pathUp;
    }
    public Integer getNumberRetry() {
        return numberRetry;
    }
    public void setNumberRetry(Integer numberRetry) {
        this.numberRetry = numberRetry;
    }
    public Integer getTimeout() {
        return numberTimeout;
    }
    public void setTimeout(Integer timeout) {
        this.numberTimeout = timeout;
    }
    public String getPathTemp() {
        return pathTemp;
    }
    public void setPathTemp(String pathTemp) {
        this.pathTemp = pathTemp;
    }
    public String getVideoQuality() {
        return videoQuality;
    }
    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }
    public String getVideoType() {
        return videoType;
    }
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
    @Override
    public String toString() {
        return "SystemBo{" + "id=" + id + "|Number Link Up=" + numberLUp + "|Number Link Down=" + numberLDown +"|Thread=" + numberThread + "|Core="+numberCore+"|pathDown=" + pathDown+ "|pathUp="+pathUp+"|Rety="+numberRetry+"|Timeout="+numberRetry+"|Video Quality="+videoQuality+"|Video Type="+videoType+ '}';
    }
}
