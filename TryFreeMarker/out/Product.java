package me.liheng.project;

public Product implements Serializable {

 /** Serial Version UID. */
 private static final long serialVersionUID = 1L;

 private int id;
 private String name;
 private double price;


 public int getId() {
     return this.id;
 }
 public void setId(int id) {
     this.id = id;
 }
 public String getName() {
     return this.name;
 }
 public void setName(String name) {
     this.name = name;
 }
 public double getPrice() {
     return this.price;
 }
 public void setPrice(double price) {
     this.price = price;
 }

}