## Goose On Web
This is a typical project focusing on main programming issues and popular usages with SpringBoot.

* Issue Topics
    * SpringBoot necessities
    * Exception Handler
    * Internationalization
        * I18N and Messages

```xml
<!-- Project Info -->
<project>
    <groupId>com.crazygoose</groupId>
    <artifactId>gooseonweb</artifactId>
    ...
</project>
```

### Issue Topics
#### SpringBoot necessities
Generally speaking, SpringBoot project is always a maven project.
To start this maven project with SpringBoot, we needs below dependencies: 
_spring-boot-starter-parent_, this is what every SpringBoot project must inherits inside "<parent/>" under POM;
_spring-boot-starter-web_, this is what every SpringBoot web project must depend on.


Below dependencies are good and recommended to use:
Lombok is very handy to avoid very much boilerplate codes.
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>provided</scope>
</dependency>
```

Below properties are necessary to config in POM:
```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
</properties> 
```

#### Exception Handler
Under "/demo.exceptionHandler", there is one simple implementation based on SpringBoot original solution: 
@ControllerAdvice and@ExceptionHandler.
@ControllerAdvice is used to make the handler work in a global way, @ExceptionHandler is used to catch specific 
Exception. Besides, @ResponseStatus is used to specify the status code for this http response.
In this example, one exception is created called "CustomedException" which extends "RuntimeException", which because 
only RuntimeException does go through the logic without explicitly catching it, hence @ExceptionHandler could catch it 
regardless of the function it throws. Otherwise, lots of additional "try-catch" blocks would be added into the project.   

Last but not least, it is very recommended to read internet articles about "SpringBoot Exception Handler" to get basic 
ideas of Exception handler in SpringBoot.

#### Internationalization(i18n)
##### I18N and Messages
Under "/demo.internationalization", one API called "getGreetingMessage()" reads one internal message property and 
returns to the client. Please be noted that it accepts "Locale" as its parameter, and this is not necessary when 
invoking this method, as SpringBoot automatically reads "Accept-Language" and inject this property as "Locale" from 
request headers. Hence, when calling this kind of API, be aware of this request header "Accept-Language".

After obtaining the client locale, another step is prepare necessary message property files. In this project, under 
"/resources", there are two messages properties: "messages.properties" and "messages_zh_cn.properties". The former is 
the default property file, and so will be used if there is no other matching property files; the latter property file is
specifically serving requests with "zh_CN" in the header, to make it clear, properties with below suffix will use 
customized languages:
> messages_xx.properties
> messages_xx_xx.properties

Where "xx" or "xx_xx" is the locale code for this language. For example, if incoming request has header "zh_CN", to make
 service pick correct property file, filename should end with "zh.properties" or "zh_cn.properties"".  

After preparation, next step is to use SpringBoot MessageSource class to pick correct message from corresponding messages
property file. Example could be found in the class "I18nDefaultService".

##### I18N Data
