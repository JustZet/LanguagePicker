package com.devmob.languagepicker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.devmob.languagepicker.bottomsheet.BottomSheetLanguagePicker;
import com.devmob.languagepicker.models.Language;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {
    Language selectedLanguage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ExtendedFloatingActionButton fab_language = findViewById(R.id.fab_language);


        fab_language.setOnClickListener(v -> {
            new BottomSheetLanguagePicker(selectedLanguage != null ? selectedLanguage.getLanguageCode() : null)
                    .setTitleLabel("Choose language")
                    .setSearchLabel("Search language")
                    .setTitleColor(getColor(R.color.orange_dark))
                    .setBackgroundColor(getColor(R.color.black_languagePicker))
                    .setSearchContainerColor(getColor(R.color.black_languagePicker))
                    .setSearchHintColor(getColor(R.color.red))
                    .setSearchTextColor(getColor(R.color.blue))
                    .setItemCheckColor(getColor(R.color.blue_very_dark))
                    .setItemCheckCircleColor(getColor(R.color.blue_basic))
                    .setItemTextColor(getColor(R.color.red))
                    .setActionListener(new BottomSheetLanguagePicker.ActionListener() {
                        @Override
                        public void onSelect(Language language) {
                            selectedLanguage = language;
                        }
                    })
                    .show(getSupportFragmentManager());
        });
    }
}