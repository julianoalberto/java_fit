# FIT SDK resources
<https://www.thisisant.com/developer/resources/downloads/>

## For my workouts:

Step

wktStepMsg.setMessageIndex(i);
        wktStepMsg.setWktStepName("Step 1");
        wktStepMsg.setDurationType(WktStepDuration.OPEN);
        wktStepMsg.setTargetType(WktStepTarget.OPEN);

Index
Name (MAX, NEXT STOP, etc)
Duration type: time in seconds or open
Target type: heart rate

For heart rate value, see this section in 
8.1.3.6 Setting Power and Heart Rate Values in https://github.com/julianoalberto/wiki/blob/master/D00001309%20FIT%20File%20Types%20Description%20Rev%202.2.pdf

Repeat

## Input format choices:
YAML with jinja2 templating
CSV


## Heart rate zones
In all files, define heart rate as zones. 
Store zones in table. 
When changed, update the table and regenerate all workouts.

**Use zone color conventions from MTS**

N1.green = 135
N1.red = 140
N1.black = 145
N2.green = 155
N2.red = 150
N2.black = 155
N3.green = 155
N3.red = 160
N3.black = 165
N4.green = 165
N4.red = 170
N4.black = 175
N5.green = 175
N5.red = 180
N5.black = 185
N6.green = 185
N6.red = 190
N6.black = 195

ZoneFactory.getZone()


# Instructions format
## Workout information
name "B1"
comments "detailed information about workout"

## Steps
45m N1.green "85 RPM"

## Repeat
repeat previous X steps Y times

## Example
workout name "B1"
45m N1.black "85 RPM NX UP"

30s N5.black "N5"
30s N1.black "N1"
repeat previous 2 steps 7 times

# Workout serial strategy
Workout serial number must be unique within device.

## Strategy
Create long from file name.

1. Strip suffix and extension
2. Convert to upper case
3. For each character:

    1. Get integer representing position in alphabet
    2. Concatenate

3. Parse concatenated string to long

# Workout file name format


## Valid characters

* [A-Z]
* [0-9]
* -