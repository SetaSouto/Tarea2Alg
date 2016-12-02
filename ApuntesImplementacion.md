# Apuntes implementación

Acá van las lluvias de ideas al momento de ir implementando los algoritmos.

### Node

Bueno, un nodo debe tener un collection de hijos el cual debe ser siempre mayor a dos pues si fuera un solo hijo este nodo se debería eliminar.  

Mejor diremos que el nodo tiene una collection de aristas, de esta forma definimos una clase Edge que tiene la etiqueta de la arista.  

Obviamente una arista sabe qué nodos conecta. Por lo tanto debe tener un Nodo padre y un nodo hijo.  

De esta forma un padre cuando quiere agregar un hijo debe crear la conexión entre él y su hijo para así ponerle una etiqueta a esa conexión.  
