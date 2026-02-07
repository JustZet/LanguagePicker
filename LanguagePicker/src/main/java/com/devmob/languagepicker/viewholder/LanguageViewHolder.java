package com.devmob.languagepicker.viewholder;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.devmob.languagepicker.R;
import com.devmob.languagepicker.adapter.LanguageAdapter;
import com.devmob.languagepicker.databinding.AdapterLanguageBinding;
import com.devmob.languagepicker.models.Language;

public class LanguageViewHolder extends RecyclerView.ViewHolder {
    LanguageAdapter.ActionListener actionListener;
    AdapterLanguageBinding binding;
    Language language;
    boolean isStackVisible = false;

    public LanguageViewHolder(AdapterLanguageBinding binding, LanguageAdapter.ActionListener actionListener) {
        super(binding.getRoot());
        this.binding = binding;
        this.actionListener = actionListener;
    }
    public void bindItem(Language language) {
        this.language = language;

        binding.tvName.setText(language.getLanguageName());
        Glide.with(itemView.getContext())
                .load(language.getIcon())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(false)
                .fitCenter()
                .into(binding.ivIcon);

        binding.cvContainer.setOnClickListener(v -> actionListener.onSelect(language));
    }

    public void bindSelected(boolean isSelected) {
        binding.ivCheck.setImageDrawable(isSelected ?
                AppCompatResources.getDrawable(itemView.getContext(), R.drawable.ic_check) : null);
//        binding.ivCheck.setVisibility(isSelected ? View.VISIBLE : View.GONE);
    }
}
