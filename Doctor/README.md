Doctor module — running tests and GUI
=====================================

Quick commands (from the repository root):

- Run unit tests:

```
mvn -f Doctor/pom.xml test
```

- Run the JavaFX GUI (Linux/X11):

If you are on a headless machine or CI, use Xvfb:

```
xvfb-run -s "-screen 0 1280x800x24" mvn -f Doctor/pom.xml exec:java
```

Or on a regular Linux desktop with DISPLAY available:

```
mvn -f Doctor/pom.xml exec:java
```

Notes
- The project is configured for **Java 25** and **JavaFX 25.0.1**. If you run on macOS or Windows, adjust JavaFX dependency classifiers in `Doctor/pom.xml` or add Maven profiles to match your OS.
- Sources now follow the standard Maven layout with package `com` (files under `src/main/java/com` and tests under `src/test/java/com`), which is NetBeans-friendly.
- If you need a portable Maven wrapper, I can add `mvnw` to the repo.## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
