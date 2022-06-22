/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panh.dto;

/**
 *
 * @author Panh
 */
public class Plant {
    private int id;
    private String name;
    private int price;
    private String imgPath;
    private String description;
    private int status;
    private int cateId;
    private String cateName;

    public Plant(int id, String name, int price, String imgPath, String description, int status, int cateId, String cateName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.description = description;
        this.status = status;
        this.cateId = cateId;
        this.cateName = cateName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return "Plant{" + "id=" + id + ", name=" + name + ", price=" + price + ", imgPath=" + imgPath + ", description=" + description + ", status=" + status + ", cateId=" + cateId + ", cateName=" + cateName + '}';
    }
    
    
}
