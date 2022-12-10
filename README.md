# Fortgeschrittene Softwaretechnik Projekt "Hikma"
## Project Description
Religious reminder application written in Java using Intellij, builds managed by gradle, and basic GUI built with AWT.
## Assignment Criteria
### Git
- [Using pull requests](https://github.com/vsvincent/hikma/pulls)
- Following clean commit message practices
- Managing branches
- [Integrating Sonarcloud into the repository](https://github.com/vsvincent/hikma/blob/main/.github/workflows/build.yml)
### UML diagrams
- [Activity Diagram](https://github.com/vsvincent/hikma/blob/develop/documentation/activityDiagram.drawio.png)
- System Context Diagram (WIP)
- Component Diagram (WIP)
### Domain-Driven-Design Diagrams
- [Context Map](https://github.com/vsvincent/hikma/blob/develop/documentation/domainModel.drawio.png)
### Metrics
![image](https://user-images.githubusercontent.com/79527821/206612646-ce1ae3bc-372c-4c82-8f0a-abe416bc56c1.png)
### Clean Code Development
-[Clean Code Cheat Sheet](https://github.com/vsvincent/hikma/blob/develop/documentation/CleanCodeCheatSheet.drawio.pdf)
### Build Management
### Integrate Unit-Tests
- In progress... (See the [develop](https://github.com/vsvincent/hikma/tree/develop/reminder/src/test/java/hikma/reminder) branch)
### Continuous Delivery
- [Sonarcloud integration](https://sonarcloud.io/project/overview?id=vsvincent_hikma)
- [Example Sonarcloud workflow](https://github.com/vsvincent/hikma/actions/runs/3651159124/jobs/6168034715)
### IDE
### DSL
### Functional Programming

## Run Instructions
### Quick Run
1. Run reminderApp.bat
### Manual Alternative
1. Build project.
2. Run gradlew bootJar in project root directory *i.e. hikma/reminder*.
3. Run java -jar **jarFileName***.jar in the build/libs directory of the project.
   *Depends on the project name and version.
4. Follow the menu prompts.**

    **Currently only the "show next prayer" menu item is functional
