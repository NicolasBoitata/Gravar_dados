// Generated by view binder compiler. Do not edit!
package com.test.gravardados.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.test.gravardados.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MerendaTimetableToolbarBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final AppCompatButton btnAfternoon;

  @NonNull
  public final AppCompatButton btnEarly;

  @NonNull
  public final AppCompatButton btnMorning;

  @NonNull
  public final MaterialToolbar navbartoolbar;

  private MerendaTimetableToolbarBinding(@NonNull RelativeLayout rootView,
      @NonNull AppCompatButton btnAfternoon, @NonNull AppCompatButton btnEarly,
      @NonNull AppCompatButton btnMorning, @NonNull MaterialToolbar navbartoolbar) {
    this.rootView = rootView;
    this.btnAfternoon = btnAfternoon;
    this.btnEarly = btnEarly;
    this.btnMorning = btnMorning;
    this.navbartoolbar = navbartoolbar;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MerendaTimetableToolbarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MerendaTimetableToolbarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.merenda_timetable_toolbar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MerendaTimetableToolbarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_afternoon;
      AppCompatButton btnAfternoon = ViewBindings.findChildViewById(rootView, id);
      if (btnAfternoon == null) {
        break missingId;
      }

      id = R.id.btn_early;
      AppCompatButton btnEarly = ViewBindings.findChildViewById(rootView, id);
      if (btnEarly == null) {
        break missingId;
      }

      id = R.id.btn_morning;
      AppCompatButton btnMorning = ViewBindings.findChildViewById(rootView, id);
      if (btnMorning == null) {
        break missingId;
      }

      id = R.id.navbartoolbar;
      MaterialToolbar navbartoolbar = ViewBindings.findChildViewById(rootView, id);
      if (navbartoolbar == null) {
        break missingId;
      }

      return new MerendaTimetableToolbarBinding((RelativeLayout) rootView, btnAfternoon, btnEarly,
          btnMorning, navbartoolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}