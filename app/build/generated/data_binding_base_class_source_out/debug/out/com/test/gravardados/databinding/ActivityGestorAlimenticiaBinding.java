// Generated by view binder compiler. Do not edit!
package com.test.gravardados.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.test.gravardados.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGestorAlimenticiaBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final AppCompatButton btnAddGaleria;

  @NonNull
  public final AppCompatButton btnEnviar;

  @NonNull
  public final MaterialToolbar customlToolbar;

  @NonNull
  public final TextView descriptionTextView;

  @NonNull
  public final EditText editDate;

  @NonNull
  public final TextInputEditText editDescriptionFood;

  @NonNull
  public final TextInputLayout editDescriptionFood1;

  @NonNull
  public final EditText editTitleFood;

  @NonNull
  public final ImageView imgPerfil;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final Button menuresButton;

  @NonNull
  public final AutoCompleteTextView optionPeriod;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textViewData;

  @NonNull
  public final TextView titleTextView;

  private ActivityGestorAlimenticiaBinding(@NonNull ConstraintLayout rootView,
      @NonNull AppCompatButton btnAddGaleria, @NonNull AppCompatButton btnEnviar,
      @NonNull MaterialToolbar customlToolbar, @NonNull TextView descriptionTextView,
      @NonNull EditText editDate, @NonNull TextInputEditText editDescriptionFood,
      @NonNull TextInputLayout editDescriptionFood1, @NonNull EditText editTitleFood,
      @NonNull ImageView imgPerfil, @NonNull ConstraintLayout main, @NonNull Button menuresButton,
      @NonNull AutoCompleteTextView optionPeriod, @NonNull RecyclerView recyclerView,
      @NonNull TextView textView7, @NonNull TextView textViewData,
      @NonNull TextView titleTextView) {
    this.rootView = rootView;
    this.btnAddGaleria = btnAddGaleria;
    this.btnEnviar = btnEnviar;
    this.customlToolbar = customlToolbar;
    this.descriptionTextView = descriptionTextView;
    this.editDate = editDate;
    this.editDescriptionFood = editDescriptionFood;
    this.editDescriptionFood1 = editDescriptionFood1;
    this.editTitleFood = editTitleFood;
    this.imgPerfil = imgPerfil;
    this.main = main;
    this.menuresButton = menuresButton;
    this.optionPeriod = optionPeriod;
    this.recyclerView = recyclerView;
    this.textView7 = textView7;
    this.textViewData = textViewData;
    this.titleTextView = titleTextView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGestorAlimenticiaBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGestorAlimenticiaBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_gestor_alimenticia, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGestorAlimenticiaBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAddGaleria;
      AppCompatButton btnAddGaleria = ViewBindings.findChildViewById(rootView, id);
      if (btnAddGaleria == null) {
        break missingId;
      }

      id = R.id.btn_enviar;
      AppCompatButton btnEnviar = ViewBindings.findChildViewById(rootView, id);
      if (btnEnviar == null) {
        break missingId;
      }

      id = R.id.customlToolbar;
      MaterialToolbar customlToolbar = ViewBindings.findChildViewById(rootView, id);
      if (customlToolbar == null) {
        break missingId;
      }

      id = R.id.descriptionTextView;
      TextView descriptionTextView = ViewBindings.findChildViewById(rootView, id);
      if (descriptionTextView == null) {
        break missingId;
      }

      id = R.id.editDate;
      EditText editDate = ViewBindings.findChildViewById(rootView, id);
      if (editDate == null) {
        break missingId;
      }

      id = R.id.editDescriptionFood;
      TextInputEditText editDescriptionFood = ViewBindings.findChildViewById(rootView, id);
      if (editDescriptionFood == null) {
        break missingId;
      }

      id = R.id.editDescriptionFood1;
      TextInputLayout editDescriptionFood1 = ViewBindings.findChildViewById(rootView, id);
      if (editDescriptionFood1 == null) {
        break missingId;
      }

      id = R.id.editTitleFood;
      EditText editTitleFood = ViewBindings.findChildViewById(rootView, id);
      if (editTitleFood == null) {
        break missingId;
      }

      id = R.id.imgPerfil;
      ImageView imgPerfil = ViewBindings.findChildViewById(rootView, id);
      if (imgPerfil == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.menures_button;
      Button menuresButton = ViewBindings.findChildViewById(rootView, id);
      if (menuresButton == null) {
        break missingId;
      }

      id = R.id.option_period;
      AutoCompleteTextView optionPeriod = ViewBindings.findChildViewById(rootView, id);
      if (optionPeriod == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textViewData;
      TextView textViewData = ViewBindings.findChildViewById(rootView, id);
      if (textViewData == null) {
        break missingId;
      }

      id = R.id.titleTextView;
      TextView titleTextView = ViewBindings.findChildViewById(rootView, id);
      if (titleTextView == null) {
        break missingId;
      }

      return new ActivityGestorAlimenticiaBinding((ConstraintLayout) rootView, btnAddGaleria,
          btnEnviar, customlToolbar, descriptionTextView, editDate, editDescriptionFood,
          editDescriptionFood1, editTitleFood, imgPerfil, main, menuresButton, optionPeriod,
          recyclerView, textView7, textViewData, titleTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
