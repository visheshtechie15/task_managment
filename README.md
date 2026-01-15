-

# Task Management System – Spring Boot

##  Project Description

This is a **Spring Boot–based asynchronous task management system** that allows users to:

* Submit tasks for background execution
* Execute tasks using a fixed-size thread pool
* Track task status (QUEUED, RUNNING, DONE, STOPPED)
* Stop running or queued tasks safely
* Handle concurrency using Java’s built-in concurrency utilities

The project is designed to demonstrate **thread pool usage, concurrency control, synchronization, and safe task cancellation** in Java.

---

##  Technologies Used

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





## ▶️ How to Run the Project

### 1️⃣ Clone the Repository

```bash
git clone <repository-url>
cd assignment
```

---

### 2️⃣ Build the Project

#### Windows / macOS / Linux

```bash
mvn clean install
```

---

### 3️⃣ Run the Application

#### Option 1: Using Maven

```bash
mvn spring-boot:run
```

#### Option 2: Using Maven Wrapper

**macOS / Linux**

```bash
./mvnw spring-boot:run
```

**Windows**

```cmd
mvnw.cmd spring-boot:run
```

---

## 🌐 Application Details

* Server starts on: **[http://localhost:8080](http://localhost:8080)**
* No authentication required
* REST APIs available for:

   * Queue task
   * Check task status
   * Stop task

---

## ⚙️ Configuration

Edit `src/main/resources/application.properties` if needed:

```properties
# Thread pool size
worker.pool.size=3

# Application name
spring.application.name=assignment
```

* Increase `worker.pool.size` to allow more concurrent tasks
* Decrease it to limit resource usage

---

## 🧪 Optional: Test with cURL

```bash
curl -X POST http://localhost:8080/queueTask \
  -H "Content-Type: application/json" \
  -d '{"id":"task-1","task":"demo","time":5}'
```

---

## ✅ Summary

* Demonstrates **asynchronous task execution**
* Uses **fixed-size thread pool**
* Ensures **thread safety and proper synchronization**
* Supports **graceful task cancellation**
* Simple to run on **Windows and macOS**

---

If you want, I can also:

* Make this **resume-ready**
* Convert it to **GitHub-style README**
* Add a **diagram-only version**
* Simplify it further for **assignment submission**

Just tell me 👍
