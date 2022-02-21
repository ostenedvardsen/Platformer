# Obligatorisk oppgave 1: Prosjekt Plattformspill – oppstart
### Deloppgave 1 (uke 6): Organiser teamet
Gruppenavn: <b>Mutual-glue</b> <br>
Alle fire har erfaring med programmering fra INF100, INF101 og INF102. Anton har erfaring med spillutvikling fra folkehøyskole. Det ble gjort i en game engine, så det blir nytt å skrive direkte i java.

Fordeling av roller for obligatorisk oppgave 1, med begrunnelse:
<br><b> Team lead: Thomas Sjåstad</b>. Har vært den som har tatt mest initiativ i gruppa og foreslått møter.
<br><b> Prosjekt lead: Anton Rønneberg</b>. Den med mest erfaring innen spillutvikling.
<br><b> Hovedansvarlig for tester: André Kvalvik</b>. Mest erfaring innen testing.
<br><b>Sekretær, gitansvarlig og kundekontakt: Østen Edvardsen</b>. Har skrevet referat fra tidligere gruppetimer og kommunisert mest med gruppelederne. 

### Deloppgave 2 (uke 6): Velg og tilpass en prosess for laget
I første omgang er planen å bruke elementer fra scrum og ekstrem programmering. 

Scrum blir rammeverket for å organisere og planlegge gjøremål for å sørge for at vi alltid jobber mot å få et fungerende produkt. Gjennom Otrello har vi satt opp en scrum-tavle som er delt inn i "gjøremål", "pågår", "testes", og "ferdig". Arbeidsoppgavene i "gjøremål" er sortert etter hvor viktige de er for å få prosjektet til å møte MVP-kravene. Lenger nede finner man mindre kritiske elementer som vi ønsker å implementere om vi får tid. Hver av oss drar en arbeidsoppgave fra "gjøremål" til "pågår". Når den er implementert går oppgaven til "testes". Når oppgaven er implementert og testet blir den merge requestet til prosjekt-repoet. Der blir den gått igjennom av en annen på teamet, og potensielt dratt til ferdig. Så begynner man på nytt med prosessen.

Kodepraksisen og arbeidsteknikken vår vil ta elementer fra ekstrem programmering. Som nevnt i scrum vil enhver feature bli kontinuerlig testet og så gått igjennom av en annen på teamet før den får bli lagt til i prosjekt-repoet. Å rangere gjøremål etter hvor viktige de er og å kun jobbe med en arbeidsoppgave hver om gangen hjelpe oss med å ha ikke implementere features før de er nødvendige. Vi skal også være beredt på endringer i krav til sluttresultat. Dette får vi ved å jobbe modulært med hver oppgave, og å passe på at hvert element så enkelt som mulig skal kunne fjernes og erstattes uten å ødelegge noe annet i koden. Vi skal til tider dra nytte av parprogrammering for å få maksimal kodegjennomgang, men som det står i Agile Technical Practices Distilled skal parprogrammering være frivillig.

Plan for møter frem til innlevering av obligatorisk oppgave 1: Fysisk møte hver tirsdag 12-14 på gruppetime. Ett fysisk møte på grupperom per uke på torsdager klokka 16. Ett fleksibelt discordmøte per uke.

Kommunikasjon mellom møter vil foregå på discord, og potensielt parprogrammering på høyteknologisenteret når det skulle passe for parene.

Vi har avtalt å ha fokus på å være tidlig ute med å spørre om hjelp på discord, så de andre kan komme med innspill.

### Deloppgave 3 (uke 6–7): Få oversikt over forventet produkt
Kort beskrivelse av det overordnede målet for applikasjonen: <br>
Bevege en karakter sidelengs og opp/ned i en 2d-verden, hvor man må bevege seg på plattformer forbi fiender for å komme i mål på andre siden av banen. «Kameraet»/verden skal bevege seg til siden i takt med spillerens bevegelser. Man skal kunne sanke poeng, og vinne og tape. Det skal kunne være opp til 2 menneskelige spillere. Spillet skal ha en startskjerm ved oppstart eller game over, og ved fullført spillbrett skal det komme et nytt spillbrett.

