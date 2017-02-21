## Intro

Automatically turn translations like this:

    key    en-US  zh-CN  zh-TW      ja
    file    file   文件   檔案   ファイル
    edit    edit   编辑   編輯      編集
    print  print   打印   列印      印刷

into this:

    out
    ├── values-en-rUS
    │   └── strings.xml
    ├── values-ja
    │   └── strings.xml
    ├── values-zh-rCN
    │   └── strings.xml
    └── values-zh-rTW
        └── strings.xml

which can be fed into an Android project directly.

Contents of the xml files generated:

out/values-en-rUS/strings.xml

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
      <string name="file">"file"</string>
      <string name="edit">"edit"</string>
      <string name="print">"print"</string>
    </resources>

out/values-ja/strings.xml

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
      <string name="file">"ファイル"</string>
      <string name="edit">"編集"</string>
      <string name="print">"印刷"</string>
    </resources>

out/values-zh-rCN/strings.xml

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
      <string name="file">"文件"</string>
      <string name="edit">"编辑"</string>
      <string name="print">"打印"</string>
    </resources>

out/values-zh-rTW/strings.xml

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
      <string name="file">"檔案"</string>
      <string name="edit">"編輯"</string>
      <string name="print">"列印"</string>
    </resources>

## Usage:

1. Prepare the translation file in tsv format. (You can use Google Sheets and its handy `GOOGLETRANSLATE()` function to generate translations automatically, like [this](https://docs.google.com/spreadsheets/d/1DXLyH6ieE_ZPUtEE6T8uc08B2AHe6xySKveUml10bK4/edit?usp=sharing))

3. Build.
    javac Convert.java

4. Run
    java Convert <path-to-the-translation-file>

## Format of the translation file:

Structure:

    | this cell is ignored | lang-a  | lang-b  | lang-c  | ...
    | key-1                | val-a-1 | val-b-1 | val-c-1 | ...
    | key-2                | val-a-2 | val-b-2 | val-c-2 | ...
                             ...

`lang-*` is in `<language>[-<region>]`. `<language>` is a 2-letter code defined in [ISO 639-1](http://www.loc.gov/standards/iso639-2/php/code_list.php). `<region>` is a 2-letter code defined in [ISO 3166-1](https://www.iso.org/obp/ui/#search). 

Note that `<region>` IS NOT preceded by 'r'.
