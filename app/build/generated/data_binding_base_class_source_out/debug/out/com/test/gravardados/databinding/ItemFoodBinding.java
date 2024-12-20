// Generated by view binder compiler. Do not edit!
package com.test.gravardados.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.test.gravardados.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ItemFoodBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView categoryText;

  @NonNull
  public final TextView nameText;

  private ItemFoodBinding(@NonNull ConstraintLayout rootView, @NonNull TextView categoryText,
      @NonNull TextView nameText) {
    this.rootView = rootView;
    this.categoryText = categoryText;
    this.nameText = nameText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemFoodBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemFoodBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_food, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemFoodBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.categoryText;
      TextView categoryText = ViewBindings.findChildViewById(rootView, id);
      if (categoryText == null) {
        break missingId;
      }

      id = R.id.nameText;
      TextView nameText = ViewBindings.findChildViewById(rootView, id);
      if (nameText == null) {
        break missingId;
      }

      return new ItemFoodBinding((ConstraintLayout) rootView, categoryText, nameText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
