# Oblig 3 - "Prosjekt plattformspill"
## Deloppgave 1: Team og prosjekt
Team: *Mutual-glue* (Gruppe 8): *Thomas Sjåstad, Anton Rønneberg, Andrè Kvalvik, Østen Edvardsen*

### Møte: 19.03
### Deltakelse: Thomas Sjåstad, Østen Edvardsen
Andrè lå syk og Anton hadde ikke mulighet til å stille. Det ble derfor ikke noe møte denne uken, men Østen var innom gruppetimen for å snakke litt med gruppeleder. På Trello har vi tydelige arbeidsoppgaver for hva som gjenstår før innlevering, vi vet nå godt hvordan hverandre jobber, og vi kommuniserer godt over discord, så ting fungerte godt uten et standard møte. Anton skal fokusere se på bakgrunn og animasjoner, Østen fokuserer på kollisjoner og entities, Andrè fokuserer på tester og grafikk, og Thomas fokuserer på menyer, game over-skjerm og brukerhistorier. 

### Møte: 26.03
### Deltakelse: Anton Rønneberg, Andrè Kvalvik (digitalt), Østen Edvardsen
I dette møtet la vi frem hva vi hadde gjort uken før, og tydeliggjorde hva alle skulle drive med frem til fristen. Slik som i forrige uke så jobber vi nå komfortablet om hverandre, og kommuniserer godt over discord. Vi snakket med gruppeleder om å lage .jar-fil. 

### Oppsummering siden oblig2
Rollene i teamet har fungert godt siden forrige sprint. Vi jobber nå veldig komfortabelt med hverandre. Vi har god kontroll på hvert av våre områder, og hjelper de andre der det trengs. Vi har lagt til en til rolle, som er grafisk designer. All kunsten i spillet vårt er egenutviklet av Andrè (utenom menyen som er laget av Østen og Thomas). Her er litt gjentagelse fra forrige innlevering om hva de forskjellige rollene betyr for oss: "Team lead Thomas holder god oversikt på prosjektet, prosjekt lead Anton har god oversikt over kodebasen og hva som må utbedres. Sektretær, git-ansvarlig og kundekontakt Østen skriver referaterer og innleveringer, og kontrollerer giten." Siden sist sprint har Andrè ordnet JUnit-tester. 

I den forrige sprinten var fokuset på å være tydeligere med arbeidsoppgaver ved bruk av Trello-brettet. Vi hadde også kommet oss gjennom alle MVP-kravene, så fokuset i denne sprinten har vært å jobbe gjennom oppgavene på brettet. Oppgavene var skrevet slik at vi har 

Kommunkasjonen i teamet fungerer veldig godt. Vi har ikke hatt like mange møter i denne sprinten, men vi er komfortable med hverandre og har blitt gode på å spille på hverandres styrker. 


*For siste innlevering (Oblig 4): Gjør et retrospektiv hvor dere vurderer prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt?*

Retroperspektiv: 
Ble bedre på git commits
Var gode på møter tidlig i løpet, som gjorde oss mer komfortable med hverandre
Viktig å komme godt forberedt til møter, både fysisk, psykisk og faglig. 

Vi burde ha fokusert mer på Trello-tavla fra tidlig av
Møtereferater ville gjort det enklere for alle å henge med, spesielt når man gikk gklipp av et møte

## Deloppgave 2: krav
### «Stretch goal»

""Stretch goalet vårt er lokal multiplayer. Det var en god idé å ha det i bakhodet fra tidlig av. Det førte til at vi skrev mer generell og modulær kode, som ga oss muligheten til å enkelt implementere multiplayer over én klient allerede nå. Dette er en veldig enkel multiplayer, men den viser at funksjonaliteten vi har til nå fungerer godt med multiplayer. Om vi får tid vil stretch goalet bli en multiplayer over LAN. ""


### MVP og annet
""*Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.* <br> Vi har hatt fokus på alle de gjenværende kravene MVP7-MVP11. Siden forrige gang har vi har fullført MVP7, MVP10 og MVP11. Det som nå gjenstår er MVP8 og MVP9. ""


<b>Brukerhistorier </b> <br>

Som spiller trenger jeg en meny for å velge antall spillere, for å oppnå riktige innstillinger for hvor mange spillere som skal være med i spillet.
Akseptansekriterier: Når jeg trykker på enten en knapp ved navn 1 player, 2 players, 3 players eller 4 players, så starter spille med tilsvarende antall spillere.
Arbeidsoppgaver: Lage en ny Klasse som representerer en PlayerMenuScreen. Designe knappene, og få lagt de inn i assets. Knappene må implementeres slik at antall spillere starter etter hva knappen viser. 


Som spiller trenger jeg et siffer på skjermen som viser livene til karakteren min, for å oppnå oversikt over hvor mye liv karakteren min har for en bedre spillopplevelse. 
Akseptansekriterier: HP viser i Headsup Displayet på skjermen.
Arbeidsoppgaver: Legge inn HP og en metode som henter spillerens nåværende HP som blir oppdatert på samme måte som Score blir oppdatert i Labelen.

Som spiller trenger jeg en «game over» meny eller lignende, for å oppnå å enklest mulig kunne gå tilbake til main menu hvis jeg taper eller spillet er ferdig.
Akseptansekriterier: Etter spillet er ferdig, kommer det opp en melding som viser hvilken knapp en skal trykke på for å gå tilbake til Main Menu. Når knappen trykkes på, returnerer spille til Main Menu.
Arbeidsoppgaver: Legg til en egen label for Game over og beskjeden i Hud. 


Som spiller trenger jeg en enkel fil for å starte spille, for å oppnå enkel oppstart av spillet uten å bruke IDE.
Akseptansekriterier: ved å starte en enkelt fil i filutforsker, så starter spillet.
Arbeidsoppgaver: lage en .jar fil, som deretter blir starter i cmd i en .bat fil.


<b>Brukerhistorier </b> <br>

Som spiller trenger jeg utfordringen ved at spilleren min dør om jeg interagerer med fiendtlige enheter eller faller utfor spillbrettet.
<br><b>Akseptansekriterier</b>: Spilleren og fiendtlige gjenstander har egen Collidable Rectangle som returnerer True når de interagerer med hverandre. Spilleren skal forsvinne fra kartet når spilleren dør.
<br><b>Arbeidsoppgaver</b>: Implementere Healthpoints for Entities, og en metode som fjerner Entity når Healthpoints <= 0.
<br>Brukerhistorien oppfyller MVP-krav 7.


*Forklar kort hvordan dere har prioritert oppgavene fremover*
""Det som nå gjenstår er MVP8 og MVP9. Vi har god kontroll på disse. Målet for brettet skal være å ta på "goalet" på slutten av banen, og så skal det komme et nytt brett. MVP8 og MVP9 henger derfor veldig sammen. TiledGameMap-objektet vårt får all informasjon om hvert brett gjennom dets .tmx-fil. Det skal derfor være en enkel prosess å få TileGameMap-objektet til å laste et nytt brett.""

*Forklar kort hvordan dere har prioritert oppgavene fremover*
Til oblig2 hadde vi fullført krav 1-6, og vi har nå har prioritert og fullført krav 7, 10 og 11. 


### Deloppgave 3
Klassediagram:
![Klassediagram](/Deliverables/klassediagramObli4.png " Klassediagram")

