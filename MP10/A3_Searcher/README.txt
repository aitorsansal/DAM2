Utiització cancelation token:

El cancelation token l'he utilitzat a l'hora d'afegir i treure elements de les 
col·leccions, permetent així que aquests no executin res més una vegada es cancel·la
la cerca.

Utilització BlockingCollection:

En comptes d'utilitzar una ConcurrentQueue per tal de gestionar les carpetes que cal 
processar. Aquesta el que permet és bloquejar la col·lecció mentre no hi hagi cap 
element dintre, permetent que els threads agafin el següent element disponible cada vegada,
fent que no hi hagi threads buits utilitzats, ja que no tenen carpeta.

Threads:

En cada thread es recorre una de les carpetes dintre la carpeta la qual s'agafa amb 
folderQueue.GetConsumingEnumerable, entregant una carpeta si n'hi ha o bloquejant el
thread fins que aquest tingui una carpeta disponible. 
Per acabar el programa i mostrar la messageBox, es fa un Task.WhenAll per a esperar 
que tots acabin. 