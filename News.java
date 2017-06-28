package model.bean;

import java.sql.Timestamp;

public class News {
private int id;
private String name;
private String preview;
private String detail;
private Timestamp date;
private int idcat;
private String picture;
private String catname;
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
public String getPreview() {
	return preview;
}
public void setPreview(String preview) {
	this.preview = preview;
}
public String getDetail() {
	return detail;
}
public void setDetail(String detail) {
	this.detail = detail;
}
public Timestamp getDate() {
	return date;
}
public void setDate(Timestamp date) {
	this.date = date;
}
public int getIdcat() {
	return idcat;
}
public void setIdcat(int idcat) {
	this.idcat = idcat;
}
public String getPicture() {
	return picture;
}
public void setPicture(String picture) {
	this.picture = picture;
}
public String getCatname() {
	return catname;
}
public void setCatname(String catname) {
	this.catname = catname;
}
public News(int id, String name, String preview, String detail, Timestamp date,
		int idcat, String picture, String catname) {
	super();
	this.id = id;
	this.name = name;
	this.preview = preview;
	this.detail = detail;
	this.date = date;
	this.idcat = idcat;
	this.picture = picture;
	this.catname = catname;
}
public News() {
	super();
}

}
