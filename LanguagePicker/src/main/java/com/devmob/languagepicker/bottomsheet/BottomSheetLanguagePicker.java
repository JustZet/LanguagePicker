package com.devmob.languagepicker.bottomsheet;


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.devmob.activityhelper.managers.BottomSheetManager;
import com.devmob.languagepicker.adapter.LanguageAdapter;
import com.devmob.languagepicker.databinding.BottomsheetLanguagePickerBinding;
import com.devmob.languagepicker.helpers.LanguageHelper;
import com.devmob.languagepicker.models.Language;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;


public class BottomSheetLanguagePicker extends DialogFragment {
    private Context context;
    private @Nullable String languageCode = null;

    BottomsheetLanguagePickerBinding binding;

    ActionListener actionListener;
    LanguageAdapter adapter;


    private List<Language> languages = new ArrayList<>();
    private Integer backgroundColor, titleColor, searchContainerColor, itemTextColor,
            itemStrokeIconColor, itemCheckColor, itemCheckCircleColor = null;
    private String titleLabel = "Languages";
    private String searchLabel = "Search";
    private boolean isSearchVisible = true;

    public BottomSheetLanguagePicker() {}
    public BottomSheetLanguagePicker(@Nullable String code) {
        this.languageCode = code;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = BottomsheetLanguagePickerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BottomSheetManager.with((BottomSheetDialog) requireDialog())
                .setCancelable(true)
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .apply();

        binding.tvTitle.setVisibility(titleLabel != null ? View.VISIBLE : View.GONE);
        binding.cvSearch.setVisibility(isSearchVisible ? View.VISIBLE : View.GONE);
        if (backgroundColor != null) binding.llContainer.setBackgroundColor(backgroundColor);
        if (titleColor != null) binding.tvTitle.setTextColor(titleColor);
        if (searchContainerColor != null) binding.cvSearch.setBackgroundColor(searchContainerColor);
        if (searchLabel != null) binding.tietSearch.setHint(searchLabel);
        if (titleLabel != null) binding.tvTitle.setText(titleLabel);
        if (languages == null || languages.isEmpty()) new ArrayList<>(LanguageHelper.getAllLanguages());
        initAdapter();
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

    // region Setters

    public BottomSheetLanguagePicker setLanguages(List<Language> languages) {
        this.languages = languages;
        return this;
    }

    public BottomSheetLanguagePicker setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
        return this;
    }

    public BottomSheetLanguagePicker setBackgroundColor(Integer backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public BottomSheetLanguagePicker setTitleColor(Integer titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public BottomSheetLanguagePicker setTitleLabel(String titleLabel) {
        this.titleLabel = titleLabel;
        return this;
    }

    public BottomSheetLanguagePicker setSearchLabel(String searchLabel) {
        this.searchLabel = searchLabel;
        return this;
    }

    public BottomSheetLanguagePicker setSearchVisible(boolean searchVisible) {
        isSearchVisible = searchVisible;
        return this;
    }

    public BottomSheetLanguagePicker setSearchContainerColor(Integer searchContainerColor) {
        this.searchContainerColor = searchContainerColor;
        return this;
    }

    public BottomSheetLanguagePicker setItemTextColor(Integer itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    public BottomSheetLanguagePicker setItemStrokeIconColor(Integer itemStrokeIconColor) {
        this.itemStrokeIconColor = itemStrokeIconColor;
        return this;
    }


    public BottomSheetLanguagePicker setItemCheckColor(Integer itemCheckColor) {
        this.itemCheckColor = itemCheckColor;
        return this;
    }

    public BottomSheetLanguagePicker setItemCheckCircleColor(Integer itemCheckCircleColor) {
        this.itemCheckCircleColor = itemCheckCircleColor;
        return this;
    }

    // endregion
    public interface ActionListener {
        void onSelect(Language language);
    }
}
