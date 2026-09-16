# Sistema de Registro y Consulta de Clientes (JavaFX)

Aplicación de escritorio desarrollada para la asignatura **Programación de Aplicaciones de Escritorio** (Semana 5). El sistema permite gestionar el acceso de usuarios, el registro de clientes con imágenes y la consulta iterativa de solicitudes mediante transferencia de datos entre ventanas.

[![Repo GitHub](https://img.shields.io/badge/GitHub-AplicacionRegistro-blue?style=flat&logo=github)](https://github.com/Jesy05/AplicacionRegistro)
[![JavaFX](https://img.shields.io/badge/JavaFX-17%2B-orange?style=flat&logo=java)](https://openjfx.io/)
[![IDE](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-purple?style=flat&logo=intellijidea)](https://www.jetbrains.com/idea/)

---

## Integrantes del Equipo

| Nombre Integrante | Rol / Función Desarrollada |
| :--- | :--- |
| **[  ]** | Diseño de interfaces (.fxml) en Scene Builder y diseño visual. |
| **[ ]** | Controladores (`LoginController`, `MenuController`) y manejo de eventos (`ActionEvent`, `KeyEvent`). |
| **[  ]** | Controladores (`RegistroController`, `ConsultaController`) y gestión de `FileChooser`/`DirectoryChooser`. |
| **[ ]** | Lógica de modelos, gestión de memoria (lista de clientes) y paso de datos entre controladores. |

---

##  Requisitos del Sistema y Tecnologías

* **Lenguaje:** Java 17 o superior.
* **Framework GUI:** JavaFX.
* **Diseñador Visual:** Scene Builder.
* **Entorno de Desarrollo:** IntelliJ IDEA.
* **Gestor de Dependencias:** Maven.

---

##  Estructura del Proyecto

El proyecto sigue una arquitectura MVC (Modelo-Vista-Controlador) para garantizar una clara separación de responsabilidades:

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── registro/
    │           ├── controller/     # Controladores de JavaFX
    │           │   ├── LoginController.java
    │           │   ├── MainController.java
    │           │   ├── RegistroController.java
    │           │   ├── ConsultaController.java
    │           │   └── DetalleController.java
    │           ├── model/          # Clases POJO (Cliente, Solicitud, etc.)
    │           │   └── Cliente.java
    │           └── MainApp.java    # Clase Principal (Launcher)
    └── resources/
        ├── com/
        │   └── registro/
        │       └── fxml/           # Archivos de vista FXML
        │           ├── LoginView.fxml
        │           ├── MainView.fxml
        │           ├── RegistroView.fxml
        │           ├── ConsultaView.fxml
        │           └── DetalleView.fxml
        └── images/                 # Assets e imágenes del sistema
