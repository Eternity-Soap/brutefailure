# Brutefailure
Low effort java program that attempts to brute force a dormant btc address. Requires bitcoinj to compile. The program uses a lexicographically sorted list of 42525 dormant bitcoin addresses with a balance and generates new ones until either a public key match is found or the user stops the program. If a match is found, the program outputs the public and private key (wallet import format) to a text file called luckynumbers.txt. Program must be run in the same directory as the alphabetized btc txt file. There is absolutely no validation, so if the file is meddled with or not run right it will fail. Run from the command line using `java -jar brutefailure.jar`. Obviously requires java to run.

Know that you are probably much more likely to have your computer spontaneously explode and spit out winning lottery numbers for every lottery for the rest of time than get a collision. Have fun. 

Because this is a crypto currency related program, I am obligated to list a donation address:
13Z1Kyoh5Xb7Z863jArHT8bwyFkEcHSZgQ
