## Getting Started

Install JDK8 using the package attached and make sure that the path variable is set correctly. Test this by typing `java -version` into your terminal. It should respond with something like ```java version "1.8.0_202"
Java(TM) SE Runtime Environment (build 1.8.0_202-b08)
Java HotSpot(TM) 64-Bit Server VM (build 25.202-b08, mixed mode)```

Drag the entire folder into VSCode and click run on the top right corner. The first run will take some time to compile the project, but subsequent runs will be faster to initialize.

If you encounter an error, please read the error message and determine its source.
# Common Errors
1. Python script isn't working - this is likely due to an issue with the way the python command is invoked on your system. Check this by typing `python --version` in your terminal. If this doesn't work, test it with other invokations like `python3`, `py`, or `py3`. Then replace the string in KalmanTrackerRunner.java from `python` to whichever invokation gave you a response in the line `ProcessBuilder pb = new ProcessBuilder("python", "src\\Track-Spot_Merger_Auto.py", "--csvlist", csvArgumentString);`.
