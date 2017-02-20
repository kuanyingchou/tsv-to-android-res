Automatically turn a tsv file like this:

    key    en-US  zh-CN  zh-TW        ja
    file    file   文件   檔案  ファイル
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

Build:

    javac Convert.java

Usage:

    java Convert <tsv-file>

