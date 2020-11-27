# Wrapper for Garmin's Java SDK
Java API that wraps part of Garmin's Java SDK and makes it easier for me to create my Edge 500 workouts.

# FIT SDK resources
<https://www.thisisant.com/developer/resources/downloads/>

# Generating MTS workouts
``mvn compile && mvn exec:java -Dexec.mainClass="fit.mts.MTSSetGenerator"``