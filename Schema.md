###XSD元素
定义元素的语法:
```
<xs:element name="xxx" type="yyy"/>
```

###Schema常用类型:
```
xs:string
xs:decimal
xs:integer
xs:boolean
xs:date
xs:time
```

###例子：
这是一些XML元素：
```
<lastname>Refsnes</lastname>
<age>36</age>
<dateborn>1970-03-27</dateborn>
```
元素的定义:
```
<xs:element name="lastname" type="xs:string"/>
<xs:element name="age" type="xs:integer"/>
<xs:element name="dateborn" type="xs:date"/>
```

###元素的默认值和固定值
元素可拥有指定的默认值或固定值。
当没有其他的值被规定时，默认值就会自动分配给元素。
下面的例子中，缺省值是"red"
```
<xs:element name="color" type="xs:string" default="red"/>
```

固定值同样会自动分配给元素，并且无法规定另外一个值
``` 
<xs:element name="color" type="xs:string" fixed="red"/>
```

###XSD属性
定义属性的语法:
```
<xs:attribute name="xxx" type="yyy"/>
```

###可选的属性和必须的属性
在缺省的情况下，属性是可选的。如果规定属性为必选，使用"use"属性
```
<xs:attribute name="lang" type="xs:string" use="required"/>
```