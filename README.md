# Sample Task App – Android Intern Assignment (Josh Talks)

This repository contains the implementation of the **Android Intern Task – Humanness by Josh Talks**.  
The app is a **Kotlin Multiplatform (KMM) + Compose Multiplatform (CMP)** prototype that allows users to complete different sample recording tasks and view them in a local **Task History** screen. :contentReference[oaicite:0]{index=0}

---

## 🎯 Objective

The goal of this project is to build a prototype app where a user can:

1. Start with a **Sample Task**
2. Perform a **Noise Test**
3. Choose between:
   - Text Reading Task
   - Image Description Task
   - Photo Capture Task
4. Submit tasks and view all completed tasks in a **Task History** list.

---

## 📱 Features

### 1. Start Screen
- Heading: _“Let’s start with a Sample Task for practice.”_
- Sub-text: _“Pehele hum ek sample task karte hain.”_
- Button: **Start Sample Task**
- On click → Navigates to **Noise Test Screen**.

### 2. Noise Test Screen
- Displays a **decibel meter** (0–60 dB).
- Button: **Start Test**.
- Simulates/measures ambient noise:
  - If average dB `< 40` → **“Good to proceed”**
  - If `≥ 40` → **“Please move to a quieter place”**
- On pass → Navigates to **Task Selection Screen**.

### 3. Task Selection Screen
Lets the user choose one task:

1. **Text Reading Task**
2. **Image Description Task**
3. **Photo Capture Task**

Each option opens its respective screen.

---

### 4. Text Reading Task

- Fetches a sample text (product description) from:  
  `https://dummyjson.com/products`
- UI:
  - Shows text passage (description).
  - Instruction: _“Read the passage aloud in your native language.”_
  - **Mic Button (Press & Hold)**:
    - Start recording on press.
    - Stop recording on release.
    - Validate duration:
      - Reject if `< 10s` → “Recording too short (min 10 s).”
      - Reject if `> 20s` → “Recording too long (max 20 s).”
  - After valid recording:
    - Playback bar.
    - Checkboxes:
      - `No background noise`
      - `No mistakes while reading`
      - `Beech me koi galti nahi hai`
    - Buttons:
      - **Record again**
      - **Submit** (enabled only when all checkboxes are checked)

Project Structure
root/
 ├─ androidApp/          # Android application module
 │   ├─ ui/              # Compose screens
 │   ├─ navigation/      # Navigation graph / routes
 │   └─ ...
 ├─ shared/              # (If using KMM) shared module with business logic
 │   ├─ data/            # Repositories, local storage
 │   ├─ models/          # Task models, enums
 │   └─ ...
 └─ README.md

