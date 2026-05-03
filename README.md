# ReCore

A lightweight Java game engine built on LWJGL 3

![Version](https://img.shields.io/badge/version-0.0.5--alpha-blue)
![Java](https://img.shields.io/badge/java-21-orange)
![License](https://img.shields.io/badge/license-MIT-green)

---

## Warning

> **Platform:** Windows, Mac is not supported in alpha (see [Issues](https://github.com/IsThisALis/ReCore/issues)).

---

## Features

- **Component logic** -- `init()`, `update()`, `cleanup()` via `ComponentLogic`
- **Lightweight** -- Minimal dependencies
- **Simple API** -- simple methods with no need to learn OpenGL

---

## Requirements

| Category | Requirement |
| :--- | :--- |
| **JDK** | 21 |
| **Build tool** | Maven 3.6+ |
| **GPU** | OpenGL 3.3+ compatible |
| **OS** | Linux |

---

## Examples

See `rexample` module for usage.

---

## FAQ

**Q: How do I use the engine?**  
A: Use built-in methods as a foundation, extend with your own logic.

**Q: Can i get help with modifications (forked and modificated versions)?**    
A: Author is not liable for conflicts but still can help you.

**Q: Where do I report bugs or get help?**  
A: Create a ticket in [Issues](https://github.com/IsThisALis/ReCore/issues).

**Q: Can I contribute?**  
A: Yes! This is a pet project, contributions are welcome.

**Q: Is there a roadmap?**  
A: No fixed roadmap. Development depends on tasks and contributors.

**Q: How to correctly open issue?**  
A: Because this is only alpha there is no template for issues, just provide enough (or not, just be ready to answer questions) info.

**Q: I saw "custom" in method comment, what it means?**    
A: This method is used to create custom logic, will be moved to module for advanced users.

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit changes (`git commit -m 'Add YourFeature'`)
4. Push (`git push origin feature/YourFeature`)
5. Open a Pull Request

---
