# Pametna kuća
Informacioni sistem nekoliko programa ( Alarm, Planer, MuzickiUredjaj ).                                     

Uređaji komuniciraju međusobno i sa deljenom bazom podataka. Svaki uređaj ima zasebnu, specijalno osmišljenu aplikaciju.

Aplikacije različitih uređaja međusobno komuniciraju putem JMS. Baza podataka je MySQL. Aplikacije sa bazom komuniciraju korišćenjem JPA.

Opis rada sistema

Uređaj za reprodukciju zvuka:

● Reprodukuje zadatu pesmu korišćenjem internet pretraživača.

● Na zahtev pošalje imena svih pesama prethodno reprodukovanih od strane
zadatog korisnika.

Alarm:

● Navije alarm da zvoni u trenutku koji je zadat.

● Navije periodične alarme.

● Navije alarm da zvoni u jednom od ponuđenih trenutaka.

● Konfiguriše željeno zvono alarma.

Planer

Planer služi za beleženje obaveza u kalendar. Prilikom unošenja obaveze moguće je
uneti destinaciju. Obaveze je moguće izlistavati, dodavati, menjati i brisati.
Planer u sebi ima implementiran i kalkulator razdaljine. Kalkulator razdaljine može
da:

● Izračuna vreme potrebno da se stigne od lokacije A na lokaciju B

● Izračuna vreme potrebno da se stigne od trenutne lokacije na lokaciju B

Planer može da aktivira i podsetnik za polazak koji na osnovu vremena početka
obaveze i vremena potrebnog za dolazak navija potrebni alarm. 
