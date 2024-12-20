// Generated by view binder compiler. Do not edit!
package com.test.gravardados.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.test.gravardados.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityTestBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText editDate;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final RecyclerView usersRecyclerView;

  private ActivityTestBinding(@NonNull ConstraintLayout rootView, @NonNull EditText editDate,
      @NonNull RecyclerView recyclerView, @NonNull RecyclerView usersRecyclerView) {
    this.rootView = rootView;
    this.editDate = editDate;
    this.recyclerView = recyclerView;
    this.usersRecyclerView = usersRecyclerView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityTestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityTestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_test, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityTestBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editDate;
      EditText editDate = ViewBindings.findChildViewById(rootView, id);
      if (editDate == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.usersRecyclerView;
      RecyclerView usersRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (usersRecyclerView == null) {
        break missingId;
      }

      return new ActivityTestBinding((ConstraintLayout) rootView, editDate, recyclerView,
          usersRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
