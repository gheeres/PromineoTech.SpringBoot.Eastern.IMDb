package com.promineotech.imdb.models;

public class TitleModel {
  private String id;
  private String name;
  private int releasedYear;
  
  public TitleModel(String id, String name, int releasedYear) {
    setId(id);
    setName(name);
    setReleasedYear(releasedYear);
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getReleasedYear() {
    return releasedYear;
  }
  public void setReleasedYear(int releasedYear) {
    this.releasedYear = releasedYear;
  }
}
