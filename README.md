# Wrapper for Garmin's Java SDK
Java API that wraps part of Garmin's Java SDK and makes it easier for me to create my Edge 500 workouts.

# FIT SDK resources
<https://www.thisisant.com/developer/resources/downloads/>

# Generating MTS workouts
``mvn compile && mvn exec:java -Dexec.mainClass="fit.mts.MTSSetGenerator"``

# Testing
``mvn test -Dtest="fit.parser.ParserTest"``

# See spreadsheet
workout "B9"

do 10m N1-black "85RPM"

repeat 2 times:
do 30s N5-black
do 15s N2-green

do 10m N1-green



Comment: #

Workout: workout

Step: do

Repeat: repeat



