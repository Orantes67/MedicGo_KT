# 📋 FLUJO COMPLETO DEL PANEL ADMINISTRATIVO - MedicGo

## 🎯 Descripción General

El panel administrativo es la interfaz principal para gestionar:
- **Métricas**: Estadísticas generales del sistema
- **Usuarios**: Doctores y enfermeras registrados
- **Áreas**: Distribución de pacientes por área hospitalaria

---

## 🔐 AUTENTICACIÓN Y TOKENS

### 1. Flujo de Login

```
PANTALLA DE LOGIN
    ↓
Usuario ingresa:
  - License Number: "ADMIN001"
  - Password: "admin123"
    ↓
POST /v1/login
  Body: {
    "license_number": "ADMIN001",
    "password": "admin123"
  }
    ↓
Backend valida credenciales
    ↓
Respuesta exitosa (200 OK):
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "message": "Login exitoso",
  "user": {
    "id": 1,
    "name": "Administrador",
    "email": "admin@medicgo.com",
    "license_number": "ADMIN001"
  }
}
    ↓
App guarda token en SharedPreferences (TokenManager)
    ↓
ENTRA A PANEL ADMINISTRATIVO
```

### 2. Token Management (Cada Petición)

```
Petición HTTP a API:
GET /admin/metricas
    ↓
AuthInterceptor (OkHttp):
  1. Obtiene token de SharedPreferences
  2. Agrega header: "Authorization: Bearer <token>"
  3. Envía petición
    ↓
Request llega al backend:
{
  Method: GET
  Path: /admin/metricas
  Headers: {
    Authorization: "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
    ↓
Backend valida:
  1. AuthMiddleware() → Extrae y valida JWT
  2. RequireRole("administrador") → Verifica rol
    ↓
✅ Token válido + Rol correcto:
  → Procesa request
  → Devuelve datos
    ↓
❌ Token inválido o Rol incorrecto:
  → Error 401 Unauthorized o 403 Forbidden
```

### 3. Flujo de Logout

```
PANEL ADMINISTRATIVO
    ↓
Usuario presiona botón ATRÁS
    ↓
AdministratorViewModel.onLogout()
  1. Inicia loading (isLoggingOut = true)
  2. Llama LogoutUseCase
    ↓
LogoutUseCase.invoke()
    ↓
AdminRemoteDataSource.logout()
    ↓
POST /v1/logout
Headers: {
  Authorization: "Bearer <token>"
}
    ↓
Backend procesa logout:
  1. Valida JWT (AuthMiddleware)
  2. Valida rol (RequireRole)
  3. Invalida sesión (opcional)
  4. Devuelve 200 OK
    ↓
App limpia datos locales:
  1. TokenManager.clearAllData()
  2. Borra token de SharedPreferences
  3. Borra datos del usuario
    ↓
NAVEGACIÓN AL LOGIN
(Incluso si POST /logout falla, app limpia datos)
```

---

## 📊 TAB 1: MÉTRICAS

### Pantalla

```
┌─────────────────────────────────────┐
│ Panel Administrativo      🔔         │
│ Doctor                               │
├─────────────────────────────────────┤
│ [Métricas] [Usuarios] [Áreas]       │
├─────────────────────────────────────┤
│                                     │
│  Total Pacientes: 150               │
│  Pacientes Críticos: 5              │
│  En Observación: 23                 │
│                                     │
│  Actividad Reciente:                │
│  - Paciente X ingresó hace 2h       │
│  - Paciente Y cambió estado hace 5h │
│  - Paciente Z asignado a Dr. López  │
│                                     │
└─────────────────────────────────────┘
```

### Flujo de Datos

