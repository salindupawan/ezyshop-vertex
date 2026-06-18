# EZYSHOP Vertex — User Management Module

EZYSHOP Vertex is a modular, high-performance desktop backend application built using **JavaFX 21** and Java's strict Object Modular Architecture. This repository houses the administrative security, user tracking, and system access workflows, specifically optimized for deployment on standard desktop environments as well as heavy-retail **touchscreen POS terminals**.

---

## 🚀 Core Features

* **Heuristics Form Validation Engine:** Integrates a customized `ControlsFX ValidationSupport` layer across all text and choice parameters. It implements headless error decorators paired with an enterprise modal popup alert framework.
* **Dual-Input Optimization (Touch & Non-Touch):** Fully compliant with hybrid hardware requirements. Built using enlarged target touch footprints ($\ge 48\text{px}$ textfields/buttons) alongside explicit CSS padding expansions on dropdown popups and calendars to eliminate accidental taps.
* **Live Search & Predictive Filtering:** Uses synchronized JavaFX `FilteredList` and `SortedList` data structures for instant UI updates and column-header sorting without memory leaks or UI thread lag.
* **Rigorous Audit Trail Logging:** Automated background tracking of object metadata parameters (`createdBy`, `createdAt`, `modifiedBy`, `modifiedAt`) upon entry creation.

---

## 🛠️ Tech Stack & Architecture

* **Language Runtime:** Java JDK 21+
* **UI Toolkit:** JavaFX 21 (Modules used: `javafx.controls`, `javafx.fxml`, `javafx.base`, `javafx.graphics`)
* **Libraries:** * ControlsFX (UI Validation Lifecycle)
  * Lombok (Data Entity Builder Pattern)
* **Build Architecture:** Maven

---
