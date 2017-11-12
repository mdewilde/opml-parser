# OPML Parser

![Maven Central badge](https://maven-badges.herokuapp.com/maven-central/be.ceau/opml-parser/badge.svg) [![Javadocs](https://javadoc.io/badge/be.ceau/opml-parser.svg)](https://javadoc.io/doc/be.ceau/opml-parser)  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.txt)

Java library for parsing OPML (Outline Processor Markup Language) documents.

### Usage

```Java
Opml opml = new Parser().parse(input);
```

### Maven Central
Include this project directly from Maven Central
```XML
<groupId>be.ceau</groupId>
<artifactId>opml-parser</artifactId>
<version>${project.version}</version>
```

### GnuPG public key
Verify signature files with my [GnuPG public key](https://www.ceau.be/pubkey.gpg).

### License
Licensed under [the Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.txt).