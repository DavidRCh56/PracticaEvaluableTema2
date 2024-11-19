# Practica Evaluable Tema 2

Esta es una aplicación Android diseñada para permitir a los usuarios enviar mensajes personalizados, 
establecer alarmas y realizar llamadas telefónicas de manera sencilla.

## Características

- **Enviar Mensajes**: Permite a los usuarios escribir un mensaje totalmente personalizable y elegir una aplicación
  para enviarlo, pudiendo de esta manera mandar el mismo mensaje a mas de una persona a la vez.
- **Establecer Alarmas**: Los usuarios pueden configurar alarmas con un mensaje personalizado y la hora deseada.
- **Realizar Llamadas**: Permite realizar llamadas telefónicas a un número guardado o ingresado por el usuario, pudiendo
  modificarlo en cualquier momento o mantener el ingresado desde un principio si asi se desea.

## Estructura del Proyecto

El proyecto está organizado en varias actividades principales:

- `MainActivity`: Pantalla principal que permite navegar a otras actividades.
- `MensajeActivity`: Permite al usuario enviar mensajes de texto.
- `AlarmaActivity`: Permite al usuario establecer alarmas.
- `LlamadaActivity`: Permite realizar llamadas telefónicas.

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
  ```
- Opciones de kotlin
  ```bash
  kotlinOptions {
     jvmTarget = "1.8"
  }
  ```
- Permisos para llamadas telefónicas y acceso a la alarma.
  ```bash
     <uses-permission android:name="android.permission.CALL_PHONE" />
     <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
  ```

## Configuración del Proyecto

1. **Clonar el Repositorio**:
   ```bash
   git clone https://github.com/DavidRCh56/PracticaEvaluableTema1.git
   cd practicaevaluabletema1
   ```
