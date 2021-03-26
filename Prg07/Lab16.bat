rem grade lab16
rem parameter 1: drive letter
%1:
cd \OopJava\Lab16
pause
set classpath=
javac MineSweeper.java
pause
appletviewer MineSweeperContainer.html
pause
javadoc -author -private MineSweeper.java
pause
index.html
