# WordStream
An etymology utility for word tracking.
The term "stream" alludes to the flux that is language evolution.

I have a casual interest in Indo-Iranian languages, and I've thought it might be fun to track and analyze the use of terms derived from Indo-Iranian words in historical literature.

I've imagined a variety of structures that might be used to follow word associations across various languages. At present, all I've got is a simple relational structure wherein languages are directly associated with some number of words, and words have one or more parents and one or more children. Words each have one or more definitions. Each definition, in turn, can be linked to one or more words.

So far, it's just a curiosity, but it's a good excuse to play with algorithms, data structures, and various technologies.

With regard to specific technologies, this project will likely make use of the following:
- Java 11 (latest release supported by AWS Lightsail)
- Spring Boot
- Spring Web
- Spring Data JPA
- AWS Lightsail (cloud service)
