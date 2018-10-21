# CraftyProfessions
CraftyProfessions is a Jobs plugin for vairous Minecraft servers being Spigot and Sponge currently. The plugin will aim to distribute money to players who have taken a Profession or multiple professions and do specific tasks within those professions. One of the goals of this plugin is to be as lightweight as possible when it comes to memory management and cpu usage. If you see any better way to perform or implement a given task within the source code please let us know, we are always wanting to expand our knowledge of the Java platform.

That being said this plugin is still in Development and the features listed above are still being worked on. This is a plugin that we are making in our spare time to have fun so don't expect things to get done with haste. Furthermore because we want this plugin to be lightweight we want to take our time on developing it so it's easily maintainable and we weigh all the options while implementing the core and main features of the plugin. Feel free to give us feedback and new feature ideas right here in the github issue tracker, just make sure to mark it with a correct label such as [enchancement] or [improvement].

# Building the Project
In order to build CraftyProfessions simply clone the repository and run the following commands.

**Note: Currently all of the projects are in development so they WILL NOT work as intended at all. Building at this stage is simply to test any changed made while developing.**

### Make All Projects
<p>Make both the spigot and sponge jar files.</p>
<code>./gradlew makeall</code>

### Spigot Project
<p>Creates the jar file for the spigot version of crafty professions.</p>
<code>./gradlew makeSpigot</code>

### Sponge Project
<p>Creates the jar file for the sponge version of crafty professions.</p>
<code>./gradlew makeSponge</code>

### After Compilation
All jar files can be found in the output/ directory at the root of the project.

### More
If you would like to see more on the build information feel free to check out the root directories build.gradle along with the sub-projects build.gradles.

# License
The Full License can be found in our License File, but some info is below                     

                          GNU GENERAL PUBLIC LICENSE
                            Version 3, 29 June 2007
