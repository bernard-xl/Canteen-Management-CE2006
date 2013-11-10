This is a project managed by Maven, GUI is created using Netbeans.

To build the project, weblaf-1.25.jar must be installed in local Maven Repository as it is not available on any repository on the web.
Refer to http://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html for more information about the installation.

To correct run the application, jdbc login and url must be configured correctly in SpringConfiguration class and shiro.ini configuration file.
Run the solid5ive.cams.demo.app.FirstSetup to initialize the system for first time running.

To pack the application into a standalone executable .jar, run assembly:assembly in Maven.

Administrator account
   login:    root@admin
   password: 1234

Stall owner account
   login:    user[x]@stall   ([x] is replaced with number, for example: user1@stall or user6@stall)
   password: 1234

