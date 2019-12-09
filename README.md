# HIPLUNCH #

This is a simple Java app for parsing RSS or JSON sources for lunch menus and posting them to a Hipchat or Teams channel. Purpose for this is training and refining Java skills, attempting different methods for parsing web data and transforming it to another channel.

At the moment, mainly useful for usage around Jyväskylä, Finland, as the parsing is feed specific, and no menus elsewhere have been added yet.

## Build ##
```
penni ~/projects/hiplunch $ gradle build
```

Or use a prebuilt jar from Releases

## Usage ##
```
penni ~/projects/hiplunch $ java -jar build/libs/hiplunch.jar help
Usage:
	java -jar hiplunch.jar <provider...|default|help>

Providers:
mattilanniemi	- Sodexo Mattilanniemi
bittipannu	- Sodexo Bittipannu
piato	- Semma Piato
maija	- Semma Maija
fiilu	- Fazer Fiilu
nurkka	- Lutakon Nurkka
trattoria	- Trattoria Aukio
qulkuri	- Le Qulkuri Lutakko
sodexo-ge	- GE Healthcare

Default providers (run with argument "default"):
	bittipannu fiilu nurkka trattoria qulkuri sodexo-ge

To enable sending messages, you need to add two vars either to system Env or as -D properties to the command, eg:
	java -DHIPCHAT_ROOM=***** -DHIPCHAT_TOKEN=******* -jar build/libs/hiplunch.jar bittipannu
or	
	java -DTEAMS_HOOK=***** -jar build/libs/hiplunch.jar bittipannu qulkuri
```

## Providers ##

Included providers, see list above or run 'help'
Adding a provider: see commit https://github.com/pennifi/hiplunch/commit/06038c1ecf47a5514dfc61d9fbf17b47b68e73d8

## TODO: ##

- Add MORE providers!
- Course tags and formatting (VH, L, G...)?
- Clean up code
