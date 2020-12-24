EXEC = competition
TEST = java -jar test4poo.jar

all: classes doc exe competition extract compTest test

doc:
	cd src;javadoc -d ../docs -sourcepath . -subpackages core_match_classique util;

classes:
	rm -rf classes;mkdir classes

comp: classes
	cd src;javac core_match_classique/*.java util/*.java -d ../classes;

compTest: comp
	javac -cp test4poo.jar:src/ test/*.java;
    # javac -classpath test4poo.jar test/*/*/*.java;

test: compTest
	$(TEST) LeagueTest
	$(TEST) MatchTest
	$(TEST) CompetitorTest
	$(TEST) CompetitionTest
	$(TEST) TournamentsTest
	$(TEST) UnautorizedPlayersMatchExceptionTest
	$(TEST) MasterTest
	$(TEST) ObserverTest


competition:
	java -jar appli.jar
	# Sans le fichier jar : 
	# cd classes;java core_match_classique.main -classpath /core_match_classique/*.class /util/*.class;

exe: comp
	cd classes;jar cvf ../appli.jar core_match_classique/*.class util/*.class;
	cd classes;jar cmf ../manifest.mf ../appli.jar core_match_classique/*.class util/*.class

extract:
	mkdir temp;cd temp;jar xvf ../appli.jar

clean:
	rm -rf classes docs test/*.class test/*/*.class test/*/*/*.class temp/META-INF temp/core_match_classique temp/util temp/

.PHONY: clean extract exe test compTest comp classes doc all competition
