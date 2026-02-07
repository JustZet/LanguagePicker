package com.devmob.languagepicker.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.devmob.languagepicker.databinding.AdapterLanguageBinding;
import com.devmob.languagepicker.models.Language;
import com.devmob.languagepicker.viewholder.LanguageViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageViewHolder> {
    private List<Language> items = new ArrayList<>();
    private List<Language> filteredList = new ArrayList<>();
    private ActionListener actionListener;
    private @Nullable String selectedCountryCode = null;


    // region Style
    private Integer itemTextColor, itemStrokeIconColor, itemCheckIconColor, itemCheckCircleColor = null;
    // endregion

    public LanguageAdapter() {}

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        AdapterLanguageBinding binding = AdapterLanguageBinding.inflate(inflater, parent, false);
        if(itemCheckIconColor != null) binding.ivCheck.setColorFilter(itemCheckIconColor);
        if(itemTextColor != null) binding.tvName.setTextColor(itemTextColor);
        if(itemStrokeIconColor != null) binding.ivIcon.setColorFilter(itemStrokeIconColor);
//        if(itemCheckCircleColor != null) binding.cv.setColorFilter(itemCheckCircleColor);
        return new LanguageViewHolder(binding, actionListener);
    }
    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        Language item = filteredList.get(position);
        boolean isSelected = selectedCountryCode != null && item.getLanguageCode().equalsIgnoreCase(selectedCountryCode);
        holder.bindItem(item);
        holder.bindSelected(isSelected);
    }

    @Override
    public int getItemCount() {
        return filteredList == null ? 0 : filteredList.size();
    }

    public void setItems(List<Language> newItems) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(this.filteredList, newItems));
        this.items.clear();
        this.items.addAll(newItems);
        this.filteredList.clear();
        this.filteredList.addAll(newItems);
        diffResult.dispatchUpdatesTo(this);
    }
    public void setSelected(String languageCode) {
        if (selectedCountryCode != null) {
            int oldPos = findPositionByCountryCode(selectedCountryCode);
            if (oldPos != -1) {
                notifyItemChanged(oldPos);
            }
        }

        selectedCountryCode = languageCode;

        // Find new selection
        int newPos = findPositionByCountryCode(languageCode);
        if (newPos != -1) {
            notifyItemChanged(newPos);
        }
    }
    private int findPositionByCountryCode(String languageCode) {
        for (int i = 0; i < filteredList.size(); i++) {
            if (filteredList.get(i).getLanguageCode().equalsIgnoreCase(languageCode)) {
                return i;
            }
        }
        return -1;
    }
    public static class DiffCallback extends DiffUtil.Callback {

        private final List<Language> oldList;
        private final List<Language> newList;

        public DiffCallback(List<Language> oldList, List<Language> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            // Compare unique IDs
            return Objects.equals(oldList.get(oldItemPosition).getLanguageCode(),
                    newList.get(newItemPosition).getLanguageCode());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Language oldItem = oldList.get(oldItemPosition);
            Language newItem = newList.get(newItemPosition);

            // Compare relevant fields
            return oldItem.equals(newItem);
        }
    }

    public void filter(String query) {
        filteredList.clear();
        if (query == null || query.isEmpty()) {
            filteredList.addAll(items);
        } else {
            String lowerQuery = query.toLowerCase();
            for (Language lang : items) {
                if (lang.getLanguageName().toLowerCase().contains(lowerQuery)) {
                    filteredList.add(lang);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }


    // region Setters

    public LanguageAdapter setItemTextColor(Integer itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    public LanguageAdapter setItemStrokeIconColor(Integer itemStrokeIconColor) {
        this.itemStrokeIconColor = itemStrokeIconColor;
        return this;
    }



    public LanguageAdapter setItemCheckIconColor(Integer itemCheckIconColor) {
        this.itemCheckIconColor = itemCheckIconColor;
        return this;
    }

    public LanguageAdapter setItemCheckCircleColor(Integer itemCheckCircleColor) {
        this.itemCheckCircleColor = itemCheckCircleColor;
        return this;
    }

    // endregion

    public interface ActionListener {
        void onSelect(Language language);
    }
}
