Automatically turn a tsv file like this:

    key    en-US  zh-CN  zh-TW        ja
    file    file   文件   檔案  ファイル
    edit    edit   编辑   編輯      編集
    print  print   打印   列印      印刷

into this structure:

    out
    ├── values-en-rUS
    │   └── strings.xml
    ├── values-ja
    │   └── strings.xml
    ├── values-zh-rCN
    │   └── strings.xml
    └── values-zh-rTW
        └── strings.xml

Content of the xml files:

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

Build:

    javac Convert.java

Usage:

    java Convert <tsv-file>

