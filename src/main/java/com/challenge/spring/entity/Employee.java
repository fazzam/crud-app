package com.challenge.spring.entity;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class Employee {

  private Integer id;
  private String name;
  private String number;
  private Integer limitReimburs;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date startingDate;
  private Date createdDate;
  private Date updatedDate;

  public Employee() {

  }

  public Employee(String name, String number, Date startingDate, Integer limitReimburs, Date createdDate, Date updatedDateDate) {
    this.name = name;
    this.number = number;
    this.startingDate = startingDate;
    this.limitReimburs = limitReimburs;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Date getStartingDate() {
    return startingDate;
  }

  public void setStartingDate(Date startingDate) {
    this.startingDate = startingDate;
  }

  public Integer getLimitReimburs() {
    return limitReimburs;
  }

  public void setLimitReimburs(Integer limitReimburs) {
    this.limitReimburs = limitReimburs;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", number=" + number + ", startingDate=" + startingDate
        + ", limitReimburs=" + limitReimburs + ", cretaedDate=" + createdDate +
            ", updatedDate" + updatedDate + "]";
  }

}