```
MetricsScreen (UI)
    ↓
MetricsViewModel (State Management)
  - uiState: StateFlow<MetricsUiState>
    {
      metric: Metric? = null,
      isLoading: Boolean = false,
      error: String? = null
    }
    ↓
GetMetricsUseCase (Business Logic)
    ↓
MetricsRepository (Data Layer Interface)
    ↓
MetricsRepositoryImpl (Implementation)
    ↓
AdminRemoteDataSource (Network Calls)
    ↓
AdminApi (Retrofit Interface)
    ↓
GET /v1/admin/metricas
Headers: {
  Authorization: "Bearer <token>"
}
    ↓
Backend Response:
{
  "total_patients": 150,
  "critical_patients": 5,
  "observation_patients": 23
}
    ↓
AdminMapper.toMetric() (DTO → Entity)
    ↓
Metric Entity:
{
  id: 1L,
  totalPatients: 150,
  criticalPatients: 5,
  observationPatients: 23
}
    ↓
MetricsViewModel actualiza state
    ↓
MetricsScreen se redibuja con datos
```

### Requisitos del Backend

**Endpoint:** `GET /v1/admin/metricas`

**Validación:**
- AuthMiddleware() valida JWT
- RequireRole("administrador") verifica rol

**Response (200 OK):**
```json
{
  "total_patients": 150,
  "critical_patients": 5,
  "observation_patients": 23
}
```

**Errores:**
- `401 Unauthorized` - Token inválido o expirado
- `403 Forbidden` - Usuario no es administrador

---

## 👥 TAB 2: USUARIOS

### Pantalla

```
┌─────────────────────────────────────┐
│ Panel Administrativo      🔔         │
│ Doctor                               │
├─────────────────────────────────────┤
│ [Métricas] [Usuarios] [Áreas]       │
├─────────────────────────────────────┤
│                                     │
│ Gestión de Usuarios                 │
│                                     │
│ Doctores (2)                        │
│ ┌─────────────────────────────────┐ │
│ │ Dr. Juan Pérez                  │ │
│ │ LIC123456                       │ │
│ │ Especialidad: Cardiología       │ │
│ └─────────────────────────────────┘ │
│ ┌─────────────────────────────────┐ │
│ │ Dr. Carlos López                │ │
│ │ LIC345678                       │ │
│ │ Especialidad: Cirugía           │ │
│ └─────────────────────────────────┘ │
│                                     │
│ Enfermeras (3)                      │
│ ┌─────────────────────────────────┐ │
│ │ Enfermera María García          │ │
│ │ LIC789012                       │ │
│ └─────────────────────────────────┘ │
│                                     │
│ [Asignar Enfermera a Doctor]        │
│                                     │
└─────────────────────────────────────┘
```

### Flujo de Datos - Obtener Usuarios

```
UsersScreen (UI)
    ↓
UsersViewModel (State Management)
  - uiState: StateFlow<UsersUiState>
    {
      professionals: List<HealthProfessional> = [],
      isLoading: Boolean = false,
      error: String? = null
    }
    ↓
GetHealthProfessionalsUseCase
    ↓
UsersRepository (Interface)
    ↓
UsersRepositoryImpl (Implementation)
    ↓
AdminRemoteDataSource (Network)
    ↓
AdminApi (Retrofit)
    ↓
GET /v1/admin/usuarios
Headers: {
  Authorization: "Bearer <token>"
}
    ↓
Backend Response:
{
  "enfermeros": [
    {
      "id": 2,
      "name": "Enfermera María García",
      "email": "maria@medicgo.com",
      "license_number": "LIC789012",
      "rol": "enfermero",
      "especialidad": null
    }
  ],
  "doctores": [
    {
      "id": 3,
      "name": "Dr. Juan Pérez",
      "email": "juan@medicgo.com",
      "license_number": "LIC123456",
      "rol": "doctor",
      "especialidad": "Cardiología"
    }
  ]
}
    ↓
AdminMapper.toHealthProfessional() (DTO → Entity)
    ↓
HealthProfessional Entities:
{
  id: 2,
  name: "Enfermera María García",
  profession: "enfermero",
  licenseNumber: "LIC789012"
}
    ↓
UsersViewModel combina listas y actualiza state
    ↓
UsersScreen muestra doctores y enfermeras separados
```

