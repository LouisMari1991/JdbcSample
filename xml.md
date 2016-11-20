**XML文档声明：**
```
<?xml version="1.0" encoding="UTF-8">
```

**XMl约束：**
在XML技术里，可以编写一个文档来约束一个XML文档的书写规范，这称之为XML约束。

常用的XML约束：
   1.XML DTD
   2.XML Schema
   
DTD约束：(Document Type Definition)

**引用DTD约束：**

1.当引用在本地时，采用如下方式：
```
<!DOCTYEP 文档根节点 SYSTEM "DTD文件的URL">
```
2.当引用的文件是一个公共的文件时，采用如下形式：
```
<!DOCTYEP 文档根节点 PUBLIC "DTD名称" "DTD文件的URL">
```

**DTD语法细节:**
1.在DTD文档中使用ELEMENT声明一个XML元素，语法格式如下：
```
<!ELEMENT 元素名称 元素类型>
```
元素类型可以是元素内容、或类型
 - 如果是元素内容：则需要使用()括起来，如：
    <!ELEMENT 书架(书名,作者,售价)>
    <!ELEMENT 书名(#PCDATA)>
 - 如果是元素类型，则可以直接书写，DTD规范定义了如下几种类型：
     - EMPTY:用户定义空元素，例如<br/><hr/>
     - ANY 表示元素为任意类型