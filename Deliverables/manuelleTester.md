# Manuelle tester

I denne fila forklarer vi noen manuelle tester som blir gjort til klasser som krever input fra bruker, 
(klasser under screens) eller krever en umocket gameMap. Spesifikt GameMap får vi en error grunnet shader rendering
som vi ikke har klart å finne ut av.

##MainMenuScreen

textures med bakgrunn, tittel, play og exit knapp dukker opp. 
Om du peker musa over en av knappene kommer mouseHover(button) til å bli true, 
og knappen endrer texture.

Man kan endre størrelse på programmet uten at det ødelegger for knappene sin hover plassering. De er alltid
relativt til størrelsen av programmet. Når man trykker museknappen vil programmet sjekke om musepekeren
er oppå en av knappene og derretter gjøre funksjonen til knappen. Quit avslutter programmet og play
endrer til neste skjerm: PlayerMenuScreen.

##PlayerMenuScreen

Samme som MainMenuScreen, bare med 5 knapper i forskjellig posisjon på skjermen. Når spiller antall
har blitt valgt, endrer skjermen til ActiveGameScreen der spiller antallet er antall spillere som
blir med i spillet.

##ActiveGameScreen

Hovudfunksjonen til ActiveGameScreen er å vise alt som trengst på skjermen.
Ettersom vi har lokal multiplayer i spillet vårt, bør alle spillere ha kameraet over seg.
Dette blir gjort med funksjonen CameraFollowPlayer() som zoomer ut og inn for å holde alle spillere
innenfor skjermen, og slik at spillerene også kan se hva som kommer til å skje på game mappen.

Om spillere beveger seg fra hverandre zoomer kameraet ut.
Om spillere dør beveger kameraet seg bare til de levende spillerene. 

##EntityFactory

EntityFactory blir brukt i GameMap for å lage alle entitiene som skal vere på mappen. Player,
Goal og Skeleton er eksempel på hva entityFactory lager. EntityFactory baserer seg på pointer posisjoner
til en objectLayer i Tiled. Om EntityFactory er i en layer finner den alle posisjonene til pointerene
og plasserer tilsvarende Entity der.

Om vi plaserer en pointer til f.eks Coin, vil en coin bli plassert på tilsvarende plass.

##ColissionHandling

Sjekker plassering til entity i forhold til andre entities. Om entities går i hverandre blir de stoppet.
Om en entity hopper på en annen entity blir den sendt opp med litt fart.

##GameMap/TiledGameMap

TiledGameMap holder alle .tmx filene, og går til neste map når Goal klassen sin interact funksjon
blir kallet på.  Når en spiller går bort til Goal på map0 blir mappen endret til map1.
Når siste map er ferdig blir map endret tilbake til 0 (En congratulations eller lignende
for å gjøre ferdig siste map er ikke ferdig implementert enda). 

TiledGameMap fjerner alle entities som er døde, om en spiller eller entity detter av mappen (hp blir =< 0)
fjerner TiledGameMap tilsvarende entity. 

GetTileTypeByCoordinate sjekker at alle de rette typene av tiles er på sine plasser i følge .tmx fila.
Den bruker et id system der alle tilene på tilesettet får sin egen id og derfor kan ha forskjellige
funksjoner. Om spiller er borti spike blir spiller damaget. Samme med lava. Spiller kan også dette gjennom
noen av tilene som ikke har kollisjoner, f.eks "void"-tilen.

Om player er over fiendtlige entities blir player "attacker" og kan skade eller drepe entitien.
Players kan også gjøre dette mot hverandre. I spillet blir player skadet av alle fiendtlige
entities som ikke blir hoppet på. Skaden = Entity sin damage value.

##Cannon / CannonBall

En entity som fokuserer på nermeste player. Kanonen har 5 retninger den kan skyte i basert på hvor den
nærmeste playeren er. Kanonen skyter en kanonball i sin nåverende retning etter et interval (bombtimer). 

CanonBall beveger seg etter gravitasjon til den treffer en entity, en tile eller går under map. Om dette
skjer blir kanonballen fjernet fra map.

