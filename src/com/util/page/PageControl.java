package com.util.page;

import java.util.List;


public interface PageControl {

   public int getMaxRowCount();
   public int getMaxPage();
   public int getCurPage();
    public int getRowsPerPage();
    public int getPosition();
    public String getNextPage();
    public String getBackPage();
    public String getFirstPage();
    public String getEndPage();

    public String getNextPage(String formName);
    public String getBackPage(String formName);
    public String getFirstPage(String formName);
    public String getEndPage(String formName);
   public void setMaxRowCount(int maxRowCount);
   
   public String getNextImgPage(String formName);
   public String getBackImgPage(String formName);
    
   public String getLinkPage(int number);
   public String getLinkPage(int number,String formName);

   public String getLinkUrl();
   public List getDataList();
}