### Flujo de Datos - Asignar Enfermera a Doctor

```
Usuario selecciona:
  - Doctor: "Dr. Juan Pérez"
  - Enfermera: "Enfermera María García"
    ↓
Presiona botón "Asignar"
    ↓
UsersViewModel.onAssignNurse()
    ↓
AssignNurseUseCase (nuevo)
    ↓
AdminRemoteDataSource.assignNurse()
    ↓
PATCH /v1/admin/usuarios/asignar-enfermero
Headers: {
  Authorization: "Bearer <token>"
}
Body: {
  "enfermero_id": 2,
  "doctor_id": 3
}
    ↓
Backend valida y actualiza relación
    ↓
Response: 200 OK (vacío o confirmación)
    ↓
UI muestra confirmación
    ↓
Vuelve a cargar lista de usuarios
```

### Requisitos del Backend

**Endpoint 1:** `GET /v1/admin/usuarios`

**Response (200 OK):**
```json
{
  "enfermeros": [
    {
      "id": 2,
      "name": "Enfermera María García",
      "email": "maria@medicgo.com",
      "license_number": "LIC789012",
      "rol": "enfermero",
      "especialidad": null
    }
  ],
  "doctores": [
    {
      "id": 3,
      "name": "Dr. Juan Pérez",
      "email": "juan@medicgo.com",
      "license_number": "LIC123456",
      "rol": "doctor",
      "especialidad": "Cardiología"
    }
  ]
}
```

**Endpoint 2:** `PATCH /v1/admin/usuarios/asignar-enfermero`

**Body:**
```json
{
  "enfermero_id": 2,
  "doctor_id": 3
}
```

**Response (200 OK):**
```
Empty body or:
{ "message": "Enfermera asignada exitosamente" }
```

---

## 🏥 TAB 3: ÁREAS

### Pantalla

```
┌─────────────────────────────────────┐
│ Panel Administrativo      🔔         │
│ Doctor                               │
├─────────────────────────────────────┤
│ [Métricas] [Usuarios] [Áreas]       │
├─────────────────────────────────────┤
│                                     │
│ Distribución por Área               │
│                                     │
│ Emergencia (25 pacientes)           │
│ [████████████░] 25/50              │
│                                     │
│ Cuidados Intensivos (15 pacientes)  │
│ [████░░░░░░░░] 15/50               │
│                                     │
│ Pediatría (40 pacientes)            │
│ [████████████████░] 40/50          │
│                                     │
│ [+ Agregar Nuevo Paciente]          │
│                                     │
└─────────────────────────────────────┘
```

### Modal de Agregar Paciente

```
┌────────────────────────────────────┐
│ Agregar Nuevo Paciente              │
├────────────────────────────────────┤
│                                    │
│ Nombre: [______________]           │
│ Apellido: [______________]         │
│ Edad: [____]                       │
│ Tipo de Sangre: [O+]               │
│ Síntomas: [__________________]     │
│ Estado Actual: [Estable]           │
│ Área: [Emergencia]                 │
│ Asignar a Doctor: [Dr. Juan]       │
│ Asignar a Enfermera: [María]       │
│                                    │
│         [Cancelar] [Guardar]       │
│                                    │
└────────────────────────────────────┘
```

### Flujo de Datos - Obtener Áreas

```
AreasScreen (UI)
    ↓
AreasViewModel (State Management)
  - uiState: StateFlow<AreasUiState>
    {
      areas: List<Area> = [],
      isLoading: Boolean = false,
      error: String? = null
    }
    ↓
GetAreasUseCase
    ↓
AreasRepository (Interface)
    ↓
AreasRepositoryImpl (Implementation)
    ↓
AdminRemoteDataSource (Network)
    ↓
AdminApi (Retrofit)
    ↓
GET /v1/admin/areas
Headers: {
  Authorization: "Bearer <token>"
}
    ↓
Backend Response:
[
  {
    "id": 1,
    "name": "Emergencia",
    "total_patients": 25
  },
  {
    "id": 2,
    "name": "Cuidados Intensivos",
    "total_patients": 15
  },
  {
    "id": 3,
    "name": "Pediatría",
    "total_patients": 40
  }
]
    ↓
AdminMapper.toArea() (DTO → Entity)
    ↓
Area Entities:
{
  id: 1,
  name: "Emergencia",
  patientCount: 25
}
    ↓
AreasViewModel actualiza state
    ↓
AreasScreen muestra áreas con barra de progreso
```

