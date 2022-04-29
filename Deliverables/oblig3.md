# Oblig 3 - "Prosjekt plattformspill"
## Deloppgave 1: Team og prosjekt
Team: *Mutual-glue* (Gruppe 8): *Thomas Sjåstad, Anton Rønneberg, André Kvalvik, Østen Edvardsen*

### Møte: 29.03
### Deltakelse: Anton Rønneberg, Andrè Kvalvik, Østen Edvarsen, Thomas Sjåstad
I dette møtet var fokuset på å forbedre Trello-boardet vår. Vi gjorde den mer oversiktlig og enklere å jobbe med. Vi renset så opp i entity-koden fra oblig 2 for å tilrettelegge for interaksjoner mellom entities. 

### Møte: 05.04
### Deltakelse: Anton Rønneberg, Andrè Kvalvik, Østen Edvarsen, Thomas Sjåstad
I dette møtet jobbet vi mest med å legge til multiplayer funksjoner. Vi jobbet også med Game-klassen for å tilrettelegge for implementasjonen av en Main Menu- og Game Over-skjerm.

### Oppsummering siden oblig2
Mesteparten av efaringene våre siden oblig2 er like som de vi hadde siden oblig1. Det har kun vært 2 uker siden forrige oblig, så det kan være en faktor. 

Rollene i teamet fungerer godt. Vi har ennå ikke støtt på noen problemer med fordelingen. Vi opplever ikke at vi trenger noen flere roller. Team lead Thomas holder god oversikt på prosjektet, prosjekt lead Anton har god oversikt over kodebasen og hva som må utbedres, sektretær, git-ansvarlig, kundekontakt og skriver referaterer og innleveringer, og kontrollerer giten. Andrè har jobbet godt med å utvikle tester, men har støtt på en del problemer. Han har allikevel best kontroll på tester av alle på gruppa, så han skal holde stillingen, men få mer støtte med det fremober. Test-utvikling vil være et stort fokus til oblig4. 

I denne sprinten var det fokus på være tydeligere med arbeidsoppgaver. Det har vi fått til gjennom Trello-boardet og god kommunikasjon på møter. 

Gruppedynamikken og kommunikasjon fungerer bra. Vi møter opp på tirsdagsmøtet hver uke, kommuniserer godt over discord, og har av og til ekstra møter på torsdager. 

Forbedringspunktet til neste sprint er å dra mer nytte av hjelp fra gruppeledere og forelesere, slik at vi kan komme i gang med fungerende tester. 

## Deloppgave 2: krav
### «Stretch goal»
Stretch goalet vårt er lokal multiplayer. Det var en god idé å ha det i bakhodet fra tidlig av. Det førte til at vi skrev mer generell og modulær kode, som ga oss muligheten til å enkelt implementere multiplayer over én klient allerede nå. Dette er en veldig enkel multiplayer, men den viser at funksjonaliteten vi har til nå fungerer godt med multiplayer. Om vi får tid vil stretch goalet bli en multiplayer over LAN. 

### MVP og annet
*Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.* <br> Vi har hatt fokus på og fullført alle de gjenværende kravene MVP7-MVP11.  

<b>Brukerhistorier </b> <br>

Som spiller trenger jeg utfordringen ved at spilleren min dør om jeg interagerer med fiendtlige enheter eller faller utfor spillbrettet.
<br><b>Akseptansekriterier</b>: Spilleren og fiendtlige gjenstander har egen Collidable Rectangle som returnerer True når de interagerer med hverandre. Spilleren skal forsvinne fra kartet når spilleren dør.
<br><b>Arbeidsoppgaver</b>: Implementere Healthpoints for Entities, og en metode som fjerner Entity når Healthpoints <= 0.
<br>Brukerhistorien oppfyller MVP-krav 7.


Som spiller trenger jeg en meny der jeg enkelt kan velge når jeg vil starte selve spille etter å ha åpnet det.
<br><b>Akseptansekriterier</b>: En knapp for å kunne starte spille som sender meg til det første brettet og en knapp til å kunne avslutte spillet.
<br><b>Arbeidsoppgaver</b>: Lage en klasse som representerer Main Menu, og implementere interaktive knapper som sender deg til spillbrettet eller avslutter spillet.
<br>Brukerhistorien oppfyller MVP-krav 10.


Som spiller vil jeg kunne spille sammen med flere spillere.
<br><b>Akseptansekriterier</b>: Flere spillere dukker opp på kartet og kan kunne styres med andre kontroller.
<br><b>Arbeidsoppgaver</b>: Legge inn en variabel som bestemmer hvor mange spillere som skal settes på map. Spesifisere en ID for hver spiller, hvor hver ID er gitt sine egne kontrollere.
<br>Brukerhistorien oppfyller MVP-krav 11.


*Forklar kort hvordan dere har prioritert oppgavene fremover*
Vi har nå funksjonalitet på alle kravene. Planen frem til oblig4 er å jobbe med tester og å legge til mer funksjonalitet innenfor hvert krav. Vi skal legge til flere fiender, flere baner, en game-over skjerm, og en skjerm som viser kontrollene. Vi skal også gi player evnen til å skade fiender. Om vi har tid så skal vi legge til multiplayer over LAN. 

Til oblig2 hadde vi fullført krav 1-6, og vi har nå har prioritert og fullført resten av MVP-kravene. 

### Deloppgave 3
Klassediagram:
![Klassediagram](/Deliverables/klassediagramOblig3.png " Klassediagram")

