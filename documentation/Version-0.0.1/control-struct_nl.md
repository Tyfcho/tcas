# Control Structure (NL)

TCAS krijgt ingangen en uitgangen binnen vanuit TrainCarts, waardoor we bepaalde acties kunnen uitvoeren.
Denk hierbij bijvoorbeeld aan:
- Automatisch vrijgeven
- Handmatige bediening omschakelen
- Blokkade van vrijgave indien de veiligheidswaardes niet bereikt worden
- Indicaties krijgen waar een kar zich bevindt in een circuit (gepland)

---
## Variabelen
De volgende dingen zijn variabel in te stellen tijdens het automatisch draaien:
- Hoelang de poortjes open blijven
- Hoelang de kar moet wachten terwijl de poortjes dicht zijn
- Hoelang het duurt voordat de volgende kar wordt uitgestuurd
- Hoelang het duurt voordat de absolute vrijgave van het voertuig wordt geactueerd

De volgende formule geldt dan:
- a = speler aanwezig       (nee = 0, ja = 1)
- b = kar aanwezig          (nee = 0, ja = 1)
- c = poorten open          (nee = 0, ja = 1)
- d = tijd verlopen         (nee = 0, ja = 1)
- e = baan vrij             (nee = 0, ja = 1)

Bij absolute vrijgave moet de uitkomst van ```a*b*c*d*e``` gelijk staan aan 1.
Verder wordt dan variabele f toegevoegd, wat gebruikt wordt voor externe triggers, bijvoorbeeld deuren of bewegende vloeren.

Wanneer dit berekend is kan een TTD (time to dispatch) berekend worden, dit wordt gedaan d.m.v. het volgende:
TTD = (`puls poortjes open` + `vertraging` + `wegvallen puls poortjes` + `externe actuatoren tDelta(totaal)`) * `veiligheidsfactor`

Stel dat we zeggen dat wanneer een speler zit, dat er dan een vertraging van 5 seconden komt, daarna wacht het systeem nog 10 seconden voordat de puls van de poortjes wegvalt, waarna de puls wordt doorgegeven dat de externe actuatoren mogen aangestuurd worden (bijvoorbeeld een tDelta(totaal) van 5 seconden) en tot slot een veiligheidsfactor van 2. dat zal dan inhouden dat de uitkomst van dit scenario het volgende is:
TTD = ```10+5+5=20 * 2 = 40 seconden``` totdat de vrijgave compleet is