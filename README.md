# Exception-Handling-Assignment
This program reads an ARXML file containing a list of containers, each with a unique ID, and reorders the containers alphabetically by their sub-container's "SHORT-NAME". The program then writes the reordered containers to a new ARXML file.
# How to Use
  1. Make sure you have Java installed on your machine. You can download it from the official website: https://www.java.com/en/download/
  2. Clone or download the repository to your local machine.
  3. Navigate to the root directory of the project.
  4. Open the run.bat file.
  5. The program will read the input file, reorder the containers alphabetically by their sub-container's "SHORT-NAME"
     and write the reordered data to the output file.
# Notes
- The program assumes that the input ARXML file is well-formed and contains valid XML.
- The program uses the javax.xml.parsers.DocumentBuilderFactory and javax.xml.parsers.DocumentBuilder classes to parse the input file and manipulate the XML data.
- The program preserves the original formatting of the input file in the output file.
- The output file shall be named as the same of the input file concatenated with “_mod.arxml”  
  > e.g. if the input was named “Rte_Ecuc.arxml” then the output should be “Rte_Ecuc_mod.arxml”.
