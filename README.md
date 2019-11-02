
## About
This is a re-write of a simple paint program I did while still a student in computer science. Updated
it to use 'var' for Java 11 and apply new coding skills that I learned.


## Details
A simple paint program which can be used to draw rectangles, circles, and triangles of different sizes 
and dimensions. The shapes can be of nearly any color using the JColorChooser and be filled with color 
or be just an outline. Shapes can also be sorted by size (pushing the larger shapes to the back). 
Current projects can be saved and then resumed later. This was my second major programming assignment 
before becoming a computer science major. It was the final assignment in my second quarter of Java 
programming. The primary purpose of this assignment was to learn inheritance, interfaces, and basics 
of the Java Swing library.


## Compiling
- Compile src files: `javac -d bin/ src/*.java`
- Create jar file: `jar cvfe SimplePaint.jar Main -C bin .`
- Run the jar file: `java -jar SimplePaint.jar`
