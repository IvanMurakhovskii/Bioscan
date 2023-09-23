package com.murik.lite.ui.bottom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class BottomAppBarBehavior extends CoordinatorLayout.Behavior<BottomAppBar> {

  public BottomAppBarBehavior(Context context, AttributeSet attrs){

  }

  @Override
  public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull BottomAppBar child,
      @NonNull View dependency) {
    return dependency instanceof BottomAppBar;
  }

  @Override
  public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent,
      @NonNull BottomAppBar child, @NonNull View dependency) {

    return true;
  }
}
