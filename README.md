Automatically turn a translation table like this:

    key	en-US	zh-CN	zh-TW	ja
    file	file	文件	檔案	ファイル
    edit	edit	编辑	編輯	編集
    print	print	打印	列印	印刷

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

Usage:

    javac Convert.java
    java Convert <tsv-file>

