#Adaptation of Makefiles from various authors.
.SUFFIXES: .java .class

PKG = main/ \

BIN = ./bin/

LIBS = ./libs/*

SRC = ./src/

JFLAGS = -g -d $(BIN) -cp $(SRC):$(LIBS)

JC = javac

COMPILE = $(JC) $(JFLAGS)

EMPTY = 

SOURCE = $(subst $(SRC), $(EMPTY), $(wildcard $(SRC)*.java))

ifdef PKG
	PACKAGEDIRS = $(addprefix $(SRC), $(PKG))
	PACKAGEFILES = $(subst $(SRC),$(EMPTY),$(foreach DIR, $(PACKAGEDIRS), $(wildcard $(DIR)/*.java)))
	ALL_FILES = $(PACKAGEFILES) $(SOURCE)
else
	ALL_FILES = $(SOURCE)
endif

CLASS_Files = $(ALL_FILES:.java=.class)

all: $(addprefix $(BIN), $(CLASS_Files))

$(BIN)%.class: $(SRC)%.java
	$(COMPILE) $<

docs:
	javadoc -d ./doc/ $$(find -name *java)
	
clean:
	rm -rf $(BIN)*.class

run:
	java -cp $(BIN):$(LIBS) ToCTeditor
