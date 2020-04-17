# FreeMarkerForVelocity
* Try out Apache Velocity and FreeMarker
* Try if FreeMarker is able to replace Apache Velocity

&nbsp;

### Some Learning Notes ###
##### What is Apache Velocity #####
* "If you're new to Velocity, you can read the Overview to understand its principles.  
Then, you can go through the User Guide for more details. 
* You can check the Velocity Template Language (VTL) reference to write templates,   
and the Developer Guide to guide you in the process of configuring and integrating Velocity. "
&nbsp;


* Velocity is a Java-based **templating engine**. 
* A template engine is a Java **library** to generate text output (HTML web pages, e-mails, configuration files, source code, etc.)
 based on templates and changing data.
&nbsp;

##### Velocity Singleton Model #####
* there is only one instance of the Velocity engine in the JVM that is shared by all.
* This is very convenient as it allows **localized configuration** and **sharing of resources**, e.g. templates, a logger, properties etc. 
* Velocity.init();
&nbsp;

##### Velocity Separate Instance #####
* separate instance allows you to create, configure and use as many instances of Velocity as you wish in the same JVM. 
* This is useful when you wish to support separate configurations, such as template directories, loggers, etc in the same application.
* VelocityEngine ve = new VelocityEngine(); ve.init();
&nbsp;

##### Important #####
* The Singleton we talking about here is the **Velocity engine instance**. 
&nbsp;   
&nbsp;   

#### FreeMarker: Ways to pass in Static methods to templates ####
The issue is: Passing in `StringUtils.class` and using static `StringUtils.trimmedSplit()` in template will result in: `StringUtils.trimmedSplit evaluated to null or missing`
##### 1:  Use TemplateMethodModelEx #####
* Make trimmedSplit method into a class and implement [TemplateMethodModelEx](https://freemarker.apache.org/docs/pgui_datamodel_method.html).
* Pass in an instance of the method object into template.
* Diff between `SimpleScalar.getAsString()` vs `SimpleScalar.toString()`: 
  * `SimpleScalar.getAsString()`: `return this.value == null ? "" : this.value;`
  * `SimpleScalar.toString()`: `return this.value;`
&nbsp;
##### 2: Pass in an initialised StringUtils instance #####
* Pass in `new StringUtils()` to template 
* Question: Will this work for Utility Classes?  
Depends.   
For a completely stateless utility class in Java, the is declared public and final,   
and it has a private constructor to prevent instantiation.  
&nbsp;

&nbsp;
----
### Useful links ###
* [Javaguides Apache Velocity Tutorial](javaguides.net/2019/11/apache-velocity-tutorial.html)
* [Apache Velocity User Guide](https://velocity.apache.org/engine/2.0/user-guide.html)
* [Apache Velocity Developer Guide](https://velocity.apache.org/engine/1.7/developer-guide.html)
* [Baeldung Introduction to Apache Velocity](https://www.baeldung.com/apache-velocity)
* [FreeMarker Manual](https://freemarker.apache.org/docs/index.html)
* [The official extension of FreeMarker template files](https://freemarker.apache.org/docs/versions_2_1_3.html)
* [javatpoint: JavaMail Tutorial](https://www.javatpoint.com/java-mail-api-tutorial)
* [Google SMTP Server – How to Send Emails for Free](https://www.siteground.com/kb/google_free_smtp_server/)
* [tutorialspoint: Java - Sending Email](https://www.tutorialspoint.com/java/java_sending_email.htm)
* [Java Properties](https://docs.oracle.com/javase/tutorial/essential/environment/properties.html)


