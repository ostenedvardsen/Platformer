# Oblig 4 - "Prosjekt plattformspill"
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

I den forrige sprinten var fokuset på å være tydeligere med arbeidsoppgaver ved bruk av Trello-brettet. Vi hadde også kommet oss gjennom alle MVP-kravene, så fokuset i denne sprinten har vært å jobbe gjennom oppgavene på brettet.

Kommunkasjonen i teamet fungerer veldig godt. Vi har ikke hatt like mange møter i denne sprinten, men vi er komfortable med hverandre og har blitt gode på å spille på hverandres sterke sider. Vi diskuterte arbeidsoppgavene tydelig og fikk dem opp på arbeidstavlen. Så tok man en relevant arbeidsoppgave på tavlen, markerte den som sin, satte i gang, og tok kontakt med de andre når vi møtte på problemer. 

*For siste innlevering (Oblig 4): Gjør et retrospektiv hvor dere vurderer prosjektet har gått. Hva har dere gjort bra, hva hadde dere gjort annerledes hvis dere begynte på nytt?*
Vi startet prosjektet godt med godt oppmøte på de standard gruppetimene, og på ekstra møter. Det ga oss et godt grunnlag for å komme med innspill om hvordan prosjektet skulle se ut, hvordan vi skulle jobbe, og å bli kjent med hverandre. Vi lærte fort var at det var viktig å jobbe godt mellom møtene, slik at møtetiden ble produktiv brukt på å planlegge og diskutere problemer som oppstod utenom møtene. Inndelingen av roller fungerte godt. Alle fikk lede i den avdelingen de var sterkest i og kunne alltid spørre de andre om hjelp når arbeidsmengden innenfor et område ble for stor. Bruken av git har vært et utvikling-område i løpet av prosjektet. Om man ser på starten av prosjektet så ble det gjort store omveltninger i løpet av veldig få commits, og commit-meldingene var ikke like gode som de er nå. Branches, commits og relevante commit-meldinger er noe vi har blitt mye bedre på. Bruken av Trello-arbeidstavlen var definitivt et område vi var trege på å utvikle oss innen. Det tok lang tid før vi virkelig brukte det med tydelige arbeidsoppgaver og tildelte oppgavene til medlemmer. Å virkelig bruke arbeidstavlen var veldig gunstig i de siste to sprintene, og spesielt i den siste. Møtereferater var også noe vi manglet i starten, men som var til stor hjelp da vi mot slutten av prosjektet hadde flere møter hvor alle ikke kunne stille. Møtereferatene i kombinasjon med arbeidstavlen gjorde det enklere å få oversikt hva som hadde blitt diskutert, hva som skulle bli gjort, og om man eventuelt hadde kommet frem til at ting ikke skulle bli gjort på en viss måte. 

Det vi ville ha gjort annerledes fra starten av er bruken av arbeidstavlen, møtereferater, og å jobbe utenom møtene. Git er noe vi kunne ha vært bedre på fra starten av, men på starten av prosjektet så var vi ikke like kompetente med git som vi er nå. Å jobbe utenom møtene tidlig i prosjektet ville ha gjort det mye enklere for oss å diskutere problemene vi møtte, istedenfor å støte på dem i løpet av møtet. I starten følte vi ikke at vi visste helt hvor vi skulle begynne, så vi mistet nok en uke eller to på å ikke jobbe utenfor møter, og å så gå sakte frem på møtene. Dette løste vi mot slutten av første sprint ved å ta ett langt møte hvor vi jobbet sammen hele gruppen for å få en grunnleggende kode som alle kunne jobbe med. Om vi hadde tatt denne lange kodeøkten med en gang så ville vi ha fått gjort mye mer i løpet av den første sprinten. Bruken av arbeidstavlen og møtereferater ville ha gitt alle mye mer oversikt, slik at flere ikke jobbet med det samme unødvendig. 


## Deloppgave 2: krav
### «Stretch goal»
Stretch goalet vårt var multiplayer på samme maskin, og eventuelt multiplayer over LAN om vi fikk ekstra med tid. Vi endte opp med multiplayer på samme maskin. Vi vil si at implementeringen av flerspiller fungerte veldig godt. Vi skrev modulær kode, slik at vi hovedsakelig bare måtte legge til flere instanser av Player-objektet for å legge til multiplayer. Siden planen var å legge til flere spillere så var den relevante dataen om spilleren ofte lagret i lister, slik at det var enkelt å legge til flere. 


### MVP og annet
""*Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.* <br> Vi har hatt fokus på alle de gjenværende kravene MVP7-MVP11. Siden forrige gang har vi har fullført MVP7, MVP10 og MVP11. Det som nå gjenstår er MVP8 og MVP9. ""
Til forrige sprint hadde vi fullført alle MVP-kravene. I den sprinten var fokuset på å være tydeligere med arbeidsoppgaver ved bruk av Trello-brettet. Fokuset i denne sprinten var dermed å jobbe gjennom oppgavene på brettet. Oppgavene har blitt prioritert etter hvor mye ekstra funksjonalitet hver oppgave vil gi, og hvor mye enklere oppgaven ville gjøre det å legge til mer funksjonalitet. For eksempel så ble CollisionHandling prioritert i starten av sprinten, slik at det gikk fort å legge til nye fiender og entities mot slutten av sprinten. Mer spesifikt så har vi jobbet med kollisjoner, fiender, interaksjon mellom spiller og terreng, gamefeel, animasjoner og testing. 


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
Arbeidsoppgaver: lage en .jar fil som er lett tilgjengelig.


<b>Brukerhistorier </b> <br>

Som spiller trenger jeg utfordringen ved at spilleren min dør om jeg interagerer med fiendtlige enheter eller faller utfor spillbrettet.
<br><b>Akseptansekriterier</b>: Spilleren og fiendtlige gjenstander har egen Collidable Rectangle som returnerer True når de interagerer med hverandre. Spilleren skal forsvinne fra kartet når spilleren dør.
<br><b>Arbeidsoppgaver</b>: Implementere Healthpoints for Entities, og en metode som fjerner Entity når Healthpoints <= 0.
<br>Brukerhistorien oppfyller MVP-krav 7.


*Forklar kort hvordan dere har prioritert oppgavene fremover*
Prosjektet er over, men ved videre utvikling ville vi nok ha prioritert å legge til flere fiender, baner og tiles. Spillet er nå et godt grunnlag for videre utvikling. 


### Deloppgave 3
Klassediagram:
![Klassediagram](/Deliverables/klassediagramObli4.png " Klassediagram")

