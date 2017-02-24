# HIPLUNCH #

[![Build Status](https://travis-ci.com/soikea/hiplunch.svg?token=BwvYvsK33EqYoqbDsVYm&branch=master)](https://travis-ci.com/soikea/hiplunch)

A simple Java app for parsing RSS or JSON sources for lunch menus and posting them to a Hipchat channel.

Mainly useful for Mattilanniemi area at Jyväskylä, Finland, as the feed parsing is feed specific (no real deterministic approach) At the moment parses Sonaatti lunch cafeterias and Sodexo Mattilanniemi feeds.

## Build ##
```
penni ~/projects/hiplunch $ gradle build
```

## Usage ##
```
penni ~/projects/hiplunch $ java -jar build/libs/hiplunch.jar
Usage:
	java -jar hiplunch.jar <provider...|default|help>

Providers:
mattilanniemi	- Sodexo Mattilanniemi
bittipannu	- Sodexo Bittipannu
piato	- Sonaatti Piato
wilhelmiina	- Sonaatti Wilhelmiina
fiilu	- Fazer Fiilu
nurkka	- Lutakon Nurkka
trattoria	- Trattoria Aukio
qulkuri	- Le Qulkuri Lutakko

Default providers (run with argument "default"):
	bittipannu fiilu nurkka trattoria qulkuri
```
## Providers ##

Included providers, see list above or run 'help'
Adding a provider: see commit https://github.com/soikea/hiplunch/commit/06038c1ecf47a5514dfc61d9fbf17b47b68e73d8


## TODO: ##

- Move hipchat channel key and id to external config
- Add MORE providers!
- Course tags and formatting (VH, L, G...)?
- Clean up code