### Flujo de Datos - Agregar Paciente

```
Usuario presiona "+ Agregar Nuevo Paciente"
    ↓
Modal se abre
    ↓
Usuario llena formulario:
  - nombre: "Juan"
  - lastName: "García"
  - bloodType: "O+"
  - symptoms: "Fiebre, tos"
  - currentState: "Crítico"
  - age: 45
  - areaId: 1 (Emergencia)
  - assignedDoctor: 3 (Dr. Juan)
  - assignedNurse: 2 (Enfermera María)
    ↓
Presiona "Guardar"
    ↓
AreasViewModel.onAddPatient()
    ↓
AddPatientUseCase
    ↓
AdminRemoteDataSource.addPatient()
    ↓
POST /v1/admin/pacientes
Headers: {
  Authorization: "Bearer <token>"
}
Body: {
  "name": "Juan",
  "lastName": "García",
  "bloodType": "O+",
  "symptoms": "Fiebre, tos",
  "currentState": "Crítico",
  "age": 45,
  "areaId": 1,
  "assignedDoctor": 3,
  "assignedNurse": 2
}
    ↓
Backend valida y crea paciente
    ↓
Response: 200 OK
    ↓
Modal se cierra
    ↓
Lista de áreas se actualiza
    ↓
UI muestra confirmación
```

### Requisitos del Backend

**Endpoint 1:** `GET /v1/admin/areas`

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "name": "Emergencia",
    "total_patients": 25
  },
  {
    "id": 2,
    "name": "Cuidados Intensivos",
    "total_patients": 15
  },
  {
    "id": 3,
    "name": "Pediatría",
    "total_patients": 40
  }
]
```

**Endpoint 2:** `POST /v1/admin/pacientes`

**Body:**
```json
{
  "name": "Juan",
  "lastName": "García",
  "bloodType": "O+",
  "symptoms": "Fiebre, tos",
  "currentState": "Crítico",
  "age": 45,
  "areaId": 1,
  "assignedDoctor": 3,
  "assignedNurse": 2
}
```

**Response (200/201 OK):**
```
Empty body or confirmation
```

---

## 🗂️ ESTRUCTURA DE CARPETAS

```
features/admistrator/
├── data/
│   ├── datasource/
│   │   └── remote/
│   │       ├── AdminRemoteDataSource.kt
│   │       ├── api/
│   │       │   └── AdminApi.kt (Retrofit Interface)
│   │       ├── mapper/
│   │       │   └── AdminMapper.kt (DTO → Entity)
│   │       └── model/
│   │           └── AdminResponse.kt (DTOs)
│   ├── di/
│   │   ├── AdminNetworkModule.kt (DI)
│   │   └── RepositoryModule.kt
│   └── repositories/
│       ├── MetricsRepositoryImpl.kt
│       ├── UsersRepositoryImpl.kt
│       └── AreasRepositoryImpl.kt
├── domain/
│   ├── entities/
│   │   ├── Metric.kt
│   │   ├── HealthProfessional.kt
│   │   ├── Patient.kt
│   │   ├── Area.kt
│   │   └── RecentActivity.kt
│   ├── repositories/ (Interfaces)
│   │   ├── MetricsRepository.kt
│   │   ├── UsersRepository.kt
│   │   └── AreasRepository.kt
│   └── usescases/
│       ├── GetMetricsUseCase.kt
│       ├── GetHealthProfessionalsUseCase.kt
│       ├── GetAreasUseCase.kt
│       ├── AddPatientUseCase.kt
│       ├── AssignNurseUseCase.kt
│       └── LogoutUseCase.kt
└── presentation/
    ├── viewmodels/
    │   ├── AdministratorViewModel.kt (Main)
    │   ├── MetricsViewModel.kt
    │   ├── UsersViewModel.kt
    │   └── AreasViewModel.kt
    ├── screens/
    │   ├── administrator/
    │   │   └── AdministratorScreen.kt (Main Screen)
    │   ├── metrics/
    │   │   └── MetricsScreen.kt
    │   ├── users/
    │   │   └── UsersScreen.kt
    │   └── areas/
    │       └── AreasScreen.kt
    └── components/ (Shared UI)
