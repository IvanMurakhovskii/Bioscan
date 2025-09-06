package com.murik.lite.model;

import android.content.Context;

public interface ResultBySens {
   void setResult();
   void setStressResult();
   void setSecondStressResult();
   double getA();
   int getStressA();
   int getSecondStressA();
   int getViewColor();
   String getResultComment();
   Context getContext();
   String getLegend();
   String getResources(int idRes);
   double Normalise();
}
