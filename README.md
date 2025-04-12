# SC2002-BTO

## Repository Structure (as of now)
```
SC2002-BTO/
│── src/                      # Source folder to store source code
│    ├── main/                # Main application code
│    │    ├── boundary/       # Stores all boundary classes
|    │    │    ├── applicantUI.java    #Shows all the options for applicant and process input
|    │    │    ├── officerUI.java      #Shows all the options for officer and process input
|    │    │    ├── managerUI.java      #Shows all the options for manager and process input
│    │    ├── control/        # Stores all control classes
|    │    │    ├── InvalidLoginException.java      #Custom exception class handling invalid logins
|    │    │    ├── loginManager.java               #Checks input username and password
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