```

---

## 🔌 ENDPOINTS REQUERIDOS

| Método | Ruta | Descripción | Validación |
|--------|------|-------------|-----------|
| GET | `/v1/admin/metricas` | Obtener métricas generales | JWT + Admin |
| GET | `/v1/admin/usuarios` | Obtener doctores y enfermeras | JWT + Admin |
| POST | `/v1/admin/usuarios` | Crear nuevo profesional | JWT + Admin |
| PATCH | `/v1/admin/usuarios/asignar-enfermero` | Asignar enfermera a doctor | JWT + Admin |
| GET | `/v1/admin/areas` | Obtener distribución de áreas | JWT + Admin |
| POST | `/v1/admin/pacientes` | Crear nuevo paciente | JWT + Admin |
| POST | `/v1/logout` | Cerrar sesión | JWT |

---

## 🔐 HEADERS REQUERIDOS EN TODAS LAS PETICIONES

```
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json
```

El JWT viene del login exitoso y se almacena localmente.
Se agrega automáticamente en TODAS las peticiones vía AuthInterceptor.

---

## ⚠️ VALIDACIÓN EN BACKEND

Para TODOS los endpoints del admin:

```go
// 1. AuthMiddleware() valida JWT
AuthMiddleware()

// 2. RequireRole("administrador") verifica rol
RequireRole("administrador")

// 3. Si pasa ambas validaciones → procesa el request
// 4. Si falla → responde con 401 Unauthorized o 403 Forbidden
```

---

## 📱 NAVEGACIÓN

```
Login Screen
    ↓ (Login exitoso)
Administrator Screen
    ├─ Tab: Métricas → MetricsScreen
    ├─ Tab: Usuarios → UsersScreen
    └─ Tab: Áreas → AreasScreen
    
Botón Atrás en Header
    ↓ (Presionar)
Logout
    ↓
Login Screen (Sesión cerrada)
```

---

## ✅ CHECKLIST PARA EL BACKEND

- [ ] Usuario admin creado: `ADMIN001 / admin123`
- [ ] POST `/v1/login` retorna JWT válido
- [ ] GET `/v1/admin/metricas` implementado
- [ ] GET `/v1/admin/usuarios` retorna `{ enfermeros: [], doctores: [] }`
- [ ] PATCH `/v1/admin/usuarios/asignar-enfermero` implementado
- [ ] GET `/v1/admin/areas` retorna array de áreas
- [ ] POST `/v1/admin/pacientes` implementado
- [ ] POST `/v1/logout` implementado
- [ ] AuthMiddleware valida JWT en todos los endpoints
- [ ] RequireRole("administrador") en todos los endpoints `/admin/*`
- [ ] Errores 401/403 manejados correctamente
- [ ] CORS habilitado si es necesario

---

## 📞 CONTACTO QUICK

**Si hay dudas sobre:**
- **Endpoints**: Ver tabla "ENDPOINTS REQUERIDOS"
- **Formatos**: Ver ejemplos de Request/Response
- **Autenticación**: Ver sección "AUTENTICACIÓN Y TOKENS"
- **Validación**: Ver sección "VALIDACIÓN EN BACKEND"


