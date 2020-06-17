package com.example.myapplication.beans;

public class Cuns {
 private String title;
 private String content;
 private String times;
 private int ids;
 public Cuns(String ti,int id,String con ,String time){
	 this.ids=id;
	 this.title=ti;
	 this.content=con;
	 this.times=time;
 }
 public Cuns(String ti,String con,String time){
	 this.title=ti;
	 this.content=con;
	 this.times=time;
 }
 public Cuns(int i,String ti,String time){
	 this.ids=i;
	 this.title=ti;
	 this.times=time;
 }
 public Cuns(String ti,String con){
	 this.title=ti;
	 this.content=con;
 }
public int getIds() {
	return ids;
}
public String getTitle() {
	return title;
}
public String getContent() {
	return content;
}
public String getTimes() {
	return times;
}
 
}
