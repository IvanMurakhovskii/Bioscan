package com.murik.lite.model;

import android.content.Context;

public interface ResultBySens {
   void setResult();
   void setStressResult();
   double getA();
   int getViewColor();
   String getResultComment();
   Context getContext();
   String getLegend();
   String getResources(int idRes);

}
