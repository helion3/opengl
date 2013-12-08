# OpenGL Tests

This is a loose collection of OpenGL (mainly VBO) rendering tests. 

I have a Minecraft-style 3d block world game that currently renders chunks using `display lists` - the block faces for the chunk are generated and cached, and the chunk renders with the display list id. When changes to blocks are needed, we rebuild the cache.

Plenty of people have recommended switching to VBOs. They should be able to equal the performance of display lists with added flexibility. I needed to build a sample application t a) verify my VBO rendering worked and b) push performance so that it will be worth moving my actual game without losing performance.

### Running

- clone the repo
- This uses maven, so `mvn package`
- Copy the .jar files from the `repo` folder to the `target/lib` folder
- `cd target`
- `java -jar -Djava.library.path="./natives" opengl-0.0.1-SNAPSHOT.jar`


### Open Source

I've open-sourced this code so that once completed, I can construct some better tutorials around VBO and any pitfalls to better help new users. Please feel free to add/improve.

