# SC2002-BTO application project

## Repository Structure (as of now)
```
BTO_Application/
│── src/                      # Source folder to store source code
│    ├── main/                # Main application code
│    │    ├── boundary/       # Stores all boundary classes
|    │    │    ├── applicantUI.java    #Shows all the options for applicant and process input
|    │    │    ├── officerUI.java      #Shows all the options for officer and process input
|    │    │    ├── managerUI.java      #Shows all the options for manager and process input
|    │    │    ├── IusergroupUI.java      #Interface for UI
│    │    ├── control/        # Stores all control classes
|    │    │    ├── InvalidLoginException.java      #Custom exception class handling invalid logins
|    │    │    ├── loginManager.java               #Control logic that checks input username and password
|    │    │    ├── usergroupUIFactory.java         #Control logic to decide which UI to display
│    │    ├── entity/         # Stores all entity classes
│    │    │── btoApp.java     # The application java code
│    ├── test/                # Test cases
│    │    ├── test.java              # Test code, add more if needed
│── data/                    # Stores .xlsx datasets for input and processing
│    ├── input/              # Initial dataset
│    ├── processed/          # Modified datasets (output)
│── docs/                    # Documentation folder
│    ├── UML/                # UML diagrams
│    ├── //add documentation        # Project-related PDFs and written report
│── .gitignore               # Specifies files to be ignored by Git
│── README.md                # Project documentation (overview, instructions)
```
Project assignment pdf is in docs/ for easy access
