Socket-ul e un fel de legatura intre programe, adica intre client si server. Socket-ul e format din
ip si port. Ip-ul este folosit de client pentru a indentifica adresa serverului. Portul este ca un fel de
pin si este folosit pentru a stii exact ce program este folosit ( fiecare program din calculator are portul propriu)

Pentru utilizarea datelor din Data Base, un client trimite comanda dorita. Aceasta comanda este serializata, 
trimisa prin outStream (cu writeObject) spre server, interceptata de server prin inStream( readObject) si deserializata.
Apoi serverul proceseaza datele si trimite raspunsul la client la fel ca mai sus.

Serializarea presupune transformarea unui obiect intr-un sir de biti. Avem nevoie deoarece nu putem trimite un obiect
cu mai multe atribute, de aceea acesta este serializat, trimis si deserializat de catre destinatar, datele fiind aceleasi.

