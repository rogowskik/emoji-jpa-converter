[![Build Status](https://travis-ci.com/kamilrogowski/emoji-jpa-converter.svg?branch=master)](https://travis-ci.com/kamilrogowski/emoji-jpa-converter)
[![Code Coverage](https://codecov.io/gh/kamilrogowski/emoji-jpa-converter/branch/master/graph/badge.svg)](https://codecov.io/gh/kamilrogowski/emoji-jpa-converter)

**emoji-jpa-converter** is a lightweight java library that helps you store plain text emoji in a database and and later converts it to an entity field as emoji.

__Java library which converts emoji symbols such as 😂 to their aliases ":grinnng_face" and vice versa.__


## How to install

##### Maven:

```xml
<dependency>
  <groupId>com.github.rogowskik</groupId>
  <artifactId>emoji-jpa-converter</artifactId>
  <version>0.0.2</version>
</dependency>
```

##### Via Gradle:

```gradle
implementation 'com.github.rogowskik:emoji-jpa-converter:0.0.2''
```

####Alternative way:
Download the source code of project, build it with `mvn clean install` and add the generated jar to classpath.


## How to use it?

There are two ways to use this library:

#### EmojiUtils

##### Exposes two methods:

1.To replace all emojis found in a string by their aliases, use `EmojiUtils#toAlias(String)`.

For example:

```java
String str = "What a nice day 😍, Let's go outside 😗";
String result = EmojiUtils.toAlias(str);
System.out.println(result);
// Produces:
// "What a nice day :smiling_face_with_heart_eyes:, Let's go outside :kissing_face:"
```

2.To replace all aliases found in a string by their unicode, use `EmojiUtils#toEmoji(String)`.

For example:

```java
String str = ":smiling_face_with_halo: I am sending you a :love_letter: :upside_down_face::upside_down_face:";
String result = EmojiUtils.toEmoji(str);
System.out.println(result);
// Produces:
// "😇 I am sending you a 💌 🙃🙃"
```

________
#### EmojiConverter

Can be used directly as jpa converters:


```java
@Entity
public class Order {
    
   private String name;
   
   @Convert(converter = EmojiConverter.class)
   private String comment;
   ...
}
```


## Credits

**emoji-jpa-converter** is using the data provided by the [github/unicode-emoji-json](https://github.com/muan/unicode-emoji-json).
