# ReCore

A lightweight Java game engine built on LWJGL 3, designed for learning and experimentation.

![Version](https://img.shields.io/badge/version-0.0.2--alpha-blue)
![Java](https://img.shields.io/badge/java-21-orange)
![License](https://img.shields.io/badge/license-MIT-green)

---

## Warning

> **Alpha Stage:** API may change without notice.  
> **Platform:** Currently hardcoded for X11. Windows support is limited until fixed (see [Issues](https://github.com/IsThisALis/ReCore/issues)).

---

## Features

- **GLFW Window Management** — OpenGL context handling
- **Component Lifecycle** — `init()`, `update()`, `cleanup()` via `ComponentLogic`
- **Lightweight** — Minimal dependencies

---

## Requirements

| Category | Requirement |
| :--- | :--- |
| **JDK** | 21 or higher |
| **Build Tool** | Maven 3.6+ |
| **GPU** | OpenGL 3.2+ compatible |
| **OS** | Linux, Windows |

---

## Installation

### Maven
```xml
<dependency>
    <groupId>org.gfs.recore</groupId>
    <artifactId>recore</artifactId>
    <version>0.0.2-alpha</version>
</dependency>
```
## Buid & Run

### Clone repository
git clone https://github.com/IsThisALis/ReCore.git
cd ReCore

### Build
mvn package -P(windows/linux/OSX)

### Install to local Maven repository
mvn clean install

### Run example
java -jar target/recore-0.0.2-alpha.jar

## Examples

See `org.gfs.recore.example` package for usage patterns.

---

## FAQ

**Q: How do I use the engine?**  
A: Use built-in methods as a foundation, extend with your own logic.

**Q: Who is responsible for third-party libraries?**  
A: You manage dependencies. Author is not liable for conflicts, but still can help you.

**Q: Does it work on Windows?**  
A: Listed in requirements, but currently hardcoded for X11. Track fixes in [Issues](https://github.com/IsThisALis/ReCore/issues/1).

**Q: Where do I report bugs?**  
A: Create a ticket in [Issues](https://github.com/IsThisALis/ReCore/issues).

**Q: Can I contribute?**  
A: Yes! This is a pet project, contributions are welcome.

**Q: Is there a roadmap?**  
A: No fixed roadmap. Development depends on tasks and contributors.

**Q: How to correctly open issue?**    
A: Because this is only alpha there is no template for issues, just provide enough (or not, just be ready to answer questions) info.

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit changes (`git commit -m 'Add YourFeature'`)
4. Push (`git push origin feature/YourFeature`)
5. Open a Pull Request

---
