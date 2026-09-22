Taller # 3 Clase 4

1. Si activas la opción de desarrollador "No conservar actividades" y minimizas la app, ¿qué callbacks crees que se dispararán al reabrirla? ¿Es lo mismo que rotar la pantalla?

R/ Supongo que si sería lo mismo que rotar la pantalla, Entraría en un On Stop y volvería a un On create.

2. Si guardas un contador en una variable `companion object` (estática) en vez de usar `onSaveInstanceState`, ¿sobrevivirá a una rotación? ¿Sobrevivirá a que el sistema mate el proceso en segundo plano?

R/ Puede sobrevivir a una rotación mientras que el proceso de la app siga vivo ya que permanece en memoria  pero no a cerrar la app (matar el proceso en segundo plano) ya que pues en este caso si se pierde la memoria.

3. Si `ActivityA` abre `ActivityB` y `ActivityB` abre `ActivityC`, y desde `ActivityC` presionas "Atrás" tres veces seguidas, ¿en qué orden exacto se destruyen las tres Activities?

R/ Supongo que se cerrará ActivityA primero, después ActivityB, y por ultimo ActivityC.

Parte 1:

2. Abre `AndroidManifest.xml` y responde, con justificación (no solo la respuesta): ¿cuál es el nombre completo (`android:name`) de la Activity principal? ¿Qué `intent-filter` la marca como pantalla de inicio? ¿Qué pasaría si dos Activities declararan el mismo `intent-filter` de `LAUNCHER`?

R/ El nombre es: .MainActivity ya que es la Actividad por defecto que se crea junto a el Main y el Launcher. Main indica el punto de entrada principal y Launcher permite que esta sea iniciada desde el launcher del dispositivo. Si dos Activities declaran el mismo intent-filter podrían havber ambiguedades para el usuario ya que se tomarían ambas como puntos de entrada para el usuario.

3. En `app/build.gradle.kts`, identifica `minSdk`, `targetSdk` y `compileSdk`. Investiga y responde: ¿qué diferencia real hay entre los tres? ¿Qué consecuencia tiene subir el `targetSdk` sin probar la app (pista: `android:exported` en Android 12+, que ya usaste en el manifest de la Clase 4)?

R/

minSdk: indica la versión mínima de Android en la que tu aplicación puede instalarse y ejecutarse.

CompileSdk: indica la versión de Android con la que se compila tu aplicación.
Esto permite que durante la compilación puedas utilizar las APIs disponibles hasta esa versión.
TargetSdk: indica para qué versión de Android has preparado y probado el comportamiento de tu aplicación.
Es especialmente importante porque Android puede aplicar cambios de comportamiento del sistema dependiendo del targetSdk que declares.

Diferencias:

minSdk       → ¿Desde dónde funciona? O ¿Cual es la versión mínima desde donde puede funcionar mi app?
compileSdk   → ¿Con qué compilo? O ¿Con que versión de API’s voy a compilar?
targetSdk    → ¿Para qué versión la he adaptado? O ¿Para que versión de API’s estoy preparando o probando el comportamiento de mi app?

4. Explica la diferencia entre el `applicationId` (Gradle) y el nombre del paquete Kotlin (`package` en el código). ¿Por qué pueden ser distintos?

R/ Pueden ser distintos ya que el Gradle es el identificador unico de la app en Android, en cambio el Package en el código indica a que paquete pertenece esa clase en Kotlin

Parte 2 – Insttrumentación completa + cacería de bugs (2 horas aprox).

