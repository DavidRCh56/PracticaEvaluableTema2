# Practica Evaluable Tema 2

Esta es una aplicación Android diseñada para permitir a los usuarios enviar mensajes personalizados, 
establecer alarmas y realizar llamadas telefónicas de manera sencilla.

## Características

- **Enviar Mensajes**: Permite a los usuarios escribir un mensaje totalmente personalizable y elegir una aplicación
  para enviarlo, pudiendo de esta manera mandar el mismo mensaje a más de una persona a la vez.
- **Establecer Alarmas**: Los usuarios pueden configurar alarmas con un mensaje personalizado y la hora deseada.
- **Realizar Llamadas**: Permite realizar llamadas telefónicas a un número guardado o ingresado por el usuario, pudiendo
  modificarlo en cualquier momento o mantener el ingresado desde un principio si así se desea.

## Estructura del Proyecto

El proyecto está organizado en varias actividades principales:

- `MainActivity`: Pantalla principal que permite navegar a otras actividades.
- `MensajeActivity`: Permite al usuario enviar mensajes de texto.
- `AlarmaActivity`: Permite al usuario establecer alarmas.
- `LlamadaActivity`: Permite realizar llamadas telefónicas.
- `JuegoDadosActivity`: Permite al usuario jugar a un juego de dados, con opciones de sumar los resultados de las tiradas y controlar el tiempo entre tiradas.
- `JokerActivity`: Permite al usuario escuchar chistes mediante un sistema de Text-to-Speech, con una interfaz mejorada y una barra de progreso durante la narración.

## Archivos Modificados

### `JuegoDadosActivity.kt`

Este archivo contiene la lógica del **Juego de Dados**. Los jugadores pueden lanzar dados y obtener los resultados con un tiempo entre tiradas configurable. Además, el juego permite sumar los valores de las tiradas o mostrar el resultado de la última tirada.

- **Spinner de tiempo**: Los usuarios pueden seleccionar el tiempo entre tiradas (1, 2 o 3 segundos).
- **Configuración de la interfaz**: Los resultados se muestran mediante imágenes de dados, y se puede elegir entre sumar los valores o mostrar solo el último resultado.

### `JokerActivity.kt`

Este archivo contiene la lógica para la actividad relacionada con **escuchar chistes**. Utiliza la funcionalidad de **Text-to-Speech** para narrar chistes aleatorios, y presenta una interfaz mejorada para hacer más interactiva la experiencia del usuario.

- **Barra de progreso**: Se ha añadido una barra de progreso que aparece mientras se narra un chiste y dura 10 segundos.
- **Botones dinámicos**: Los botones de la interfaz (acción y volver) se ocultan mientras se está contando el chiste y se muestran de nuevo cuando el chiste ha terminado.
- **Toques dobles**: El sistema detecta toques rápidos en el botón para lanzar el chiste, lo que mejora la interacción del usuario.

## Requisitos

- Android SDK (versión mínima recomendada: 21)
  ```bash
  defaultConfig {
        applicationId = "com.iesvdc.multimedia.practicaevaluabletema2"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
