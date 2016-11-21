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
<br/>

**DTD语法细节:**

1.在DTD文档中使用ELEMENT声明一个XML元素，语法格式如下：

```
<!ELEMENT 元素名称 元素类型>
```

2.元素类型可以是元素内容、或类型
 - 如果是元素内容：则需要使用()括起来，如：
    <!ELEMENT 书架(书名,作者,售价)>
    <!ELEMENT 书名(#PCDATA)>
 - 如果是元素类型，则可以直接书写，DTD规范定义了如下几种类型：
     - EMPTY:用户定义空元素，例如<br/><hr/>
     - ANY 表示元素为任意类型
     
3.元素内容可以使用如下方式，描述内容的组成关系
 　　a.用逗号分隔，表示内容的竖线顺序必须与声明时一致。
 ```
 <!ELEMENT MYFILE (TITILE,AUTHOR,EMAIL)>
 ``` 
 　　b.用|号分隔，表示任选其一，即多个只能出现一个
 ```
  <!ELEMENT MYFILE (TITLE|AUTHOR|EMAIL)>
 ``` 
 
 4.在元素内容中也可以使用 +,*,? 等符号表示元素出现的次数：
  + : 一次或多次(书+);
  ? : 0次或一次(书?);
  * : 0此或多次(书*)
  (书) : 必须要出现一个
也可以使用圆括号()批量设置,例：
 ```
  <!ELEMENT MYFILE (TITLE*,AUTHOR?,EMAIL)*|COMMENT>
 ``` 
<br/> 

**属性定义 attlist**
1.xml文档中的标签属性需要通过ATTLIST为其设置属性
 语法格式：
  ```
  <!ATTLIST 元素名
    属性名1 属性值类型 设置说明
    属性名2 属性值类型 设置说明
    ...
  >
  ``` 
  属性声明举例：
  ```
  <!ATTLIST 商品
    类别 CDATA #REQUIRED
    颜色 CDATA #IMPLIED
  >
  ```
  对应XML文件：
  ```
  <商品 类别="服装" 颜色="黄色">...</商品>
  <商品 类别="服装">...</商品>
  ```

设置说明：
```
#REQUIRED:必须设置属性
#IMPLIED:可以设置也可以不设置
#FIXED:说明该属性的取值固定为一个值，在XML文件中不能为该属性设置其他值。但需要为该属性提供这个值。
直接只用默认值：在XML中可以设置该值也可以不设置该属性值。若没设置则使用默认值。
```

举例：
```
<!ATTLIST 页面作者
  姓名 CDATA #IMPLIED
  年龄 CDATA #IMPLIED
  联系信息 CDATA #REQURED
  网站职务 CDATA #FIXED "页面作者"
  个人爱好 CDATA "上网
>
```

常用的属性值类型： 
1.CDATA:表示属性值为普通文本字符串。
2.ENUMERATED(枚举)
3.ID
4.ENTITY(实体)


属性值类型：
ENUMERATED(枚举):属性的类型可以是一组取值的列表，在XML文件中设置的数值型只能是这个了表中的某个值(枚举)
```
<? xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE 购物篮[
   <!ELEMENT 肉 EMPTY>
   <!ATTLIST 肉 品种(鸡肉|牛肉|猪肉|鱼肉)"鸡肉">
]>
<购物篮>
  <肉 品种="鱼肉"/>
  <肉 品种="牛肉"/>
  <肉/>
</购物篮>
```

ID:
a.表示属性的设置值为一个唯一的值
b.ID属性的值只能由字母，下划线开始，不能出现空白字符。
```
<? xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE 联系人列表[
  <!ELEMENT 联系人列表 ANY>
  <!ELEMENT 联系人(姓名,EMAIL) ANY>
  <!ELEMENT 姓名(#PCDATA) ANY>
  <!ELEMENT EMAIL(#PCDATA) ANY>
  <!ELEMENT 联系人 编号 ID #REQUIRED ANY>
]>

<联系人列表>
  <联系人 编号="a1"> 
    <姓名>张三</姓名>
    <EMAIL>zhang@163.com</EMAIL>
  </联系人>  
  <联系人 编号="a2"> 
    <姓名>栗色</姓名>
    <EMAIL>lise@163.com</EMAIL>
  </联系人>
</联系人列表>

```

实体定义：
1.实体用于为一段内容创建一个别名，以后在XML文档中就可以使用别名引用这段内容了。
2.DTD定义中，一条 <!ENTITY ...> 语句用于定义一个实体。
3.实体可以分为两种类型：引用实体和参数实体。

定义实体→引用实体：
1.语法格式：
```
<!ENTITY 实体名称 "实体内容" > : 直接转变成实体内容
```

2.引用方式：
 ```
 &实体名称;
 ```
 
3.举例：
```
<ENTITY copyright "I am a programmer">
......
&copyright;
```

参数实体：
1.参数实体被DTD自身使用

2.语法格式:
<!ENTITY % 实体名称 "实体内容>

2.引用方式：
```
%实体名称
```

3.举例1：
```
<!ENTITY % TAG_NAMES "姓名 | EMAIL | 电话 | 地址">

<!ELEMENT 个人信息 (%TAG_NAMES; | 生日)>
<!ELEMENT 客户信息 (%TAG_NAMES; | 公司名)>
```

举例2：
```
<!ENTITY % common.attributes
  "id ID #IMPLIED
  account CDATA #REQUIRED "
>

...
<!ATTLIST purchaseOrder %common.attributes;>
<!ATTLIST item %common.attributes;>
```




