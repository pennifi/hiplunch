# README #

A simple Java app for parsing RSS or JSON sources for lunch menus and posting them to a Hipchat channel.

Mainly useful for Mattilanniemi area at Jyväskylä, Finland, as the feed parsing is feed specific (no real deterministic approach) At the moment parses Sonaatti lunch cafeterias and Sodexo Mattilanniemi feeds.

## Usage ##
```
penni ~/projects/hiplunch $ java -jar target/hiplunch-2.0.0-jar-with-dependencies.jar help
Usage:
	java -jar hiplunch.jar <provider...|default|help>

Providers:
mattilanniemi	- Sodexo Mattilanniemi
dynamo	- Sodexo Dynamo
piato	- Sonaatti Piato
wilhelmiina	- Sonaatti Wilhelmiina
fiilu	- Fazer Fiilu
nurkka	- Lutakon Nurkka
trattoria	- Trattoria Aukio

Default providers (run with argument "default"):
	dynamo fiilu nurkka trattoria
```


## Providers ##

- Sonaatti
-- All locations (just add provider)
-- Piato
-- Wilhelmiina
- Sodexo
-- All locations (just add provider)
-- Mattilanniemi
-- Dynamo
- Fazer
-- Fiilu

## TODO: ##

- Move hipchat channel key and id to external config
- Add providers for Lutakko area restaurants
- Parametrize which menus should be ran (empty= all?) 
- Course tags and formatting (VH, L, G...)?
- Clean up code