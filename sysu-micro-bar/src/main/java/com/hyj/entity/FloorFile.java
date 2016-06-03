package com.hyj.entity;

public class FloorFile {
    private Integer id;

    private Integer floorId;

    private String fileUrl;

    private Integer fileType;

    public FloorFile(Integer floorId, String fileUrl, Integer fileType) {
        this.floorId = floorId;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}