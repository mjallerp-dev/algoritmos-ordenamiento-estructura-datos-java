# Algoritmos de ordenamiento - Estructura de datos (Java)

Actividad de la Unidad 2: algoritmos de ordenamiento. Incluye los programas del taller (Bubble Sort,
Selection Sort, Insertion Sort, Quick Sort, Merge Sort, Counting Sort y ordenamiento de objetos) y el
**ejercicio asignado número 10: Ordenamiento de archivos numéricos**.

## Ejercicio 10: Ordenamiento de archivos numéricos

El programa lee números enteros desde un archivo de texto, los guarda en un arreglo `int[]`, los ordena
de forma ascendente con **Merge Sort implementado manualmente** y guarda el resultado en un segundo archivo.

- Lee archivos con números separados por espacios, saltos de línea, comas o punto y coma.
- Los valores que no son enteros (por ejemplo `hola` o `8.5`) se ignoran y se informan.
- Muestra cada **división** del arreglo y cada **operación de mezcla**, con sangría según el nivel de recursión.
- Opcionalmente muestra cada comparación realizada dentro de una mezcla.
- Cuenta divisiones, mezclas, comparaciones, movimientos, llamadas recursivas y profundidad.
- Guarda el resultado en `datos/salida/<nombre>_ordenado.txt`.
- Explica por qué Merge Sort necesita memoria auxiliar.

| Aspecto              | Merge Sort                                   |
|----------------------|----------------------------------------------|
| Tiempo (todos los casos) | O(n log n)                               |
| Memoria adicional    | O(n) del arreglo auxiliar + O(log n) de la pila |
| Estable              | Sí (`a[i] <= a[j]` conserva el orden de los repetidos) |

### Archivos de ejemplo (`datos/entrada`)

| Archivo                   | Caso de prueba                         |
|---------------------------|----------------------------------------|
| `numeros.txt`             | 12 números desordenados en varias líneas |
| `numeros_repetidos.txt`   | Valores repetidos separados por comas  |
| `numeros_invertidos.txt`  | Números en orden descendente           |
| `numeros_con_errores.txt` | Separadores mixtos y valores inválidos |

Desde el menú también se pueden generar archivos aleatorios de cualquier tamaño.

## Estructura del proyecto

```
datos/entrada                         Archivos de números de ejemplo
datos/salida                          Archivos ordenados (se crean al ejecutar)
src/main/java/com/mjallerp
├── Main.java                         Punto de entrada
├── consola                           Menú principal y lectura de datos
├── taller                            Programas del taller
│   └── modelo                        Book, Producto, ItemVenta, Venta
└── archivos                          Ejercicio 10
    ├── modelo                        DatosArchivo
    ├── persistencia                  ArchivoNumeros (leer, escribir, generar)
    ├── servicio                      MergeSortConTraza
    ├── consola                       MenuArchivos
    └── pruebas                       PruebasMergeSort
```

## Requisitos

- Java 17 o superior
- Maven (opcional, IntelliJ IDEA lo trae incluido)

## Ejecución

Ejecutar siempre desde la carpeta raíz del proyecto para que encuentre la carpeta `datos`.

Desde IntelliJ IDEA: abrir el proyecto y ejecutar `com.mjallerp.Main`.

Desde la terminal con Maven:

```bash
mvn compile
java -cp target/classes com.mjallerp.Main
```

Sin Maven:

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.mjallerp.Main
```

## Menú

```
1. Bubble Sort (int[], ascendente)
2. Selection Sort (double[], descendente)
3. Insertion Sort (char[], ascendente)
4. Quick Sort (int[], pivote medio)
5. Merge Sort (long[], ascendente)
6. Counting Sort (int[], rango conocido)
7. Ordenamiento de objetos Book[]
8. Ordenamiento de objetos Producto[]
9. Ventas por fecha e items por precio
10. Ejercicio 10: Ordenamiento de archivos numéricos
0. Salir
```

Dentro de la opción 10 se puede seleccionar el archivo de entrada, ver sus números, ordenarlos con Merge
Sort viendo las divisiones y mezclas, ver el archivo de salida, generar archivos aleatorios, ejecutar los
casos de prueba y consultar la explicación de la memoria auxiliar y la complejidad.
