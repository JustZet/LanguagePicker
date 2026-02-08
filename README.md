<h1 align="center">🌐 LanguagePicker</h1>

<p align="center">
🌐 Modernized and sophisticated Language picker for android.
</p>
</br>
<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/JustZet/LanguagePicker"><img alt="Profile" src="https://img.shields.io/badge/github-LanguagePicker-orange?logo=github"/></a>
  <a href="https://github.com/JustZet"><img alt="Profile" src="https://img.shields.io/badge/github-Zettie-blue?logo=github"/></a>
</p> <br>


[![Watch the video](https://raw.githubusercontent.com/JustZet/LanguagePicker/refs/heads/main/assets/ChatGPT%20Image%20Feb%208%2C%202026%2C%2001_27_26%20PM.png)](https://youtu.be/z7hUyHIA6Wk?si=P-l4znM3BLASvj6K)
 
Applies changes only when .show(fragmentManager, "tag") is called
| Class                                                   | Description                                                               |
| --------------------------------------------------------| ------------------------------------------------------------------------- |
| :sparkles: `DialogLanguagePicker`                       | Language picker dialog.                                                   |
| :sparkles: `BottomSheetLanguagePicker`                  | Language picker bottom sheet .                                           ||


### Install:
```gradle
dependencies {
		implementation 'com.github.JustZet:LanguagePicker:1.0.3'
}
```

<img src="https://raw.githubusercontent.com/JustZet/LanguagePicker/refs/heads/main/assets/Screenshot_20260207_192429.png" align="right" width="150px"/>

## DialogLanguagePicker.java
```java
String language = Locale.US.getLanguage(); // en
new DialogLanguagePicker(language) // Adding selected language (optional)
        .setLanguages(DialogLanguagePicker.getAllLanguages().subList(0, 20)) // Setting first 20 languages
        .setActionListener(new DialogLanguagePicker.ActionListener() {
            @Override
            public void onSelect(Language language) {
              // Manage when language select
            }
        })
        .show(getSupportFragmentManager(), "Dialog")
```

#### Style:
```java
.setTitleColor(getColor(R.color.black))
.setItemTextColor(getColor(R.color.black))
.setBackgroundColor(getColor(R.color.white))
.setTitleLabel("Select language")
.setSearchLabel("Search")
```


<img src="https://raw.githubusercontent.com/JustZet/LanguagePicker/refs/heads/main/assets/Screenshot_20260207_192512.png" align="right" width="150px"/>

## BottomSheetLanguagePicker.java
```java
String language = Locale.US.getLanguage(); // en
new BottomSheetLanguagePicker(language) // Adding selected language (optional)
        .setLanguages(BottomSheetLanguagePicker.getAllLanguages().subList(0, 20)) // Setting first 20 languages
        .setActionListener(new BottomSheetLanguagePicker.ActionListener() {
            @Override
            public void onSelect(Language language) {
              // Manage when language select
            }
        })
        .show(getSupportFragmentManager(), "BottomSheet");
```

#### Style:
```java
.setTitleLabel("Choose language")
.setSearchLabel("Search language")
.setTitleColor(getColor(R.color.orange_dark))
.setBackgroundColor(getColor(R.color.black))
.setSearchContainerColor(getColor(R.color.black))
.setSearchHintColor(getColor(R.color.red))
.setSearchTextColor(getColor(R.color.blue))
.setItemCheckColor(getColor(R.color.blue_very_dark))
.setItemCheckCircleColor(getColor(R.color.blue_basic))
.setItemTextColor(getColor(R.color.red))
```




