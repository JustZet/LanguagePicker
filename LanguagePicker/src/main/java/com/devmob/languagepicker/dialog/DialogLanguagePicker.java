package com.devmob.languagepicker.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.devmob.languagepicker.R;
import com.devmob.languagepicker.adapter.LanguageAdapter;
import com.devmob.languagepicker.databinding.BottomsheetLanguagePickerBinding;
import com.devmob.languagepicker.databinding.DialogLanguagePickerBinding;
import com.devmob.languagepicker.helpers.LanguageHelper;
import com.devmob.languagepicker.managers.DialogManager;
import com.devmob.languagepicker.models.Language;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class DialogLanguagePicker extends DialogFragment {
    private Context context;
    private @Nullable String languageCode = null;

    DialogLanguagePickerBinding binding;

    ActionListener actionListener;
    LanguageAdapter adapter;


    private List<Language> languages = new ArrayList<>();
    private Integer backgroundColor, titleColor, searchContainerColor, itemTextColor,
            itemStrokeIconColor, itemCheckColor, itemCheckCircleColor = null;
    private String titleLabel = "Languages";
    private String searchLabel = "Search";
    private boolean isSearchVisible = true;

    public DialogLanguagePicker() {}
    public DialogLanguagePicker(@Nullable String code) {
        this.languageCode = code;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = DialogLanguagePickerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        }
        DialogManager.with(getDialog())
                .setCancelable(true)
                .setSize((int) (getResources().getDisplayMetrics().widthPixels * 0.85), ViewGroup.LayoutParams.WRAP_CONTENT)
                .setKeyboardListener(getActivity(), this::bindKeyboardOpened)
                .apply();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvTitle.setVisibility(titleLabel != null ? View.VISIBLE : View.GONE);
        binding.cvSearch.setVisibility(isSearchVisible ? View.VISIBLE : View.GONE);
        if (backgroundColor != null) binding.cvContainer.setCardBackgroundColor(backgroundColor);
        if (titleColor != null) binding.tvTitle.setTextColor(titleColor);
        if (searchContainerColor != null) binding.cvSearch.setBackgroundColor(searchContainerColor);
        if (searchLabel != null) binding.tietSearch.setHint(searchLabel);
        if (titleLabel != null) binding.tvTitle.setText(titleLabel);
        if (languages == null || languages.isEmpty()) languages = new ArrayList<>(LanguageHelper.getAllLanguages());
        initAdapter();
        bindKeyboardOpened(false);
    }
    public void show(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(this.getClass().getName()) != null) return;
        this.show(fragmentManager, this.getClass().getName());
    }
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter = new LanguageAdapter()
                .setItemCheckCircleColor(itemCheckCircleColor)
                .setItemCheckIconColor(itemCheckColor)
                .setItemTextColor(itemTextColor)
                .setItemStrokeIconColor(itemStrokeIconColor);

        binding.rvItems.setLayoutManager(layoutManager);
        binding.rvItems.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
//                bindPlaceholderVisible(adapter.getItemCount() == 0);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
//                bindPlaceholderVisible(adapter.getItemCount() == 0);
            }
        });
        adapter.setActionListener(language -> {
            if (actionListener != null) actionListener.onSelect(language);
            dismiss();
        });
        adapter.setItems(languages);
        adapter.setSelected(languageCode);

        binding.tietSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.filter(editable.toString());
            }
        });
    }
    private void bindKeyboardOpened(boolean isOpen){
        int size = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, isOpen ? 200 : 350,
                context.getResources().getDisplayMetrics());

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.cvItems.getLayoutParams();
        params.height = size;
        binding.cvItems.setLayoutParams(params);
    }
    // region Setters

    public DialogLanguagePicker setLanguages(List<Language> languages) {
        this.languages = languages;
        return this;
    }

    public DialogLanguagePicker setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
        return this;
    }

    public DialogLanguagePicker setBackgroundColor(Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public DialogLanguagePicker setTitleColor(Integer titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public DialogLanguagePicker setTitleLabel(String titleLabel) {
        this.titleLabel = titleLabel;
        return this;
    }

    public DialogLanguagePicker setSearchLabel(String searchLabel) {
        this.searchLabel = searchLabel;
        return this;
    }

    public DialogLanguagePicker setSearchVisible(boolean searchVisible) {
        isSearchVisible = searchVisible;
        return this;
    }

    public DialogLanguagePicker setSearchContainerColor(Integer searchContainerColor) {
        this.searchContainerColor = searchContainerColor;
        return this;
    }

    public DialogLanguagePicker setItemTextColor(Integer itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    public DialogLanguagePicker setItemStrokeIconColor(Integer itemStrokeIconColor) {
        this.itemStrokeIconColor = itemStrokeIconColor;
        return this;
    }


    public DialogLanguagePicker setItemCheckColor(Integer itemCheckColor) {
        this.itemCheckColor = itemCheckColor;
        return this;
    }

    public DialogLanguagePicker setItemCheckCircleColor(Integer itemCheckCircleColor) {
        this.itemCheckCircleColor = itemCheckCircleColor;
        return this;
    }
    public List<Language> getAllLanguages() {
        return new ArrayList<>(LanguageHelper.getAllLanguages());
    }
    // endregion
    public interface ActionListener {
        void onSelect(Language language);
    }
}
