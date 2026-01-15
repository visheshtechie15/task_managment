
# Task Management System – Spring Boot

## Project Description

This is a **Spring Boot–based asynchronous task management system** that allows users to:

* Submit tasks for background execution
* Execute tasks using a fixed-size thread pool
* Track task status (`QUEUED`, `RUNNING`, `DONE`, `STOPPED`)
* Stop running or queued tasks safely
* Handle concurrency using Java’s built-in concurrency utilities

The project demonstrates **thread pool management, concurrency control, synchronization, and safe task cancellation** in Java.

---

## Technologies Used

* **Java 17**
* **Spring Boot**
* **Spring Web (REST APIs)**
* **Maven**
* **Java Concurrency Utilities**

  * `ThreadPoolExecutor`
  * `ConcurrentHashMap`
  * `CountDownLatch`
  * `synchronized`, `volatile`

---

## How to Run the Project

### 1. Clone the Repository

```bash
git clone https://github.com/visheshtechie15/task_managment.git
cd assignment
```

---

### 2. Build the Project

```bash
mvn clean install
```

---

### 3. Run the Application

#### Using Maven

```bash
mvn spring-boot:run
```



---

## Application Details

* Server runs on: `http://localhost:8080`


---

## API Endpoints

### 1. Queue a Task

**Endpoint**

```
POST /queueTask
```

**Request Body**

```json
{
  "id": "task-1",
  "task": "sample-task",
  "taskParams": {
    "key": "value"
  },
  "time": 5
}
```

**Fields**

* `id` (String): Unique task identifier
* `task` (String): Task name or type
* `taskParams` (Object): Optional task parameters
* `time` (Number): Task execution time in seconds

**Response**

```json
{
  "id": "task-1",
  "status": "RUNNING"
}
```

---

### 2. Check Task Status

**Endpoint**

```
POST /checkStatus
```

**Request Body**

```json
{
  "id": "task-1"
}
```

**Response**

```json
{
  "id": "task-1",
  "status": "RUNNING"
}
```

**Possible Status Values**

* `QUEUED`
* `RUNNING`
* `DONE`
* `STOPPED`

---

### 3. Stop a Task

**Endpoint**

```
POST /stopTask
```

**Request Body**

```json
{
  "id": "task-1"
}
```

**Response**

```json
{
  "id": "task-1",
  "status": "STOPPED"
}
```

**Behavior**

* Stops a task if it is queued or running
* Interrupts the worker thread if the task is already running

---

## Configuration

Edit `src/main/resources/application.properties` 

```properties
spring.application.name=assignment
worker.pool.size=3
```

* `worker.pool.size`: Number of concurrent worker threads

---


## Summary
This project uses **Spring Boot with Java concurrency utilities** to implement an asynchronous task management system where tasks are executed using a **fixed-size `ThreadPoolExecutor`** to control parallel execution and prevent resource exhaustion. Task state and execution metadata are maintained in a shared control object, with **`synchronized` blocks** used to ensure atomic state transitions and avoid race conditions when multiple threads access or modify task status. **`ConcurrentHashMap`** provides thread-safe storage for task tracking, while **`CountDownLatch`** is used to coordinate between threads by allowing request threads to wait until a task starts or terminates, ensuring proper synchronization and preventing premature responses.
