# Apuntes implementación

Acá van las lluvias de ideas al momento de ir implementando los algoritmos.

### Node

Bueno, un nodo debe tener un collection de hijos el cual debe ser siempre mayor a dos pues si fuera un solo hijo este nodo se debería eliminar.  

Mejor diremos que el nodo tiene una collection de aristas, de esta forma definimos una clase Edge que tiene la etiqueta de la arista.  

Obviamente una arista sabe qué nodos conecta. Por lo tanto debe tener un Nodo padre y un nodo hijo.  

De esta forma un padre cuando quiere agregar un hijo debe crear la conexión entre él y su hijo para así ponerle una etiqueta a esa conexión.  

Agregamos una hoja, que implementa la clase INode (en conjunto con Node) para que las edges tengan INodes (hojas y nodos).  

En vista de que una hoja no puede ser padre, diremos que un Edge solo tiene de INode al hijo.  

Avancé más. Hasta ahora tenemos que las edges pueden hacer `split`, que es necesario cuando estamos recorriendo el camino de $\beta$ y se termina el string a mitad del arco, en ese momento se debe crear un nodo y todo el show. Para eso sirve el split, que separa el edge rápidamente y me devuelve el nuevo nodo que se debe crear, entonces el algoritmo lo único que debería hacer es agregar una hoja al nuevo nodo con el valor de $j$ no más.  

Actualización domingo 4/12:  
No había leído todo el enunciado y había puesto como tag de los arcos strings, siendo que se manejan simplemente los índices de los caracteres inicial y final del tag en el string verdadero. Se cambió eso y eso modificó los otros métodos.  

Se agregaron algunos métodos más como getLength() que permite saber el largo del tag del arco para ver si lo podemos saltar de una o no.  
