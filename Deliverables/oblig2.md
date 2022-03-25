# Oblig 2 - "Prosjekt plattformspill"
### Deloppgave 1 (uke 11): Team og Prosjekt
Team: *Mutual-glue* (Gruppe 8): *Thomas Sjåstad, Anton Rønneberg, André Kvalvik, Østen Edvardsen*

## Deloppgave 1: Team og prosjekt
### Møte: 15.03
### Deltakelse: Anton Rønneberg, Andrè Kvalvik, Østen Edvarsen
I dette møtet var fokuset på å generalisere hvordan vi legger til entities på spillbrettet og fysikken på alle entities, altså hovedsakelig å legge rammen for MVP 6 (Vise fiender/monstre; de skal interagere med terreng og spiller). Branchen til MVP4 ble merget med MVP 6. Til neste møte skal det utvikles en EntityFactory som lar oss lese av navnene til object-layerene på .tmx-kartet og automatisk legge til disse entitiene på spillkartet. Entities inkludere ting som player, fiender og coins. Fysikken for entities skal også generaliseres og implementeres på de entitiene som skal påvirkes av fysikk. 

### Møte: 15.03
### Deltakelse: Anton Rønneberg, Andrè Kvalvik, Østen Edvarsen, Thomas Sjåstad



### Oppsummering siden oblig1
Rollene i teamet fungerer greit. Vi har ennå ikke støtt på noen problemer med fordelingen. 

Vi opplever ikke at vi trenger noen flere roller. Team lead har hovedansvar for å organisere møter, hvor langt vi er prosjektet, og hvilke arbeidsoppgaver alle jobber med, slik at alle er på samme bølgelengde. Prosjekt lead holder øye på kode og hva som må gjort, og hvordan vi skriver mer generell og oversiktlig kode. Testansvarlig har så langt visuelt testet ting, men fremover vil det innebære JUnit testing og statistiske analyseverktøy som SpotBugs. Sekretær innebærer å ha hovedansvar for møterapporter og skriving av obligtekster. Gitansvarlig innebærer ansvar for git-repoet med merging og branches. Kundekontakt kommuniserer med gruppeledere og foreleser. 

Kunne hatt bedre kontakt underveis med hvem som har holdt på med hva. De første ukene etter oblig1 hadde vi ikke like mye oversikt som vi har nå. Det vi kan gjøre annerledes er å være tydeligere med arbeidsoppgaver. Under møtet 15.03 delte vi inn arbeidsoppgaver veldig tydelig, og det ga gode resultater. 

Gruppedynamikken og kommunikasjon fungerer bra. Vi møter opp på tirsdagsmøtet hver uke, kommuniserer godt over discord, og har av og til ekstra møter på torsdager. 

Det vi har klart til nå er å kommunisere godt på gruppemøter. Det vi kan forbedre er å bruke Trello-boardet mer, for å gi hverandre bedre oversikt over hva vi jobber med. 

Det har visst vært et problem med Østens git, så commitene med "no" som author er hans. Feilen er nå fikset. 

Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.
Til neste spring skal vi forbedre oss på å ha tydelige arbeidsoppgaver, og mer bruk av Trello. 


## Deloppgave 2: krav
### «Stretch goal»
Stretch goalet vårt er lokal multiplayer. 

### MVP og annet
Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
Vi har prioritert MVP i rekkefølge, så vi har fullført MVP5 og MVP6. 


For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester

Format for brukerhistorier:
Som rolle trenger jeg funksjonalitet for å oppnå nytteverdi.
Akseptansekriterier:
Arbeidsoppgaver:
Brukerhistorien oppfyller krav …

Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie, akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også.


Forklar kort hvordan dere har prioritert oppgavene fremover
Vi liker rekkefølgen fra MVP-oversikten i oblig1, så vi følger den. Vi har ikke gjort noen justeringer på kravene som er med i MVP. 


Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.
Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
Til oblig1 fullført vi krav 1-4, og vi har nå har prioritert og fullført krav MVP5 og MVP6. 
Kjente bugs: Om man endrer på skjermstørrelsen etter spillstart blir entities med tyngdekraft sendt ut av banen. 

Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige.

*Brukerhistorier* 

Som spiller trenger jeg å kunne ha oversikt over poengsum, og interagerer med poenggjenstander som øker poengsummen.
Akseptansekriterier:
Spilleren og poenggjenstander har en collidable rectange (CollisionRect.java), som returnerer True når de interagerer med hverandre. En HUD(Heads Up Display) skal vise poengesummen når spille kjører og skal kunne forandres når en spiller og poenggjenstand kolliderer.
Arbeidsoppgaver:
Implementere CollisionRect som i prinsippe er en boks på størrelse med entity, og implementere collidesWith() metode som sjekker vektoren til CollisionRect i forhold til mape. når vektorene til to gjenstander overlapper hverandre, blir de definert som en kollisjon.
Implementere HUD med en _stage_ på størrelse med spillskjermen, der vi legger til _labels_ øverst på stage, og blir endret på når collidesWith returnerer True.
Brukerhistorien oppfyller MVP-krav 5.

Som spiller trenger jeg at karakteren min interagerer med fiendtlige enheter som viser på spillbrettet. Når karakteren min interagerer med en fiendtlig gjenstand, taper jeg poeng.
Akseptansekriterier:
Spilleren og fiendtlige gjenstander har begge en collidable rectange (CollisionRect.java), som returnerer True når de interagerer med hverandre. HUD som ble implementert i MVP5 reduserer poengsummen når collidesWith() returnerer True.
Arbeidsoppgaver:
Implementere Skeleton.java som en ny _Entity_. En ny metode for Entitys, _playerInteract()_, returnerer True og en condition når en entity kolliderer med karakteren. _playerInteract()_ er en abstract metode i Entity som alle Entity bruker.
Brukerhistorien oppfyller MVP-krav 6.

### Deloppgave 3
Klassediagram:

Tester funksjonalitet i linux: 