1. En `MainActivity`, sobrescribe los 7 callbacks (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onRestart`, `onDestroy`) y agrega un `Log.d` distinto en cada uno, incluyendo el `hashCode()` de la instancia (`Log.d(TAG, "onCreate - instancia: ${this.hashCode()}")`) para poder distinguir si la Activity es la MISMA instancia o una NUEVA tras cada evento.

R

<img width="1917" height="1078" alt="imagen" src="https://github.com/user-attachments/assets/5f0b8902-49d2-473a-8e5f-a5762523602b" />

<img width="1917" height="1078" alt="imagen" src="https://github.com/user-attachments/assets/ca4a1a3d-6011-401b-bdcd-8295af0aed6e" />

<img width="1917" height="1078" alt="imagen" src="https://github.com/user-attachments/assets/0f235eeb-2245-480a-b318-5035186939d2" />

<img width="1917" height="1077" alt="imagen" src="https://github.com/user-attachments/assets/2290c8c4-79d3-486c-ba84-1ccf9c04f58a" />





2. **Cacería de bugs (obligatoria):** el siguiente fragmento tiene un error real y común en apps de producción. No lo copies tal cual: identifica el problema, explica por qué es peligroso, y corrígelo en tu propio código.

R// El companion object no es buena opción para guardar un Activity ya que este pertenece a una clase no a un objeto o a cada objeto de Main Activity. Además hay otro problema al intentar guardar una activity en una variable (instanciaActual) ya que al querer destruir la activity con un onDestroy esta aun estaría encapsulada en esta variable por ende queda en existencia una referencia alcanzable y se pueden producir “memory leaks” o fugas de memoria. contadorGlobal representa un estado global que persiste entre diferentes instancias de la Activity, por lo que puede generar resultados inesperados después de una recreación.

<img width="1917" height="1075" alt="imagen" src="https://github.com/user-attachments/assets/cc9d9a75-3a38-48db-b671-1f0b6d5f7347" />




3. Ejecuta la app y realiza, en este orden, registrando el Logcat completo de cada paso (incluyendo el `hashCode()`):
   - Abrir la app.
   - Presionar Home (minimizar) y volver a abrirla desde "Recientes".
R// Se pone en onStop al minimizar y al volverla abrir se reanuda la app haciendo el ciclo (onRestart – onStart – onResume).
   - Rotar la pantalla (activa la rotación si el emulador la bloquea).
R// Al activar la rotación de la pantalla de forma horizontal se pone (onPause – onStop – onDestroy) y al volver a rotar de forma vertical se pone en (onCreate – onRestart – onResume).
   - Presionar "Atrás" para cerrar la app.
R// Primero se pone en onPause y después se pone en onStop.
4. **Reto de diagnóstico:** activa la opción de desarrollador "No conservar actividades" (Don't keep activities), repite la secuencia de "minimizar y reabrir", y compara el `hashCode()` de la instancia antes y después. Explica por escrito qué diferencia real hay entre esto y una rotación de pantalla, en términos de qué le pasó al PROCESO de tu app (no solo a la Activity).

<img width="827" height="186" alt="imagen" src="https://github.com/user-attachments/assets/c25043e3-7229-43a9-bbd6-6c1416b638ba" />


Al presionar Home y volver a la aplicación, el `hashCode()` se mantuvo igual. Esto indica que la misma instancia de la Activity continuó existiendo mientras estaba detenida. Al rotar la pantalla, el `hashCode()` cambió después de `onDestroy()`, lo que demuestra que Android destruyó la instancia anterior y creó una nueva Activity.

4. Reto de diagnostico.

Al activar la opción “No conservar actividades” podemos evidenciar que al volver al home , la app no se para, inmediatamente se destruye y cuando queremos nuevamente abrir la app vielve a crearse una nueva instancia, la diferencia entre una rotación de pantalla y esto es que la rotación de pantalla crea el on destroy pero el proceso queda vivo y la activity cambia, en cambio con la opción “No conservar actividades” el proceso y la actividad mueren con el destroy, esto quiere decir que al momento de volver a iniciar la app la actividad que va a iniciar es una actividad completamente nueva.

<img width="1917" height="1078" alt="imagen" src="https://github.com/user-attachments/assets/42176d71-3c17-40e4-ba76-1203bdf768f3" />


La diferencia respecto a una rotación es que la rotación provoca la recreación de la Activity debido a un cambio de configuración. En ambos casos puede existir una Activity nueva aunque el proceso de la aplicación continúe existiendo. Por esta razón, la destrucción de una Activity no debe interpretarse automáticamente como la muerte del proceso. La Activity y el proceso son conceptos diferentes dentro del ciclo de vida de Android.


Parte 3.

1- Crea `ActivityA` (la principal), `ActivityB` y `ActivityC`, las tres con los 7 callbacks instrumentados con logs (incluyendo `hashCode()`).

<img width="545" height="158" alt="imagen" src="https://github.com/user-attachments/assets/6af164ad-8cbc-414c-90e4-699b71f15524" />

2- `ActivityA` → `ActivityB`: envía un `String` con `Intent.putExtra`.

<img width="1147" height="492" alt="imagen" src="https://github.com/user-attachments/assets/503f4cbb-7d5f-48fc-8b44-bff2c46685a0" />

3- `ActivityB` → `ActivityC`: envía un `Int` (por ejemplo, un contador) con `Intent.putExtra`.


<img width="1142" height="420" alt="imagen" src="https://github.com/user-attachments/assets/3709acc4-dcb4-4d64-8338-11f6659b0841" />

<img width="1917" height="355" alt="imagen" src="https://github.com/user-attachments/assets/6b83c029-b21e-46f4-abcd-4639fb107b85" />

<img width="1130" height="445" alt="imagen" src="https://github.com/user-attachments/assets/c872d789-b475-4729-b0a6-cd9aa6383866" />


4- `ActivityC` debe permitir modificar ese `Int` (por ejemplo, con dos botones +/-) y **devolverlo a `ActivityB`** al presionar "Guardar y volver", usando `registerForActivityResult(ActivityResultContracts.StartActivityForResult())` (la API moderna, no `startActivityForResult` deprecado).

<img width="1155" height="462" alt="imagen" src="https://github.com/user-attachments/assets/8d814cc4-0d50-439d-882f-767ade3c4cbb" />

<img width="1116" height="550" alt="imagen" src="https://github.com/user-attachments/assets/e42b5fa3-ff79-4804-baea-dad5fcce5088" />


Parte 4 — Reto de estado: sobrevivir a la rotación Y a la muerte del proceso (1.5 horas aprox.)


Esta es la parte más exigente del taller. Implementa un contador en `ActivityA` que debe sobrevivir a **ambos** escenarios, no solo a uno:
1. Implementa `onSaveInstanceState`/`savedInstanceState` correctamente para que el contador sobreviva a una **rotación de pantalla**.

<img width="891" height="453" alt="imagen" src="https://github.com/user-attachments/assets/6167f397-bdf0-4d0a-9ea5-139907d78fdd" />

<img width="968" height="216" alt="imagen" src="https://github.com/user-attachments/assets/70317078-1d09-45d9-9b0f-211fa2795bbb" />

2. Prueba con "No conservar actividades" activado: minimiza la app (esto simula que el sistema mata el proceso) y vuelve a abrirla. Verifica si tu contador sobrevive.
Al rotar la pantalla podemos observar que el contador sobrevivió al onDestroy, guardó el dato y después al ponerla nuevamente en vertical se observa que se guarda el dato en el contador, pero esto solo sucedió de forma interna, en la interfaz el contador volvió a 0, no quedó con el valor 6 en interfaz.

<img width="1907" height="372" alt="imagen" src="https://github.com/user-attachments/assets/699cfdf0-0f81-476b-9e7d-e9d70d3a9110" />

<img width="1902" height="372" alt="imagen" src="https://github.com/user-attachments/assets/96bde9bc-8d4a-426b-ad8a-77a2ad70f731" />

4. Responde por escrito (con justificación técnica, no una frase genérica): ¿por qué `onSaveInstanceState` NO es un reemplazo de una base de datos o `SharedPreferences` para datos que deben persistir después de que el usuario cierra la app completamente con "Atrás"?

R// onSaveInstanceState() no esta diseñado para almacenamiento persistente a largo plazo. Su propósito es guardar temporalmente el estado de la interfaz para poder restaurarlo si Android destruye y recrea la actividad.
Esta opción no garantiza la persistencia de los datos después de cerrar la app, está diseñado para garantizar la permanencia de la interfaz no de los datos, por ende no sustituye a una base de datos


## Parte 5 — Reto avanzado (bonus, +15 puntos)
Implementa una clase `Application` personalizada (`MiApp : Application()`) que registre en Logcat cuándo se crea (arranque en frío del proceso completo, no solo de una Activity). Con esa evidencia, responde: ¿cómo distinguirías, mirando solo el Logcat, un **cold start** (proceso recién creado) de un **warm start** (proceso ya vivo, solo se recrea la Activity)?

<img width="1912" height="636" alt="imagen" src="https://github.com/user-attachments/assets/8214c4ff-ad3c-4328-a34b-f975cb5f962c" />

<img width="1907" height="438" alt="imagen" src="https://github.com/user-attachments/assets/2e8d13f8-c9d5-4549-a237-b31d091cd08e" />

Como podría diferenciarlo?

El Cold start significa que crea un proceso nuevo a partir de un objeto, en este caso la aplicación, desde .Miapp, un proceso iniciado totalmente nuevo. En cambio de Warm start que da a entender que el proceso de la app ya existe pero se crea o recrea a partir de una Activity es decir que no va a aparecer el indicador de que la app arrancó antes que las Actividades como en un Cold start sino como ya está el proceso iniciado directamente inicia con la Actividad que tenga el Main y launcher.
