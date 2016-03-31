scala-workshop
==============

Installation
-----------
We need to install the Java Development Kit (JDK) in version 8.

[Open JDK 8](http://openjdk.java.net/install/)

[Oracle JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)


Commandline
-----------
1. Start the sbt console with ``./sbt`` ``./sbt.bat``.

2. wait to have downloaded the whole internet. SBT will download all scala dependencies like
   the compiler and the scala standard lib.

3. In the sbt console, compile the project with 'compile'

3. In the sbt console, let the tests run with 'test'
No surprise, the tests are not successful. You have to fix this later.

with IntelliJIDEA >= 14
-----------------------
1. Start your IDE and make sure, that you have installed the 'Scala' plugin
   Go to 'Settings' -> 'Plugins' -> 'Install JetBrains Plugins' and search for 'scala'

2. Go to 'File' -> 'Open' and open the 'build.sbt' file of this project.

3. In the Import Windows mark 'Use auto-import' and 'Download sources and Docs'

4. Wait to have downloaded the whole internet

5. You should now see the project structure and the 'src/main/scala' and 'src/test/scala' directory.

6. Open the context menu of 'src/test/scala', click 'Run' -> 'ScalaTests in Scala'