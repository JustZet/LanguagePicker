<h1 align="center">🌐 LanguagePicker</h1>


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

### Examples:

`DialogLanguagePicker.java`
```java
String language = Locale.US.getLanguage(); // en
new DialogLanguagePicker(language) Adding selected language (optional)
        .setLanguages(DialogLanguagePicker.getAllLanguages().subList(0, 20)) // Setting first 20 languages
        .setActionListener(new DialogLanguagePicker.ActionListener() {
            @Override
            public void onSelect(Language language) {
              // Manage when language select
            }
        })
        .show(getSupportFragmentManager(), "Dialog")
```


`BottomSheetLanguagePicker.java`
```java
String language = Locale.US.getLanguage(); // en
new BottomSheetLanguagePicker(language) Adding selected language (optional)
        .setLanguages(BottomSheetLanguagePicker.getAllLanguages().subList(0, 20)) // Setting first 20 languages
        .setActionListener(new BottomSheetLanguagePicker.ActionListener() {
            @Override
            public void onSelect(Language language) {
              // Manage when language select
            }
        })
        .show(getSupportFragmentManager(), "BottomSheet");
```