Til første obligatoriske innlevering gjelder MVP-kravene 1-4 i de gitte kravene. Brukerhistorier til disse kravene:
<b> Brukerhistorier </b>

<br>Som spiller trenger jeg å kunne se spillbrettet for å kunne vite hva som skjer i verden og hvor jeg kan bevege meg.
<br>Akseptansekriterier: Trenger et spillbrett og det skal være synlig.
<br>Arbeidsoppgaver: Lage enkelt spillbrett i Tiled. Lage pakke med klasser for GameWorld og for tiles på spillbrett.
<br>Brukerhistorien oppfyller MVP-krav 1.


Som spiller trenger jeg å kunne se spillkarakteren min slik at jeg vet hvor på spillbrettet jeg befinner meg.
<br>Akseptansekriterier: Spillerens karakter skal være synlig "over" spillbrettet. 
<br>Arbeidsoppgaver: Lage enkel spillkarakter. Lage pakke for entitier, hvor spiller er en spesiell form for entity fra Entity-klassen.
<br>Brukerhistorien oppfyller MVP-krav 2.


Som spiller trenger jeg å kunne bevege karakteren slik at jeg kan manøvrere spillbrettet. 
<br>Akseptansekriterier: Spilleren skal kunne bruke AD for å bevege seg horisontalt, og SPACE for å hoppe vertikalt. Skal med disse kombinert kunne bevege seg i alle retninger. 
<br>Arbeidsoppgaver: Implementere metoder for bevegelse i x- og y-retninger. Bruke Gdx.input.isKeyPressed for å sjekke om spilleren skal bevege seg til siden eller hoppe. 
<br>Brukerhistorien oppfyller MVP-krav 3.


Som spiller trenger jeg at karakteren min interagerer med terrenget og påvirkes av eksterne krefter som tyngekraft. 
<br>Akseptansekriterier: Tiles skal ha en feltvariabel collidable. Dersom collidable er true skal spilleren ikke gå gjennom tilen. Spilleren skal kun kunne hoppe om de står på en kolliderbar tile. Dersom spilleren er på vei opp skal de kunne holde inne SPACE for å ta et lengre hopp. Entities (i dette tilfellet spesielt spilleren) skal ha en feltvariabel velocityY som vi skal kunne øke eller minke for å endre spillerens hastighet i retningY. RetningX kan komme senere. 
<br>Arbeidsoppgaver: Implementere metode for å sjekke om en entity er inntil en tile som er collidable. Player skal kunne hoppe om den står på en collidable tile. 
<br>Brukerhistorien oppfyller MVP-krav 4.

Format for brukerhistorier:
<br>Som <em>rolle</em> trenger jeg <em>funksjonalitet</em> for å oppnå <em>nytteverdi</em>.
<br>Akseptansekriterier: 
<br>Arbeidsoppgaver:
<br>Brukerhistorien oppfyller krav … 


### Prosjekt-retrospektiv
Selve arbeidsmetodikken med scrum og ekstrem programmering fungerte godt. For å kickstarte prosjektet hadde vi en felles programmeringsøkt med hele gruppen, hvor alle deltok og vi satte fundamentet sammen. Utover vil vi selvfølgelig gå over til av enkel- og parprogrammering, siden det er en del mer effektivt enn at alle jobber på det samme. Det som ikke gikk så bra var nok git. Vi har brukt en del tid på sette opp origin og upstream hos alle, og alt av fetching, forking merging og branches. Så det har krevd en del tid, men vi har lært veldig mye av de feilene og har hatt veldig stor fremgang rundt våre evner på git. 

Vil si at vi traff ganske godt på oppgaven. Vi valgte en veldig trygg mengde brukerhistorier og jobbet derfra. Sammen lagde vi en enkel prototype, og så reformaterte vi koden i en mer enkapsulert og modulær form. 

