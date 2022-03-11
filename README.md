# WebPOS

The demo shows a simple POS system in MVC architecture, which replaces the shell interface in aw02 with a pos web ui (https://github.com/bshbsh404/simple-pos-ui
).

![](screenshot.png)

To run

```shell
mvn clean spring-boot:run
```

Currently, it just lists the products for sale with a cart with one item (just for demonstration). 

Please read the tutorial at  https://www.baeldung.com/spring-boot-crud-thymeleaf and make the POS system robust and fully functional. You can also refer to other articles, for instance https://www.baeldung.com/tag/thymeleaf/ .



And please elaborate your understanding in MVC architecture via this homework in your README.md.

## 对MVC的理解

MVC架构中，所有用户输入最终都是由`Controller`组件即`PosController`来处理。由于本人完全不懂前端知识，所以目前的实现中，数据输入完全是按照GET请求通过URL发送的，处理完成后再重定向到首页。

`View`组件则是通过网页实现，具体而言就是`Controller`将所有网页所需动态属性填入网页后再发给客户端。

`Model`组件用于封装与应用程序的业务逻辑相关的数据以及对数据的处理方法。对应`PosServiceImp`与`PosInMemoryDB`以及`model`包内的几个数据结构相关的类，对应了三层架构中的两层。

从这个角度看，MVC架构和现代基于网页的应用有相当好的吻合度，但为了可拓展性，现代应用程序中的`Model`组件实际上是一个相当的抽象。