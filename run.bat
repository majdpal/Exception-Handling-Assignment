@echo off

rem Compiling java program
javac ARXMLSort.java

rem Not Valid Autosar File Case
echo Case 1: .xml extension (test.xml)
java ARXMLSort test.xml
echo --------------------------------------------------------------
rem Empty File Case
echo Case 2: empty file (test_no_content.arxml)
java ARXMLSort test_no_content.arxml
echo --------------------------------------------------------------
rem Normal Case
echo Case 3: valid file (test_content.arxml)
java ARXMLSort test_content.arxml
echo --------------------------------------------------------------

rem Pause the batch file util the user presses a key
pause
