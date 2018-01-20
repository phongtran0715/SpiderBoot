/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.vuploaed;

/**
 *
 * @author hanht
 */
public class VUploadedBo {
    private String link;
    private String path;
    private boolean status;
    
    public VUploadedBo() {
    }
    
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public boolean getStatus()  {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "VideoDownloadedBo{" + "link=" + link + ", path=" + path + ", status=" + status + '}';
    }
}
